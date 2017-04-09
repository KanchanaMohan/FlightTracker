package JUnitTests;

import java.util.HashMap;
import java.util.Map;

import Queries.MaximumConnections;
import junit.framework.TestCase;

public class MaximumConnectionsTest extends TestCase {

	public void testMaximumConnections() {
		try {
            String methodName = "testMaximumConnections";
			int quesNo = 1;
			String source = "NUE";
			String destination = "FRA";
			Integer count = 3;
			MaximumConnections maximumConnections = new MaximumConnections(source, destination, count);
			Map<String, String> conPriceMap = new HashMap<String, String>();

			conPriceMap.put("NUE-FRA", "43");
			conPriceMap.put("NUE-AMS", "67");
			conPriceMap.put("FRA-AMS", "17");
			conPriceMap.put("FRA-LHR", "27");
			conPriceMap.put("LHR-NUE", "23");
			String connNames = "NUE-FRA-43, NUE-AMS-67, FRA-AMS-17, FRA-LHR-27, LHR-NUE-23";
			String maxConnect = maximumConnections.getMaxConnections(conPriceMap, connNames, source, destination);
			assertEquals("Error in"+methodName+"in"+MaximumConnectionsTest.class.getName(),"2",maxConnect);
		} catch (Exception e) {
			fail("Exception should not be thrown");
		}
	}
}
