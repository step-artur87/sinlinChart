package tags.func;

import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import tags.AbstractTag;
import tags.Tag;
import tags.TagException;
import util.UtArray;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/26/15
 * Time: 8:05 PM
 */
public class Fn extends AbstractTag implements Tag {
    public static final String DEFAULT_SERIES_NAME = "xy";
    private ArrayDeque<Const> constSet = new ArrayDeque<>();//fixme kostyl
    private ArrayDeque<Args> argsSet = new ArrayDeque<>();//fixme kostyl
    private ArrayDeque<Res> resSet = new ArrayDeque<>();//fixme kostyl

    /**
     * Creates XYDataset from all data of this
     *
     * @return XYDataset
     */
    public XYDataset createXYDataset() {
        //OR many with id OR one without, no that and that
        //todo other Datasets
        DefaultXYDataset xyDataset
                = new DefaultXYDataset();
        Set<String> xKey = getArgs().keySet();
        Set<String> yKey = getRes().keySet();
        Map<String, Num> xIds = argsSet.peek().getIds();
        Map<String, Num> yIds = resSet.peek().getIds();

        //no ids
        if (xIds.isEmpty() && yIds.isEmpty()) {
            yKey.forEach((k) -> {
                xyDataset.addSeries(k, UtArray.arraysTo2D(
                        getArgs().get(xKey.toArray()[0]),
                        getRes().get(k)));
            });
        } else
        //ids same in args and res
        {
            if (xIds.keySet().containsAll(yIds.keySet())
                    && yIds.keySet().containsAll(xIds.keySet())) {
                xIds.keySet().forEach((s)
                        -> xyDataset.addSeries(s, UtArray.arraysTo2D(
                        argsSet.peek().getIds().get(s).getValues(),
                        resSet.peek().getIds().get(s).getValues())));
            } else {
                try {
                    throw new TagException("args.ids!=res.ids");
                } catch (TagException e) {
                    e.printStackTrace();
                }
            }
        }
        return xyDataset;
    }

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

    private Map<String, ArrayList<Double>> getConst() {
        Map<String, ArrayList<Double>> result = new HashMap<>();
        constSet.forEach((a)
                -> a.getNums().forEach((n)
                -> {
            result.put(n.getText(), n.getValues());
        }));
        return result;
    }

    private Map<String, ArrayList<Double>> getArgs() {
        Map<String, ArrayList<Double>> result = new HashMap<>();
        argsSet.forEach((a)
                -> a.getNums().forEach((n)
                -> {
            result.put(n.getText(), n.getValues());
        }));
        return result;
    }

    private Map<String, ArrayList<Double>> getRes() {
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
