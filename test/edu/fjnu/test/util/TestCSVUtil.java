package edu.fjnu.test.util;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;

import edu.fjnu.util.CSVUtil;
import edu.fjnu.util.FileTools;

public class TestCSVUtil {

	@Test
	public void testImportCsv() throws FileNotFoundException{
		String fileName = FileTools.getApplicationRootPath() + "/R/csv/customerMade/test1.csv";
		List<String[]> list = CSVUtil.importCsv(fileName);
		for(String[] strArr : list){
			System.out.print(strArr[0] + "," + strArr[1] + "," + strArr[2] + "," + strArr[3]);
		}
	}
}
