package JUnitTests;

import java.util.HashMap;
import java.util.Map;

import Queries.ConnectionPrice;
import junit.framework.TestCase;

public class ConnectionPriceTest extends TestCase {
	public void testConnectionPrice() {
		try {
            
			String connectionName = "NUE-FRA-AMS";
            Integer index = 1;
			ConnectionPrice connectionPrice = new ConnectionPrice(connectionName);
			Map<String, String> conPriceMap = new HashMap<String, String>();
            
			conPriceMap.put("NUE-FRA", "43");
			conPriceMap.put("NUE-AMS", "67");
			conPriceMap.put("FRA-AMS", "17");
			conPriceMap.put("FRA-LHR", "27");
			conPriceMap.put("LHR-NUE", "23");
			String connNames = "NUE-FRA-43, NUE-AMS-67, FRA-AMS-17, FRA-LHR-27, LHR-NUE-23";
			connectionPrice.process(conPriceMap, connNames, index);
			String strPrice = connectionPrice.getPrice(conPriceMap, connectionName);
			Integer intPrice = Integer.valueOf(strPrice);
			assertEquals(new Integer(60),intPrice);
			
			connectionName = "NUE-FRA";
			strPrice = connectionPrice.getPrice(conPriceMap, connectionName);
			intPrice = Integer.valueOf(strPrice);
			assertEquals(new Integer(43),intPrice);
		
		} catch (Exception e) {
			fail("Exception should not be thrown");
		}
	}
}
