package render;

import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

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

    private void sortOwned() {
        owned.forEach((ri) -> {
            rimap.put(ri.getName(), ri);
        });
    }

    @Override
    public Shape getItemShape(int row, int column) {//bottleneck
        D1RI<Double> widthRI;
        D1RI<Double> heightRI;
        double width = super.getItemShape(row, column)
                .getBounds().width;
        double height = super.getItemShape(row, column)
                .getBounds().height;

        if (rimap.containsKey(/*"points_width"*/RenderInstruction.WIDTH)) {
            widthRI = (D1RI<Double>) rimap.get(/*"points_width"*/ RenderInstruction.WIDTH);
            width = widthRI.getData()[column];
        }
        if (rimap.containsKey(/*"points_height"*/ RenderInstruction.HEIGHT)) {
            heightRI = (D1RI<Double>) rimap.get(/*"points_height"*/RenderInstruction.HEIGHT);
            height = heightRI.getData()[column];
        }
        return new Ellipse2D.Double(-width / 2, -height / 2, width, height);
    }
}
