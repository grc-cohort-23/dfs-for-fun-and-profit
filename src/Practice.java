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
    Set<Vertex<T>> set = new HashSet<>();

    printVertexVals(vertex,set);

  }

  private <T> void printVertexVals(Vertex<T> vertex, Set<Vertex<T>> set){
    if(vertex == null || set.contains(vertex))return;

    set.add(vertex);
    System.out.println(vertex.data);
    for(Vertex<T> neighbor : vertex.neighbors){
      printVertexVals(neighbor,set);
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
    if(vertex == null)return new HashSet<>();

    Set<Vertex<T>> set = new HashSet<>();

    reachable(vertex,set);

    return set;
  }

  private <T> void reachable(Vertex<T> vertex, Set<Vertex<T>> set){
    if(vertex == null || set.contains(vertex))return;
    set.add(vertex);
    for(Vertex<T> neighbor : vertex.neighbors){
      reachable(neighbor,set);
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
    Set<Vertex<Integer>> set = new HashSet<>();

    return max(vertex,set);
  }

  private int max(Vertex<Integer> vertex,Set<Vertex<Integer>> set){
    if(vertex == null || set.contains(vertex))return Integer.MIN_VALUE;
    set.add(vertex);
    
    int max = vertex.data;
    for(Vertex<Integer> neighbor : vertex.neighbors){
      max = Math.max(max, max(neighbor,set));
    }
    return max;
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
    if(vertex == null)return new HashSet<>();

    Set<Vertex<T>> set = new HashSet<>();
    Set<Vertex<T>> leaves = new HashSet<>();

    leaves(vertex,set);

    for(Vertex<T> neighbor : set){
      if(neighbor.neighbors.size() == 0) leaves.add(neighbor);
    }

    return leaves;
  }

  private <T> void leaves(Vertex<T> vertex, Set<Vertex<T>> set){
    if(vertex == null || set.contains(vertex))return;
    set.add(vertex);
    for(Vertex<T> neighbor : vertex.neighbors){
      leaves(neighbor,set);
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
    if(vertex == null)return true;

    Set<Vertex<Integer>> set = new HashSet<>();

    return allOdd(vertex,set);
  }

  private boolean allOdd(Vertex<Integer> vertex, Set<Vertex<Integer>> set){
    if(vertex == null || set.contains(vertex))return true;
    set.add(vertex);
    
    boolean allOdd = vertex.data % 2 != 0;

    for(Vertex<Integer> neighbor : vertex.neighbors){
      allOdd = allOdd && allOdd(neighbor,set);
    }
    return allOdd;
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
    if(start == null || end == null) throw new NullPointerException();

    Stack<Vertex<Integer>> stack = new Stack<>();
    stack.add(start);

    Set<Vertex<Integer>> set = new HashSet<>();


    while(!stack.isEmpty()){
      Vertex<Integer> current = stack.pop();
      if(set.contains(current)) continue;
      if(current == end) return true;

      for(Vertex<Integer> neighbor : current.neighbors){
        if(neighbor.data > current.data){
          stack.add(neighbor);
        }
      }
    }
    return false;
  }
}
