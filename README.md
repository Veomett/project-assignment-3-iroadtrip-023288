# CS 245 (Fall 2023) - Assignment 3 - IRoadTrip

Can you plan the path for a road trip from one country to another?
Yes, I can.

Change the java source code, but do not change the data files. See Canvas for assignment details.


In order to solve this problem, I write the following class to implement this problem:
   1).  The Array List class implement the Array List data structure, this data structure is used to
        store the path information in the IRoadTrip class's findPath function
	    and the Graph class's dijkstra function, it is also use to store the vertex's index to state information
		map.

   2).  The Linked List class implement the Linked List data structure, this data structure is used to
        store the adjacency vertex list in the Graph class, it is also used to store
	    the Border Information data read from the borders.txt file,
	    store the Capital Dist data read from the capdist.csv file,
	    and store the State Information data read from the state_name.tsv file.

   3).  The List interface, this interface provide the List data structure's interface.

   4).  The BorderInfo class, this class is used to encapsulation the Border Information data read from the borders.txt file.

   5).  The CapitalDist class, this class is used to encapsulation the Capital Dist data read from the capdist.csv file.

   6).  The StateInfo class, this class is used to encapsulation the State Information data read from the capdist.csv file.

   7).  The CountryName class, some countries have alias names, and some country names
        in the booders.txt file is irregular, the CountryName will solve these program.

   8).  The Hashtable class, this class implement the Hash Table data structure, it it use to store the stateid to
        the Graph's vertex index map. It's time complexity for insert and get operation is O(1).

   9).  The Hashkey class, this class is used to encapsulation the Hash Key for the Hash Table.

   10). The Heap class, this class implement the Minimum Heap Data Structure,
        this class is used in the Graph's dijkstra algorithm. 

   11). The DistVertexPair class, this class is used to store the priority and vertex's index information of the Graph's vertex
        in the Heap class.

   12). The Graph class, this class implement the Graph Data Structure
        and the Dijkstra Algorithm which is used in the IRoadTrip class's findPath function.

   13). The IRoadTrip class, this class implement the whole application's main function.

