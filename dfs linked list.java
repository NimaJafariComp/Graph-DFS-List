class StackX {

    private final int SIZE = 20;

    private int[] st;

    private int top;

    // ------------------------------------------------------------

    public StackX() // constructor

    {

        st = new int[SIZE]; // make array

        top = -1;

    }

    // ------------------------------------------------------------

    public void push(int j) // put item on stack

    {

        st[++top] = j;

    }

    // ------------------------------------------------------------

    public int pop() // take item off stack

    {

        return st[top--];

    }

    // ------------------------------------------------------------

    public int peek() // peek at top of stack

    {

        return st[top];

    }

    // ------------------------------------------------------------

    public boolean isEmpty() // true if nothing on stack

    {

        return (top == -1);

    }

    // ------------------------------------------------------------

} // end class StackX

////////////////////////////////////////////////////////////////

// Todo: Implement Link class with key (int) & data (floating point)

class Link {

    // Todo: Declare variables for key & data

    int key;

    double data;

    Link next;

    // Todo: Implement constructor

    public Link(int key, double data) {

        this.key = key;

        this.data = data;

        this.next = null;

    }

    // Todo: Uncomment

    public void displayLink() {

        System.out.print("[key: " + key + ", data: " + data + "]");

    }

}

class LinkList {

    private Link first; // ref to first link on list

    // -------------------------------------------------------------

    public LinkList() // constructor

    {

        first = null; // no links on list yet

    }

    // -------------------------------------------------------------

    public void insertFirst(int id, double dd) { // make new link

        Link newLink = new Link(id, dd);

        newLink.next = first; // it points to old first link

        first = newLink; // now first points to this

    }

    // -------------------------------------------------------------

    public Link find(int key) // find link with given key

    { // (assumes non-empty list)

        Link current = first; // start at 'first'

        while (current.key != key) // while no match,

        {

            if (current.next == null) // if end of list,

                return null; // didn't find it

            else // not end of list,

                current = current.next; // go to next link

        }

        return current; // found it

    }

    // -------------------------------------------------------------

    public Link delete(int key) // delete link with given key

    { // (assumes non-empty list)

        Link current = first; // search for link

        Link previous = first;

        while (current.key != key) {

            if (current.next == null)

                return null; // didn't find it

            else {

                previous = current; // go to next link

                current = current.next;

            }

        } // found it

        if (current == first) // if first link,

            first = first.next; // change first

        else // otherwise,

            previous.next = current.next; // bypass it

        return current;

    }

    // -------------------------------------------------------------

    public void displayList() // display the list

    {

        System.out.print("List (first-->last): ");

        Link current = first; // start at beginning of list

        while (current != null) // until end of list,

        {

            current.displayLink(); // print data

            current = current.next; // move to next link

        }

        System.out.println("");

    }

    public Link getFirst() {

        return first;

    }

    public boolean hasEdgeToSomeOtherVertex() {

        return first != null;

    }

    // -------------------------------------------------------------

} // end class LinkList

class AdjacencyList {

    private LinkList[] adjacencyListArray;

    public AdjacencyList(int size) {

        adjacencyListArray = new LinkList[size];

        for (int i = 0; i < size; i++) {

            adjacencyListArray[i] = new LinkList();

        }

    }

    public void addEdge(int start, int end) {

        Link current = adjacencyListArray[start].getFirst();

        if (adjacencyListArray[start].hasEdgeToSomeOtherVertex()) {

            // If there is already an edge from vertex 'start', add 'end' to the existing

            // edge

            while (current.next != null) {

                current = current.next;

            }

            current.next = new Link(end, 1.0);

        } else {

            // Otherwise, add a new edge

            adjacencyListArray[start].insertFirst(end, 1.0);

        }

        adjacencyListArray[end].insertFirst(start, 1.0);

    }

    public LinkList getAdjacencyList(int vertex) {

        return adjacencyListArray[vertex];

    }

}

class Vertex {

    public char label; // label (e.g. 'A')

    public boolean wasVisited;

    public LinkList adjacencyList; // adjacency list

    // ------------------------------------------------------------

    public Vertex(char lab) // constructor

    {

        label = lab;

        wasVisited = false;

        adjacencyList = new LinkList();

    }

    // ------------------------------------------------------------

} // end class Vertex

////////////////////////////////////////////////////////////////

class Graph {

    private final int MAX_VERTS = 20;

    private Vertex vertexList[]; // list of vertices

    private int nVerts; // current number of vertices

    private AdjacencyList adjacencyList;

    private StackX theStack;

    // ------------------------------------------------------------

    public Graph() // constructor

    {

        vertexList = new Vertex[MAX_VERTS];

        nVerts = 0; // Initialize nVerts to 0

        adjacencyList = new AdjacencyList(MAX_VERTS);

        theStack = new StackX();

    } // end constructor

    // ------------------------------------------------------------

    public void addVertex(char lab) {

        vertexList[nVerts++] = new Vertex(lab);

    }

    // ------------------------------------------------------------

    public void addEdge(int start, int end) {

        adjacencyList.addEdge(start, end);

    }

    // ------------------------------------------------------------

    public void displayVertex(int v) {

        System.out.print(vertexList[v].label);

    }

    // ------------------------------------------------------------

    public void resetWasVisitedFlags() {

        for (int j = 0; j < nVerts; j++) {

            vertexList[j].wasVisited = false;

        }

    }

    public void dfs() // depth-first search

    {

        // begin at vertex 0

        vertexList[0].wasVisited = true; // mark it

        displayVertex(0); // display it

        theStack.push(0); // push it

        while (!theStack.isEmpty()) // until stack empty,

        {

            // get an unvisited vertex adjacent to stack top

            int v = getAdjUnvisitedVertex(theStack.peek());

            if (v == -1) // if no such vertex,

            {

                theStack.pop();

            } else // if it exists,

            {

                vertexList[v].wasVisited = true; // mark it

                displayVertex(v); // display it

                theStack.push(v); // push it

            }

        } // end while

        resetWasVisitedFlags(); // Reset the wasVisited flags after the DFS

    } // end dfs // end dfs

    // ------------------------------------------------------------

    // returns an unvisited vertex adj to v

    public int getAdjUnvisitedVertex(int v) {

        LinkList adjList = adjacencyList.getAdjacencyList(v);

        Link current = adjList.getFirst();

        while (current != null) {

            int neighborKey = current.key;

            if (!vertexList[neighborKey].wasVisited) {

                return neighborKey;

            }

            current = current.next;

        }

        return -1;

    }

} // end getAdjUnvisitedVertex()

// ------------------------------------------------------------

// end class Graph

class DFSApp {

    public static void main(String[] args) {

        Graph theGraph = new Graph();

        theGraph.addVertex('A'); // 0 (start for dfs)

        theGraph.addVertex('B'); // 1

        theGraph.addVertex('C'); // 2

        theGraph.addVertex('D'); // 3

        theGraph.addVertex('E'); // 4

        theGraph.addEdge(0, 1); // AB

        theGraph.dfs(); // depth-first search

        System.out.println();

        theGraph.addEdge(1, 2); // BC

        theGraph.dfs(); // depth-first search

        System.out.println();

        theGraph.addEdge(0, 3); // AD

        theGraph.dfs(); // depth-first search

        System.out.println();

        theGraph.addEdge(3, 4); // DE

        System.out.print("Visits: ");

        theGraph.dfs(); // depth-first search

        System.out.println();

    } // end main()

} // end class DFSApp
