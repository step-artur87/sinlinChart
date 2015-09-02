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

    public String getName();
}