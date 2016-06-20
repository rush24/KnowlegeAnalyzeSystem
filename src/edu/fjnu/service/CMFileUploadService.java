package edu.fjnu.service;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import edu.fjnu.util.FileTools;

public class CMFileUploadService {

    private String tempPath = FileTools.getApplicationRootPath() + "/R/csv/temp/"; // 临时文件目录
    private static final Integer MAX_File_CAPACITY = 10*1024*1024;
    
	public void uploadFile(String savaFileName, HttpServletRequest request){
		String uploadFilePath = FileTools.getApplicationRootPath() + "/R/csv/customerMade/";
		File uploadFile = new File(uploadFilePath);
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}
		File tempPathFile = new File(tempPath);
        if (!tempPathFile.exists()) {
           tempPathFile.mkdirs();
        }
        try {
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
	        factory.setRepository(tempPathFile);// 设置缓冲区目录
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        upload.setSizeMax(MAX_File_CAPACITY); 
	        List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
	        Iterator<FileItem> i = items.iterator();
	        while (i.hasNext()) {
			    FileItem fi = (FileItem) i.next();
			    String fileName = fi.getName();
			    if (fileName != null){
			    		File file = new File(uploadFilePath,savaFileName);
				    fi.write(file);				    
			    }
	        }
        }
		 catch (Exception e) {
			e.printStackTrace();
		}
        
        
	}
}
