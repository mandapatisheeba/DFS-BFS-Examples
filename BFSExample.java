
import java.util.*;

class MyGraph {
    private Map<String, List<String>> graph;

    public MyGraph() {
        this.graph = new HashMap<>();
    }

    public void addEdge(String node, List<String> neighbors) {
        this.graph.put(node, neighbors);
    }

    // Getter method for accessing the graph
    public Map<String, List<String>> getGraph() {
        return this.graph;
    }
}

public class BFSExample {
    // Breadth-First Search algorithm
    public static List<String> bfs(MyGraph graph, String start, String goal) {
        Queue<List<String>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        // Enqueue the starting node
        queue.offer(Collections.singletonList(start));

        while (!queue.isEmpty()) {
            List<String> path = queue.poll();
            String currentNode = path.get(path.size() - 1);

            // Check if the current node is the goal
            if (currentNode.equals(goal)) {
                return path;
            }

            // Explore neighbors
            for (String neighbor : graph.getGraph().get(currentNode)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.offer(newPath);
                }
            }
        }

        return null; // No path found
    }

    public static void main(String[] args) {
        // Define the graph based on provided edges
        MyGraph graph = new MyGraph();
        graph.addEdge("S", Arrays.asList("B", "C"));
        graph.addEdge("B", Arrays.asList("D", "E"));
        graph.addEdge("C", Collections.singletonList("F"));
        graph.addEdge("D", Collections.singletonList("H"));
        graph.addEdge("E", Arrays.asList("G", "I"));
        graph.addEdge("F", Collections.singletonList("J"));
        graph.addEdge("G", Collections.emptyList());
        graph.addEdge("H", Collections.emptyList());
        graph.addEdge("I", Collections.emptyList());
        graph.addEdge("J", Collections.emptyList());

        // Perform BFS and print the path from 'S' to 'G'
        String startNode = "S";
        String goalNode = "G";
        List<String> resultPath = bfs(graph, startNode, goalNode);

        // Print the result
        if (resultPath != null) {
            System.out.println("Path from '" + startNode + "' to '" + goalNode + "': " + resultPath);
        } else {
            System.out.println("No path found from '" + startNode + "' to '" + goalNode + "'.");
        }
    }
}
