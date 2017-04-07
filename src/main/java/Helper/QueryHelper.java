package Helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import Constant.QueryConstants;
import Processors.QueryProcessor;

/**
 * 
 * Helper class for finding cheapest connection and available connections
 * Query classes delegate functionality to this helper class.
 *  
 * @author Kanchana Mohan
 */

public class QueryHelper {
	private final static Logger LOGGER = Logger.getLogger(QueryProcessor.class.getName());
	private final static String CLASS_NAME = QueryProcessor.class.getName();
	public static boolean ascOrder = true;
	private static HashMap<String, List<String>> left_to_rights;

	/**
	 * Method to get connections sorted based on price
	 * 
	 * @param unSortedMap
	 * @return
	 */
	public Map getSortedConncetion(Map<String, Integer> unSortedMap) {
		String methodName = "getSortedConncetion";
		LOGGER.entering(CLASS_NAME, methodName);
		Map<String, Integer> sortedMap = sortComparator(unSortedMap, ascOrder);
		LOGGER.exiting(CLASS_NAME, methodName);
		return sortedMap;
	}

	static Map<String, Integer> sortComparator(Map<String, Integer> unsortMap, final boolean order) {
		String methodName = "sortComparator";
		LOGGER.entering(CLASS_NAME, methodName);
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
		LOGGER.exiting(CLASS_NAME, methodName);
		return sortedMap;
	}

	/**
	 * Method to get connection price
	 * 
	 * @param conPriceMap
	 * @param strConnection
	 * @return
	 */
	public String getConnectionPrice(Map<String, String> conPriceMap, String strConnection) {

		String methodName = "getConnectionPrice";
		LOGGER.entering(CLASS_NAME, methodName);
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
		LOGGER.exiting(CLASS_NAME, methodName);
		return displayValue;
	}

	/**
	 * Method to get all Connections for an input
	 * 
	 * @param connNames
	 * @param source
	 * @param destination
	 * @return
	 */
	public List getAllConnections(String connNames, String source, String destination) {

		String methodName = "getAllConnections";
		LOGGER.entering(CLASS_NAME, methodName);
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

		List<List<String>> connecRoutes = getRoutes(source, destination);
		LOGGER.exiting(CLASS_NAME, methodName);
		return connecRoutes;
	}

	public List<List<String>> getRoutes(String begin, String last) {
		String methodName = "GetRoutes";
		LOGGER.entering(CLASS_NAME, methodName);
		List<List<String>> routes = new ArrayList<List<String>>();
		List<String> rights = left_to_rights.get(begin);
		if (rights != null) {
			for (String right : rights) {
				List<String> route = new ArrayList<String>();
				route.add(begin);
				route.add(right);
				findRoute(routes, route, right, last);
			}
		}
		LOGGER.exiting(CLASS_NAME, methodName);
		return routes;
	}

	public void findRoute(List<List<String>> routes, List<String> route, String right_most_currently, String end) {
		String methodName = "Chain";
		LOGGER.entering(CLASS_NAME, methodName);
		if (right_most_currently.equals(end)) {
			routes.add(route);
			return;
		}
		List<String> rights = left_to_rights.get(right_most_currently);
		if (rights != null) {
			for (String right : rights) {
				if (!route.contains(right)) {
					List<String> newRoute = new ArrayList<String>(route);
					newRoute.add(right);
					findRoute(routes, newRoute, right, end);
				}
			}
		}
		LOGGER.exiting(CLASS_NAME, methodName);
	}

}
