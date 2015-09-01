package tags.render;

import tags.Tag;
import tags.TagException;

import java.util.ArrayDeque;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/26/15
 * Time: 8:05 PM
 */
public class Element extends AbstractRenderTag implements Tag {
    private ArrayDeque<Chart> charts = new ArrayDeque<>();
    private ArrayDeque<Prop> props = new ArrayDeque<>();

    {
        rio.add(charts);
        rio.add(props);
    }

    @Override
    public void addChildTag(Tag tag) throws TagException {
        if (tryAddToArray(tag, Chart.class, charts))
            return;

        if (tryAddToArray(tag, Prop.class, props))
            return;

        throw new TagException("Can not add "
                + tag.getClass()
                + " to "
                + this.getClass() + ".");
    }
}
