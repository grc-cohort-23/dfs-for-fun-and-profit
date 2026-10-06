import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
  public List<Vertex> vertices = new LinkedList();
  public <T> void printVertexVals(Vertex<T> vertex) {
    if(vertex == null) return;
    System.out.println(vertex.data);
    for(Vertex vert : vertex.neighbors){{
      if(!vertices.contains(vert)){
        vertices.add(vert);
        System.out.println(vert.data);
        printVertexVals(vert);
      }
    }}
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
  public Set reaching = new HashSet<>();
  public <T> Set<Vertex<T>> reachable(Vertex<T> vertex) {
    if(vertex == null){
      Set<Vertex<T>> veturn = new HashSet();
      return veturn;
    }
    reaching.add(vertex);
    for(Vertex n : vertex.neighbors){
      if(!reaching.contains(n)){
        reaching.add(n);
        reachable(n);
      }
    }
    return reaching;
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
  public int val = 0;
  public int max(Vertex<Integer> vertex) {
    if(vertex == null){
      return Integer.MIN_VALUE;
    }
    int currentMax = 0;
    for(Vertex<Integer> n : vertex.neighbors){
      if(!reaching.contains(n)){
        Set<Vertex<Integer>> result = reachable(n);
        for(Vertex<Integer> a : result){
          currentMax = Math.max(currentMax, a.data);
        }
      }
    }
    return currentMax;
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
  public Set leavesVisited = new HashSet<>();
  public Set leafNodes = new HashSet<>();
  public <T> Set<Vertex<T>> leaves(Vertex<T> vertex) {
    if(vertex == null){
      Set<Vertex<T>> veturn = new HashSet();
      return veturn;
    }
    leavesVisited.add(vertex);
    if(vertex.neighbors.isEmpty()) leafNodes.add(vertex);
    for(Vertex n : vertex.neighbors){
      if(n.neighbors.isEmpty()){
        leafNodes.add(n);
      }
      if(!reaching.contains(n)){
        reaching.add(n);
        leaves(n);
      }
    }
    return leafNodes;
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
  public Set oddVisited = new HashSet<>();
  public boolean allOdd(Vertex<Integer> vertex) {
    if(vertex == null) return true;
    for(Vertex n : vertex.neighbors){
      if(oddVisited.contains(n)){
        continue;
      } else oddVisited.add(n);
      if(allOdd(n) == false) return false;
    }
    return (vertex.data % 2 != 0);
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
    return false;
  }
}
