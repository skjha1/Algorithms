package graph;

import java.util.PriorityQueue;

/**
 * Implementation of Prim's algorithm to find Minimum Spanning Tree
 * Time Complexity: O(ElogE)
 *
 * @author Narendra Jha, njha.sde@gmail.com
 *
 */
public class PrimsMst extends Graph {
    
    private int n, m, edgeCount;
    private boolean[] visited;
    private Edge[] mst;
    private double cost;
    private PriorityQueue<Edge> pq;

    public PrimsMst(int numberOfVertices) {
        super(numberOfVertices);
        n = numberOfVertices;
    }
    
    public Double mst(int s) {
        m = n - 1; // number of expected edges in mst
        edgeCount = 0;
        mst = new Edge[m];
        visited = new boolean[n];
        pq = new PriorityQueue<>();        
        
        addEdges(s);
        
        while (!pq.isEmpty() && edgeCount != m) {
            Edge edge = pq.poll();
            int nodeIndex = edge.to;
            
            // skip edges pointing to already visited nodes
            if (visited[nodeIndex]) continue;
            
            mst[edgeCount] = edge;
            edgeCount++;
            cost += edge.wt;
            
            addEdges(nodeIndex);
        }
        if (edgeCount != m) return null;
        
        return cost;
    }

    private void addEdges(int index) {
        visited[index] = true;
        for (Edge edge : adjList.get(index))  {
            if (visited[edge.to]) continue;
            pq.add(edge);
        }
    }
    
    public static void main(String[] args) {
        //example1();
        //example2();
        //example3();
        example4();
    }
    
    // example 1 from notes
    private static void example1() {
        int n = 7;
        PrimsMst graph = new PrimsMst(n);
        
        graph.addEdge(0, 1, true, 9);
        graph.addEdge(0, 2, true, 0);
        graph.addEdge(0, 3, true, 5);
        graph.addEdge(0, 5, true, 7);
        graph.addEdge(1, 3, true, -2);
        graph.addEdge(1, 4, true, 3);
        graph.addEdge(1, 6, true, 4);
        graph.addEdge(2, 5, true, 6);
        graph.addEdge(3, 5, true, 2);
        graph.addEdge(3, 6, true, 3);
        graph.addEdge(4, 6, true, 6);
        graph.addEdge(5, 6, true, 1);
        
        int s = 0;
        Double cost = graph.mst(s);
        if (cost != null) {
            System.out.println(cost); // 9.0
            for (Edge e : graph.mst)
                System.out.println(String.format("Used edge (%d, %d) with cost: %.2f", e.from, e.to, e.wt));
            /*
             * Used edge (0, 2) with cost: 0.00
             * Used edge (0, 3) with cost: 5.00
             * Used edge (3, 1) with cost: -2.00
             * Used edge (3, 5) with cost: 2.00
             * Used edge (5, 6) with cost: 1.00
             * Used edge (1, 4) with cost: 3.00
             */
        }
        else {
            System.out.println("MST not found!");
        }
    }

    // example from kruskal's
    private static void example2() {
        int n = 10;
        PrimsMst graph = new PrimsMst(n);
        
        graph.addEdge(0, 1, true, 5);
        graph.addEdge(1, 2, true, 4);
        graph.addEdge(2, 9, true, 2);
        graph.addEdge(0, 4, true, 1);
        graph.addEdge(0, 3, true, 4);
        graph.addEdge(1, 3, true, 2);
        graph.addEdge(2, 7, true, 4);
        graph.addEdge(2, 8, true, 1);
        graph.addEdge(9, 8, true, 0);
        graph.addEdge(4, 5, true, 1);
        graph.addEdge(5, 6, true, 7);
        graph.addEdge(6, 8, true, 4);
        graph.addEdge(4, 3, true, 2);
        graph.addEdge(5, 3, true, 5);
        graph.addEdge(3, 6, true, 11);
        graph.addEdge(6, 7, true, 1);
        graph.addEdge(3, 7, true, 2);
        graph.addEdge(7, 8, true, 6);
        
        int s = 0;
        Double cost = graph.mst(s);
        if (cost != null) {
            System.out.println(cost); // 14.0
            for (Edge e : graph.mst)
                System.out.println(String.format("Used edge (%d, %d) with cost: %.2f", e.from, e.to, e.wt));
            /*
             * Used edge (9, 8) with cost: 0.00
             * Used edge (0, 4) with cost: 1.00
             * Used edge (2, 8) with cost: 1.00
             * Used edge (4, 5) with cost: 1.00
             * Used edge (6, 7) with cost: 1.00
             * Used edge (1, 3) with cost: 2.00
             * Used edge (3, 7) with cost: 2.00
             * Used edge (4, 3) with cost: 2.00
             * Used edge (1, 2) with cost: 4.00
             */
        }
        else {
            System.out.println("MST not found!");
        }
    }
    
    // example 3 from notes
    private static void example3() {
        int n = 9;
        PrimsMst graph = new PrimsMst(n);
        
        graph.addEdge(0, 1, true, 6);
        graph.addEdge(0, 3, true, 3);
        graph.addEdge(1, 2, true, 4);
        graph.addEdge(1, 4, true, 2);
        graph.addEdge(2, 5, true, 12);
        graph.addEdge(3, 4, true, 1);
        graph.addEdge(3, 6, true, 8);
        graph.addEdge(4, 5, true, 7);
        graph.addEdge(4, 7, true, 9);
        graph.addEdge(5, 8, true, 10);
        graph.addEdge(6, 7, true, 11);
        graph.addEdge(7, 8, true, 5);
        
        int s = 0;
        Double cost = graph.mst(s);
        if (cost != null) {
            System.out.println(cost); // 39.0
            for (Edge e : graph.mst)
                System.out.println(String.format("Used edge (%d, %d) with cost: %.2f", e.from, e.to, e.wt));
            /*
             * Used edge (0, 3) with cost: 3.00
             * Used edge (3, 4) with cost: 1.00
             * Used edge (4, 1) with cost: 2.00
             * Used edge (1, 2) with cost: 4.00
             * Used edge (4, 5) with cost: 7.00
             * Used edge (3, 6) with cost: 8.00
             * Used edge (4, 7) with cost: 9.00
             * Used edge (7, 8) with cost: 5.00
             */
        }
        else {
            System.out.println("MST not found!");
        }
    }
    
    // example used in demonstration from notes
    private static void example4() {
        int n = 8;
        PrimsMst graph = new PrimsMst(n);
        
        graph.addEdge(0, 1, true, 10);
        graph.addEdge(0, 2, true, 1);
        graph.addEdge(0, 3, true, 4);
        graph.addEdge(2, 1, true, 3);
        graph.addEdge(2, 5, true, 8);
        graph.addEdge(2, 3, true, 2);
        graph.addEdge(3, 5, true, 2);
        graph.addEdge(3, 6, true, 7);
        graph.addEdge(5, 4, true, 1);
        graph.addEdge(5, 7, true, 9);
        graph.addEdge(5, 6, true, 6);
        graph.addEdge(4, 1, true, 0);
        graph.addEdge(4, 7, true, 8);
        graph.addEdge(6, 7, true, 12);
        
        int s = 0;
        Double cost = graph.mst(s);
        if (cost != null) {
            System.out.println(cost); // 20.0
            for (Edge e : graph.mst)
                System.out.println(String.format("Used edge (%d, %d) with cost: %.2f", e.from, e.to, e.wt));
            /*
             * Used edge (0, 2) with cost: 1.00
             * Used edge (2, 3) with cost: 2.00
             * Used edge (3, 5) with cost: 2.00
             * Used edge (5, 4) with cost: 1.00
             * Used edge (4, 1) with cost: 0.00
             * Used edge (5, 6) with cost: 6.00
             * Used edge (4, 7) with cost: 8.00
             */
        }
        else {
            System.out.println("MST not found!");
        }
    }

}
