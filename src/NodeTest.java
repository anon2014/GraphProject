import static org.junit.Assert.*;

import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

import org.junit.Test;

public class NodeTest {
	
	  @Test
	  public void testWithoutLoops1() {
		  System.out.println();
		  System.out.println("Test case testWithoutLoops1:");
		  
		  // Construct a graph without loops
		  Map<String, List<String>> graph = new HashMap<String, List<String>>();
		  graph.put("a", Arrays.asList(new String [] {"b","c","e"}));
		  graph.put("b", Arrays.asList(new String [] {"d"}));
		  graph.put("c", Arrays.asList(new String[]{"d"}));
		  graph.put("d", Arrays.asList(new String[]{"e"}));
		  GenerateGraph g = new GenerateGraph();
		  Node rootNode = g.generateGraphWithRoot("a", graph);

		  // find maximum path length and most distant node from root node a
		  int maxPathLength = PathFinder.findMaxPathLength(rootNode);
		  assertEquals(maxPathLength, 3);
	  }
	  
	  @Test
	  public void testWithoutLoops2() {
		  System.out.println();
		  System.out.println("Test case testWithoutLoops2:");

		 // Construct a graph without loops
		 Map<String, List<String>> graph = new HashMap<String, List<String>>();
		 graph.put("a", Arrays.asList(new String [] {"b","c","e"}));
		 graph.put("b", Arrays.asList(new String [] {"d"}));
		 graph.put("c", Arrays.asList(new String[]{"d"}));
		 graph.put("d", Arrays.asList(new String[]{"e"}));
		 graph.put("e", Arrays.asList(new String[]{"f"}));
		 GenerateGraph g = new GenerateGraph();
		 Node rootNode = g.generateGraphWithRoot("a", graph);
		 
		 // find maximum path length and most distant node from root node a
		 int maxPathLength = PathFinder.findMaxPathLength(rootNode);
		 assertEquals(maxPathLength, 4);
	  }
	 
	  @Test 
	  public void testWithLoops1() {
		  System.out.println();
		  System.out.println("Test case testWithLoops1:");

		  // Construct a graph with loop(s)
		  Map<String, List<String>> graph = new HashMap<String, List<String>>();
		  graph.put("a", Arrays.asList(new String [] {"b","c","e"}));
		  graph.put("b", Arrays.asList(new String [] {"d"}));
		  graph.put("c", Arrays.asList(new String[]{"d"}));
		  graph.put("d", Arrays.asList(new String[]{"e"}));
		  graph.put("e", Arrays.asList(new String[]{"b"})); // adding loop to graph
		  GenerateGraph g = new GenerateGraph();
		  Node rootNode = g.generateGraphWithRoot("a", graph);
		  
		  // find maximum path length and most distant node from root node a
		  int maxPathLength = PathFinder.findMaxPathLength(rootNode);
		  assertEquals(maxPathLength, -1);
	  }

	  @Test 
	  public void testWithLoops2() {
		  System.out.println();
		  System.out.println("Test case testWithLoops2:");

		  // Construct a graph with loop(s)
		  Map<String, List<String>> graph = new HashMap<String, List<String>>();
		  graph.put("a", Arrays.asList(new String [] {"b","c","e"}));
		  graph.put("b", Arrays.asList(new String [] {"d"}));
		  graph.put("c", Arrays.asList(new String[]{"d"}));
		  graph.put("d", Arrays.asList(new String[]{"b", "e"})); // adding loop tp graph
		  GenerateGraph g = new GenerateGraph();
		  Node rootNode = g.generateGraphWithRoot("a", graph);
		         
		  // find maximum path length and most distant node from root node a
		  int maxPathLength = PathFinder.findMaxPathLength(rootNode);
		  assertEquals(maxPathLength, -1);
	  }
	  
	  @Test 
	  public void testWithLoops3() {
		  System.out.println();
		  System.out.println("Test case testWithLoops3:");
		  
		  // Construct a graph with loop(s)
		  Map<String, List<String>> graph = new HashMap<String, List<String>>();
		  graph.put("a", Arrays.asList(new String [] {"b","c","e"}));
		  graph.put("b", Arrays.asList(new String [] {"b", "d"})); // adding self-loop to node b in graph
		  graph.put("c", Arrays.asList(new String[]{"d"}));
		  graph.put("d", Arrays.asList(new String[]{"e"}));
		  GenerateGraph g = new GenerateGraph();
		  Node rootNode = g.generateGraphWithRoot("a", graph);
		  
		  // find maximum path length and most distant node from root node a
		  int maxPathLength = PathFinder.findMaxPathLength(rootNode);
		  assertEquals(maxPathLength, -1);
	  }
	  
	  @Test
	  public void testWithErroreousInput() {
		  System.out.println();
		  System.out.println("Test case testWithErroreousInput:");

		  // Test cases for erroneous input
		  
		  Map<String, List<String>> graph = new HashMap<String, List<String>>();
		  GenerateGraph g = new GenerateGraph();
		  
		  // root is null
		  Node rootNode = g.generateGraphWithRoot(null, graph);
		  int maxPathLength = PathFinder.findMaxPathLength(rootNode);
		  assertEquals(maxPathLength, -2);

		  // input graph is null
		  rootNode = g.generateGraphWithRoot(" a ", null);
		  maxPathLength = PathFinder.findMaxPathLength(rootNode);
		  assertEquals(maxPathLength, -2);
		  
		  // both root and graph are null
		  rootNode = g.generateGraphWithRoot(null, null);
		  maxPathLength = PathFinder.findMaxPathLength(rootNode);
		  assertEquals(maxPathLength, -2);

		  // root is an empty string
		  rootNode = g.generateGraphWithRoot("", graph);
		  maxPathLength = PathFinder.findMaxPathLength(rootNode);
		  assertEquals(maxPathLength, -2);

		  // root is a blank string
		  rootNode = g.generateGraphWithRoot(" ", graph);
		  maxPathLength = PathFinder.findMaxPathLength(rootNode);
		  assertEquals(maxPathLength, -2);
	  }

}
