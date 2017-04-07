package Helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Constant.QueryConstants;

public class QueryHelper {
	public static boolean ascOrder = true;
	private static HashMap<String, List<String>> left_to_rights;
   
	/**
	 * Method to get connections 
	 * sorted based on price
	 * @param unSortedMap
	 * @return
	 */
	public Map getSortedConncetion(Map<String, Integer> unSortedMap) {

		Map<String, Integer> sortedMap = sortComparator(unSortedMap, ascOrder);
		return sortedMap;
	}

	static Map<String, Integer> sortComparator(Map<String, Integer> unsortMap, final boolean order) {

		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> str1, Entry<String, Integer> str2) {
				if (order) {
					return str1.getValue().compareTo(str2.getValue());
				} else {
					return str2.getValue().compareTo(str1.getValue());

				}
			}
		});

		
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Entry<String, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

	/**
	 * Method to get connection price
	 * @param conPriceMap
	 * @param strConnection
	 * @return
	 */
	public String getConnectionPrice(Map<String, String> conPriceMap, String strConnection) {

		int iPrice = 0;
		String displayValue = "";
		boolean isConnection = true;
		if (conPriceMap.containsKey(strConnection)) {
			iPrice = new Integer(conPriceMap.get(strConnection)).intValue();
		} else {
			String[] strConnectInfo = strConnection.split("-");
			String strConnectName = "";
			for (int i = 0; i < strConnectInfo.length; i++) {
				if (i == 0) {
					strConnectName = strConnectInfo[i];

				} else {
					strConnectName = strConnectName + "-" + strConnectInfo[i];
					if (conPriceMap.containsKey(strConnectName.trim())) {
						iPrice = iPrice + new Integer(conPriceMap.get(strConnectName)).intValue();
					} else {
						isConnection = false;
						break;
					}
					strConnectName = strConnectInfo[i];
				}

			}

		}
		if (isConnection) {
			displayValue = new Integer(iPrice).toString();
		} else {
			displayValue = QueryConstants.NOCONNECTION;
		}
		return displayValue;
	}

	/**
	 * Method to get all Connections for an 
	 * input
	 * @param connNames
	 * @param source
	 * @param destination
	 * @return
	 */
	public List getAllConnections(String connNames, String source, String destination) {
		
		left_to_rights = new HashMap<String, List<String>>();
		String[] connection = connNames.split(",");
		HashMap<String, Void> lines = new HashMap<String, Void>();

		for (int i = 0; i < connection.length; i++) {
			String[] arrDetails = connection[i].split("-");
			String strKey = "";
			String strValue = "";
			for (int j = 0; j < arrDetails.length - 1; j++) {
				if (j == 0) {
					strKey = arrDetails[j];
				} else {
					strKey = strKey + " " + arrDetails[j];
				}
			}
			strKey = strKey.trim();
			
			if (lines.containsKey(strKey)) { 
				
				continue;
			}
			lines.put(strKey, null);
			int idelimiter = strKey.indexOf(' ');
			String left = strKey.substring(0, idelimiter);
			String right = strKey.substring(idelimiter + 1);

			if (left.equals(right)) { 
				
				continue;
			}
			List<String> rights = left_to_rights.get(left);
			if (rights == null) {
				
				rights = new ArrayList<String>();
				left_to_rights.put(left, rights);
			}
			rights.add(right);

		}
		

		
		List<List<String>> connecRoutes = GetRoutes(source, destination);
		
		
         return connecRoutes;
	}
	public  List<List<String>> GetRoutes(String begin, String end) {
		List<List<String>> routes = new ArrayList<List<String>>();
		List<String> rights = left_to_rights.get(begin);
		if (rights != null) {
			for (String right : rights) {
				List<String> route = new ArrayList<String>();
				route.add(begin);
				route.add(right);
				Chain(routes, route, right, end);
			}
		}
		return routes;
	}

	public  void Chain(List<List<String>> routes, List<String> route, String right_most_currently, String end) {
		if (right_most_currently.equals(end)) {
			routes.add(route);
			return;
		}
		List<String> rights = left_to_rights.get(right_most_currently);
		if (rights != null) {
			for (String right : rights) {
				if (!route.contains(right)) {
					List<String> new_route = new ArrayList<String>(route);
					new_route.add(right);
					Chain(routes, new_route, right, end);
				}
			}
		}
	}
	
	/**
	 * Method to print the 
	 * output
	 * @param output
	 */
	public void print(String output){
		System.out.println(output);
		QueryConstants queryConstants = new QueryConstants();
		File log = new File(queryConstants.OUTPUT_FILE_PATH);

		try{
		    if(!log.exists()){
		    	
		        log.createNewFile();
		    }

		    FileWriter fileWriter = new FileWriter(log, true);

		    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		    bufferedWriter.newLine();
		    bufferedWriter.write(output);
		    //bufferedWriter.newLine();
		    bufferedWriter.close();
		    
		} catch(IOException e) {
		    System.out.println(queryConstants.OUTPUT_ERROR_MSG);
		}
	}
}
