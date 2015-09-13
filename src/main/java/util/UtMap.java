package util;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 9/13/15
 * Time: 2:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class UtMap {
    public static Map<String, ArrayList<Double>> getRows(
            Map<String, ArrayList<Double>> data){
        Map<String, ArrayList<Double>> rows
                = new HashMap<>();

    }

    public static int getMapArrayListsSize(Map<String, ArrayList<Double>>map){
        IntStream intStream = map.values().stream().mapToInt((a)-> a.size());
        OptionalInt max = intStream.max();
        OptionalInt min = intStream.max();
        if (!max.isPresent() && !min.isPresent()) return 0;
        if (max == min) return max.getAsInt();
        return -1;
    }
}
