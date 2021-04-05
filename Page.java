import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Page {
	
	  //Create page using linked list
	  LinkedList<Object[]> entry = new LinkedList<Object[]>();
	 
	  //Number of columns per entry
	  int colNum = 9;

	  //Construct a new page
	  public Page(){
	  }

	  //Add an entry to the page
	  public void addEntry(Object[] e){
	    entry.addLast(e);
	  }
	  
	  //Write out page to the heap
	  public void writeToHeap(FileWriter writeHeap) throws IOException {
		  
		  //loop through entry and add to heap file
		  for(Object[] instance : entry) {
			  
			  //Write each column to file
			  //ID column
			  writeHeap.write((int) instance[0]);
			  writeHeap.write(",");
			  //SDT_Name column
			  writeHeap.write((String) instance[1]);
			  writeHeap.write(",");
			  //Year column
			  writeHeap.write((int) instance[2]);
			  writeHeap.write(",");
			  //Month column
			  writeHeap.write((String) instance[3]);
			  writeHeap.write(",");
			  //Mdate column
			  writeHeap.write((int) instance[4]);
			  writeHeap.write(",");
			  //Day column
			  writeHeap.write((String) instance[5]);
			  writeHeap.write(",");
			  //Time column
			  writeHeap.write((int) instance[6]);
			  writeHeap.write(",");
			  //Sensor_Name column
			  writeHeap.write((String) instance[7]);
			  writeHeap.write(",");
			  //Hourly_Counts column
			  writeHeap.write((int) instance[8]);
			  writeHeap.write("\n");			  
		  }
	  }

}
