package tags.renderable;

import org.jfree.chart.plot.Plot;
import tags.Tag;
import tags.TagException;
import tags.func.Fn;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/26/15
 * Time: 8:05 PM
 */
public class Sheet extends AbstractRenderTag implements Tag {//todo m.b props
    private ArrayDeque<Fn> fnSet = new ArrayDeque<>();//fixme kostyl
    private ArrayDeque<Chart> charts = new ArrayDeque<>();
    //todo initialise for other classes

    {
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
}