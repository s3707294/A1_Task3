/*
Erin Sutton, s3707294
COSC2406 Assignment 1, Task 3
*/

package dbload;

//Imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;


public class dbload {
	
	//Some important values
	  int pageTotal = 0;
	  static int pageSize = 0;
	  int currentPageFree = 0;

	  int recordTotal = 0;
	  int recordPage = 0;

	public static void main(String[] args) throws IOException {
		//Set the pageSize and create file name using this size
	    pageSize = Integer.parseInt(args[1]);
	    String heapfileName = "heap." + String.valueOf(pageSize);
	    String heapfilePageNum = heapfileName + ".pages.txt";
	    
	    //Start the timing the loading of data into heap file
	    long start = System.nanoTime();

	    //open file that will record number of records in each page
	    FileWriter writePage = new FileWriter(heapfilePageNum);
	    
	  //Create the heap
	    LinkedList<Page> heap = new LinkedList<Page>();

	    //Open csv data
	    try {
	    	BufferedReader br = new BufferedReader(new FileReader(args[2]));
	    	
	    	String line = "";
	    		    	
	    	//Go through each row
	    	while((line = br.readLine()) != null) {
	    		
	    		//Split using the delimiter
	    		String[] instance = line.split(",");
	    		
	    		//Convert row into types and count number of bytes
	    		int byteSize = 0;		
	    		Object[] dataEntry =  new Object[8];
	    		convertRow(dataEntry, instance, byteSize);
	    		
	    		
	    		
	    	}
	    }catch(IOException e) {
	    	e.printStackTrace();
	    }

	    

	    
	}
	
	//Converts the row to a correct types entry and counts the number of bytes used
	 public static void convertRow(Object[] entry, String[] row, int rowByteSize){

	    //Convert ID
	    entry[0] = Integer.valueOf(row[0]);
	    rowByteSize += 4;

	    //Create SDT_Name
	    String sdt_name = row[7] + row[1];
	    entry[1] = sdt_name;
	    rowByteSize += sdt_name.getBytes().length;
	    
	    //Convert Year
	    entry[2] = Integer.valueOf(row[2]);
	    rowByteSize += 4;
	    
	    //Input Month
	    entry[3] = row[3];
	    rowByteSize += row[3].getBytes().length;
	    
	    //Convert Mdate
	    entry[4] = Integer.valueOf(row[4]);
	    rowByteSize += 4;
	    
	    //Input Day
	    entry[5] = row[5];
	    rowByteSize += row[5].getBytes().length;
	    
	    //Convert Time
	    entry[6] = Integer.valueOf(row[6]);
	    rowByteSize += 4;
	    
	    //Input Sensor Name
	    entry[7] = row[8];
	    rowByteSize += row[8].getBytes().length;   
	    
	  }
	 
	 public void load(List<String[]> row, LinkedList<Page> heap){

	  }

}
