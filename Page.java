//Page class

public class Page{

  //Create page using linked list
  LinkedList<btye[][]> entry = new LinkedList<btye[][]>();

  //consruct a new page
  public Page(){
  }

  //Add an entry to the page
  public void addEntry(byte[][] e){
    entry.addLast(e);
  }
}
