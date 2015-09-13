import org.jfree.chart.plot.Plot;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import tags.TagDeque;
import tags.renderable.Sheet;
import xml.ChartSourceHandler;
import xml.DataSourceHandler;
import xml.SaxParsing;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/25/15
 * Time: 12:45 AM
 */
public class Main {
    public static void main(String[] args) {
        Graphics2D graphics;
        SaxParsing.parse(new DataSourceHandler(), "data.xml");
        SaxParsing.parse(new ChartSourceHandler(), "1.xml");

        /*create frame*/
        ApplicationFrame applicationFrame = new ApplicationFrame("tittle");
        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(1200, 800));
        applicationFrame.setContentPane(jPanel);

        TagDeque tagDeque = TagDeque.getInstance();
        Sheet sheet = (Sheet) tagDeque.getRootTag();

        //Output.printXML(sheet);

         /*show chart*/
        applicationFrame.pack();
        RefineryUtilities.centerFrameOnScreen(applicationFrame);
        applicationFrame.setVisible(true);

        graphics = (Graphics2D) applicationFrame.getGraphics();
        Map<String, ArrayList<Plot>> plotMap = sheet.getPlots();

        int w = 0;
        for (String string : plotMap.keySet()) {
            for (int i = 0; i < plotMap.get(string).size(); i++) {
                plotMap.get(string).get(i).setBackgroundAlpha(0);
                plotMap.get(string).get(i).draw(graphics, new Rectangle2D.Double(
                        i * 100,
                        w * 100,
                        100,
                        100),
                        null, null, null);
            }
            w++;
        }


/*
        JFreeChart jFreeChart = new JFreeChart(plot);
        jFreeChart.draw(graphics,
                new Rectangle2D.Double(100, 100, 600, 600));
        ExportUtils.writeAsSVG(jFreeChart, 1000, 1000, new File("export.svg"));
*/
    }
}