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
    Set<Vertex<T>> visted = new HashSet<>();
    printVertexVals(vertex, visted);

  }
  private <T> void printVertexVals(Vertex<T> vertex, Set<Vertex<T>> visted){
    if(vertex==null||visted.contains(vertex)) return;
    visted.add(vertex);
    System.out.println(vertex.data);

    for(var neighbor: vertex.neighbors){
      printVertexVals(neighbor, visted);
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
    if(vertex==null)return visted;
   return reachable(vertex, visted);
  }
  private <T> Set<Vertex<T>> reachable(Vertex<T> vertex, Set<Vertex<T>> visted){
      if(vertex==null||visted.contains(vertex))return null;
    visted.add(vertex);
    for(var neighbor:vertex.neighbors){
      reachable(neighbor, visted);
    }
    return visted;

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
    if(vertex==null) return Integer.MIN_VALUE;
    Set<Vertex<Integer>> visted = new HashSet<>();
    return max(vertex,visted);
  }

  private int max(Vertex<Integer> vertex, Set<Vertex<Integer>> visted){
    if(vertex==null||visted.contains(vertex)) return Integer.MIN_VALUE;
      visted.add(vertex);
    int higherValue=vertex.data;  
    for(var neighbor: vertex.neighbors){
      higherValue= Math.max(higherValue, max(neighbor, visted));
    }
    return higherValue;
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
    Set<Vertex<T>> allLeaves = new HashSet<>();
    Set<Vertex<T>> visted = new HashSet<>();

    if(vertex==null) return allLeaves;

    return leaves(vertex,allLeaves,visted);

    
  }

  private <T> Set<Vertex<T>> leaves(Vertex<T> vertex, Set<Vertex<T>> allLeaves, Set<Vertex<T>> visted){
    if(vertex==null||visted.contains(vertex)) return null;
    if(vertex.neighbors==null|| vertex.neighbors.isEmpty()){
      allLeaves.add(vertex);
    }
    visted.add(vertex);
    for(var neighbor: vertex.neighbors){

     leaves(neighbor,allLeaves,visted);
    }
    return  allLeaves;
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
    Set<Vertex<Integer>> visted = new HashSet<>();

    return allOdd(vertex,visted);
    
  }

  private boolean allOdd(Vertex<Integer> vertex, Set<Vertex<Integer>> visted){
  
    if(vertex!=null&&!visted.contains(vertex)) {
      if(vertex.data%2==0) return false;
      visted.add(vertex);
      for(var neighbor: vertex.neighbors){
       if( !allOdd(neighbor,visted)) return false;
      }
    }

    return  true;
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
    Set<Vertex<Integer>> visted = new HashSet<>();
    for(var neighbor: start.neighbors){
     return  hasStrictlyIncreasingPath(start, neighbor, end, visted);
    }
    return false;
  }

  private  boolean hasStrictlyIncreasingPath(Vertex<Integer> prev, Vertex<Integer> current, Vertex<Integer> end, Set<Vertex<Integer>> visted){
      if((prev.data<current.data)&&!visted.contains(current)){
        visted.add(current);
        if(current.data==end.data)return true;
        for(var neighbor: current.neighbors){
          return hasStrictlyIncreasingPath(current, neighbor,end,visted);
        }
      }
      return false;
  }
}
