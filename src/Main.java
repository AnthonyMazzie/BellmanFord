import java.util.*;

public class Main {

    public static class Node {
        private final int nodeID;
        private final ArrayList<Node> parentList;
        private int distance;

        public Node(int nodeID) {
            this.nodeID = nodeID;
            parentList = new ArrayList<>();
            distance = Integer.MAX_VALUE;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }
    }

    public static class Edge {

        private final Node source;
        private final Node destination;
        private final int weight;

        public Edge(Node source, Node destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }


    /* Generate path recursively */
    static void recursiveGeneratePathAndPrint(Node destinationNode) {
        ArrayList<Integer> pathList = new ArrayList<>();
        pathList.add(destinationNode.nodeID);
        int distanceDestination = destinationNode.distance;
        while (destinationNode.parentList.size() != 0) {
            pathList.add(destinationNode.parentList.get(0).nodeID);
            destinationNode = destinationNode.parentList.get(0);
        }
        Collections.reverse(pathList);
        System.out.println("Path (" + pathList.get(0) + " -> " + pathList.get(pathList.size() - 1) + "): " + pathList + ", Distance = " + distanceDestination);
    }

    /* Bellman–Ford algorithm. Prints the shortest path and distance from source to destination  */
    static void bellmanFordAlgorithm(List<Edge> edgeList, int quantityNodes, int destination) {

        Node destinationNodeForPath = null;

        /* Relaxation step - runs one less than the quantity of nodes in graph */
        for (int i = 1; i < quantityNodes; i++) {
            for (Edge edge : edgeList) {
                if (edge.source.distance != Integer.MAX_VALUE && (edge.source.distance + edge.weight < edge.destination.distance)) {

                    /* Update distance of node traveling to because new shortest path found */
                    edge.destination.setDistance((edge.source.distance + edge.weight));

                    /* Remove all parent distances with cost greater than new shortest distance */
                    edge.destination.parentList.clear();

                    /* Add new parent with the shortest distance */
                    edge.destination.parentList.add(edge.source);

                    /* New shortest distance to destination Node found */
                    if (edge.destination.nodeID == destination) {
                        destinationNodeForPath = edge.destination;
                    }

                } else if (edge.source.distance != Integer.MAX_VALUE && (edge.source.distance + edge.weight == edge.destination.distance)) {

                    /* Add this parent only if it is not already a parent */
                    if (!edge.destination.parentList.contains(edge.source)) {
                        edge.destination.parentList.add(edge.source);
                    }
                }
            }
        }

        /* Check for negative-weight cycles */
        for (int i = 1; i < quantityNodes; i++) {
            /* For all edges */
            for (Edge edge : edgeList) {
                /* Check if distance to destination is shortened by taking edge (u, v) to find negative-weight cycles */
                if (edge.source.distance != Integer.MAX_VALUE && (edge.source.distance + edge.weight < edge.destination.distance)) {
                    System.out.println("Negative-weight cycle found");
                }
            }
        }

        /* If a path from source to destination exists, print it */
        if (destinationNodeForPath != null) {
            recursiveGeneratePathAndPrint(destinationNodeForPath);
        } else {
            System.out.println("There is no path from source to destination");
        }

    } /* END - bellmanFordAlgorithm */

    public static void main(String[] args) {

        /* Initialize Nodes */
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n9 = new Node(9);
        Node n10 = new Node(10);
        Node n11 = new Node(11);
        Node n12 = new Node(12);
        Node n13 = new Node(13);
        Node n14 = new Node(14);

        /* Choose source node and set distance to 0 */
        n14.setDistance(0);

        /* Choose destinationNode */
        int destinationNode = 1;

        /* Set quantity nodes */
        int quantityNodes = 15;

        /* Initialize edgeList and add Edges */
        List<Edge> edgeList = new ArrayList<>();
        edgeList.add(new Edge(n0, n1, 1));
        edgeList.add(new Edge(n0, n2, 2));
        edgeList.add(new Edge(n0, n3, 1));
        edgeList.add(new Edge(n0, n4, 2));
        edgeList.add(new Edge(n0, n5, 4));
        edgeList.add(new Edge(n1, n2, 1));
        edgeList.add(new Edge(n1, n3, 5));
        edgeList.add(new Edge(n2, n4, -2));
        edgeList.add(new Edge(n2, n6, -3));
        edgeList.add(new Edge(n2, n7, 3));
        edgeList.add(new Edge(n3, n5, -2));
        edgeList.add(new Edge(n4, n6, 10));
        edgeList.add(new Edge(n5, n4, -2));
        edgeList.add(new Edge(n5, n8, 3));
        edgeList.add(new Edge(n5, n9, 3));
        edgeList.add(new Edge(n5, n10, 4));
        edgeList.add(new Edge(n6, n7, 4));
        edgeList.add(new Edge(n6, n11, -2));
        edgeList.add(new Edge(n6, n12, -1));
        edgeList.add(new Edge(n7, n1, -1));
        edgeList.add(new Edge(n7, n11, 5));
        edgeList.add(new Edge(n8, n10, 1));
        edgeList.add(new Edge(n9, n10, 1));
        edgeList.add(new Edge(n9, n13, -1));
        edgeList.add(new Edge(n10, n14, 5));
        edgeList.add(new Edge(n11, n12, 6));
        edgeList.add(new Edge(n12, n13, 4));
        edgeList.add(new Edge(n13, n5, -1));
        edgeList.add(new Edge(n13, n6, -2));
        edgeList.add(new Edge(n14, n3, -2));

        bellmanFordAlgorithm(edgeList, quantityNodes, destinationNode);
    }
}