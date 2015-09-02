package tags.renderable;

import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import rendering.RenderInstruction;
import rendering.XYLineAndShapeRendererExt;
import tags.DatasetFactory;
import tags.Tag;
import tags.TagException;
import tags.func.Fn;

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
    private ArrayDeque<Prop> props = new ArrayDeque<>();//todo for other classes
    private ArrayDeque<RenderInstruction> owned = new ArrayDeque<>();

    {
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
        return new XYPlot(DatasetFactory.createXYDataset(
                fnSet.peek().getArgs(),
                fnSet.peek().getRes()),
                new NumberAxis("x"),
                new NumberAxis("y"),
                new XYLineAndShapeRendererExt(owned));
    }
}
