package JUnitTests;

import java.util.HashMap;
import java.util.Map;

import Queries.ExactConnections;
import junit.framework.TestCase;


public class ExactConnectionsTest extends TestCase{

	public void testExactConnections() {
		try {
			
			int quesNo = 1;
			String source = "NUE";
			String destination ="LHR";
			Integer count = 1;
			ExactConnections cm = new ExactConnections(source,destination,count);
			Map <String,String> conPriceMap = new HashMap<String, String>();
			
			conPriceMap.put("NUE-FRA", "43");
			conPriceMap.put("NUE-AMS", "67");
			conPriceMap.put("FRA-AMS", "17");
			conPriceMap.put("FRA-LHR", "27");
			conPriceMap.put("LHR-NUE", "23");
			String connNames = "NUE-FRA-43, NUE-AMS-67, FRA-AMS-17, FRA-LHR-27, LHR-NUE-23";
			cm.process(conPriceMap, connNames, quesNo);
		} catch (Exception e) {
			fail("Exception should not be thrown");
		}
	}
}
