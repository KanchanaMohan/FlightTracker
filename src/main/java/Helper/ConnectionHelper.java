package Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ConnectionHelper {

    private static  String strSorce = "";
    private static  String strDestination = "";
    private static HashMap<String, ArrayList<String>> mapConnections = new HashMap<String, ArrayList<String>>();
    public static List<List<String>> newRoutes = new ArrayList<List<String>>();
   
    
    public static List getAllConnections(String connNames, String source, String destination) {
    	newRoutes.clear();
    	strSorce = source;
    	strDestination = destination;
    	String strConInfo = connNames;
    	mapConnections = (HashMap<String, ArrayList<String>>) getConnectionPaths(strConInfo);
       
        ConnectionsGraph graph = new ConnectionsGraph();
        for (Map.Entry<String, ArrayList<String>> entry : mapConnections.entrySet()) {
        	String key = entry.getKey();
	        ArrayList arrDestinations = entry.getValue();
	        for(int i=0;i<arrDestinations.size();i++){
	        	graph.addEdge(key, arrDestinations.get(i).toString());
	        }
        }
        LinkedList<String> visited = new LinkedList();
        visited.add(strSorce);
        depthFirst(graph, visited);
		return newRoutes;
	}

    private static void depthFirst(ConnectionsGraph graph, LinkedList<String> visited) {
        LinkedList<String> nodes = graph.adjacentNodes(visited.getLast());
       
        for (String node : nodes) {

            if (node.equals(strDestination)) {
                visited.add(node);
                addPaths(visited);
                visited.removeLast();
                break;
            }
        }
        for (String node : nodes) {
            if (visited.contains(node) || node.equals(strDestination)) {
                continue;
            }
            visited.addLast(node);
            depthFirst(graph, visited);
            visited.removeLast();
        }
    }

    private static void addPaths(LinkedList<String> visited) {
    	List<String> nodeList = new ArrayList<String>();
        for (String node : visited) {
        	nodeList.add(node.toString());
           
        }
        newRoutes.add(nodeList);
       
    }
    
    
    /**
	 * Method to save connection details in Map
	 * 
	 * @param strConInfo
	 * @return
	 */
	static Map getConnectionPaths(String strConInfo) {

		Map<String, ArrayList<String>> connectPathMap = new HashMap<String, ArrayList<String>>();

		String source = "";
		String[] arrConnections = strConInfo.split(",");
		for (int i = 0; i < arrConnections.length; i++) {
			String strConPath = arrConnections[i];
			String[] conLink = strConPath.split("-");
			for (int j = 0; j < conLink.length - 1; j++) {
				if (j != 0) {
					source = conLink[j - 1].trim();
					if (!connectPathMap.containsKey(source)) {
						ArrayList<String> destinations = new ArrayList<String>();
						destinations.add(conLink[j]);
						connectPathMap.put(source, destinations);
					} else {
						ArrayList<String> addDestinations = new ArrayList<String>();
						addDestinations = connectPathMap.get(source);
						addDestinations.add(conLink[j]);
						connectPathMap.put(source, addDestinations);
					}

				}

			}
		}
		return connectPathMap;
	}
}