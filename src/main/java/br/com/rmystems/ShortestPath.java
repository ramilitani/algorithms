package br.com.rmystems;

public class ShortestPath {

    public int minDistance(int[] dist, boolean[] spt)
    {
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < dist.length; v++) {
            if (spt[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }
        return min_index;
    }

    public void dijkstra(int graph[][], int src)
    {

        int V = graph.length;
        int dist[] = new int[V];
        boolean sptSet[] = new boolean[V];

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[src] = 0;

        for (int i = 0; i < V-1; i++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (!sptSet[v] && graph[u][v] != 0 && dist[u]!= Integer.MAX_VALUE && dist[u]+graph[u][v] < dist[v]) {
                    dist[v] = dist[u]+graph[u][v];
                }
            }
        }

        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + "\t\t" + dist[i]);
        }
    }

    public static void main(String[] args)
    {
        int graph[][] = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };

        ShortestPath t = new ShortestPath();
        t.dijkstra(graph, 0);
    }
}
