package tags.renderable;

import org.jfree.chart.plot.Plot;
import tags.Tag;
import tags.TagException;
import tags.func.Fn;
import util.UtMap;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public Map<String, ArrayList<Plot>> getPlots() {
        Map<String, ArrayList<Plot>> plotMap = new HashMap<>();
        ArrayList<Plot> plotArrayList = new ArrayList<>();
        ArrayList<String> rowKeys;
        Map<String, Double> row;

        if (fnSet.isEmpty()) {
            plotArrayList.clear();
            for (Chart chart : charts) {
                //todo delete arrayLists, when it can
                plotArrayList.add(chart.createPlot());
            }

            plotMap.put("default", plotArrayList);
        } else {
            for (int i = 0;
                 i < fnSet.getFirst().getRowCount();
                 i++) {
                plotArrayList.clear();
                for (Chart chart : charts) {
                    plotArrayList.add(chart.createPlot(
                            fnSet.getFirst().getArgsAndResRows()
                                    .get(i)));
                }
                plotMap.put(UtMap.getString(fnSet.getFirst().getArgsAndResRows()
                        .get(i)), plotArrayList);
            }
        }
        return plotMap;//todo set legend
    }

    @Override
    public void writeXML(XMLStreamWriter xmlStreamWriter) {
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