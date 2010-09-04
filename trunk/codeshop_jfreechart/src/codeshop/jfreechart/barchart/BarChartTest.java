package codeshop.jfreechart.barchart;

import java.io.FileOutputStream;
import java.io.IOException;
import junit.framework.TestCase;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChartTest extends TestCase {

	/**
	 * 柱状图
	 * @throws IOException
	 */
	public void testBarChart() throws IOException {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(100, "Spring Security", "Jan");
		dataset.addValue(200, "jBPM 4", "Jan");
		dataset.addValue(300, "Ext JS", "Jan");
		dataset.addValue(400, "JFreeChart", "Jan");
		
//		JFreeChart chart = ChartFactory.createBarChart(			//二维柱状图
		JFreeChart chart = ChartFactory.createBarChart3D(		//三D柱状图
		    "chart",                    	//标题
		    "num",                      	//横轴名称
		    "type",                     	//纵轴名称
		    dataset,                    	//数据
//		    PlotOrientation.HORIZONTAL, 	//使用横向柱状图
		    PlotOrientation.VERTICAL,   	//使用垂直柱状图
		    true,                       	//是否使用legend
		    false,                      	//是否使用tooltip
		    false                       	//是否使用url链接
		);
		
		FileOutputStream fos = null;
	    fos = new FileOutputStream("images/bar1.png");
	    ChartUtilities.writeChartAsPNG(fos, chart, 400, 300);
	    fos.close();
	
	}
	
	
	/**
	 * 分组柱状图
	 * @throws IOException
	 */
	public void testBarChartGroup() throws IOException {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(100, "Spring Security", "Jan");
		dataset.addValue(200, "jBPM 4", "Jan");
		dataset.addValue(300, "Ext JS", "Jan");
		dataset.addValue(100, "JFreeChart", "Jan");
		dataset.addValue(60, "Spring Security", "Fer");
		dataset.addValue(100, "jBPM 4", "Fer");
		dataset.addValue(340, "Ext JS", "Fer");
		dataset.addValue(130, "JFreeChart", "Fer");
		
//		JFreeChart chart = ChartFactory.createBarChart(			//二维柱状图
		JFreeChart chart = ChartFactory.createBarChart3D(		//三D柱状图
		    "chart",                    	//标题
		    "num",                      	//横轴名称
		    "type",                     	//纵轴名称
		    dataset,                    	//数据
//		    PlotOrientation.HORIZONTAL, 	//使用横向柱状图
		    PlotOrientation.VERTICAL,   	//使用垂直柱状图
		    true,                       	//是否使用legend
		    false,                      	//是否使用tooltip
		    false                       	//是否使用url链接
		);
		
		FileOutputStream fos = null;
	    fos = new FileOutputStream("images/bar2.png");
	    ChartUtilities.writeChartAsPNG(fos, chart, 400, 300);
	    fos.close();
	
	}	
	
	
	
	
}
