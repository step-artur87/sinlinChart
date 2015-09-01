import org.jfree.chart.plot.*;
import org.jfree.chart.plot.dial.DialPlot;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/26/15
 * Time: 5:20 PM
 */
public class MyPlotFactory {

    public static final String CATEGORY_PLOT = "CategoryPlot";
    public static final String COMPASS_PLOT = "CompassPlot";
    public static final String DIAL_PLOT = "DialPlot";
    public static final String FAST_SCATTER_PLOT = "FastScatterPlot";
    public static final String METER_PLOT = "MeterPlot";
    public static final String MULTIPLE_PIE_PLOT = "MultiplePiePlot";
    public static final String PIE_PLOT = "PiePlot";
    public static final String POLAR_PLOT = "PolarPlot";
    public static final String SPIDER_WEB_PLOT = "SpiderWebPlot";
    public static final String THERMOMETER_PLOT = "ThermometerPlot";
    public static final String WAFER_MAP_PLOT = "WaferMapPlot";
    public static final String XYPLOT = "XYPlot";

    Plot create(String type) {
        Plot plot = null;
        switch (type) {
            case CATEGORY_PLOT:
                plot = new CategoryPlot();
                break;
            case COMPASS_PLOT:
                plot = new CompassPlot();
                break;
            case DIAL_PLOT:
                plot = new DialPlot();
                break;
            case FAST_SCATTER_PLOT:
                plot = new FastScatterPlot();
                break;
            case METER_PLOT:
                plot = new MeterPlot();
                break;
            case MULTIPLE_PIE_PLOT:
                plot = new MultiplePiePlot();
                break;
            case PIE_PLOT:
                plot = new PiePlot();
                break;
            case POLAR_PLOT:
                plot = new PolarPlot();
                break;
            case SPIDER_WEB_PLOT:
                plot = new SpiderWebPlot();
                break;
            case THERMOMETER_PLOT:
                plot = new ThermometerPlot();
                break;
            case WAFER_MAP_PLOT:
                plot = new WaferMapPlot();
                break;
            case XYPLOT:
                plot = new XYPlot();
                break;
        }
        return plot;
    }
}
