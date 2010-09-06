package codeshop.xfire.client;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.TestCase;

import org.codehaus.xfire.XFire;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import codeshop.xfire.domain.User;
import codeshop.xfire.handler.OutHeaderHandler;
import codeshop.xfire.service.UserService;

public class UserServiceTest extends TestCase {
	

	public void testAddUser() throws MalformedURLException {
		String serviceURL = "http://localhost:8180/codeshop_xfire/service/UserService";
		//通过对象服务工厂创建服务模型
		Service serviceModel = new ObjectServiceFactory().create(UserService.class);
		//在Spring XML配置了，就不在用这里写了
//		serviceModel.addOutHandler(new OutHeaderHandler());
		//通过XFire工厂获取XFire实例
		XFire xFire = XFireFactory.newInstance().getXFire();
		//新建XFire代理工厂
		XFireProxyFactory proxyFactory = new XFireProxyFactory(xFire);
		//通过代理工厂创建Web服务实例
		UserService userService = (UserService)proxyFactory.create(serviceModel, serviceURL);
		
		User user = new User();
		user.setId(1L);
		user.setUsername("name_1");
		user.setPassword("pwd_1");
		int result = userService.addUser(user);
		System.out.println(result);
		
	}
	
	/**
	 * 通过WSDL文件生成客户端调用程序 
	 */
	public void testUpdateUser() throws IOException, Exception {	//FIXME
//		String wsdl = "UserService.xml";
//		Resource resource = new ClassPathResource(wsdl);
//		Client client = new Client(resource.getInputStream(), null);
		String wsdlURL = "http://localhost:8180/codeshop_xfire/service/UserService?wsdl";
		Client client = new Client(new URL(wsdlURL));
		Object[] paramArray = new Object[1];
		User user = new User();
		user.setId(1L);
		user.setUsername("name_1");
		user.setPassword("pwd_1");
		paramArray[0] = user;
		Object[] resultArray = client.invoke("updateUser", paramArray);
		System.out.println(resultArray[0]);
	}
	
	/**
	 * 根据服务地址创建客户端调用程序 
	 */
	public void deleteUser() {
	       ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config/xfire-client.xml"); 
	       UserService userService = (UserService)ctx.getBean("userServiceClient");
	       int result = userService.deleteUser(2L);
	       System.out.println(result);
	}
	
}
