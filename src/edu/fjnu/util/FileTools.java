package edu.fjnu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import edu.fjnu.domain.Grade;
import edu.fjnu.service.GradesPdfService;

public class FileTools 
{
		
	/**
	 * 创建文件夹
	 * @param path
	 * 			文件夹路径
	 * @return
	 * 			flag
	 */
	public static boolean createDir( String path)
	{
		path = path.replace("//", "/");
		
		if( !path.endsWith("/"))
		{
			path = path + "/";
		}
		boolean flag = false;
		File dir = new File(path);
		if( dir.exists())
		{
			System.out.println("文件夹已存在");
			flag = false;
		}
		else
		{
			if( dir.getParentFile().exists())
			{
				dir.mkdir();
			}
			else
			{
				FileTools.createDir(dir.getParent().toString());
				dir.mkdir();
			}
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 文件复制
	 * @param srcFileName
	 * 					源文件
	 * @param destFileName
	 * 					目标文件
	 * @param isOverwrite
	 * 					是否允许覆盖
	 * @return
	 */
	public static boolean copyFile( String srcFileName, String destFilePath, boolean isOverwrite)
	{
		File srcFile = new File(srcFileName);  
		  
        // 判断源文件是否存在  
        if (!srcFile.exists()) 
        {  
            System.out.println("源文件不存在"); 
            return false;
        
        } else if (!srcFile.isFile()) 
        {  
            System.out.println("请填写正确的源文件路径");  
            return false;
        }  
  
        // 判断目标文件是否存在  
        destFilePath = destFilePath.replace("\\", "/");
        if( !destFilePath.endsWith("/"))
        {
        	destFilePath = destFilePath + "/";
        }
        String destFileName = destFilePath;
        File destFile = new File(destFileName);  
        if (destFile.exists()) 
        {  
            // 如果目标文件存在并允许覆盖  
            if (isOverwrite) 
            {  
                // 删除已经存在的目标文件，无论目标文件是目录还是单个文件  
                new File(destFileName).delete();  
            }  
        } 
        else 
        {  
            // 如果目标文件所在目录不存在，则创建目录  
            if (!destFile.getParentFile().exists()) 
            {  
                // 目标文件所在目录不存在  
                if (!destFile.getParentFile().mkdirs())
                {  
                    // 复制文件失败：创建目标文件所在目录失败  
                    return false;  
                }  
            }
        }
        
        int byteread = 0; // 读取的字节数  
        InputStream in = null;  
        OutputStream out = null;  
  
        try 
        {  
            in = new FileInputStream(srcFile);  
            out = new FileOutputStream(destFile);  
            byte[] buffer = new byte[1024];  
  
            while ((byteread = in.read(buffer)) != -1) 
            {  
                out.write(buffer, 0, byteread);  
            }  
            return true;  
        } 
        catch (FileNotFoundException e) 
        {  
            return false;  
        }
        catch (IOException e) 
        {  
            return false;  
        } 
        finally 
        {  
            try 
            {  
                if (out != null)  
                    out.close();  
                if (in != null)  
                    in.close();  
            } 
            catch (IOException e) 
            {  
                e.printStackTrace();  
            }  
        }  
		
	}
	
	/**
	 * 复制一个文件内的所有文件
	 * @param srcDirName
	 * 			源文件夹目录名
	 * @param destDirName
	 * 			目标文件夹目录名
	 * @param overwrite
	 * 			是否允许覆盖
	 * @return
	 */
	public static boolean copyDirectory(String srcDirName, String destDirName,  
	            boolean overwrite) {  
	        // 判断源目录是否存在  
	        File srcDir = new File(srcDirName);  
	        if (!srcDir.exists()) {  
	            System.out.println("源目录不存在!"); 
	            return false;  
	        } else if (!srcDir.isDirectory()) {  
	            System.out.println("复制目标不是一个目录!");  
	            return false;  
	        }  
	  
	        // 如果目标目录名不是以文件分隔符结尾，则加上文件分隔符  
	        if (!destDirName.endsWith(File.separator)) {  
	            destDirName = destDirName + File.separator.replace("\\", "/");  
	        }  
	        File destDir = new File(destDirName);  
	        // 如果目标文件夹存在  
	        if (destDir.exists()) {  
	            // 如果允许覆盖则删除已存在的目标目录  
	            if (overwrite) {  
	                new File(destDirName).delete();  
	            } else {  
	               System.out.println("目标目录已存在!");  
	                return false;  
	            }  
	        } else {  
	            // 创建目的目录  
	            System.out.println("目的目录不存在，准备创建...");  
	            if (!destDir.mkdirs()) {  
	                System.out.println("复制目录失败：创建目的目录失败！");  
	                return false;  
	            }  
	        }  
	  
	        boolean flag = true;  
	        File[] files = srcDir.listFiles();  
	        for (int i = 0; i < files.length; i++) {  
	            // 复制文件  
	            if (files[i].isFile()) {  
	                flag = FileTools.copyFile(files[i].getAbsolutePath(),  
	                        destDirName + files[i].getName(), overwrite);  
	                if (!flag)  
	                    break;  
	            } 
	            else if (files[i].isDirectory()) {  
	                flag = FileTools.copyDirectory(files[i].getAbsolutePath(),  
	                        destDirName + files[i].getName(), overwrite);  
	                if (!flag)  
	                    break;  
	            }  
	        }  
	        if (!flag) {  
	            System.out.println("复制目录" + srcDirName + "至" + destDirName + "失败！" );  
	            return false;  
	        } else {  
	            return true;  
	        }  
	    } 
	
	/**
	 * 得到项目根路径 
	 * 在服务器上形如（/.../exam/）
	 * 在本地测试形如（/.../exam/WebContent/）
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String getApplicationRootPath(){
		String currentClassFilePath = FileTools.class.getResource("").getPath(); 
		String rootPath = "";
	    int lastpathIndex = 0; 
	    if(currentClassFilePath.contains("WEB-INF")){
	    		lastpathIndex= currentClassFilePath.lastIndexOf("WEB-INF");
	    		rootPath = currentClassFilePath.substring(0,lastpathIndex);
	    }else if(currentClassFilePath.contains("build")){
	    		lastpathIndex= currentClassFilePath.lastIndexOf("build");
	    		String tempPath = currentClassFilePath.substring(0,lastpathIndex);
	    		rootPath = tempPath+"WebContent"+File.separator ;
	    }else{
	    		System.out.println("找不到路径");
	    }
	    
//	    try {
//			rootPath = modifySomePathName(rootPath);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return rootPath;
	}
	
	public static String translateSomeChineseIntoEnglish( String key) {
		
		Map<String, String> map = new HashMap< String, String>();
		
		// 课程名称
		String[] courseNameInChinese = {"语文","数学","英文"};
		String[] courseNameInEnglish = {"chinese","math","english"};
		for( int i=0; i<courseNameInChinese.length; i++)
		{
			map.put(courseNameInChinese[i], courseNameInEnglish[i]);
		}
		// 年级名称
		String[] yearNameInChinese = {"一年级","二年级","三年级","四年级","五年级","六年级","初一","初二","初三","跨年级"};
		String[] yearNameInEnglish = {"1","2","3","4","5","6","7","8","9","cross"};
		for( int i=0; i<yearNameInChinese.length; i++)
		{
			map.put(yearNameInChinese[i], yearNameInEnglish[i]);
		}
		return map.get(key);
	}
	
	/**
	 * 修改路径
	 * @param pathName
	 * @throws UnsupportedEncodingException
	 */
	public static String modifySomePathName( String pathName) throws UnsupportedEncodingException {
		// 修正编码
		pathName = URLDecoder.decode(pathName, "UTF-8").replace("\\", "/").replace("//", "/");
		// 判断操作系统
		Properties prop = System.getProperties();
		String os = prop.getProperty("os.name");
		if( (os.startsWith("win") || os.startsWith("Win")) &&  pathName.startsWith("\\"));
		{
			pathName = pathName.substring(1, pathName.length());
		}
//		if( pathName.startsWith(":"));
//		{
//			pathName = "D" + pathName;
//		}
		return pathName;
	}
	
	/**
	 * 得到某文件夹下指定后缀的文件
	 * @param directoryName
	 * @param extensionName
	 * @param filelist
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static List<String> getFileListWithSpecifiedExtensionInDirectory( 
			String directoryName, String extensionName, List<String> filelist) throws UnsupportedEncodingException {
		
		
		// 指定文件目录
		File dir = new File(directoryName);
		if( !dir.exists() || !dir.isDirectory()){
			return null;
		}
		
		// 遍历文件
		for( File file : dir.listFiles()){
			
			if( file.getAbsolutePath().endsWith(extensionName)){
				filelist.add(file.getAbsolutePath());
			}
		}
		return filelist;
	}
	
	
	
}
