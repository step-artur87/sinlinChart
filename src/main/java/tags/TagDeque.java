package tags;

import java.util.ArrayDeque;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/26/15
 * Time: 6:51 PM
 */
public class TagDeque extends ArrayDeque {
    //first element is rootTag
    private TagDeque() {
    }

    private static TagDeque tagDeque = new TagDeque();
    private Tag rootTag;

    public static TagDeque getInstance() {
        return tagDeque;
    }

    public Tag getRootTag() {
        return rootTag;
    }

    public void setRootTag(Tag rootTag) {
        this.rootTag = rootTag;
    }
}
