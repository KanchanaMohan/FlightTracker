package Queries;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import Helper.QueryHelper;
import Processors.Query;
import Processors.QueryFactory;

public class AllConnections extends Query {
	private final static Logger LOGGER = Logger.getLogger(AllConnections.class.getName());
	private final static String CLASS_NAME = AllConnections.class.getName();
	
	Integer amount = 0;
	String source = "";
	String destination = "";
	

	public AllConnections(String strSource, String strDestination, Integer strAmount) {
		amount = strAmount;
		source = strSource;
		destination = strDestination;
	}

	/**
	 * Method to process input and print output
	 */
	public void process(Map<String, String> conPriceMap, String connNames, int index) {
        String methodName = "process";
        LOGGER.entering(CLASS_NAME, methodName);
		String allConnections = getConnections(conPriceMap, connNames, source, destination);
		String output = "#" + index + ":" + "" + allConnections;
		QueryHelper queryHelper = new QueryHelper();
		queryHelper.print(output);
		LOGGER.exiting(CLASS_NAME, methodName);

	}

	/**
	 * Method to get all connections from sorce to destination
	 * 
	 * @param conPriceMap
	 * @param connNames
	 * @param strSource
	 * @param strDestination
	 * @return
	 */
	String getConnections(Map<String, String> conPriceMap, String connNames, String strSource, String strDestination) {
		String methodName = "getConnections";
	    LOGGER.entering(CLASS_NAME, methodName);
		String allRoutes = "";
		QueryHelper queryHelper = new QueryHelper();
		Map<String, Integer> unSortedMap = new HashMap<String, Integer>();
		try{
			List<List<String>> routes = queryHelper.getAllConnections(connNames, strSource, strDestination);

			if (null != routes) {
				for (List<String> route : routes) {
					String connectionPath = "";
					for (int i = 0; i < route.size(); i++) {
						if (i == 0) {
							connectionPath = route.get(i);
						} else {
							connectionPath = connectionPath + "-" + route.get(i);
						}
					}
					String price = queryHelper.getConnectionPrice(conPriceMap, connectionPath);
					unSortedMap.put(connectionPath, new Integer(price));
				}
				Map<String, Integer> sortedMapAsc = queryHelper.getSortedConncetion(unSortedMap);
				for (Map.Entry<String, Integer> entry : sortedMapAsc.entrySet()) {
					Integer connectionPrice = entry.getValue();
					if (connectionPrice < amount) {
						allRoutes = allRoutes + entry.getKey() + "-" + connectionPrice + ",";
					}

				}

			} else {
				allRoutes = "No such connection found!";
			}
		}catch(Exception e){
			LOGGER.severe("Exception occured in method"+methodName+"in class"+CLASS_NAME+""+e);
		}
		
		LOGGER.exiting(CLASS_NAME, methodName);
		return allRoutes;

	}
}
