import java.util.Set;
import java.util.HashSet;


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
  public void printVertexVals(Vertex<?> vertex) {
    Set<Vertex<?>> visited = new HashSet<>();
    printVertexVals(vertex, visited);

  }
  public static void printVertexVals(Vertex<?> current, Set<Vertex<?>> visited){
    if(current == null || visited.contains(current)) return;
    visited.add(current);

    System.out.println(current.data);

    for(Vertex<?> neighbor : current.neighbors){
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
    Set<Vertex<T>> visited = new HashSet<>();
    reachable(vertex, visited);

    return visited;
  }

  public static <T> void reachable(Vertex<T> current, Set<Vertex<T>> visited){
    if(current == null || visited.contains(current)) return;
    visited.add(current);

    for(Vertex<T> neighbor : current.neighbors){
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
    Set<Vertex<Integer>> visited = new HashSet<>();
    reachable(vertex, visited);
    int maxValue = Integer.MIN_VALUE;
    for(Vertex<Integer> neighbor : visited){
      maxValue = Math.max(maxValue, neighbor.data);
    }

    return maxValue;
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
    Set<Vertex<T>> visited = new HashSet<>();
    Set<Vertex<T>> answer = new HashSet<>();
    if (vertex == null){
      return answer;
    }
    reachable(vertex, visited);
    for (Vertex<T> v : visited) {
      if (v.neighbors.isEmpty()) {
        answer.add(v);
      }
    }
    return answer;
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
    if (vertex == null) {
      return true;
    }

    reachable(vertex, visited);
    for (Vertex<Integer> v : visited) {
      if (v.data % 2 == 0) {
        return false;
      }
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
    if ( start == null || end == null){
    throw new NullPointerException();
  }

  return helper(start, end);
}
  public static boolean helper (Vertex<Integer> current, Vertex<Integer> end){
    if (current == end){
      return true;
    }
    for ( Vertex<Integer> neighbor : current.neighbors){
      if (current.data < neighbor.data && helper(neighbor, end)){
        return true;
      }
    }
    return false;
  }
}
