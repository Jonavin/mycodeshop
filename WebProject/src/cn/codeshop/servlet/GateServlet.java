package cn.codeshop.servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		
		System.out.println("username = " + username + ", password = " + password);
		
		response.setContentType("text/plain;charset=UTF-8");
		Writer out = response.getWriter();
		String result = null;
		
		if("login".equals(action)) {
			if("0".equals(type)) {
				result = "{\"result\":0,\"errno\":\"E001\",\"errmsg\":\"username error.\",\"ext\":\"\",\"data\":{\"field3\":101,\"field2\":\"value2\",\"field1\":\"value1\"}}";
			} else if("1".equals(type)) {
				result = "{\"result\":0,\"errno\":\"E001\",\"errmsg\":\"username error.\",\"ext\":\"\",\"data\":[{\"field3\":101,\"field2\":\"value2\",\"field1\":\"value1\"},{\"field3\":1011,\"field2\":\"value22\",\"field1\":\"value11\"}]}";
			} else if("2".equals(type)) {
				result = "{\"result\":0,\"errno\":\"E001\",\"errmsg\":\"username error.\",\"ext\":\"\",\"data\":true}";
			} else if("3".equals(type)) {
				result = "{\"result\":0,\"errno\":\"E001\",\"errmsg\":\"username error.\",\"ext\":\"\",\"data\":56}";
			} 
		}
		out.flush();
	}

}
