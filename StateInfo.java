import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StateInfo {
    
    private String statenumber;
    private String stateid;
    private CountryName countryname;
    private String start;
    private String end;
    
    
    public StateInfo(String line)
    {
        String []stateInfoArray = line.split("\t");

        statenumber = stateInfoArray[0].strip();
        stateid = stateInfoArray[1].strip();
        countryname = new CountryName(stateInfoArray[2].strip());
        start = stateInfoArray[3].strip();
        end = stateInfoArray[4].strip();
    }
        
    public String getStateNumber() {
        return statenumber;
    }
    
    public String getStateId() {
        return stateid;
    }
    
    public CountryName getCountryName() {
        return countryname;
    }
    
    public String getStart() {
        return start;
    }
    
    public String getEnd() {
        return end;
    }
 
    public int compare(StateInfo stateInfo)
    {
        long timestamp1 = DataStringToTime(end);
        long timestamp2 = DataStringToTime(stateInfo.getEnd());
        
        if (timestamp1 < timestamp2) {
            return -1;
        }
        else if (timestamp1 > timestamp2) {
            return 1;
        }
        
        return 0;
    }
    
    public boolean isTheSameCountryName(String name)
    {
        if (countryname.getFullCountryName().equals(name))
        {
            return true;
        }
        if (name.equals(stateid))
        {
            return true;
        }

        return false;
    }

    public boolean isSimilarCountryName(String name)
    {
        if (countryname.equals(name))
        {
            return true;
        }
        if (CountryName.equalsCountryName(name, stateid))
        {
            return true;
        }

        return false;
    }


    private long DataStringToTime(String s)
    {
        long timestamp = -1;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(s);
            timestamp = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return timestamp;
    }
}
