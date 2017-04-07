package Constant;

/**
 * 
 * Constant strings, regex patterns and Error outputs
 * are defined in this file
 *  
 * @author Kanchana Mohan
 */
public class QueryConstants {
	public static void main(String[] args) {

	}

	public final static String Connection = "Connection:";
	public final static String delimter = ":";
	public final static String NOCONNECTION = "No such connection found!";
	public final static String NOMATCH = "No such query found!";
	public final static String QUERYPATTERN1 = "(#\\d*: What is the price of the connection )([A-Z,-]+)(?)";
	public final static String QUERYPATTERN2 = "(#\\d*: What is the cheapest connection from )([A-Z]{3}) to ([A-Z]{3})";
	public final static String QUERYPATTERN3 = "(#\\d*: How many different connections with maximum )(\\d*)( stops*? exists between)( [A-Z]{3} )(and)( [A-Z]{3})";
	public final static String QUERYPATTERN4 = "(#\\d*: How many different connections with exactly )(\\d*)( stops*? exists between)( [A-Z]{3} )(and)( [A-Z]{3})";
	public final static String QUERYPATTERN5 = "(#\\d*: Find all connections from )([A-Z]{3}) to ([A-Z]{3}) below (\\d{1,})Euros!";
	public final static String CONNECTION_PRICE_CLASS = "ConnectionPrice";
	public final static String CONNECTION_CHEAPEST_CLASS = "CheapestConnection";
	public final static String CONNECTION_MAXIMUM_CLASS = "MaximumConnections";
	public final static String CONNECTION_EXACT_CLASS = "ExactConnections";
	public final static String CONNECTION_ALL_CLASS = "AllConnections";
	public final static String GIVE_INPUT = "Please give input file path as argument";
	public final static String INPUT_FILE_PATH = "";
	public final static String OUTPUT_FILE_PATH = "Output/Output.txt";
	public final static String OUTPUT_ERROR_MSG = "COULD NOT LOG!!";
	public final static String FILE_MISSING = "ERROR: Input Query File Not Found";
	public final static String FILE_VERSION = "\tFlightTracker v1.0";

}
