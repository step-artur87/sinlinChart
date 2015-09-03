package tags.func;

import tags.AbstractTag;
import tags.Tag;
import tags.TagException;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/26/15
 * Time: 8:05 PM
 */
public class Fn extends AbstractTag implements Tag {
    private ArrayDeque<Const> constSet = new ArrayDeque<>();//fixme kostyl
    private ArrayDeque<Args> argsSet = new ArrayDeque<>();//fixme kostyl
    private ArrayDeque<Res> resSet = new ArrayDeque<>();//fixme kostyl

    private Tag parentTag = null;

    @Override
    public void addChildTag(Tag tag) throws TagException {
        if (tryAddToVar(tag, Const.class, constSet)) return;
        if (tryAddToVar(tag, Args.class, argsSet)) return;
        if (tryAddToVar(tag, Res.class, resSet)) return;

        throw new TagException("Can not add "
                + tag.getClass()
                + " to "
                + this.getClass() + ".");
    }

    public Tag getParentTag() {
        return parentTag;
    }

    public void setParentTag(Tag parentTag) {
        this.parentTag = parentTag;
    }

    public Map<String, ArrayList<Double>> getArgs() {
        Map<String, ArrayList<Double>> result = new HashMap<>();
        argsSet.forEach((a)
                -> a.getNums().forEach((n)
                -> {
            result.put(n.getText(), n.getValues());
        }));
        return result;
    }

    public Map<String, ArrayList<Double>> getRes() {
        Map<String, ArrayList<Double>> result = new HashMap<>();
        resSet.forEach((a) -> a.getNums().forEach((n) -> {
            result.put(n.getText(), n.getValues());
        }));
        return result;
    }

    private static ArrayList<Double> filter(ArrayList<Double> places,
                                            ArrayList<Double> values,
                                            double value) {
        ArrayList<Double> result = new ArrayList<>();
        for (int i = 0; i < places.size(); i++) {
            if (places.get(i) == value) {
                result.add(values.get(i));
            }
        }
        return result;
    }
}
