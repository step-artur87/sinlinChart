package tags.render;

import org.xml.sax.Attributes;
import render.RenderInstruction;
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
    public void appendRI(ArrayDeque<RenderInstruction> rial) {
        if (renderInstruction != null) {
            rial.add(this.renderInstruction);
        }
        rio.forEach((a)
                -> a.forEach((t)
                -> ((AbstractRenderTag) t).appendRI(rial)));
    }

    @Override
    public void setAttributes(Attributes attributes) {
/*
        super.setAttributes(attributes);
                */
/*temporary*//*

        if ((attributes != null)
                && (attributes.getValue(TYPE) != null)
                && (new TestData()).getTestD2RIs().containsKey(
                attributes.getValue(TYPE))) {
            this.renderInstruction
                    = (new TestData()).getTestD2RIs()
                    .get(attributes.getValue(TYPE));
        }
*/
    }

}
