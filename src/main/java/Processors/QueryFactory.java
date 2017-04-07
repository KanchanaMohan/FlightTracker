package Processors;


import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Constant.QueryConstants;
import Queries.AllConnections;
import Queries.CheapestConnection;
import Queries.ConnectionPrice;
import Queries.ExactConnections;
import Queries.MaximumConnections;

public class QueryFactory implements IQueryFactory {
	private final static Logger LOGGER = Logger.getLogger(QueryFactory.class.getName());
	private final static String CLASS_NAME = QueryFactory.class.getName();
	
    /**
     * This method gets respective query handler classes.
     */
	public Query getQueryHandler(String query) {
		
		String methodName = "getQueryHandler";
		LOGGER.entering(CLASS_NAME, methodName);
		String line = query;
		boolean isValidConn = true;
		QueryConstants queryConstants = new QueryConstants();
		String pattern1 = queryConstants.QUERYPATTERN1;
		String pattern2 = queryConstants.QUERYPATTERN2;
		String pattern3 = queryConstants.QUERYPATTERN3;
		String pattern4 = queryConstants.QUERYPATTERN4;
		String pattern5 = queryConstants.QUERYPATTERN5;
		try{
			Map <String,String>patternsMap = new HashMap<String, String>();
			patternsMap.put(pattern1, queryConstants.CONNECTION_PRICE_CLASS);
			patternsMap.put(pattern2, queryConstants.CONNECTION_CHEAPEST_CLASS);
			patternsMap.put(pattern3, queryConstants.CONNECTION_MAXIMUM_CLASS);
			patternsMap.put(pattern4, queryConstants.CONNECTION_EXACT_CLASS);
			patternsMap.put(pattern5, queryConstants.CONNECTION_ALL_CLASS);
			
			for (Map.Entry<String, String> entry : patternsMap.entrySet())
			{
				Pattern r = Pattern.compile(entry.getKey());
				Matcher m = r.matcher(line);
				if(m.find()){
					String strHandler = entry.getValue(); 
					if(strHandler.equalsIgnoreCase(queryConstants.CONNECTION_PRICE_CLASS)){
						String connection = m.group(2).trim();
						return new ConnectionPrice(connection);
					}
					else if(strHandler.equalsIgnoreCase(queryConstants.CONNECTION_CHEAPEST_CLASS)){
						String source = m.group(2).trim();
						String destination = m.group(3).trim();
						return new CheapestConnection(source, destination);
					}
					else if(strHandler.equalsIgnoreCase(queryConstants.CONNECTION_MAXIMUM_CLASS)){
						Integer number = new Integer(m.group(2).trim());
						String source = m.group(4).trim();
						String destination = m.group(6).trim();
						return new MaximumConnections(source, destination, number);
					}
					else if(strHandler.equalsIgnoreCase(queryConstants.CONNECTION_EXACT_CLASS)){
						Integer number = new Integer(m.group(2).trim());
						String source = m.group(4).trim();
						String destination = m.group(6).trim();
						return new ExactConnections(source, destination, number);
					}
					else if(strHandler.equalsIgnoreCase(queryConstants.CONNECTION_ALL_CLASS)){
						String source = m.group(2).trim();
						String destination = m.group(3).trim();
						Integer amount = new Integer(m.group(4).trim());
						return new AllConnections(source, destination, amount);
					}
					
				}
			   
			}
		}catch(Exception e){
			LOGGER.info("Exception Occured in method" +methodName+"in class"+CLASS_NAME+""+e);
		}
		
		LOGGER.exiting(CLASS_NAME, methodName);
		return null;
	}
	
	
}
