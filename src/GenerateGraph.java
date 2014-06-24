
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class GenerateGraph {

	/**
	 * Wrapper method to generate graph 
	 * @param rootLabel
	 * @param graph
	 * @return
	 */
	  public Node generateGraphWithRoot(String rootLabel, Map<String, List<String>> graph) {
		  Node rootNode = null;
		  GenerateGraph g = new GenerateGraph();
		  try {
			  rootNode = g.generateRootedGraph(rootLabel, graph);
		  } 
		  // catch (NullRootException | NullGraphException | EmptyRootException ex) {
		  //} 
		  catch (NullRootException ex) {
			  System.out.println("Root is null");
		  } catch (NullGraphException ex) {
			  System.out.println("Graph is null");
		  } catch (EmptyRootException ex) {
			  System.out.println("Root label is empty");
		  }
		  return rootNode; 
	  }

	/**
	 * Generates a graph structure of nodes from the given input adjacency list
	 * @param rootLabel: label for the root node
	 * @param graph: Mapping between node labels representing the adjacency list for the graph
	 * @return the root node
	 */
	private Node generateRootedGraph(String rootLabel, Map<String, List<String>> graph) 
					throws NullRootException, NullGraphException, EmptyRootException {
		if (rootLabel == null) {
			throw new NullRootException();
		}
		if (graph == null) {
			throw new NullGraphException();
		}

		rootLabel = rootLabel.trim(); // trim leading and trailing whitespaces from root label
		
		if (rootLabel.isEmpty()) {
			throw new EmptyRootException();
		}
		
		// Create set of nodes
		Set<Node> nodes = new HashSet<Node>();

		// Create root node with given label;
		Node rootNode = new Node(rootLabel);
		nodes.add(rootNode);
		
		// create nodes for given labels in the input and add to set of nodes
		for (String nodeLabel: graph.keySet()) {
			if (!nodeLabel.equalsIgnoreCase(rootLabel)) {
				nodes.add(new Node(nodeLabel));
			}
		}

		// Create edges between nodes
		for (Map.Entry<String, List<String>> entry: graph.entrySet()) {
			String nodeLabel = entry.getKey();
			try {
				// get the node with the given label
				Node node = getNode(nodes, nodeLabel);
				if (node != null) {
					List<String> nodeChildrenLabels = entry.getValue();
					for (String childLabel: nodeChildrenLabels) {
						Node childNode = getNode(nodes, childLabel);
						if (childNode == null) { // case when this node does not have an outgoing edge
							childNode = new Node(childLabel);
							nodes.add(childNode);
						}
						node.addChildNode(childNode);
					}	
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return rootNode;
	}
	
	/**
	 * Search for a node with a given label in the input set of nodes
	 * @param nodes: Set of nodes
	 * @param label: given label
	 * @return node with given label in the set (if found), otherwise null
	 */
	private Node getNode(Set<Node> nodes, String label) {
		// Iterate through the set of nodes to search for node with given label
		for (Node n: nodes) {
			if (n.getLabel().equalsIgnoreCase(label)) {
				return n;
			}
		}
		return null;
	}
	
}
