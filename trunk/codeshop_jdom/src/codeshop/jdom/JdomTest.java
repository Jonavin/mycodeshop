package codeshop.jdom;

import java.io.IOException;
import java.io.StringReader;
import junit.framework.TestCase;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;
/**
 * JDOM测试
 * @author rongxinhua 2010-9-11
 *
 */
public class JdomTest extends TestCase {

	/**
	 * JDOM解析XML字符串
	 * @throws JDOMException
	 * @throws IOException
	 */
	public void testReadXmlString() throws JDOMException, IOException {
		String xmlString = "<root><name>Simple</name><password>Simple123</password></root>";
		SAXBuilder saxBuilder = new SAXBuilder();
		StringReader stringReader = new StringReader(xmlString);
		InputSource inputSource = new InputSource(stringReader);
		Document document = saxBuilder.build(inputSource);
		Element rootElement = document.getRootElement();
		String name = rootElement.getChildText("name");
		String password = rootElement.getChild("password").getText();
		System.out.println("name = " + name + ", password = " + password);	
	}
	
}
