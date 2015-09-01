package tags;

import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class DatasetFactory {

    public static XYDataset createXYDataset(
            Map<String, ArrayList<Double>> x,
            Map<String, ArrayList<Double>> y) {
        DefaultXYDataset xyDataset
                = new DefaultXYDataset();
        Set xKey = x.keySet();
        Set yKey = y.keySet();


        xyDataset.addSeries("xy", arraysTo2D(
                x.get(xKey.toArray()[0]),
                y.get(yKey.toArray()[0])));

        return xyDataset;
    }

    private static double[][] arraysTo2D(ArrayList<Double> x,
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