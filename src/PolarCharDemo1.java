import java.awt.*;

import java.io.IOException;

import javax.imageio.ImageIO;
/*
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;

import org.jfree.chart.plot.PolarPlot;

import org.jfree.chart.renderer.DefaultPolarItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import org.jfree.ui.RefineryUtilities;


/**
 * 生成一个带有多组数据的条形图（最基础图形） 
 *
 */
/*
class PolarChartDemo1 extends ApplicationFrame {

    private static final long serialVersionUID = 1L;
    public PolarChartDemo1(String title) {
        super(title);
        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(300, 300));
        setContentPane(chartPanel);
    }

    private static XYDataset createDataset() {
        // 详细数据
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        XYSeries   xyseries1   =   new   XYSeries("two");
        xyseries1.add(90D,   11.199999999999999D);
        xyseries1.add(180D,   17.300000000000001D);
        xyseries1.add(0D,   12.300000000000001D);
        xySeriesCollection.addSeries(xyseries1);
        return xySeriesCollection;
    }

    private static JFreeChart createChart(XYDataset dataset) {

        // create the chart...  
        JFreeChart chart = ChartFactory.createPolarChart(
                null,       // chart title
                dataset,                  // 文字部分替换data
                true,                     // include legend
                false,                     // tooltips?
                false                     // URLs?
        );

        //总图片设置  
        //设置背景颜色的图表
        GradientPaint gp = new GradientPaint(0.0f, 0.0f, Color.blue,
                500.0f, 270.0f, new Color(0, 0, 64));
        gp = null;
        chart.setBackgroundPaint(gp);
        //设置图片背景图片  
        if(gp == null){
            Image image = null;
            try {
                image = ImageIO.read(new  java.io.File("C:\\Users\\12733\\Desktop\\blue.jpg"));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            chart.setBackgroundImage(image);
        }
        //设置图片的边框是否显示  
        chart.setBorderVisible(false);
        //设置背景图片透明度（0.0～1.0）  
        chart.setBackgroundImageAlpha(1.0f);
        //是否字体去锯齿  
        chart.setAntiAlias(true);


        //手动设置饼图样式
        PolarPlot plot = (PolarPlot) chart.getPlot();
        //设置图片的背景颜色
        //plot.setBackgroundPaint(Color.lightGray);
        //设置图片的透明度（0.0~1.0）
        plot.setBackgroundAlpha(0.1f);
        //设置无数据时的信息      
        plot.setNoDataMessage("无对应的数据，请重新查询。");
        //plot.setDomainGridlineStroke(new BasicStroke());
        //plot.setRangeGridlineStroke(new BasicStroke());
        //plot.setAngleGridlinePaint(Color.white);
        plot.setAngleGridlineStroke(new BasicStroke(3.f));//设置坐标轴粗细
        plot.setRadiusGridlineStroke(new BasicStroke(1.f));//设置环形粗细


        DefaultPolarItemRenderer renderer = new DefaultPolarItemRenderer();
        renderer.setSeriesStroke(0, new BasicStroke(5.0F));//坐标轴线条粗细
        plot.setRenderer(renderer);



        NumberAxis numberaxis = (NumberAxis) plot.getAxis();
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        numberaxis.setVisible(false);
        numberaxis.setTickUnit(new NumberTickUnit(5));// 设置雷达网格数量

        return chart;

    }


    public static void main(String[] args) {
        PolarChartDemo1 demo = new PolarChartDemo1("Bar Chart Demo 1");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}

*/