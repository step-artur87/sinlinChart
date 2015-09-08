package rendering;

import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import util.UtColor;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/28/15
 * Time: 9:41 PM
 */
public class XYLineAndShapeRendererExt
        extends XYLineAndShapeRenderer {
    private ArrayDeque<RenderInstruction> owned;
    private Map<String, RenderInstruction> rimap
            = new HashMap<>();

    public XYLineAndShapeRendererExt(
            ArrayDeque<RenderInstruction> owned) {
        this.owned = owned;
        sortOwned();
    }

    @Override
    public Shape getItemShape(int row, int column) {
        D1RI widthRI;
        D1RI heightRI;
        double width = super.getItemShape(row, column)
                .getBounds().width;
        double height = super.getItemShape(row, column)
                .getBounds().height;

        if (rimap.containsKey(
                RenderInstruction.WIDTH)) {
            widthRI = (D1RI) rimap.get(
                    RenderInstruction.WIDTH);
            width = widthRI.getData().get(column);
        }
        if (rimap.containsKey(
                RenderInstruction.HEIGHT)) {
            heightRI = (D1RI) rimap.get(
                    RenderInstruction.HEIGHT);
            height = heightRI.getData().get(column);
        }
        return new Ellipse2D.Double(-width / 2,
                -height / 2,
                width,
                height);
    }

    @Override
    public Paint getItemFillPaint(int row, int column) {
        if (rimap.containsKey(
                RenderInstruction.COLOR)) {
            return getPaint(row, column);
        }
        return super.getItemFillPaint(row, column);
    }

    @Override
    public Paint getItemPaint(int row, int column) {
        if (rimap.containsKey(
                RenderInstruction.COLOR)) {
            return getPaint(row, column);
        }
        return super.getItemPaint(row, column);
    }

    private void sortOwned() {
        owned.forEach((ri) -> {
            rimap.put(ri.getName(), ri);
        });
    }

    /**
     * Returns a Color of these row ans column,
     * defined by RenderInstruction with name
     * RenderInstruction.COLOR.
     * Used for the same behavior of getItemFillPaint
     * and getItemPaint
     * @param row  the row (or series) index.
     * @param column  the column (or category) index.
     * @return Color
     */
    private Paint getPaint(int row, int column) {
        D1RI paintRI;
        if (rimap.containsKey(
                RenderInstruction.COLOR)) {
            paintRI = (D1RI) rimap.get(
                    RenderInstruction.COLOR);
            return UtColor.double2color(
                    paintRI.getLegend(),
                    paintRI.getData().get(column).intValue());
        }
        return Color.black;//unreachable without bugs
    }
}
