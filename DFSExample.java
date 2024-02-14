import java.util.*;

class Graph {
    private Map<String, List<String>> graph;

    public Graph() {
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

public class DFSExample {
    // Depth-First Search algorithm
    public static List<String> dfs(Graph graph, String start, String goal, Set<String> visited, List<String> path) {
        if (visited == null) {
            visited = new HashSet<>();
        }
        if (path == null) {
            path = new ArrayList<>();
        }

        // Mark the current node as visited
        visited.add(start);
        path.add(start);

        // Check if the current node is the goal
        if (start.equals(goal)) {
            return path;
        }

        // Explore neighbors in a specific order
        for (String neighbor : graph.getGraph().get(start)) {
            if (!visited.contains(neighbor) && graph.getGraph().containsKey(neighbor)) {
                // Recursively perform DFS on unvisited neighbors
                List<String> newPath = dfs(graph, neighbor, goal, visited, path);
                if (newPath != null) {
                    return newPath;
                }
            }
        }

        return null; // No path found
    }

    public static void main(String[] args) {
        // Define the graph based on provided edges
        Graph graph = new Graph();
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

        // Perform DFS and print the path from 'S' to 'G'
        String startNode = "S";
        String goalNode = "G";
        Set<String> visited = new HashSet<>();
        List<String> path = new ArrayList<>();
        List<String> resultPath = dfs(graph, startNode, goalNode, visited, path);

        // Print the result
        if (resultPath != null) {
            System.out.println("Path from '" + startNode + "' to '" + goalNode + "': " + resultPath);
        } else {
            System.out.println("No path found from '" + startNode + "' to '" + goalNode + "'.");
        }
    }
}
