package simple.file;

import java.util.Properties;
import org.junit.Test;

/**
 * 属性文件操作类
 * @author Simple Rong
 *
 */
public class PropFileHandle {

	/**
	 * 获取classpath下的XML属性文件和Properties属性文件
	 * @throws Exception
	 */
	@Test
	public void testGetProps1() throws Exception {
		Properties props = new Properties();
//		props.loadFromXML(PropFileHandle.class.getResourceAsStream("/test.xml")); 	//由本类来加载classpath下的XML属性文件
		props.load(PropFileHandle.class.getClassLoader().getResourceAsStream("test.properties"));	//由本类来加载classpath下的.properties属性文件
		String username = props.getProperty("username");	//找不到则返回null
		String password = props.getProperty("password");	//找不到则返回null
//		String username = props.getProperty("username","sa");	//找不到则返回默认值sa
//		String password = props.getProperty("password","sa");	//找不到则返回默认值sa
//		Assert.assertEquals(username, "simple");
//		Assert.assertEquals(password, "rong");
		System.out.println(username);
		System.out.println(password);
	}
	
	
	
	
}
