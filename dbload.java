/*
Erin Sutton, s3707294
COSC2406 Assignment 1, Task 3
*/

//Imports
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;


public class dbload {
	
	//Some important values
	  static int pageTotal = 0;
	  static int pageSize = 0;
	  static int currentPageFree = 0;

	  static int recordTotal = 0;
	  static int recordPage = 0;

	public static void main(String[] args) throws IOException {
		//Set the pageSize and create file name using this size
	    pageSize = Integer.parseInt(args[1]);
	    currentPageFree = pageSize;
	    String heapFileName = "heap." + String.valueOf(pageSize);
	    String heapfilePageNum = heapFileName + ".pages.txt";
	    
	    //Start the timing the loading of data into heap file
	    long start = System.nanoTime();

	    //open file that will record number of records in each page
	    FileWriter writePage = new FileWriter(heapfilePageNum, true);
	    
	    //Create first page	    
	    Page currPage = new Page();
	    pageTotal++;

	    //Open csv data
	    try {
	    	//Open record per page file
	    	BufferedReader br = new BufferedReader(new FileReader(args[2]));
	    	//open heap file
	    	BufferedWriter writeHeap = new BufferedWriter(new FileWriter(heapFileName, true));
	    	
	    	String line = "";
	    	//Used to skip the header
	    	boolean headerSkip = false;
	    	
	    		    	
	    	//Go through each row
	    	while((line = br.readLine()) != null) {
	    		
	    		if(headerSkip) {
	    		
	    			//Split using the delimiter
	    			String[] instance = line.split(",");
	    		
	    			//Convert row into types and count number of bytes	    				
	    			String[] dataEntry =  new String[9];
	    			dataEntry = instance;
	    			int byteSize = countBytes(dataEntry);
	    			dataEntry = convertRow(dataEntry, instance);	    			    		
	    		
	    			//Check if their is room on page and act accordingly
	    			currentPageFree -= byteSize;
	    		
	    			//If room on current Page
	    			if(currentPageFree >= 0){
	    				//add row to current page
	    				currPage.addEntry(dataEntry);
	    				//Increment count of records on page
	    				recordPage++;	    			
	    			}else { //If not enough room on current page
	    			
	    				//write number of records on previous page out
	    				writePage.write(String.valueOf(recordPage));
	    				writePage.write("\n");
	    				//Update counts
	    				recordTotal += recordPage;
	    				recordPage = 0;
	    				pageTotal++;
	    				currentPageFree = pageSize;
	    				//Write Page to heap
	    				currPage.writeToHeap(writeHeap);
	    				//Create new page
	    				currPage = new Page();
	    				//add row to new page
	    				currPage.addEntry(dataEntry);
	    				//Increment count of records on page
	    				recordPage++;
	    				//Update currentPageFree
	    				currentPageFree -= byteSize;	    	
	    			}
	    		}else {
	    			headerSkip = true; //the header line as been read and discarded
	    		}
	    		    		
	    	}
	    	//close the data file
	    	br.close();	    	
	    	writePage.close();
	    	writeHeap.close();
	    }catch(IOException e) {
	    	e.printStackTrace();
	    }
	    	    
	    //end the timing	    
	    long end = System.nanoTime();
	    
	    //Print out Number of records loaded
	    System.out.println("Number of records loaded : " + recordTotal);
	    //Print out number of pages used
	    System.out.println("Number of pages used : " + pageTotal);
	    //Print out number of milliseconds to create the heap file
	    System.out.println("Number of milliseconds to create heap file : " + ((end - start)/ 1000000) + "ms");   
    
	}
	
	//Converts the row to a correct types entry
	 public static String[] convertRow(String[] entry, String[] row){		 

	    //Convert ID
	    entry[0] = Integer.toBinaryString(Integer.valueOf(row[0]));	    

	    //Create SDT_Name
	    String sdt_name = stringToBinary(row[7] + row[1]);
	    entry[1] = sdt_name;
	    	    
	    //Convert Year
	    entry[2] = Integer.toBinaryString(Integer.valueOf(row[2]));
	    	    
	    //Input Month
	    entry[3] = stringToBinary(row[3]);	    
	    
	    //Convert Mdate
	    entry[4] = Integer.toBinaryString(Integer.valueOf(row[4]));	    
	    
	    //Input Day
	    entry[5] = stringToBinary(row[5]);	    
	    
	    //Convert Time
	    entry[6] = Integer.toBinaryString(Integer.valueOf(row[6]));	    
	    
	    //Input Sensor Name
	    entry[7] = stringToBinary(row[8]);	       
	    
	    //Convert Hourly_Counts
	    entry[8] = Integer.toBinaryString(Integer.valueOf(row[9]));	
	    
	    return entry;
	  }
	 
	 //Converts a String to binary
	 public static String stringToBinary(String s) {
		 //Get the bytes of the string
		 byte[] bytes = s.getBytes();
		 
		 StringBuilder binary = new StringBuilder();
		 
		 //loop through the bytes
		 for(byte b: bytes) {
			 
			 //Get int of the byte
			 int byteValue = b;
			 //Create the binary for each character
			 for(int i = 0; i < 8; i++) {
				 binary.append((byteValue & 128) == 0 ? 0:1);
				 byteValue <<= 1;
			 }
		 }
		 //Return as a String
		 return binary.toString();		 
	 }
	 
	 //Counts the number of bytes per record
	 public static int countBytes(String[] dataEntry) {
		 int bytes = 0;
		 
		 //There are 5 columns that are integers, integers take 4 bytes
		 bytes += 5*4;
		 
		 //Array of columns that are strings
		 int[] col = {1,3,5,8};
		 
		 //Get bytes for the string columns
		 for(int i = 0; i < col.length; i++) {
			 bytes += dataEntry[col[i]].getBytes().length;
		 }		 
		 return bytes;
	 } 
	 	 
}
