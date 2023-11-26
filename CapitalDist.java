
public class CapitalDist {
    
    private String numa;
    private String ida;
    private String numb;
    private String idb;
    private int kmdist;
    private int midist;
    
    public CapitalDist(String line)
    {
        String []stateInfoArray = line.split(",");

        numa = stateInfoArray[0].strip();
        ida = stateInfoArray[1].strip();
        numb = stateInfoArray[2].strip();
        idb = stateInfoArray[3].strip();
        kmdist = Integer.parseInt(stateInfoArray[4].strip());
        midist = Integer.parseInt(stateInfoArray[5].strip());
    }
    
    public String getNumA()
    {
        return numa;
    }
    
    public String getIdA()
    {
        return ida;
    }

    public String getNumB()
    {
        return numb;
    }
    
    public String getIdB()
    {
        return idb;
    }

    public int getKmDist()
    {
        return kmdist;
    }

    public int getMiDist()
    {
        return midist;
    }

}
