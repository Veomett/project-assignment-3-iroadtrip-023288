
public class BorderInfo {
    
    private String countryName1;
    private String countryName2;
    private double dist;
    
    
    public BorderInfo(String country_name1, String country_name2, double d)
    {
        countryName1 = country_name1;
        countryName2 = country_name2;        
        dist = d;
    }
    
    public String getCountryName1()
    {
        return countryName1;
    }
    
    public String getCountryName2()
    {
        return countryName2;
    }
    
    public double getDist()
    {
        return dist;
    }
    
    public static LinkedList<BorderInfo> parseBorderInfoList(String line) {
        
        
        LinkedList<BorderInfo> borderInfoList = new LinkedList<BorderInfo>();
        
        String []keyValuePairStringArray = line.split("=");
        String countryName1 = keyValuePairStringArray[0].strip();
        
        if (keyValuePairStringArray[1].strip().length() <= 0) {
            
            return borderInfoList;
        }
        String []borderInfoStringArray = keyValuePairStringArray[1].strip().split(";");

        for(int loop = 0; loop < borderInfoStringArray.length; loop++)
        {
            String borderInfoString = borderInfoStringArray[loop].strip();
            borderInfoList.add(parseBorderInfo(countryName1, borderInfoString));
        }
        
        return borderInfoList;
    }
    
    
    public static BorderInfo parseBorderInfo(String countryName1, String borderInfoString)
    {
        int distDigitStart = findStartDigitIndex(borderInfoString);
        
        String countryName2 = borderInfoString.substring(0, distDigitStart).strip();
        String distStart = borderInfoString.substring(distDigitStart);
        
        int distDigitEnd = distStart.indexOf("km");
        
        String distString = distStart.substring(0, distDigitEnd).strip();
        double dist = parseDist(distString);

        return new BorderInfo(countryName1, countryName2, dist);
    }
    
    public static double parseDist(String distString)
    {
        int fractionPos = 0;
        double dist = 0.0;
        String integerPartString = "";
        String fractionPartString = "";
        
        fractionPos = distString.indexOf(".");
        if (fractionPos >= 0) {
            integerPartString = distString.substring(0, fractionPos);
            fractionPartString = "0" + distString.substring(fractionPos);

        }
        else {
            integerPartString = distString;
        }
        
        if (integerPartString.indexOf(",") >= 0) {
            integerPartString = integerPartString.replaceAll(",", "");
        }
        
        dist = dist + Integer.parseInt(integerPartString);
        if (fractionPartString.length() > 0) {
            dist = dist + Double.parseDouble(fractionPartString);
        }

        return dist;
    }
    

    public static int findStartDigitIndex(String s)
    {
        char c = '0';
        
        for(int loop = 0; loop < s.length(); loop++)
        {
            c = s.charAt(loop);
            if ((c >= '0') && (c <= '9')) {
                return loop;
            }
        }
        return -1;
    }
    
    

}
