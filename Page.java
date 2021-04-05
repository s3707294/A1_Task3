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
}
