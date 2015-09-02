package tags;

import render.D1RI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/28/15
 * Time: 11:59 AM
 */
public class TestData {
    public Map<String, ArrayList<Double>> testDataMap
            = new HashMap<>();

    {
        Double c[] = {1., 2., 3., 4., 5.};
        Double x[] = {0., 1., 2., 3., 4.};
        Double y[] = {1., 1., 2., 0., 3.};
        Double r1[] = {10., 20., 30., 40., 50.};
        Double r2[] = {50., 40., 30., 20., 10.};
        Double r3[] = {50., 100., 150., 200., 250.};
        addToMap("c", c);
        addToMap("a", x);
        addToMap("r", y);
        addToMap("r1", r1);
        addToMap("r2", r2);
        addToMap("r3", r3);

    }

    public Map<String, D1RI> getTestD2RIs() {
        Double[] widths =
                new Double[testDataMap.get("r1").size()];
        Double[] heights
                = new Double[testDataMap.get("r2").size()];
        Double[] colors
                = new Double[testDataMap.get("r2").size()];
        testDataMap.get("r1").toArray(widths);
        testDataMap.get("r2").toArray(heights);
        testDataMap.get("r3").toArray(colors);
        Map<String, D1RI> result = new HashMap<>();
        result.put("width",
                new D1RI("width", widths));
        result.put("height",
                new D1RI("height", heights));
        result.put("color",
                new D1RI("color", colors));

        return result;
    }

    private void addToMap(String name, Double[] data) {
        ArrayList<Double> doubleArrayList = new ArrayList<>();
        Arrays.stream(data).forEach(doubleArrayList::add);
        testDataMap.put(name, doubleArrayList);
    }
}
