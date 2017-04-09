package Queries;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import Constant.QueryConstants;
import Helper.QueryHelper;
import Helper.PrintHelper;
import Helper.ConnectionHelper;
import Processors.Query;

/**
 * 
 * Processes CheapestConnection query
 * 
 * @author Kanchana Mohan
 */
public class CheapestConnection extends Query {

	private final static Logger LOGGER = Logger.getLogger(CheapestConnection.class.getName());
	private final static String CLASS_NAME = CheapestConnection.class.getName();
	String source = "";
	String destination = "";

	public CheapestConnection(String strSource, String strDestination) {
		source = strSource;
		destination = strDestination;
	}

	@Override
	public void process(Map<String, String> conPriceMap, String connNames, int index) {
		String methodName = "process";
		LOGGER.entering(CLASS_NAME, methodName);
		String cheapestConnect = getCheapestConnection(conPriceMap, connNames, source, destination);
		String output = "#" + index + ":" + "" + cheapestConnect;
		PrintHelper print = PrintHelper.getInstance();
		print.output(output);
		LOGGER.exiting(CLASS_NAME, methodName);
	}

	public String getCheapestConnection(Map<String, String> conPriceMap, String connNames, String strSource, String strDestination) {
		
		String methodName = "getCheapestConnection";
		LOGGER.entering(CLASS_NAME, methodName);
		String cheapestConnect = "";
		QueryHelper queryHelper = new QueryHelper();
		Map<String, Integer> unSortedMap = new HashMap<String, Integer>();
		try {
			ConnectionHelper search = new ConnectionHelper();
			List<List<String>> routes = search.getAllConnections(connNames, strSource, strDestination);
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
				if (null != sortedMapAsc) {
					String cheapestWay = "";
					Integer cheapestPrice = 0;
					for (Entry<String, Integer> entry : sortedMapAsc.entrySet()) {
						cheapestWay = entry.getKey();
						cheapestPrice = entry.getValue();
						break;
					}
					if (null == cheapestWay || cheapestWay.equals("") || "" == cheapestWay) {
						cheapestConnect = QueryConstants.NOCONNECTION;
					} else {
						cheapestConnect = cheapestWay + "-" + cheapestPrice;
					}

				}
			} else {
				cheapestConnect = QueryConstants.NOCONNECTION;
			}
		} catch (Exception e) {
			LOGGER.severe("Exception occured at method " + methodName + "in" + CLASS_NAME + "" + e);
		}

		LOGGER.exiting(CLASS_NAME, methodName);
		return cheapestConnect;

	}

}
