package tags;

import org.xml.sax.Attributes;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.ArrayDeque;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/27/15
 * Time: 11:18 AM
 */
public abstract class AbstractTag implements Tag {
    private Attributes attributes;

    @Override
    public Attributes getAttributes() {
        return attributes;
    }

    @Override
    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    protected boolean tryAddToVar(Tag tag,
                                  Class aClass,
                                  ArrayDeque to)
            throws TagException {
        if (tag.getClass().equals(aClass)) {
            if (to.isEmpty()) {
                to.add(tag);
            } else {
                throw new TagException(this.getClass()
                        + " already has a "
                        + tag.getClass()
                        + " object.");
            }
            return true;
        }
        return false;
    }

    protected boolean tryAddToArray(Tag tag,
                                    Class aClass,
                                    ArrayDeque to) {
        if (tag.getClass().equals(aClass)) {
            to.add(tag);
            return true;
        }
        return false;
    }
}
