package clicflyer.testCases;

import org.testng.annotations.Test;

import clicflyer.utilities.Xls_Reader;

public class TestData1 {
	
	@Test
	public void excelCheck() {
		
		Xls_Reader reader=new Xls_Reader();
		String username1=reader.getCellData("Sheet1","username", 2);
		System.out.println(username1);
		
	}

}
