package util;

import rendering.RenderInstruction;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 9/3/15
 * Time: 12:17 AM
 */
public class UtColor {
    /**
     * Creaes a color from double value using specified legend.
     * @param legend define, how depends Color
     *               from double value.
     * @param d value, from which color created.
     * @return Color.
     */

    public static Color double2color (String legend,
                                      double d){
        switch (legend){//todo if overflow
            //todo "rgb", "hsb" from 3 Nums (m.b "rg"...)
            case RenderInstruction.R:
                return new Color((int) d*256*256);
            case RenderInstruction.G:
                return new Color((int) d*256);
            case RenderInstruction.B:
                return new Color((int) d);
            case RenderInstruction.HUE:
                return Color.getHSBColor((float)d, 0, 0);
            case RenderInstruction.SAT:
                return Color.getHSBColor(0, (float)d, 0);
            case RenderInstruction.BRI:
                return Color.getHSBColor(0, 0, (float)d);
            default:
                return Color.black;
        }
    }
}
