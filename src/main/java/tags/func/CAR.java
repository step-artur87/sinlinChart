package tags.func;

import tags.AbstractTag;
import tags.Tag;
import tags.TagException;

import java.util.ArrayDeque;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 9/8/15
 * Time: 9:46 AM
 */
public class CAR extends AbstractTag {
    private ArrayDeque<Num> nums = new ArrayDeque<>();

    @Override
    public void addChildTag(Tag tag) throws TagException {
        if (tryAddToArray(tag, Num.class, getNums()))
            return;

        throw new TagException("Can not add "
                + tag.getClass()
                + " to "
                + this.getClass() + ".");
    }

    public ArrayDeque<Num> getNums() {
        return nums;
    }
}
