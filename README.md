# Depth-First Search (DFS) using Adjacency List

This Java program demonstrates the Depth-First Search (DFS) algorithm for a graph implemented using an adjacency list. DFS is a graph traversal algorithm that explores as far as possible along each branch before backtracking.

## Classes

### 1. StackX

- Implementation of a stack used in DFS.

### 2. Link

- Node class for a linked list.

### 3. LinkList

- Implementation of a linked list for adjacency lists.

### 4. AdjacencyList

- Manages the adjacency list for each vertex in the graph.

### 5. Vertex

- Represents a vertex in the graph.

### 6. Graph

- Manages the overall graph, including vertices and edges.

### 7. DFSApp

- Main application class to demonstrate DFS.

## Usage

1. **Adding Vertices**: Use `addVertex(char lab)` to add vertices to the graph.

2. **Adding Edges**: Use `addEdge(int start, int end)` to add edges between vertices.

3. **DFS**: Call `dfs()` on the graph to perform Depth-First Search.

4. **Display Visits**: The program will display the order in which vertices are visited during DFS.

## Example

```java
public static void main(String[] args) {

    Graph theGraph = new Graph();

    theGraph.addVertex('A');
    theGraph.addVertex('B');
    theGraph.addVertex('C');
    theGraph.addVertex('D');
    theGraph.addVertex('E');

    theGraph.addEdge(0, 1); // AB
    theGraph.dfs(); // Depth-First Search

    System.out.println();

    theGraph.addEdge(1, 2); // BC
    theGraph.dfs(); // Depth-First Search

    System.out.println();

    theGraph.addEdge(0, 3); // AD
    theGraph.dfs(); // Depth-First Search

    System.out.println();

    theGraph.addEdge(3, 4); // DE

    System.out.print("Visits: ");
    theGraph.dfs(); // Depth-First Search

    System.out.println();

} // end main()
```

## Note

This implementation uses an adjacency list to represent the graph, making it suitable for sparse graphs where the number of edges is much less than the number of possible edges. The DFS algorithm is applied to traverse the graph and display the order in which vertices are visited.

## Contributing

Feel free to contribute by forking the repository, making changes, and submitting a pull request. Your feedback and improvements are welcome!

## License

This project is licensed under the [MIT License](LICENSE). Use it, modify it, and share it as needed.
