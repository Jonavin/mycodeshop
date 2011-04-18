package codeshop.fileupload.servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 文件上传
 * @author rongxinhua 2010-9-4
 *
 */
public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		uploadFile(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		uploadFile(request, response);
	}	
	
	@SuppressWarnings("unchecked")
	private void uploadFile(HttpServletRequest request, HttpServletResponse response) {
		
		//硬盘临时目录设置
		String tempPath = "C:\\codeshop\\tempPath\\";
		File tempDirectory = new File(tempPath);
		if( ! tempDirectory.isDirectory()) {
			tempDirectory.mkdirs();
		}
		
		//服务器保存目录设置
		String contextPath = getServletContext().getRealPath("/");
		String uploadPath = contextPath + "servlet\\uploadPath\\";
		System.out.println(uploadPath); 	//Eclipse测试时很有用
		
		//(1) FileItemFactory
		//FileItemFactory fileItemFactory = new DiskFileItemFactory();
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		fileItemFactory.setRepository(new File(tempPath));	//临时目录
		fileItemFactory.setSizeThreshold(4096);		//置上传文件时用于临时存放文件的内存大小,这里是4K.多于的部分将临时存在硬盘
		
		//(2) FileUpload
		ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
		fileUpload.setSizeMax(2*1024*1024);		//全部的请求大小
		fileUpload.setFileSizeMax(2*1024*1024);	//单个文件大小限制
		
		List<FileItem> fileItemList = null;
		try {
			fileItemList = fileUpload.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(FileItem fileItem : fileItemList) {
			if( ! fileItem.isFormField()) {
				//可获取的文件信息：
				String fieldName = fileItem.getFieldName();			//表单域对应的name
				//文件名, 在IE下是全路径,如"N:\fontbox-1.1.0.jar"，在FireFox下只是文件名
				@SuppressWarnings("unused")
				String name = fileItem.getName();					
				String contentType = fileItem.getContentType();		//如：application/x-zip-compressed
				long size = fileItem.getSize();						//文件字节大小，如：153,220
				String string = fileItem.getString();				//同上
				//保存操作：
				try {
					//文件名获取处理
					String fileName = fileItem.getName();
					int slashIndex = fileName.lastIndexOf("\\");
					if(slashIndex >= 0) {
						fileName = fileName.substring(slashIndex + 1);
					}
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy\\MM\\dd");
					String dateStr = sdf.format(new Date());
					
					//服务器保存目录设置
					File directory = new File(uploadPath + dateStr + "\\");
					if( ! directory.isDirectory()) {
						directory.mkdirs();
					}
					
//					File file = new File(uploadPath + dateStr + "\\" + fileName);
					String filePath = directory.getPath() + "\\" + fileName;
					File file = new File(filePath);
					
					fileItem.write(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
