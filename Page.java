package dbload;

import java.util.LinkedList;

public class Page {
	
	//Create page using linked list
	  LinkedList<Object[]> entry = new LinkedList<Object[]>();

	  //consruct a new page
	  public Page(){
	  }

	  //Add an entry to the page
	  public void addEntry(Object[] e){
	    entry.addLast(e);
	  }

}
