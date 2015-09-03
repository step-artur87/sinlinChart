package util;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 9/3/15
 * Time: 1:57 PM
 */
public class UtArray {
    public static double[][] arraysTo2D(
            ArrayList<Double> x,
            ArrayList<Double> y) {
        double[][] result;
        if (x.size() == y.size()) {
            result = new double[2][y.size()];
            for (int i = 0; i < y.size(); i++) {
                result[0][i] = x.get(i);
                result[1][i] = y.get(i);
            }
            return result;
        }
        return null;
    }
}
