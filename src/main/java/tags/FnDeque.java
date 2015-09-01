package tags;

import java.util.ArrayDeque;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/26/15
 * Time: 6:51 PM
 */
public class FnDeque extends ArrayDeque {
    //first element is root
    private FnDeque() {
    }

    private static FnDeque fnDeque = new FnDeque();

    public static FnDeque getInstance() {
        return fnDeque;
    }
}
