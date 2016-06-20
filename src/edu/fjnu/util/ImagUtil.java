package edu.fjnu.util;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImagUtil 
{
	/**
	 * 图片大小转化
	 * @param path
	 * 			文件路径名
	 * @param srcImgName
	 * 			源文件名
	 * @param distImgName
	 * 			目标文件名
	 * @param width
	 * 			新宽度
	 * @param height
	 * 			新高度
	 * @throws IOException
	 */
	public static void resizeImage( String path,String srcImgName, String distImgName,  
									int width, int height) 
									throws IOException
	{  
		String srcImgPath = path + srcImgName;
        File srcFile = new File(srcImgPath);  
        Image srcImg = ImageIO.read(srcFile);  
        BufferedImage buffImg = null;  
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
        buffImg.getGraphics().drawImage(  
                srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0,  
                0, null);  
        String distImgPath = path + distImgName;
        ImageIO.write(buffImg, "JPEG", new File(distImgPath));  
    }  
	
	public static void main( String[] args) throws IOException
	{
		ImagUtil.resizeImage("D:/eclipse-jee-mars-1-win32/eclipse/workspace/exam/WebContent/Relation/语文/三年级/", 
				"GroupedMatrix.png",
				"GroupedMatrix(1).png",
				300,
				500);
	}
}
