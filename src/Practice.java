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
    Set<Vertex<T>> visited = new HashSet<>();
    printVertexVals(vertex, visited);
  }

  public <T> void printVertexVals(Vertex<T> vertex , Set<Vertex<T>> visited){
    if(visited.contains(vertex)) return;
    visited.add(vertex);
    System.out.println(vertex.data);

    
    for(Vertex<T> eachVertex : vertex.neighbors){
        printVertexVals(eachVertex, visited);
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
    if (vertex == null) return new HashSet<>();
    Set<Vertex<T>> visited = new HashSet<>(); 
    reachable(vertex, visited);

    return visited;

  }
  public <T> void reachable(Vertex<T> vertex, Set<Vertex<T>> visited) {

    if(visited.contains(vertex)) return;
    visited.add(vertex);

    for(Vertex<T> eachVertex : vertex.neighbors){
      reachable(eachVertex, visited);
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
    if (vertex == null) return  Integer.MIN_VALUE;
    Set<Vertex<Integer>> visited = new HashSet<>();
    int maxValue = Integer.MIN_VALUE;

    maxValue =max(vertex, visited, maxValue);

    return maxValue;
  }
  public int max(Vertex<Integer> vertex, Set<Vertex<Integer>> visited , int maxValue){
    if (visited.contains(vertex)) return maxValue ;
    visited.add(vertex);

    if (maxValue < vertex.data){
      maxValue = vertex.data;
    }

    for (Vertex<Integer> eachVertex : vertex.neighbors) {
        int maxNeighbor= max(eachVertex, visited, maxValue);
        if(maxValue< maxNeighbor){
          maxValue = maxNeighbor;
        }
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
    if(vertex == null) {
      return new HashSet<>();}
    Set<Vertex<T>> visited = new HashSet<>();
    Set<Vertex<T>> leave = new HashSet<>();
    leaves(vertex, visited, leave);

    return leave;
  }
  public <T> void leaves(Vertex<T> vertxe,Set<Vertex<T>> visited, Set<Vertex<T>> leave) {
    if(visited.contains(vertxe)) return;
      visited.add(vertxe);

    
    if (vertxe.neighbors.isEmpty()){
      leave.add(vertxe);
    }
    for (Vertex<T> eachVertex : vertxe.neighbors){
        leaves(eachVertex, visited ,leave);
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
    if(vertex == null ) return true;
    Set<Vertex<Integer>> visited = new HashSet<>();

    return allOdd(vertex, visited);
  }
  public boolean allOdd(Vertex<Integer> vertex , Set<Vertex<Integer>> visited){
    if (visited.contains(vertex)) return true;
    visited.add(vertex);

    if(vertex.data % 2 == 0){
      return false;
    }

    for(Vertex<Integer> eacVertex : vertex.neighbors){
      if (!allOdd(eacVertex , visited)){
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
        if (start == null || end == null) {
        throw new NullPointerException();
    }

    Set<Vertex<Integer>> visited = new HashSet<>();

    return hasStrictlyIncreasingPath(start, end, visited);
  }
  public boolean hasStrictlyIncreasingPath(Vertex<Integer> vertex, Vertex<Integer> end, Set<Vertex<Integer>> visited) {

    if (visited.contains(vertex)) return false;
    visited.add(vertex);

    if (vertex == end) {
        return true;
    }

    for (Vertex<Integer> eachVertex : vertex.neighbors) {
        if (eachVertex.data > vertex.data) {
            if (hasStrictlyIncreasingPath(eachVertex, end, visited)) {
                return true;
            }
        }
    }
    return false;
}
}
