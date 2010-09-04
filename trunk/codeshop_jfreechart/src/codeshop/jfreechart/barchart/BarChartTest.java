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
	 * ��״ͼ
	 * @throws IOException
	 */
	public void testBarChart() throws IOException {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(100, "Spring Security", "Jan");
		dataset.addValue(200, "jBPM 4", "Jan");
		dataset.addValue(300, "Ext JS", "Jan");
		dataset.addValue(400, "JFreeChart", "Jan");
		
//		JFreeChart chart = ChartFactory.createBarChart(			//��ά��״ͼ
		JFreeChart chart = ChartFactory.createBarChart3D(		//��D��״ͼ
		    "chart",                    	//����
		    "num",                      	//��������
		    "type",                     	//��������
		    dataset,                    	//����
//		    PlotOrientation.HORIZONTAL, 	//ʹ�ú�����״ͼ
		    PlotOrientation.VERTICAL,   	//ʹ�ô�ֱ��״ͼ
		    true,                       	//�Ƿ�ʹ��legend
		    false,                      	//�Ƿ�ʹ��tooltip
		    false                       	//�Ƿ�ʹ��url����
		);
		
		FileOutputStream fos = null;
	    fos = new FileOutputStream("images/bar1.png");
	    ChartUtilities.writeChartAsPNG(fos, chart, 400, 300);
	    fos.close();
	
	}
	
	
	/**
	 * ������״ͼ
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
		
//		JFreeChart chart = ChartFactory.createBarChart(			//��ά��״ͼ
		JFreeChart chart = ChartFactory.createBarChart3D(		//��D��״ͼ
		    "chart",                    	//����
		    "num",                      	//��������
		    "type",                     	//��������
		    dataset,                    	//����
//		    PlotOrientation.HORIZONTAL, 	//ʹ�ú�����״ͼ
		    PlotOrientation.VERTICAL,   	//ʹ�ô�ֱ��״ͼ
		    true,                       	//�Ƿ�ʹ��legend
		    false,                      	//�Ƿ�ʹ��tooltip
		    false                       	//�Ƿ�ʹ��url����
		);
		
		FileOutputStream fos = null;
	    fos = new FileOutputStream("images/bar2.png");
	    ChartUtilities.writeChartAsPNG(fos, chart, 400, 300);
	    fos.close();
	
	}	
	
	
	
	
}
