import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class dbquery {
	
	//Some important values
	static String pageSize = "";
	static String query = "";

	public static void main(String[] args) throws IOException{
		
		//Assign the pageSize and query variables
		query = args[0];
		pageSize = args[1];
		
		//Convert query to binary		
		query = stringToBinary(query);
		
		//Start the timing of the query
	    long start = System.nanoTime();
	    
		//Get and open the files
	    BufferedReader br = new BufferedReader(new FileReader("heap."+ pageSize + ".pages.txt"));	    
		BufferedReader heap = new BufferedReader(new FileReader("heap." + pageSize));
	    String record = "";
		
	    //Read each line to find number of records in current page
    	while((record = br.readLine()) != null) {
    		
    		int numRecords = Integer.valueOf(record);   		
    		    		
    		//Create Page and add records to it
    		Page currPage = new Page();
    		
    		for(int i = 0; i <= numRecords; i++) {
    			
    			
    			String instance = "";
    			//Make sure record exists
    			if((instance = heap.readLine()) != null) {
    				//Get the entry as a string split using delimiter
    				String[] instanceArray = instance.split(",");
    				//Add to page
    				currPage.addEntry(instanceArray);
    			}
    		}
    		
    		//Search Page for query and output any matches
    		currPage.search(query);  	
    		    	    		
    	}
    	
    	//close both files
    	br.close();
    	heap.close();
    	
    	//End Timing of the query
    	long end = System.nanoTime();
    	
    	//Print out number of milliseconds to search
	    System.out.println("Number of milliseconds to search for query : " + ((end - start)/ 1000000) + "ms");

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

}
