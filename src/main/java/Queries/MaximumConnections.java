package Queries;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import Constant.QueryConstants;
import Helper.MaximumConnectionsHelper;
import Helper.QueryHelper;
import Helper.PrintHelper;
import Helper.ConnectionHelper;
import Processors.Query;

/**
 * 
 * Processes MaximumConnections query
 *  
 * @author Kanchana Mohan
 */
public class MaximumConnections extends Query {
	private final static Logger LOGGER = Logger.getLogger(MaximumConnections.class.getName());
	private final static String CLASS_NAME = MaximumConnections.class.getName();
	Integer count = 0;
	String source = "";
	String destination = "";

	public MaximumConnections(String strSource, String strDestination, Integer iCount) {
		source = strSource;
		destination = strDestination;
		count = iCount;
	}

	public void process(Map<String, String> conPriceMap, String connNames, int i) {
		String methodName = "process";
		LOGGER.entering(CLASS_NAME, methodName);
		String allConnections = getMaxConnections(conPriceMap, connNames, source, destination);
		String output = "#" + i + ":" + "" + allConnections;
		PrintHelper print = PrintHelper.getInstance();
		print.output(output);
		LOGGER.exiting(CLASS_NAME, methodName);
	}

	public String getMaxConnections(Map<String, String> conPriceMap, String connNames, String strSource, String strDestination) {
		String methodName = "getMaxConnections";
		LOGGER.entering(CLASS_NAME, methodName);
		String numRoutes = "0";
		QueryHelper queryHelper = new QueryHelper();
		Map<String, Integer> unSortedMap = new HashMap<String, Integer>();
		try {
			MaximumConnectionsHelper stopsCounterHelper = new MaximumConnectionsHelper();
			List<List<String>> routes = stopsCounterHelper.getAllConnections(connNames, strSource, strDestination,count);
			Integer numConnections = 0;
			Integer stopsCount = 0;
			if (null != routes) {
				for (List<String> route : routes) {
					String connectionPath = "";
					for (int i = 0; i < route.size(); i++) {
						if (i != 0 && i != route.size() - 1) {
							stopsCount = stopsCount + 1;
						}
					}
					if (stopsCount >= 0 && stopsCount <= count) {
						numConnections = numConnections + 1;
						numRoutes = numConnections.toString();
					}
					stopsCount = 0;
				}
			} else {
				numRoutes = QueryConstants.NOCONNECTION;
				;
			}
		} catch (Exception e) {
			LOGGER.severe("Exception occured at method " + methodName + "in" + CLASS_NAME + "" + e);
		}

		LOGGER.exiting(CLASS_NAME, methodName);
		return numRoutes;

	}
}
