package tags.renderable;

import rendering.D1RI;
import rendering.RenderInstruction;
import tags.Data;
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
public class Prop extends AbstractRenderTag implements Tag {
    private ArrayDeque<Fn> fnSet = new ArrayDeque<>();//fixme kostyl , todo scale

    protected void setRenderInstruction(){
        ArrayList<Double> doubleArrayList;
        String value = getAttributes().getValue(
                Tag.TYPE);
        if ((value.equals(RenderInstruction.COLOR))) {
            doubleArrayList =
                    Data.getInstance().getDataMap().get(
                            value);
            renderInstruction = new D1RI(
                    value,
                    getAttributes().getValue(
                            RenderInstruction.LEGEND),
                    doubleArrayList);
        }
    }

    @Override
    public void addChildTag(Tag tag) throws TagException {
        if (tryAddToVar(tag, Fn.class, fnSet)) return;

        throw new TagException("Can not add "
                + tag.getClass()
                + " to "
                + this.getClass() + ".");
    }
}
