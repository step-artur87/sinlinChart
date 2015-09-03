package tags.renderable;

import org.xml.sax.Attributes;
import rendering.RenderInstruction;
import tags.AbstractTag;

import java.util.ArrayDeque;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/29/15
 * Time: 1:19 PM
 */
public abstract class AbstractRenderTag extends AbstractTag
        implements RenderInstructionOwner {
    protected RenderInstruction renderInstruction;
    protected ArrayDeque<ArrayDeque
            <? extends AbstractRenderTag>> rio
            = new ArrayDeque<>();

    @Override
    public void appendRI(
            ArrayDeque<RenderInstruction> rial) {
        setRenderInstruction();
        if (renderInstruction != null) {
            rial.add(this.renderInstruction);
        }
        rio.forEach((a)
                -> a.forEach((t)
                -> ((AbstractRenderTag) t).appendRI(rial)));
    }

    protected void setRenderInstruction(){
    }
}
