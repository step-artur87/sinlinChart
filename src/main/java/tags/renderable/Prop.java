package tags.renderable;

import rendering.D1RI;
import rendering.RenderInstruction;
import tags.Data;
import tags.Tag;
import tags.TagException;
import tags.func.Fn;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/26/15
 * Time: 8:05 PM
 */
public class Prop extends AbstractRenderTag implements Tag {
    private ArrayDeque<Fn> fnSet = new ArrayDeque<>();//fixme kostyl

    protected void setRenderInstruction() {
        ArrayList<Double> doubleArrayList;
        String value = getAttributes().getValue(
                Tag.TYPE);
        if (!(getAttributes().getValue(
                Tag.TYPE) == null)) {
            if ((value.equals(RenderInstruction.COLOR))) {
                doubleArrayList =
                        Data.getInstance().getDataMap().get(
                                value);
                renderInstruction = new D1RI(
                        value,
                        getAttributes().getValue(
                                RenderInstruction.LEGEND),
                        doubleArrayList);
            }
        }
    }

    @Override
    public void addChildTag(Tag tag) throws TagException {
        if (tryAddToVar(tag, Fn.class, fnSet)) return;

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
            fnSet.forEach(c -> c.writeXML(xmlStreamWriter));
            xmlStreamWriter.writeEndElement();
        } catch (XMLStreamException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
