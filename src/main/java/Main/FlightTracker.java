package Main;

import Constant.QueryConstants;
import Processors.QueryProcessor;
import java.util.logging.Logger;

public class FlightTracker {
	private final static Logger LOGGER = Logger.getLogger(FlightTracker.class.getName());
	private final static String CLASS_NAME = FlightTracker.class.getName();

	public static void main(String[] args) {
		if(args.length !=0){
			String methodName = "main";
			LOGGER.entering(CLASS_NAME, methodName);
			String fileName = args[0];
			QueryProcessor queryProcessor = new QueryProcessor();
			queryProcessor.process(fileName);
			LOGGER.exiting(CLASS_NAME, methodName);
		}
		else{
			System.out.println(QueryConstants.GIVE_INPUT);
		}
		

	}

}
