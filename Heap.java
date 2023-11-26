


public class Heap {
    

    DistVertexPair []arr;
    private int length;    
    
    public Heap(int c)
    {
        arr = new DistVertexPair[c];
        length = 0;
    }
        
    public boolean empty()
    {
        return length <= 0;
    }
    
    public int size() {
        return length;
    }
    
    public int peek()
    {
        return arr[0].getVertex();
    }

    public void insert(int vertex, int dist) {
    
        arr[length++] = new DistVertexPair(dist, vertex);
        shiftUp(length -  1);
    }
    
    public int remove() {
        
        int item = arr[0].getVertex();
        arr[0] = arr[--length];
        shiftDown(0);

        return item;
    }

    public void changeDistance(int vertex, int dist)
    {
        for (int loop = 0; loop < length; loop++)
        {
            if (vertex == arr[loop].getVertex())
            {
                arr[loop].setDist(dist);;
                shiftUp(loop);
                break;
            }
        }
    }
    
    private void shiftUp(int pos)
    {
        int child = pos;
        while (child > 0 && (arr[child].getDist() < arr[parent(child)].getDist())) {
            swap(arr, parent(child), child);
            child = parent(child);
        }
    }
    
    private void shiftDown(int parent) {
        
        int smallest = 0;
        int left = 0;
        int right = 0;

        while (true)
        {
            smallest = parent;
            left = left(parent);
            right = right(parent);

            if (left < length && (arr[left].getDist() < arr[smallest].getDist()))
            {
                smallest = left;
            }
            if (right < length && (arr[right].getDist() < arr[smallest].getDist()))
            {
                smallest = right;
            }
            if (smallest != parent)
            {
                swap(arr, parent, smallest);
                parent = smallest;
            }
            else
            {
                break;
            }
        }

        
    }

    private int parent(int child) {
        return (child - 1) / 2;
    }
    
    private int left(int parent) {
        return parent * 2 + 1;
    }
    
    private int right(int parent) {
        return parent * 2 + 2;
    }

    private void swap(DistVertexPair[] list, int i, int j)
    {
        DistVertexPair temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }
}
