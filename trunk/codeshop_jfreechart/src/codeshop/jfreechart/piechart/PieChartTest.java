package codeshop.jfreechart.piechart;

import java.io.FileOutputStream;
import java.io.IOException;
import junit.framework.TestCase;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.TableOrder;

public class PieChartTest extends TestCase {
	
	/**
	 * 饼状图
	 * @throws IOException
	 */
	public void testPieChart() throws IOException {
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Spring Security", 100);
		dataset.setValue("jBPM 4", 200);
		dataset.setValue("Ext JS", 300);
		dataset.setValue("JFreeChart", 100);
		
		JFreeChart chart = ChartFactory.createPieChart(			//二维饼状图
//		JFreeChart chart = ChartFactory.createPieChart3D(		//三D饼状图
		    "chart",    	//标题
		    dataset,    	//数据
		    true,       	//是否使用legend
		    false,      	//是否使用tooltip
		    false       	//是否使用url链接
		);
		
		FileOutputStream fos = null;
		try {
		    fos = new FileOutputStream("images/pie1.png");
		    ChartUtilities.writeChartAsPNG(fos, chart, 400, 300);
		} finally {
		    fos.close();
		}
		
	}
	
	/**
	 * 炸开的饼状图与空值处理
	 * @throws IOException
	 */
	public void testPieChartPlot() throws IOException {
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Spring Security", 100);
		dataset.setValue("jBPM 4", 200);
		dataset.setValue("Ext JS", 300);
		dataset.setValue("JFreeChart", 100);
		dataset.setValue("Null", null);
		dataset.setValue("Zero", 0);
		
		JFreeChart chart = ChartFactory.createPieChart(			//二维饼状图
//		JFreeChart chart = ChartFactory.createPieChart3D(		//三D饼状图
		    "chart",    	//标题
		    dataset,    	//数据
		    true,       	//是否使用legend
		    false,      	//是否使用tooltip
		    false       	//是否使用url链接
		);
		
		PiePlot plot = (PiePlot) chart.getPlot();
		//设置炸开哪一块
		plot.setExplodePercent("JFreeChart", 0.3);
		//设置空值不显示
		plot.setIgnoreZeroValues(true);
		plot.setIgnoreNullValues(true);
		
		FileOutputStream fos = null;
		try {
		    fos = new FileOutputStream("images/pie2.png");
		    ChartUtilities.writeChartAsPNG(fos, chart, 400, 300);
		} finally {
		    fos.close();
		}
		
	}
	
	/**
	 * 多重饼图
	 * @throws IOException
	 */
	public void testMultiplePieChart() throws IOException {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(100, "Spring Security", "Jan");
		dataset.addValue(200, "jBPM 4", "Jan");
		dataset.addValue(300, "Ext JS", "Jan");
		dataset.addValue(100, "JFreeChart", "Jan");
		dataset.addValue(60, "Spring Security", "Fer");
		dataset.addValue(100, "jBPM 4", "Fer");
		dataset.addValue(340, "Ext JS", "Fer");
		dataset.addValue(130, "JFreeChart", "Fer");
		
		JFreeChart chart = ChartFactory.createMultiplePieChart(
		    "chart",
		    dataset,
		    TableOrder.BY_COLUMN, 			//按列分割饼图，addValue中的第三个参数
		    true,
		    false,
		    false
		);
		
		FileOutputStream fos = null;
		try {
		    fos = new FileOutputStream("images/pie3.png");
		    ChartUtilities.writeChartAsPNG(fos, chart, 400, 300);
		} finally {
		    fos.close();
		}
		
		
	}
	

}
