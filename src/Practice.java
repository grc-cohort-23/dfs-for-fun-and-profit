import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

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
    Set<Vertex<?>> visited = new HashSet<>();
    printVertexVals(vertex, visited);
  }

  private void printVertexVals(Vertex<?> vertex, Set<Vertex<?>> visited) {
    if (vertex == null || visited.contains(vertex)) return;
    visited.add(vertex);
    System.out.println(vertex.data);
    for (var neighbor : vertex.neighbors) printVertexVals(neighbor, visited);
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
    Set<Vertex<T>> reachableSet = new HashSet<>();
    reachable(vertex, reachableSet);
    return reachableSet;
  }

  private <T> void reachable(Vertex<T> vertex, Set<Vertex<T>> visited) {
    if (vertex == null || visited.contains(vertex)) return;
    visited.add(vertex);
    for (var neighbor : vertex.neighbors) reachable(neighbor, visited); 
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
    Set<Vertex<Integer>> visited = new HashSet<>();
    return max(vertex,visited);
  }

  private int max(Vertex<Integer> vertex, Set<Vertex<Integer>> visited) {
    if (vertex == null || visited.contains(vertex)) return Integer.MIN_VALUE;
    visited.add(vertex);
    int largest = vertex.data;
    for (var neighbor : vertex.neighbors) largest = Math.max(largest,max(neighbor,visited));
    
    return largest;
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
    Set<Vertex<T>> visited = new HashSet<>();
    if (vertex == null) return leaves;
    Stack<Vertex<T>> stack = new Stack<>();

    stack.add(vertex);
    while (!stack.isEmpty()) {
      Vertex<T> current = stack.pop();
      visited.add(current);
      if (current.neighbors.isEmpty()) leaves.add(current);
      else for (var v : current.neighbors) if (!visited.contains(v)) stack.add(v);
    }

    return leaves;
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
    Set<Vertex<Integer>> visited = new HashSet<>();
    Stack<Vertex<Integer>> stack = new Stack<>();
    stack.add(vertex);

    while (!stack.isEmpty()) {
      Vertex<Integer> current = stack.pop();
      visited.add(current);
      // If the current node is even
      if (current.data % 2 == 0) return false;
      for (var v : current.neighbors) if (!visited.contains(v)) stack.add(v);
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
    if (start == null || end == null) throw new NullPointerException("Start and End cannot be null.");
    Stack<Vertex<Integer>> stack = new Stack<>();
    // Interestingly, doesn't need a visited set.
    stack.add(start);

    while (!stack.isEmpty()) {
      Vertex<Integer> current = stack.pop();
      // If this is the target vertex, then that means there is a strictly increasing path
      if (current == end) return true;
      // Add all the neighbors that are larger than the current node; traverse only the strictly increasing vertexes.
      for (var v : current.neighbors) if (v.data > current.data) stack.add(v);
    }

    return false;
  }
}
