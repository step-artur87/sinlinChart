package tags.renderable;

import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import rendering.RenderInstruction;
import rendering.XYLineAndShapeRendererExt;
import tags.Tag;
import tags.TagException;
import tags.func.Fn;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.ArrayDeque;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/26/15
 * Time: 8:05 PM
 */
public class Chart extends AbstractRenderTag implements Tag {
    private ArrayDeque<Fn> fnSet = new ArrayDeque<>();//fixme kostyl
    private ArrayDeque<Element> elements = new ArrayDeque<>();
    private ArrayDeque<Prop> props = new ArrayDeque<>();
    private ArrayDeque<RenderInstruction> owned = new ArrayDeque<>();

    {
        /*add to this.rio all ArrayDeques,
        that can contain RenderInstructionOwners*/
        rio.add(elements);
        rio.add(props);
    }

    @Override
    public void addChildTag(Tag tag) throws TagException {
        if (tryAddToArray(tag, Element.class, elements))
            return;

        if (tryAddToArray(tag, Prop.class, props))
            return;

        if (tryAddToVar(tag, Fn.class, fnSet)) return;

        throw new TagException("Can not add "
                + tag.getClass()
                + " to "
                + this.getClass() + ".");
    }

    @Override
    public void appendRI(ArrayDeque<RenderInstruction> rial) {
        owned.clear();
        rio.forEach((a)
                -> a.forEach((t)
                -> ((AbstractRenderTag) t).appendRI(owned)));
        if (renderInstruction != null) {
            rial.add(this.renderInstruction);
        }
    }

    public Plot createPlot() {
        return new XYPlot(
                fnSet.peek().createXYDataset(),
                new NumberAxis("x"),
                new NumberAxis("y"),
                new XYLineAndShapeRendererExt(owned));
    }

    @Override
    public void writeXML(XMLStreamWriter xmlStreamWriter){
        try {
            xmlStreamWriter.writeStartElement(
                    this.getClass().getSimpleName());
            fnSet.forEach(c -> c.writeXML(xmlStreamWriter));
            elements.forEach(c -> c.writeXML(xmlStreamWriter));
            props.forEach(c -> c.writeXML(xmlStreamWriter));
            xmlStreamWriter.writeEndElement();
        } catch (XMLStreamException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
