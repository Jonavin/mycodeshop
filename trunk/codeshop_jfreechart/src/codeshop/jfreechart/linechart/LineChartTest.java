package codeshop.jfreechart.linechart;

import java.awt.BasicStroke;
import java.awt.geom.Ellipse2D;
import java.io.FileOutputStream;
import java.io.IOException;
import junit.framework.TestCase;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.DefaultCategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.LineRenderer3D;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChartTest extends TestCase {

	/**
	 * ����ͼ
	 * @throws IOException
	 */
	public void testLineChart() throws IOException {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(100, "Jan", "Spring Security");
		dataset.addValue(150, "Jan", "jBPM 4");
		dataset.addValue(300, "Jan", "Ext JS");
		dataset.addValue(100, "Jan", "JFreeChart");
		
		JFreeChart chart = ChartFactory.createLineChart(
		    "chart",                    // ����
		    "num",                      // ������
		    "type",                     // ������
		    dataset,                    // ����
		    PlotOrientation.VERTICAL,   // ��ֱͼ��
//		    PlotOrientation.HORIZONTAL, // ����ͼ��
		    true,                       // �Ƿ���ʾlegend
		    false,                      // �Ƿ���ʾtooltip
		    false                       // �Ƿ�ʹ��url����
		);
		
		FileOutputStream fos = null;
		try {
		    fos = new FileOutputStream("images/line1.png");
		    ChartUtilities.writeChartAsPNG(fos, chart, 400, 300);
		} finally {
		    fos.close();
		}
		
	}
	
	
	/**
	 * ��������ͼ������������ʽ
	 * @throws IOException
	 */
	public void testLineChartGroup() throws IOException {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(100, "Jan", "Spring Security");
		dataset.addValue(150, "Jan", "jBPM 4");
		dataset.addValue(300, "Jan", "Ext JS");
		dataset.addValue(100, "Jan", "JFreeChart");
		dataset.addValue(200, "Fer", "Spring Security");
		dataset.addValue(120, "Fer", "jBPM 4");
		dataset.addValue(240, "Fer", "Ext JS");
		dataset.addValue(40, "Fer", "JFreeChart");
		
		JFreeChart chart = ChartFactory.createLineChart(
		    "chart",                    // ����
		    "num",                      // ������
		    "type",                     // ������
		    dataset,                    // ����
		    PlotOrientation.VERTICAL,   // ��ֱͼ��
//		    PlotOrientation.HORIZONTAL, // ����ͼ��
		    true,                       // �Ƿ���ʾlegend
		    false,                      // �Ƿ���ʾtooltip
		    false                       // �Ƿ�ʹ��url����
		);
		
		CategoryPlot plot = (CategoryPlot)chart.getPlot();
//		LineRenderer3D renderer = new LineRenderer3D();
		LineAndShapeRenderer renderer = new LineAndShapeRenderer(); 
		
		//���ýڵ����ʽ
//		renderer.setShapesVisible(true);
		renderer.setBaseShapesVisible(true);		//Deprecation since 1.0.6����ͬ�������Base��
		renderer.setBaseShape(new Ellipse2D.Double(-2, -2, 4, 4));
		        
		//��ʾ��ֵ
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		        
		//��ʾ����
		renderer.setBaseStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
		        BasicStroke.JOIN_MITER, 10.0f, new float[] {10.0f}, 0.0f));		
		
		plot.setRenderer(renderer);
		
		FileOutputStream fos = null;
		try {
		    fos = new FileOutputStream("images/line2.png");
		    ChartUtilities.writeChartAsPNG(fos, chart, 400, 300);
		} finally {
		    fos.close();
		}
		
	}
	
	
	
	
}
