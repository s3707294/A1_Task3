/*
Erin Sutton, s3707294
COSC2406 Assignment 1, Task 3
*/

//Imports
import java.io.BufferedReader;
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
	    
	    //Create the heap and create first page
	    LinkedList<Page> heap = new LinkedList<Page>();
	    Page currPage = new Page();
	    pageTotal++;

	    //Open csv data
	    try {
	    	BufferedReader br = new BufferedReader(new FileReader(args[2]));
	    	
	    	String line = "";
	    	
	    		    	
	    	//Go through each row
	    	while((line = br.readLine()) != null) {
	    		
	    		//Split using the delimiter
	    		String[] instance = line.split(",");
	    		
	    		//Convert row into types and count number of bytes	    				
	    		Object[] dataEntry =  new Object[9];
	    		dataEntry = convertRow(dataEntry, instance);
	    		int byteSize = countBytes(dataEntry);	    		
	    		
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
	    			//Add page to the heap
	    			heap.addLast(currPage);
	    			//Create new page
	    			currPage = new Page();
	    			//add row to new page
	    			currPage.addEntry(dataEntry);
	    			//Increment count of records on page
	    			recordPage++;
	    			//Update currentPageFree
	    			currentPageFree -= byteSize;	    	
	    		}   		
	    		    		
	    	}
	    	//close the data file
	    	br.close();
	    	//close the file detailing number of records per page
	    	writePage.close();
	    }catch(IOException e) {
	    	e.printStackTrace();
	    }
	    
	    //Write the heap to file
	    writeHeap(heap, heapFileName);
	    
	    //end the timing	    
	    long end = System.nanoTime();
	    
	    //Print out Number of records loaded
	    System.out.println("Number of records loaded : " + recordTotal);
	    //Print out number of pages used
	    System.out.println("Number of pages used : " + pageTotal);
	    //Print out number of miliseconds to create the heap file
	    System.out.println("Number of milliseconds to create heap file : " + ((end - start)/ 1000000));   
    
	}
	
	//Converts the row to a correct types entry
	 public static Object[] convertRow(Object[] entry, String[] row){		 

	    //Convert ID
	    entry[0] = Integer.valueOf(row[0]);	    

	    //Create SDT_Name
	    String sdt_name = row[7] + row[1];
	    entry[1] = sdt_name;
	    	    
	    //Convert Year
	    entry[2] = Integer.valueOf(row[2]);
	    	    
	    //Input Month
	    entry[3] = row[3];	    
	    
	    //Convert Mdate
	    entry[4] = Integer.valueOf(row[4]);	    
	    
	    //Input Day
	    entry[5] = row[5];	    
	    
	    //Convert Time
	    entry[6] = Integer.valueOf(row[6]);	    
	    
	    //Input Sensor Name
	    entry[7] = row[8];	       
	    
	    //Convert Hourly_Counts
	    entry[8] = Integer.valueOf(row[9]);	
	    
	    return entry;
	  }
	 
	 //Counts the number of bytes per record
	 public static int countBytes(Object[] dataEntry) {
		 int bytes = 0;
		 
		 //There are 5 columns that are integers, integers take 4 bytes
		 bytes += 5*4;
		 
		 //Get bytes for the string columns
		 //SDT_Name
		 bytes += ((String) dataEntry[1]).getBytes().length;
		 //Month
		 bytes += ((String) dataEntry[3]).getBytes().length;
		 //Day
		 bytes += ((String) dataEntry[5]).getBytes().length;
		 //Sensor_Name
		 bytes += ((String) dataEntry[7]).getBytes().length;
		 
		 return bytes;
	 }
	 
	 //Writes each page into the heap file
	 public static void writeHeap(LinkedList<Page> heap, String fileName) {
		 
		 //open the file
		 try { 
			 FileWriter writeHeap = new FileWriter(fileName, true);
			 
			 //loop through each page of the heap
			 for(Page p : heap) {
				 p.writeToHeap(writeHeap);
			 }
			 writeHeap.close();
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
	 }
	 
}
