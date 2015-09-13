package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        StringBuffer stringBuffer;
        int n = data.get(
                data.keySet().iterator().next()).size();
        for (int i = 0; i < n; i++) {
            stringBuffer = new StringBuffer();
            data.forEach((s, a)->{
                stringBuffer.append(s + )
            });
            rows.put()
        }

    }
}
