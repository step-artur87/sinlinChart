package tags.renderable;

import rendering.RenderInstruction;

import java.util.ArrayDeque;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/29/15
 * Time: 6:43 PM
 */
public interface RenderInstructionOwner {
    public void appendRI(ArrayDeque<RenderInstruction> rial);
}
