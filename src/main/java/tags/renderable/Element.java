package tags.renderable;

import tags.Tag;
import tags.TagException;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.ArrayDeque;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/26/15
 * Time: 8:05 PM
 */
public class Element extends AbstractRenderTag implements Tag {
    private ArrayDeque<Chart> charts = new ArrayDeque<>();
    private ArrayDeque<Prop> props = new ArrayDeque<>();

    {
        /*add to this.rio all ArrayDeques,
        that can contain RenderInstructionOwners*/
        rio.add(charts);
        rio.add(props);
    }

    @Override
    public void addChildTag(Tag tag) throws TagException {
        if (tryAddToArray(tag, Chart.class, charts))
            return;

        if (tryAddToArray(tag, Prop.class, props))
            return;

        throw new TagException("Can not add "
                + tag.getClass()
                + " to "
                + this.getClass() + ".");
    }

    @Override
    public void writeXML(XMLStreamWriter xmlStreamWriter) {
        try {
            xmlStreamWriter.writeStartElement(
                    this.getClass().getSimpleName());
            charts.forEach(c -> c.writeXML(xmlStreamWriter));
            props.forEach(c -> c.writeXML(xmlStreamWriter));
            xmlStreamWriter.writeEndElement();
        } catch (XMLStreamException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
