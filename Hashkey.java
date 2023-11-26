
public class Hashkey {
    
    public String name;
    
    public Hashkey(String n)
    {
        name = n;
    }
    
    public String getName () {
        return name;
    }
    
    @Override
    public int hashCode()
    {
        int c = 127; // Constant
        int hashed_key = 1;

        for (int i = 0; i < name.length(); i++)
        {
            hashed_key *= c;
            hashed_key += name.charAt(i); // c**p in here
        }
        
        return hashed_key;
    }
    
    @Override
    public boolean equals(Object o)
    {
        Hashkey hashkey = null;
        
        hashkey = (Hashkey)o;
        return name.equals(hashkey.getName());
    }
    

}
