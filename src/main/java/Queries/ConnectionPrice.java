package Queries;

import java.util.Map;
import java.util.logging.Logger;

import Helper.QueryHelper;
import Helper.PrintHelper;
import Processors.Query;


/**
 * 
 * Processes ConnectionPrice query
 *  
 * @author Kanchana Mohan
 */
public class ConnectionPrice extends Query {
	private final static Logger LOGGER = Logger.getLogger(ConnectionPrice.class.getName());
	private final static String CLASS_NAME = ConnectionPrice.class.getName();
	String connectionName = "";

	public ConnectionPrice(String connectName) {
		connectionName = connectName;
	}

	@Override
	public void process(Map<String, String> conPriceMap, String connNames, int index) {
		String methodName = "process";
		LOGGER.entering(CLASS_NAME, methodName);
		String price = getPrice(conPriceMap, connectionName);
		String output = "#" + index + ":" + "" + price;
		PrintHelper print = PrintHelper.getInstance();
		print.output(output);
		LOGGER.exiting(CLASS_NAME, methodName);
	}

	public String getPrice(Map<String, String> conPriceMap, String strConnection) {
		String methodName = "getPrice";
		LOGGER.entering(CLASS_NAME, methodName);
		String displayValue = "";
		QueryHelper queryHelper = new QueryHelper();
		try {
			displayValue = queryHelper.getConnectionPrice(conPriceMap, strConnection);
		} catch (Exception e) {
			LOGGER.severe("Exception occured at method " + methodName + "in" + CLASS_NAME + "" + e);
		}

		LOGGER.exiting(CLASS_NAME, methodName);
		return displayValue;
	}

}
