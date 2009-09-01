package simple.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.io.filefilter.OrFileFilter;
import org.apache.commons.io.filefilter.PrefixFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.junit.Test;

public class ApacheIO {

	/**
	 * 用普通的Java IO来解析HTML <br>
	 */
	@Test
	public void testReadURL1() throws MalformedURLException, IOException{
		
		 InputStream in = new URL( "http://www.blogjava.net/rongxh7" ).openStream();
		 try {
		   InputStreamReader inR = new InputStreamReader( in );
		   BufferedReader buf = new BufferedReader( inR );
		   String line;
		   while ( ( line = buf.readLine() ) != null ) {
		     System.out.println( line );
		   }
		 } finally {
		   in.close();
		 }
		   
	}
	
	/**
	 * 用Apache IO来解析HTML <br>
	 * 学习要点: <br>
	 * IOUtils contains utility methods dealing with reading, writing and copying. <br> 
	 * The methods work on InputStream, OutputStream, Reader and Writer. 
	 */
	@Test
	public void testReadURL2() throws MalformedURLException, IOException {
		InputStream in = new URL("http://www.blogjava.net/rongxh7").openStream();
		try {
			System.out.println(IOUtils.toString(in));
		} finally {
			IOUtils.closeQuietly(in);
		}
	}
	
	/**
	 * 用Apache IO来读文件<br>
	 * 学习要点:<br>
	 * The FileUtils class contains utility methods for working with File objects.<br>
	 * These include reading, writing, copying and comparing files. 
	 */
	@Test
	public void testReadFile() throws IOException {
		File file = new File("README");
		List<String> lines = FileUtils.readLines(file, "GBK");
		for(String line : lines){
			System.out.println(line);
		}
	}
	
	/**
	 * 用Apache IO来操作文件名
	 * The FilenameUtils class contains utility methods <br>
	 * for working with filenames without using File objects. 
	 */
	@Test
	public void testFileName() {
		String filename = "C:/a/b/ccc.txt";
		String baseName = FilenameUtils.getBaseName(filename);	//ccc
		String extName = FilenameUtils.getExtension(filename);	//txt
		String fullPath = FilenameUtils.getFullPath(filename);	//C:/a/b
		String name = FilenameUtils.getName(filename);			//ccc.txt
		System.out.println(baseName);
		System.out.println(extName);
		System.out.println(fullPath);
		System.out.println(name);
		
	}
	
	/**
	 * 用Apache IO查询磁盘空间
	 * The FileSystemUtils class contains utility methods <br> 
	 * for working with the file system to access functionality not supported by the JDK. 
	 */
	@Test
	public void testFindDrive() throws IOException{
		long space = FileSystemUtils.freeSpaceKb("C:/");	//查C盘还剩下多少可用空间
		System.out.println("C盘可用空间为: " + space/1024 + " MB");
	}
	
	/**
	 * Line Iterator的用法 <br>
	 * The org.apache.commons.io.LineIterator class provides a flexible way <br>
	 * for working with a line-based file. An instance can be created directly, <br>
	 * or via factory methods on FileUtils or IOUtils. 
	 */
	@Test
	public void testLineIterater() throws IOException{
		File file = new File("README");
		LineIterator it = FileUtils.lineIterator(file, "GBK");
		try{
			while(it.hasNext()){
				String line = it.nextLine();
				System.out.println(line);
			}
		} finally {
			LineIterator.closeQuietly(it);
		}

	}
	
	/**
	 * 
	 * 各种常用的FileFiter
	 * 
	 * There are a number of 'primitive' filters:
     *
	 * DirectoryFilter Only accept directories 
	 * PrefixFileFilter Filter based on a prefix 
	 * SuffixFileFilter Filter based on a suffix 
	 * NameFileFilter Filter based on a filename 
	 * WildcardFileFilter Filter based on wildcards 
	 * AgeFileFilter Filter based on last modified time of file 
	 * SizeFileFilter Filter based on file size 
	 * 
	 * And there are five 'boolean' filters:
	 * 
	 * TrueFileFilter Accept all files 
	 * FalseFileFilter Accept no files 
	 * NotFileFilter Applies a logical NOT to an existing filter 
	 * AndFileFilter Combines two filters using a logical AND 
	 * OrFileFilter Combines two filter using a logical OR 
	 * 
	 */
	
	/**
	 * FileFilter的用法1
	 * 用 "new 子类" 的方式
	 * 
	 */
	@Test
	public void testFileFilter1(){
		  File dir = new File("data");	//若表示本目录,则用"."
		  //Returns an array of strings naming the files and directories in the directory denoted by this abstract pathname that satisfy the specified filter.
		  String[] files = dir.list(
			//Constructs a new file filter that ANDs the result of two other filters.
		    new AndFileFilter(
		    
		      new AndFileFilter(
		        new PrefixFileFilter("t"),	//Constructs a new Prefix file filter for a single prefix.
		        new OrFileFilter(	//Constructs a new file filter that ORs the result of two other filters.
		          new SuffixFileFilter(".txt"),	//Constructs a new Suffix file filter for a single extension.
		          new SuffixFileFilter(".dic")
		        )
		      ),
		      //Constructs a new file filter that NOTs the result of another filters.
		      new NotFileFilter(
		        DirectoryFileFilter.INSTANCE	//This filter accepts Files that are directories. 
		      )
		      
		    )
		  );
		  for ( int i=0; i<files.length; i++ ) {
		    System.out.println(files[i]);
		  }
	}
	
	/**
	 * FileFilter的用法2
	 * 用FileFilterUtils的方式
	 */
	@Test
	public void testFileFilter2(){
		File dir = new File("data");
		  String[] files = dir.list( 
		    FileFilterUtils.andFileFilter(
		      FileFilterUtils.andFileFilter(
		        FileFilterUtils.prefixFileFilter("t"),
		        FileFilterUtils.orFileFilter(
		          FileFilterUtils.suffixFileFilter(".txt"),
		          FileFilterUtils.suffixFileFilter(".dic")
		        )
		      ),
		      FileFilterUtils.notFileFilter(
		        FileFilterUtils.directoryFileFilter()
		      )
		    )
		  );
		  for ( int i=0; i<files.length; i++ ) {
		    System.out.println(files[i]);
		  }

	}
	
	
}
