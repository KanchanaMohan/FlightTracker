package JUnitTests;

import java.util.HashMap;
import java.util.Map;

import Queries.ExactConnections;
import junit.framework.TestCase;

public class ExactConnectionsTest extends TestCase {

	public void testExactConnections() {
		try {
            String methodName = "testExactConnections";
			int quesNo = 1;
			String source = "LHR";
			String destination = "AMS";
			Integer count = 1;
			ExactConnections exactConnections = new ExactConnections(source, destination, count);
			Map<String, String> conPriceMap = new HashMap<String, String>();

			conPriceMap.put("NUE-FRA", "43");
			conPriceMap.put("NUE-AMS", "67");
			conPriceMap.put("FRA-AMS", "17");
			conPriceMap.put("FRA-LHR", "27");
			conPriceMap.put("LHR-NUE", "23");
			String connNames = "NUE-FRA-43, NUE-AMS-67, FRA-AMS-17, FRA-LHR-27, LHR-NUE-23";
			String exactConnect = exactConnections.getExactConnections(conPriceMap, connNames, source, destination);
			assertEquals("Error in"+methodName+"in"+ExactConnectionsTest.class.getName(),"1",exactConnect);
		} catch (Exception e) {
			fail("Exception should not be thrown");
		}
	}
}
