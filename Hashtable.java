
public class Hashtable<K, V> {    
    
    class HashNode {
        K key;
        V value;
        HashNode next;
        
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
 // Storage for items
    private ArrayList<HashNode> bucketArray;
    // Slots / current capacity of the ArrayList
    private int numBuckets;
    // Number of items stored in Hashtable
    private int size;
    // Beyond this ratio (items / capacity), resize
    private static final float LAMBDA = 0.75f;
    
    private int []bucketCapacityList;
    
 // Default constructor. May be useful to implement
    // a constructor with ArrayList size as parameter
    public Hashtable (int s) {
        
        int index = 0;
        
        bucketCapacityList = new int[] {
            17, 37, 79, 163, 331,
            673, 1361, 2729, 5471, 10949,
            21911, 43853, 87719, 175447, 350899,
            701819, 1403641, 2807303, 5614657, 11229331,
            22458671, 44917381, 89834777, 179669557, 359339171,
            718678369, 1437356741, 2147483647
        };
        
        while (s >= bucketCapacityList[index] * LAMBDA) {
            index++;
        }
        numBuckets = bucketCapacityList[index];
        bucketArray = new ArrayList<HashNode>();
        // Create empty chains
        for (int i = 0; i < numBuckets; i++)
        {
            bucketArray.add(null);
        }
        
        size = 0;
    }
    
    public void add(K key, V value)
    {
        int bucketIndex = getBucketIndex(key);
        HashNode hashNode = new HashNode(key, value);
        hashNode.next = bucketArray.get(bucketIndex);
        bucketArray.set(bucketIndex, hashNode);
        
        size ++;
        /*
        if (null != hashNode.next) {
            System.out.println("" + bucketIndex);
        }
        */
    }

    public V get (K key) {

        // Find the head of the chain
        int index = getBucketIndex(key);
        HashNode head = bucketArray.get(index);
        
        // Search the chain for the item with key
        while (head != null) {
            if (head.key.equals(key))
            {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }
    
    // Useful to ensure key always returns a valid
    // array position.
    private int getBucketIndex (K key) {

        // Call Java's hashCode... or another function 
        int hashCode = key.hashCode();
        // Every now and again, hashCode returns
        // a negative value 
        hashCode = Math.abs(hashCode);
        int index = hashCode % numBuckets;
        return index;
    }
    
}


