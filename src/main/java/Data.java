import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 9/2/15
 * Time: 9:41 PM
 */
public class Data {
    private Map<String, ArrayList<Double>> dataMap = new HashMap<>();
    private static Data data = new Data();

    public static Data getInstance(){
        return data;
    }

    private Data(){};

    public Map<String, ArrayList<Double>> getDataMap() {
        return dataMap;
    }
}
