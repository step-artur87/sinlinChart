package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 9/13/15
 * Time: 2:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class UtMap {
    public static Map<String, ArrayList<Double>> getRows(
            Map<String, ArrayList<Double>> data) {
        Map<String, ArrayList<Double>> rows
                = new HashMap<>();

        int n = getMapArrayListsSize(data);
        if (n > 0) {
            for (int i = 0; i < n; i++) {

            }
        } else {
            //todo
        }
        return rows;
    }

    public static int getMapArrayListsSize(Map<String, ArrayList<Double>> map) {
        IntStream intStream = map.values().stream().mapToInt((a) -> a.size());
        OptionalInt max = intStream.max();
        OptionalInt min = intStream.max();
        if (!max.isPresent() && !min.isPresent()) return 0;
        if (max == min) return max.getAsInt();
        return -1;
    }

    //without checking
    public static ArrayList<ArrayList<Double>> transpose(
            ArrayList<ArrayList<Double>> data){
        ArrayList<ArrayList<Double>> result = new ArrayList<>();
        int w = data.size();
        int h = data.get(0).size();

        for (int i = 0; i < h; i++) {
            result.add(new ArrayList<>());
        }
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                //result.
            }     
        }
        return result;
    }
}
