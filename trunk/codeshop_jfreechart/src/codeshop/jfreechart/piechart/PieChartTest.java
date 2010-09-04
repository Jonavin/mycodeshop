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
	 * ��״ͼ
	 * @throws IOException
	 */
	public void testPieChart() throws IOException {
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Spring Security", 100);
		dataset.setValue("jBPM 4", 200);
		dataset.setValue("Ext JS", 300);
		dataset.setValue("JFreeChart", 100);
		
		JFreeChart chart = ChartFactory.createPieChart(			//��ά��״ͼ
//		JFreeChart chart = ChartFactory.createPieChart3D(		//��D��״ͼ
		    "chart",    	//����
		    dataset,    	//����
		    true,       	//�Ƿ�ʹ��legend
		    false,      	//�Ƿ�ʹ��tooltip
		    false       	//�Ƿ�ʹ��url����
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
	 * ը���ı�״ͼ���ֵ����
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
		
		JFreeChart chart = ChartFactory.createPieChart(			//��ά��״ͼ
//		JFreeChart chart = ChartFactory.createPieChart3D(		//��D��״ͼ
		    "chart",    	//����
		    dataset,    	//����
		    true,       	//�Ƿ�ʹ��legend
		    false,      	//�Ƿ�ʹ��tooltip
		    false       	//�Ƿ�ʹ��url����
		);
		
		PiePlot plot = (PiePlot) chart.getPlot();
		//����ը����һ��
		plot.setExplodePercent("JFreeChart", 0.3);
		//���ÿ�ֵ����ʾ
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
	 * ���ر�ͼ
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
		    TableOrder.BY_COLUMN, 			//���зָ��ͼ��addValue�еĵ���������
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
