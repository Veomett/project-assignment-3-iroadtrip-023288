
public class ArrayList<T> implements List<T> {

    private T[] arr;
    private int length;
    private int capacity;

    public ArrayList() {
        arr = null;
        length = 0;
        capacity = 0;
    }
    
    @SuppressWarnings("unchecked")
    public ArrayList(int c)
    {
        arr = (T[]) new Object[c];
        length = 0;
        capacity = c;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public T get(int pos) {
        if (pos < 0 || pos >= length)
        {
            return null;
        }
        return arr[pos];
    }
    
    public void set(int pos, T value) {
        if (pos < 0 || pos >= length)
        {
            return;
        }
        arr[pos] = value;
    }

    @Override
    public boolean add(T item) {
        if (capacity == length)
        {
            grow_array();
        }
        arr[length++] = item;
        return true;
    }

    private void grow_array() {

        if (0 == capacity) {
            capacity = 1;
        }
        else {
            capacity = capacity * 2;
        }
        @SuppressWarnings("unchecked")
        T[] new_arr = (T[]) new Object[capacity];
        for (int i = 0; i < length; i++) {
            new_arr[i] = arr[i];
        }
        arr = new_arr;
    }

    @Override
    public void add(int pos, T item) throws Exception {
        if (pos < 0 || pos > length)
            throw new Exception("Illegal position");
        if (capacity == length)
        {
            grow_array();
        }
        for (int i = length; i > pos; i--) {
            arr[i+1] = arr[i];
        }
        ++length;
    }

    @Override
    public T remove(int pos) throws Exception {
        if (pos < 0 || pos > length)
            throw new Exception("Illegal position");
        T temp = arr[pos];
        for (int i = pos; i < length - 1; i++) {
            arr[pos] = arr[pos+1];
        }
        --length;
        return temp;
    }
}
