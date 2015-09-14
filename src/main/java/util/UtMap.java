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
    public static ArrayList<Map<String, Double>> getRowsAL(
            Map<String, ArrayList<Double>> data) {
        ArrayList<Map<String, Double>> rows
                = new ArrayList<>();
        ArrayList<ArrayList<Double>> matrix = new ArrayList<>();
        ArrayList<ArrayList<Double>> transposed;
        Map<String, Double> tmpMap;
        ArrayList<String> keys = getKeys(data);
        int n = getMapArrayListsSize(data);
        if (n > 0) {
            data.forEach((s, a) -> {
                matrix.add(a);
            });
            transposed = transpose(matrix);
            for (int i = 0; i < transposed.size(); i++) {
                tmpMap = new HashMap<>();
                for (int j = 0; j < keys.size(); j++) {
                    tmpMap.put(keys.get(j), transposed.get(i).get(j));
                }
                rows.add(tmpMap);
            }
        } else {
            //todo
        }
        return rows;
    }

    public static int getMapArrayListsSize(
            Map<String, ArrayList<Double>> map) {
        IntStream intStream = map.values().stream().mapToInt((a) -> a.size());
        OptionalInt max = intStream.max();
        //fixme
        intStream = map.values().stream().mapToInt((a) -> a.size());
        OptionalInt min = intStream.min();
        if (!max.isPresent() && !min.isPresent()) return 0;
        if (max.equals(min)) return max.getAsInt();
        return -1;
    }

    //without checking
    public static ArrayList<ArrayList<Double>> transpose(
            ArrayList<ArrayList<Double>> data) {
        ArrayList<ArrayList<Double>> result = new ArrayList<>();
        int w = data.size();
        int h = data.get(0).size();

        for (int i = 0; i < h; i++) {
            result.add(new ArrayList<>());
            for (int j = 0; j < w; j++) {
                result.get(i).add(data.get(j).get(i));
            }
        }
        return result;
    }

    public static ArrayList<String> getKeys(
            Map<String, ?> map) {
        ArrayList<String> keys = new ArrayList<>();

        map.forEach((s, a) -> {
            keys.add(s);
        });
        return keys;
    }

    public static String getString(
            Map<String, Double> row) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : row.keySet()) {
            stringBuilder.append(string)
                    .append(" = ")
                    .append(row.get(string))
                    .append(";");
        }
        return stringBuilder.toString();
    }
}
