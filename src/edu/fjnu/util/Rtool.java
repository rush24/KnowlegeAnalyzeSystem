//package edu.fjnu.util;
//
//
//
//import java.io.IOException;
//import java.sql.SQLException;
//import org.junit.Test;
//import org.rosuda.REngine.REXPMismatchException;
//import org.rosuda.REngine.REngineException;
//import org.rosuda.REngine.Rserve.RConnection;
//import org.rosuda.REngine.Rserve.RserveException;
//
//import edu.fjnu.service.PaperService;
//
//public class Rtool 
//{
//	/**
//	 * 绘制双数组
//	 * @param arr1
//	 * @param arr2
//	 * @throws REngineException 
//	 */
//	public void DoubleArrayScatterPlot( double[] arr1, double[] arr2) 
//			throws REXPMismatchException, REngineException
//	{
//		RConnection rconn = new RConnection();
//		try {
//			
//			rconn.assign("x1", arr1);
//			rconn.assign("x2", arr2);
//
//			rconn.eval("png('"+System.getProperty("user.dir").replace("\\", "/")+"/WebContent/R/Rimag/test.png',width = 15000, height =4000)");
//			rconn.eval("source('"+System.getProperty("user.dir").replace("\\", "/")+"/WebContent/R/RScript/testRScript.R')");
//
//			rconn.eval("png('/Users/vengeance/Desktop/test221.png',width = 2048, height = 2048)");
//			rconn.eval("source('/Users/vengeance/Desktop/testRScript.R')");
//
//			rconn.eval("DoubleArrayScatterPlot(x1,x2)");
//			rconn.eval("dev.off()");
//		} catch (RserveException e) {
//			e.printStackTrace();
//		}
//	}
//	
//
////	public static void main( String[] args) throws REXPMismatchException, REngineException, SQLException 
////	{
////		PaperService ps = new PaperService();
////		double[] arrWrong = ps.getWrongArray();
////		double[] arrScore = ps.getRealScoreArray();
////		
////		
////		Rtool rtool = new Rtool();
////		rtool.DoubleArrayScatterPlot(arrWrong, arrScore);
////		
////		
////	}
//	@Test
//	public void paint() throws SQLException, REXPMismatchException, REngineException {
//
//		Rtool rtool = new Rtool();
//		rtool.DoubleArrayScatterPlot(arrWrong, arrScore);
//
//	}
//
//	
//}
