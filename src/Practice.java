import java.util.HashSet;
import java.util.Set;

/**
 * A utility class providing various graph traversal methods using DFS.
 */
public class Practice {

  /**
   * Prints the value of every vertex reachable from the given starting vertex,
   * including the starting vertex itself. Each value is printed on a separate line.
   * The order of printing is unimportant.
   *
   * Each vertex's value should be printed only once, even if it is reachable via multiple paths.
   * It is guaranteed that no two vertices will have the same value.
   *
   * If the given vertex is null, this method prints nothing.
   *
   * @param vertex The starting vertex for the traversal.
   */
  public <T> void printVertexVals(Vertex<T> vertex) {
    if (vertex == null) return;
    printVertexVals(vertex, new HashSet<>());
  }

  private <T> void printVertexVals(Vertex<T> current, Set<Vertex<T>> visited) { 
    if (!visited.add(current)) return;
    System.out.println(current.data);
    for (Vertex<T> neighbor : current.neighbors) {
      printVertexVals(neighbor, visited);
    }
  }

  /**
   * Returns a set of all vertices reachable from the given starting vertex,
   * including the starting vertex itself.
   *
   * If the given vertex is null, an empty set is returned.
   *
   * @param vertex The starting vertex for the traversal.
   * @return A set containing all reachable vertices, or an empty set if vertex is null.
   */
  public <T> Set<Vertex<T>> reachable(Vertex<T> vertex) {
    Set<Vertex<T>> visted = new HashSet<>();
    if (vertex == null) return visted; // Return an empty set if the vertex is null
    reachable(vertex, visted);  // Start the recursive traversal
    return visted; // Return the set of reachable vertices
  }

  private <T> void reachable(Vertex<T> current, Set<Vertex<T>> visited) {
    if (!visited.add(current)) return; // If the current vertex has already been visited -> return 
    for (Vertex<T> neighbor : current.neighbors) { 
      reachable(neighbor, visited);
    }
  }



  /**
   * Returns the maximum value among all vertices reachable from the given starting vertex,
   * including the starting vertex itself.
   *
   * If the given vertex is null, the method returns Integer.MIN_VALUE.
   *
   * @param vertex The starting vertex for the traversal.
   * @return The maximum value of any reachable vertex, or Integer.MIN_VALUE if vertex is null.
   */
  public int max(Vertex<Integer> vertex) {
    if (vertex == null) return Integer.MIN_VALUE;
    return max(vertex, new HashSet<>());
  }

  private int max(Vertex<Integer> current, Set<Vertex<Integer>> visited) {
    if (!visited.add(current)) return Integer.MIN_VALUE; // If the current vertex has already been visited -> return Integer.MIN_VALUE
    int maxVal = current.data; //  maxVal with the current vertex's value
    for (Vertex<Integer> neighbor : current.neighbors) { 
      maxVal = Math.max(maxVal, max(neighbor, visited)); // update maxVal with the maximum value found in the neighbors
    }
    return maxVal; // return the maximum value found
  }

  /**
   * Returns a set of all leaf vertices reachable from the given starting vertex.
   * A vertex is considered a leaf if it has no outgoing edges (no neighbors).
   *
   * The starting vertex itself is included in the set if it is a leaf.
   *
   * If the given vertex is null, an empty set is returned.
   *
   * @param vertex The starting vertex for the traversal.
   * @return A set containing all reachable leaf vertices, or an empty set if vertex is null.
   */
  public <T> Set<Vertex<T>> leaves(Vertex<T> vertex) {
    Set<Vertex<T>> leaves = new HashSet<>();
    if (vertex == null) return leaves;
    leaves(vertex, leaves); //start of recursion
    return leaves; 
  }

  private <T> void leaves(Vertex<T> current, Set<Vertex<T>> leaves) {
    if (current.neighbors.isEmpty()) { // checks if the current vertex has no neighbors
      leaves.add(current); // its a leaf -> add it to the leaves set
    }
    for (Vertex<T> neighbor : current.neighbors) { // call leaves recursively for each neighbor of the current vertex
      leaves(neighbor, leaves); // continue the traversal to find leaves in the neighbors
    }
  }

  /**
   * Returns whether all reachable vertices (including the starting vertex) hold
   * odd values. Returns false if at least one reachable vertex (including the starting vertex)
   * holds an even value.
   * 
   * If the given vertex is null, returns true.
   * 
   * @param vertex The starting vertex
   * @return true if all reachable vertices hold odd values, false otherwise
   */
  public boolean allOdd(Vertex<Integer> vertex) {
    if (vertex == null) return true;
    return allOdd(vertex, new HashSet<>());
  }

  private boolean allOdd(Vertex<Integer> current, Set<Vertex<Integer>> visited) {
    if (!visited.add(current)) return true; // if the current vertex has already been visited -> return true
    if (current.data % 2 == 0) return false; // otherwise false
    for (Vertex<Integer> neighbor : current.neighbors) { 
      if (!allOdd(neighbor, visited)) return false; // if any neighbor is not odd -> return false
    }
    return true;
  }

  /**
   * Determines whether there exists a strictly increasing path from the given start vertex
   * to the target vertex.
   *
   * A path is strictly increasing if each visited vertex has a value strictly greater than
   * (not equal to) the previous vertex in the path.
   *
   * If either start or end is null, a NullPointerException is thrown.
   *
   * @param start The starting vertex.
   * @param end The target vertex.
   * @return True if a strictly increasing path exists, false otherwise.
   * @throws NullPointerException if either start or end is null.
   */
  public boolean hasStrictlyIncreasingPath(Vertex<Integer> start, Vertex<Integer> end) {
    if (start == null || end == null) throw new NullPointerException();

    for (Vertex<Integer> neighbor : start.neighbors) {
      if (neighbor.data > start.data) { // check if the neighbor's value is  greater than the current vertex's value
        if (neighbor == end || hasStrictlyIncreasingPath(neighbor, end)) { // check if the neighbor is the target or if a path exists from neighbor
        }
      }
    }
    return false; 
  }
}