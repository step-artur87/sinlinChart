package tags.func;

import tags.AbstractTag;
import tags.Tag;
import tags.TagException;
import xml.DataSourceHandler;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 9/8/15
 * Time: 9:46 AM
 */
public class CAR extends AbstractTag {
    private ArrayDeque<Num> nums = new ArrayDeque<>();
    //todo choose type of collection for all
    private Map<String, Num> ids = new HashMap<>();

    /**
     * Add specified tag to this tag if this tag can have
     * this class as child (this tag has not "id"
     * or its id unical)
     * and throws TagException otherwise.
     *
     * @param tag that becomes a child of this if can
     * @throws TagException
     */
    @Override
    public void addChildTag(Tag tag) throws TagException {
        String value;
        if ((tag.getAttributes() != null)
                && (tag.getAttributes().getValue(
                DataSourceHandler.ID) != null)) {
            value = tag.getAttributes().getValue(
                    DataSourceHandler.ID);
            if (!getIds().containsKey(value)) {
                if (tryAddToArray(tag, Num.class, getNums())) {
                    getIds().put(value, ((Num) tag));
                    return;
                }
            } else {
                throw new TagException("Tag with id "
                        + value
                        + " already present");
            }
        } else if (tryAddToArray(tag, Num.class, getNums()))
            return;

        throw new TagException("Can not add "
                + tag.getClass()
                + " to "
                + this.getClass() + ".");
    }

    public ArrayDeque<Num> getNums() {
        return nums;
    }

    public Map<String, Num> getIds() {
        return ids;
    }

    @Override
    public void writeXML(XMLStreamWriter xmlStreamWriter) {
        try {
            xmlStreamWriter.writeStartElement(
                    this.getClass().getSimpleName());
            nums.forEach(c -> c.writeXML(xmlStreamWriter));
            xmlStreamWriter.writeEndElement();
        } catch (XMLStreamException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
