package tags.func;

import tags.AbstractTag;
import tags.Tag;
import tags.TagException;
import xml.DataSourceHandler;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 9/8/15
 * Time: 9:46 AM
 */
public class CAR extends AbstractTag {
    private ArrayDeque<Num> nums = new ArrayDeque<>();
    //todo choose type of collection for all
    private Set<String> ids = new HashSet<>();

    /**
     * Add specified tag to this tag if this tag can have
     * this class as child (this tag has not "id"
     * or its id unical)
     * and throws TagException otherwise.
     *
     * @param tag that becomes a child of this if can
     * @throws TagException
     */
    @Override
    public void addChildTag(Tag tag) throws TagException {
        String value;
        if (tag.getAttributes() != null) {
            value = tag.getAttributes().getValue(
                    DataSourceHandler.ID);
            if (!getIds().contains(value)) {
                if (tryAddToArray(tag, Num.class, getNums())){
                    getIds().add(value);
                    return;
                }
            } else {
                throw new TagException("Tag with id "
                        + value
                        + " already present");
            }
        } else if (tryAddToArray(tag, Num.class, getNums()))
            return;

        throw new TagException("Can not add "
                + tag.getClass()
                + " to "
                + this.getClass() + ".");
    }

    public ArrayDeque<Num> getNums() {
        return nums;
    }

    public Set<String> getIds() {
        return ids;
    }
}
