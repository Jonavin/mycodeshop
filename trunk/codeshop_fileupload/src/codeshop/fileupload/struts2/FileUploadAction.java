package codeshop.fileupload.struts2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 文件上传Action
 * 
 * @author rongxinhua 2010-9-4
 * 
 */
public class FileUploadAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final int BUFFER_SIZE = 16 * 1024;

	private File inFile;
	private String contentType;
	private String fileName;
	
	

	public void setInFile(File inFile) {
		this.inFile = inFile;
	}

	//注意：这两个Setter方法的写法
	public void setInFileContentType(String contentType) {
		this.contentType = contentType;
	}
	public void setInFileFileName(String fileName) {
		this.fileName = fileName;
	}

	public String execute() throws Exception {
		System.out.println(ServletActionContext.getServletContext().getRealPath("/struts2/uploadPath"));
		String inFileName = new Date().getTime() + getExtention(fileName);
		File outFile = new File(ServletActionContext.getServletContext().getRealPath("/struts2/uploadPath") + "/" + inFileName);
		upload(inFile, outFile);
		return SUCCESS;
	}

	/**
	 * 获取文件扩展名（后缀）
	 * @param fileName
	 * @return
	 */
	private static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}

	/**
	 * 执行文件上传操作
	 * @param src
	 * @param dst
	 */
	public void upload(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
