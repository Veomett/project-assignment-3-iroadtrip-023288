
public class CountryName {

    private String countryName;
    private String []countryNameArray;
    
    public CountryName(String s)
    {
        int secondCountryNameStart = -1;
        int secondCountryNameEnd = -1;
        String firstCountryName = "";
        String secondCountryName = "";
        
        countryName = s;        
        secondCountryNameStart = countryName.indexOf("(");
        if (secondCountryNameStart >= 0) {
            
            firstCountryName = countryName.substring(0, secondCountryNameStart).strip();
            
            secondCountryNameStart++;
            secondCountryName = countryName.substring(secondCountryNameStart);
            secondCountryNameEnd = secondCountryName.indexOf(")");
            secondCountryName = secondCountryName.substring(0, secondCountryNameEnd).strip();
        }
        if (secondCountryName.length() > 0) {
            
            countryNameArray = new String[2];
            countryNameArray[0] = firstCountryName;
            countryNameArray[1] = secondCountryName;
        }
        else {
            countryNameArray = new String[1];
            countryNameArray[0] = countryName.strip();            
        }
    }
    
    public String getFirstName() {
        return countryNameArray[0];
    }
    
    public String getFullCountryName()
    {
        return countryName;
    }
    
    @Override
    public boolean equals(Object o)
    {
        CountryName countryNameInst = null;
        
        countryNameInst = new CountryName((String)o);
        for(int i = 0; i < countryNameArray.length; i++)
        {
            for(int j = 0; j < countryNameInst.countryNameArray.length; j++)
            {
                if (equalsCountryName(countryNameArray[i], countryNameInst.countryNameArray[j])) {
                    return true;
                }
            }
        }
        
        return false;
    }

    public static boolean equalsCountryName(String countryName1, String countryName2)
    {
        String name1 = countryName1;
        String name2 = countryName2;

        if ((name1.indexOf("Cote") == 0)
            && (name2.indexOf("Cote") == 0)) {
            return true;
        }
        if ((name1.indexOf(name2) == 0)
            || (name2.indexOf(name1) == 0)) {
            return true;
        }
        
        return false;
    }
    

}
