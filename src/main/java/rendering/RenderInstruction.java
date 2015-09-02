package rendering;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/29/15
 * Time: 6:42 PM
 */
public interface RenderInstruction {
    public static final String HEIGHT = "height";
    public static final String WIDTH = "width";
    public static final String COLOR = "color";
    public static final String LEGEND = "legend";
    public static final String R = "r";
    public static final String G = "g";
    public static final String B = "b";
    public static final String HUE = "hue";
    public static final String SAT = "sat";
    public static final String BRI = "bri";

    public String getName();
}
