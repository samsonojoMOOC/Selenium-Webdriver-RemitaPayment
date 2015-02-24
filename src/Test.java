import datatable.Xls_Reader;


public class Test {

		public static void main(String[] args){
			
			String testCase="LoginTest";
			Xls_Reader datatable = new Xls_Reader(System.getProperty("user.dir")+"//src//config//AgentConnector.xlsx");
			System.out.println(datatable.getRowCount("TestCase"));
			System.out.println(datatable.getCellData("TestCase", "TCID", 3));
			
			for(int rowNum=2; rowNum<=datatable.getRowCount("TestCase");rowNum++){
				if(testCase.equals(datatable.getCellData("TestCase", "TCID", rowNum))){
					if(datatable.getCellData("TestCase", "Runmode", rowNum).equals("Y"))
						System.out.println("run the test");
					else
						System.out.println("skip test");
				}
			}
		}
}
