import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Page {
	
	  //Create page using linked list
	  LinkedList<String[]> entry = new LinkedList<String[]>();
	 
	  //Number of columns per entry
	  int colNum = 9;

	  //Construct a new page
	  public Page(){
	  }

	  //Add an entry to the page
	  public void addEntry(String[] e){
	    entry.addLast(e);
	  }
	  
	  //Write out page to the heap
	  public void writeToHeap(BufferedWriter writeHeap) throws IOException {
		  
		  //loop through entry and add to heap file
		  for(String[] instance : entry) {
			  
			  //Write each column to file be loop through array
			  for(int i = 0; i < 9; i++) {
				//If last column add a new line at end 
				if(i == 8) {
					writeHeap.append(instance[8]);
					writeHeap.newLine();
				}else { //Otherwise write delimiter between columns
					writeHeap.append(instance[i]);
					writeHeap.append(",");
				}
			  }   
		  }
	  }
	  
	  //Search through the records to find any matching query
	  public void search(String query) {
		  
		  //loop through the records 
		  for(String[] instance : entry) {
			  
			  //Check the correct column (column 2)
			  if(instance[1].equals(query)) {
				  
				  //Convert instance out of binary and print out
				  convertPrint(instance);				  
			  }
		  }
	  }
	  
	  //Converts binary to string or int as case may be and prints instance
	  private void convertPrint(String[] instance) {
		  
		  //ID column
		  System.out.print(Integer.parseInt(instance[0], 2));
		  System.out.print("|");
		  
		  //The SDT_Name column
		  System.out.print(convertString(instance[1]));
		  System.out.print("|");
		  
		  //Year Column
		  System.out.print(Integer.parseInt(instance[2], 2));
		  System.out.print("|");
		  
		  //Month Column
		  System.out.print(convertString(instance[3]));
		  System.out.print("|");
		  
		  //Mdate Column
		  System.out.print(Integer.parseInt(instance[4], 2));
		  System.out.print("|");
		  
		  //Day Column
		  System.out.print(convertString(instance[5]));
		  System.out.print("|");
		  
		  //Time Column
		  System.out.print(Integer.parseInt(instance[6], 2));
		  System.out.print("|");
		  
		  //Sensor Name Column
		  System.out.print(convertString(instance[7]));
		  System.out.print("|");
		  
		  //Hourly_Counts Column
		  System.out.print(Integer.parseInt(instance[8], 2));
		  System.out.print("|");
		  
		  //Print out new line
		  System.out.println();
	  }
	  
	  //Converts a binary string into a string
	  private String convertString(String s) {
		  
		  //Get array consisting of characters in string
		  String[] sArray = s.split("(?<=\\G.{8})");
		  
		  //Turn into readable string
		  StringBuilder readableS = new StringBuilder();
		  
		  //loop through split array
		  for(String c : sArray) {
			  //Turn into int
			  int charNum = Integer.parseInt(c, 2);
			  //Append to string
			  readableS.append((char)charNum).toString();
		  }
		  
		  return readableS.toString();
	  }
}
