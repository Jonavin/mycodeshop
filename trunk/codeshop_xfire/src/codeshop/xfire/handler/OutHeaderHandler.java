package codeshop.xfire.handler;

import org.codehaus.xfire.MessageContext;
import org.codehaus.xfire.handler.AbstractHandler;
import org.codehaus.xfire.util.dom.DOMOutHandler;
import org.jdom.Document;
import org.jdom.Element;

public class OutHeaderHandler extends AbstractHandler {

	@Override
	public void invoke(MessageContext ctx) throws Exception {
		//为SOAP Header构造信息   
        Element soapInfo = new Element("SVCHeader"); 
        	Element head1 = new Element("Head");
	         	Element name1 = new Element("Username");
	         		name1.addContent("Svc_Code");
	         	Element value1 = new Element("Value");
	         		value1.addContent("rongxinhua");
	         	head1.addContent(name1);
	         	head1.addContent(value1);
	         Element head2 = new Element("Head");
	         	Element name2 = new Element("Name");
	         		name2.addContent("Svc_ReqTime");
	         	Element value2 = new Element("Value");
	         		value2.addContent("2010-09-06");
	         	head2.addContent(name2);
	         	head2.addContent(value2);
	      soapInfo.addContent(head1);
    	  soapInfo.addContent(head2);
    	  
		Element header = ctx.getOutMessage().getOrCreateHeader();
		header.addContent(soapInfo);
		Document inputDoc = (Document) ctx.getOutMessage().getProperty(
				DOMOutHandler.DOM_MESSAGE);
		//System.out.println(inputDoc.toString());
		
		System.out.println("out body : " + ctx.getOutMessage().getBody()); 
		
//		if (inputDoc != null) {
//			System.out
//					.println("------------XfireServiceOutHandler OUT SOAP XML-------------");
//			System.out.println(this.buildDocment(inputDoc).asXML());
//		}

		
	}
	
//	public org.dom4j.Document buildDocment(org.w3c.dom.Document domDocument) {
//		DOMReader xmlReader = new DOMReader();
//		return xmlReader.read(domDocument);
//	}

}
