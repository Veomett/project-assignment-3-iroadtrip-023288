

public class Graph {

    private int vertices;
    private int[][] distance;
    private LinkedList<Integer> [] adjacencyList;
    private final int INFINITE = 0x7FFFFFFF;

    @SuppressWarnings("unchecked")
    public Graph(int n, int [][]dist)
    {
        vertices = n;
        distance = dist;

        adjacencyList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++)
        {
            adjacencyList[i] = new LinkedList<Integer>();
        }
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if ((i != j) && (distance[i][j] > 0)) {
                    adjacencyList[i].add(Integer.valueOf(j));
                }
            }
        }
    }
    
    public int getDistance(int source, int destination)
    {
        return distance[source][destination];
    }
    
    public List<Integer> dijkstra(int source, int destination)
    {        
        int infinite = 0;
        @SuppressWarnings("unchecked")
        List<Integer> []pathList = new List[vertices];
        int []dist = new int[vertices];
        Heap priority_queue = new Heap(vertices);        

        infinite = INFINITE - (vertices + 5);
        for (int loop = 0; loop < vertices; loop++)
        {
            if (source == loop)
            {
                dist[loop] = 0;
            }
            else
            {
                dist[loop] = INFINITE - loop;
            }
            priority_queue.insert(loop, dist[loop]);
            pathList[loop] = new ArrayList<Integer>();
        }
        
        while (!priority_queue.empty()) {
            
            int i = priority_queue.remove();
            if (dist[i] >= infinite) {
                continue;
            }

            LinkedList<Integer>.Link currJ = adjacencyList[i].getFirstLink();        
            for(int loop = 0; loop < adjacencyList[i].size(); loop++)
            {
                int j = currJ.element().intValue();
                
                if (dist[i] + distance[i][j] < dist[j]) {
                    dist[j] = dist[i] + distance[i][j];
                    pathList[j] = concatPath(pathList[i], j);
                    priority_queue.changeDistance(j, dist[j]);
                }                
                currJ = currJ.next();
            }    
        }
        
        return pathList[destination];
    }
    

    private List<Integer> concatPath(List<Integer> oldPath, int vertex)
    {
        List<Integer> newPath = new ArrayList<Integer>(oldPath.size() + 1);
        
        for(int loop = 0; loop < oldPath.size(); loop++)
        {
            newPath.add(oldPath.get(loop));
        }
        newPath.add(Integer.valueOf(vertex));
        
        return newPath;
    }
    
    
}
