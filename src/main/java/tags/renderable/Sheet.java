package tags.renderable;

import org.jfree.chart.plot.Plot;
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
public class Sheet extends AbstractRenderTag implements Tag {
    private ArrayDeque<Fn> fnSet = new ArrayDeque<>();//fixme kostyl
    private ArrayDeque<Chart> charts = new ArrayDeque<>();

    {
        /*add to this.rio all ArrayDeques,
        that can contain RenderInstructionOwners*/
        rio.add(charts);
    }

    @Override
    public void addChildTag(Tag tag) throws TagException {
        if (tryAddToArray(tag, Chart.class, charts)) return;

        if (tryAddToVar(tag, Fn.class, fnSet)) return;

        throw new TagException("Can not add "
                + tag.getClass()
                + " to "
                + this.getClass() + ".");
    }

    public ArrayList<Plot> getPlots() {
        ArrayList<Plot> plotArrayList = new ArrayList<>();
        charts.forEach((c) -> {
            plotArrayList.add(c.createPlot());
        });
        return plotArrayList;
    }

    @Override
    public void writeXML(XMLStreamWriter xmlStreamWriter){
        try {
            xmlStreamWriter.writeStartElement(
                    this.getClass().getSimpleName());
            charts.forEach(c -> c.writeXML(xmlStreamWriter));
            fnSet.forEach(c -> c.writeXML(xmlStreamWriter));
            xmlStreamWriter.writeEndElement();
        } catch (XMLStreamException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}