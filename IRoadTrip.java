import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;



public class IRoadTrip {
    
    private Hashtable<Hashkey, Integer> stateIdToIdHashtable;
    private ArrayList<StateInfo> idToStateInfoList;
    private LinkedList<StateInfo> stateInfoList;
    private Graph graph;
    
    public IRoadTrip (String [] args) {
        // Replace with your code

        int vertices = 0;
        stateInfoList = readStateInfoList();
        LinkedList<CapitalDist> capitalDistList = readCapitalDistList();
        
        LinkedList<StateInfo>.Link currStateInfo = stateInfoList.getFirstLink();        
        stateIdToIdHashtable = new Hashtable<Hashkey, Integer>(stateInfoList.size());
        idToStateInfoList = new ArrayList<StateInfo>(stateInfoList.size());
        for (int loop = 0; loop < stateInfoList.size(); loop++) {
            StateInfo stateInfo = currStateInfo.element();
            Integer id = stateIdToIdHashtable.get(new Hashkey(stateInfo.getStateId()));
            if (null == id) {
                stateIdToIdHashtable.add(new Hashkey(stateInfo.getStateId()), Integer.valueOf(vertices++));                
                idToStateInfoList.add(stateInfo);
            }
            else if (stateInfo.compare(idToStateInfoList.get(id.intValue())) > 0) {
                idToStateInfoList.set(id.intValue(), stateInfo);
            }
            currStateInfo = currStateInfo.next();
        }

//        System.out.println(n);

        int [][] capitalDist = new int[vertices][vertices];
        int [][] distance = new int[vertices][vertices];
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                capitalDist[i][j] = -1;
                distance[i][j] = -1;
            }            
        }
        for (int loop = 0; loop < vertices; loop++) {
            capitalDist[loop][loop] = 0;
            distance[loop][loop] = 0;
        }
        
        LinkedList<CapitalDist>.Link currCapitalDist = capitalDistList.getFirstLink();        
        for (int loop = 0; loop < capitalDistList.size(); loop++) {
            
            CapitalDist capitalDistInst = currCapitalDist.element();
            
            if (null == stateIdToIdHashtable.get(new Hashkey(capitalDistInst.getIdA()))) {
                currCapitalDist = currCapitalDist.next();
                continue;
            }
            if (null == stateIdToIdHashtable.get(new Hashkey(capitalDistInst.getIdB()))) {
                currCapitalDist = currCapitalDist.next();
                continue;
            }

            
            int u = stateIdToIdHashtable.get(new Hashkey(capitalDistInst.getIdA())).intValue();            
            int v = stateIdToIdHashtable.get(new Hashkey(capitalDistInst.getIdB())).intValue();
            
            capitalDist[u][v] = capitalDistInst.getKmDist();
            
            currCapitalDist = currCapitalDist.next();
        }

        readBorderInfoList(distance, capitalDist);
        
        graph = new Graph(vertices, distance);
    }
    
    private int getCountryId(String countryName)
    {
        StateInfo stateInfo = null;

        LinkedList<StateInfo>.Link currStateInfo = stateInfoList.getFirstLink();        
        for (int loop = 0; loop < stateInfoList.size(); loop++) {
            stateInfo = currStateInfo.element();
            if (stateInfo.isTheSameCountryName(countryName))
            {
                return stateIdToIdHashtable.get(new Hashkey(stateInfo.getStateId())).intValue();
            }
            currStateInfo = currStateInfo.next();
        }        
        currStateInfo = stateInfoList.getFirstLink();        
        for (int loop = 0; loop < stateInfoList.size(); loop++) {
            stateInfo = currStateInfo.element();
            if (stateInfo.isSimilarCountryName(countryName))
            {
                return stateIdToIdHashtable.get(new Hashkey(stateInfo.getStateId())).intValue();
            }
            currStateInfo = currStateInfo.next();
        }
        
        return -1;
    }
    
    private LinkedList<StateInfo> readStateInfoList() {
        LinkedList<StateInfo> stateInfoList = new LinkedList<StateInfo>();
        try (
            BufferedReader br = new BufferedReader(new FileReader("state_name.tsv"))) {
            String line;
            
            br.readLine();
            while ((line = br.readLine()) != null) {
                
                StateInfo stateInfo = new StateInfo(line);
                stateInfoList.add(stateInfo);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stateInfoList;
    }


    private LinkedList<CapitalDist> readCapitalDistList() {
        LinkedList<CapitalDist> capitalDistList = new LinkedList<CapitalDist>();
        try (
            BufferedReader br = new BufferedReader(new FileReader("capdist.csv"))) {
            String line;
            
            br.readLine();
            while ((line = br.readLine()) != null) {
                
                CapitalDist capitalDist = new CapitalDist(line);
                capitalDistList.add(capitalDist);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return capitalDistList;
    }
    
    private void readBorderInfoList(int [][]distance, int [][]capitalDist)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("borders.txt"));
            String line;
            
            br.readLine();
            while ((line = br.readLine()) != null) {
                
                LinkedList<BorderInfo> borderInfoList = BorderInfo.parseBorderInfoList(line);
                LinkedList<BorderInfo>.Link currBorderInfo = borderInfoList.getFirstLink();        
                for (int loop = 0; loop < borderInfoList.size(); loop++) {
                    
                    BorderInfo borderInfo = currBorderInfo.element();
                    
                    int u = getCountryId(borderInfo.getCountryName1());            
                    int v = getCountryId(borderInfo.getCountryName2());                                
                    if (u < 0) {

//                            System.out.println(borderInfo.getCountryName1());
                        currBorderInfo = currBorderInfo.next();
                        continue;
                    }
                    if (v < 0) {

//                            System.out.println(borderInfo.getCountryName2());
                        currBorderInfo = currBorderInfo.next();
                        continue;
                    }
                    
                    distance[u][v] = capitalDist[u][v];
                    distance[v][u] = capitalDist[v][u];
                    
                    currBorderInfo = currBorderInfo.next();

                }
            }
            
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public int getDistance (String country1, String country2) {
        // Replace with your code
        
        int u = getCountryId(country1);
        int v = getCountryId(country2);
        
        if ((u < 0) || (v < 0)) {
            return -1;
        }
        
        return graph.getDistance(u, v);
    }


    public List<String> findPath (String country1, String country2) {
        // Replace with your code
        
        int u = 0;
        int v = 0;
        int source = getCountryId(country1);
        int destination = getCountryId(country2);
        
        if ((source < 0) || (destination < 0)) {
            return new ArrayList<String>();
        }
        
        List<Integer> path = graph.dijkstra(source, destination);

        if (path.size() <= 0) {
            return new ArrayList<String>();            
        }
        
        u = source;
        List<String> pathStepList = new ArrayList<String>();
        for(int loop = 0; loop < path.size(); loop++)
        {
            v = path.get(loop).intValue();
            int distance = graph.getDistance(u, v);
            String countryName1 = idToStateInfoList.get(u).getCountryName().getFirstName();
            String countryName2 = idToStateInfoList.get(v).getCountryName().getFirstName();
            String pathStep = "";
            pathStep = pathStep + "* " + countryName1;
            pathStep = pathStep + " --> " + countryName2;
            pathStep = pathStep + " (" + distance + "km.)";
            pathStepList.add(pathStep);
            
            u = v;
        }            

        
        return pathStepList;
    }


    public void acceptUserInput() {
        // Replace with your code
        
        Scanner scanner = new Scanner(System.in);
        while (true) {

            String sourceCountry = "";
            String destinationCountry = "";
            while (true) {
                
                System.out.print("Enter the name of the first country (type EXIT to quit): ");

                sourceCountry = scanner.next();
                if (sourceCountry.equals("EXIT")) {
                    scanner.close();
                    return;
                }
                else if (getCountryId(sourceCountry) >= 0) {
                    break;
                }
                else {
                    System.out.println("Invalid country name. Please enter a valid country name.");
                }
            }
            while (true) {
                
                System.out.print("Enter the name of the second country (type EXIT to quit): ");

                destinationCountry = scanner.next();
                if (destinationCountry.equals("EXIT")) {
                    scanner.close();
                    return;
                }
                else if (getCountryId(destinationCountry) >= 0) {
                    break;
                }
                else {
                    System.out.println("Invalid country name. Please enter a valid country name.");
                }
            }
            
            List<String> pathStepList = findPath(sourceCountry, destinationCountry);
            for(int loop = 0; loop < pathStepList.size(); loop++)
            {
                System.out.println(pathStepList.get(loop));
            }            
        }
    }


    public static void main(String[] args) {
        IRoadTrip a3 = new IRoadTrip(args);

        a3.acceptUserInput();
    }

}

