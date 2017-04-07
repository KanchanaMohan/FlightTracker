package Processors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import Constant.QueryConstants;
import Helper.PrintHelper;
import Helper.QueryHelper;
/**
 * 
 * Handles queries from the user
 *  
 * @author Kanchana Mohan
 */
public class QueryProcessor {

	private final static Logger LOGGER = Logger.getLogger(QueryProcessor.class.getName());
	private final static String CLASS_NAME = QueryProcessor.class.getName();

	/**
	 * Method to take and process input
	 * 
	 * @param fileName
	 */
	public void process(String fileName) {

		readInputFile(fileName);
	}

	/**
	 * This method creates connection price map and also calls factory class
	 * 
	 * @param fileName
	 */
	public void readInputFile(String fileName) {
		String methodName = "readInputFile";
		try {
			LOGGER.entering(CLASS_NAME, methodName);

			if (new File(fileName).exists()) {

				FileInputStream fis = new FileInputStream(fileName);
				BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
				String line = reader.readLine();
				String queryLine;
				String connNames = "";
				QueryFactory queryFactory = new QueryFactory();
				QueryHelper queryHelper = new QueryHelper();
				Map<String, String> conPriceMap = new HashMap<String, String>();
				Map<String, List> conPathMap = new HashMap<String, List>();
				int index = 0;

				while (line != null && line != "") {

					queryLine = line;
					if (queryLine.contains(QueryConstants.Connection)) {

						connNames = getConnectionNames(queryLine);
						conPriceMap = saveConnectionPrices(connNames);

					} else {

						Query query = queryFactory.getQueryHandler(queryLine);
						if (null != query) {
							query.process(conPriceMap, connNames, index);
						} else {

							String output = "#" + index + ":" + QueryConstants.NOMATCH;
							PrintHelper print = PrintHelper.getInstance();
							print.output(output);
							// queryHelper.print(output);

						}

					}
					index = index + 1;

					line = reader.readLine();
				}

				reader.close();
				fis.close();
			} else {
				System.out.println(QueryConstants.FILE_MISSING);
			}

		} catch (IOException e) {
			LOGGER.info("IOException occured in method " + methodName + "in class" + CLASS_NAME + "" + e);
		}
		LOGGER.entering(CLASS_NAME, methodName);
	}

	/**
	 * Method to get connection names
	 * 
	 * @param query
	 * @return
	 */
	static String getConnectionNames(String query) {
		String connectNames = "";
		String[] connArr = query.split(QueryConstants.delimter);
		connectNames = connArr[1];
		return connectNames;

	}

	/**
	 * Method to save connection details in Map
	 * 
	 * @param strConInfo
	 * @return
	 */
	static Map saveConnectionPrices(String strConInfo) {

		Map<String, String> connectInfoMap = new HashMap<String, String>();
		String[] arrConnections = strConInfo.split(",");
		for (int i = 0; i < arrConnections.length; i++) {
			String strConnection = arrConnections[i];
			String[] arrDetails = strConnection.split("-");
			String strKey = "";
			String strValue = "";
			for (int j = 0; j < arrDetails.length; j++) {
				if (j == 0) {
					strKey = arrDetails[j];
				} else if (j == (arrDetails.length - 1)) {
					strValue = arrDetails[j];
				} else {
					strKey = strKey + "-" + arrDetails[j];
				}
			}

			connectInfoMap.put(strKey.trim(), strValue.trim());
		}
		return connectInfoMap;
	}

}
