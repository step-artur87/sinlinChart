package tags.render;

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
public class Prop extends AbstractRenderTag implements Tag {
    private ArrayDeque<Fn> fnSet = new ArrayDeque<>();//fixme kostyl , todo scale

    @Override
    public void addChildTag(Tag tag) throws TagException {
        if (tryAddToVar(tag, Fn.class, fnSet)) return;

        throw new TagException("Can not add "
                + tag.getClass()
                + " to "
                + this.getClass() + ".");
    }
}
