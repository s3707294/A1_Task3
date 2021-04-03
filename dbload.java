/*
Erin Sutton, s3707294
COSC2406 Assignment 1, Task 3
*/

//Imports
import com.copencsv.CSVreader;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class bdload{

  //Some important values
  int pageTotal = 0;
  int pageSize = 0;
  int currentPageFree = 0;

  int recordTotal = 0;
  int recordPage = 0;

  public static void main(String[] args) {

    //Set the pageSize and create file name using this size
    pageSize = args[1];
    String heapfileName = "heap." + String.valueOf(pageSize);
    String heapfilePageNum = heapfileName + ".pages.txt"

    //Open csv data
    try( CSVReader reader = new CSVReader(new FileReader(args[2]))){
      List<String[]> data = reader.readAll();
    }

    //Start the timing the loading of data into heap file
    long start = System.nanoTime();

    //open file that will record number of records in each page
    FileWriter writePage = new FileWriter(heapfilePageNum);

    //Create the heap
    LinkedList<Page> heap = new LinkedList<Page>();

    //Binary version of the row
    byte[][] dataEntry =  new byte[8][]

    //Load the data onto the heap
    //loop through the rows of data
    for(i = 0; data.size(); i++){

      int rowByteSize = 0;

      //Convert to binary and store in array
      String [] row = data.get(i);

      convertRow(row, rowByteSize);

    }


  }

  public byte[][] convertRow(String[] row, int rowByteSize){

    //Convert ID
    byte[0] = new byte[] {Integer.toBinaryString(Integer.parseInt(row[0]))}
    rowByteSize += 4;

    //Create and Convert SDT_Name
    String sdt_name = row[7] + row[1];
    btye[1] = new byte[] {sdt_name.getBytes()}


  }

  public void load(List<String[]> row, LinkedList<Page> heap){

  }
}
