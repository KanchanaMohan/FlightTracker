package JUnitTests;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import Queries.AllConnections;

public class AllConnectionsTest extends TestCase {

	public void testAllConnections() {
		try {
			Integer amount = 170;
			int quesNo = 1;
			String source = "NUE";
			String destination = "LHR";
			AllConnections allConnections = new AllConnections(source, destination, amount);
			Map<String, String> conPriceMap = new HashMap<String, String>();

			conPriceMap.put("NUE-FRA", "43");
			conPriceMap.put("NUE-AMS", "67");
			conPriceMap.put("FRA-AMS", "17");
			conPriceMap.put("FRA-LHR", "27");
			conPriceMap.put("LHR-NUE", "23");
			String connNames = "NUE-FRA-43, NUE-AMS-67, FRA-AMS-17, FRA-LHR-27, LHR-NUE-23";
			allConnections.process(conPriceMap, connNames, quesNo);
		} catch (Exception e) {
			fail("Exception should not be thrown");
		}
	}

}
