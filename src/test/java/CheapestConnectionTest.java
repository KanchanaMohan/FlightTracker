package JUnitTests;

import java.util.HashMap;
import java.util.Map;

import Queries.CheapestConnection;
import junit.framework.TestCase;

public class CheapestConnectionTest extends TestCase {

	public void testCheapestConnections() {
		try {

			int quesNo = 1;
			String source = "NUE";
			String destination = "LHR";
			CheapestConnection cheapestConnection = new CheapestConnection(source, destination);
			Map<String, String> conPriceMap = new HashMap<String, String>();

			conPriceMap.put("NUE-FRA", "43");
			conPriceMap.put("NUE-AMS", "67");
			conPriceMap.put("FRA-AMS", "17");
			conPriceMap.put("FRA-LHR", "27");
			conPriceMap.put("LHR-NUE", "23");
			String connNames = "NUE-FRA-43, NUE-AMS-67, FRA-AMS-17, FRA-LHR-27, LHR-NUE-23";
			cheapestConnection.process(conPriceMap, connNames, quesNo);
		} catch (Exception e) {
			fail("Exception should not be thrown");
		}
	}

}
