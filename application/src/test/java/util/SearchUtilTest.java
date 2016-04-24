package util;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Test cases for the {@link SearchUtil} class.
 */
public class SearchUtilTest {

    @Test
    public void searchForCustomer() {
        Map<Long, String> testMap = new HashMap<>();
        testMap.put(0L, "Andrew");
        testMap.put(1L, "Ben");
        testMap.put(2L, "Calvin");
        testMap.put(3L, "Daniel");
        assertTrue(SearchUtil.searchForCustomer(testMap, "x").isEmpty());
        assertTrue(SearchUtil.searchForCustomer(testMap, "i").size() == 2);
        assertTrue(SearchUtil.searchForCustomer(testMap, "i").contains(2L));
        assertTrue(SearchUtil.searchForCustomer(testMap, "i").contains(3L));
        assertTrue(SearchUtil.searchForCustomer(testMap, "an").size() == 2);
        assertTrue(SearchUtil.searchForCustomer(testMap, "an").contains(0L));
        assertTrue(SearchUtil.searchForCustomer(testMap, "an").contains(3L));
        assertTrue(SearchUtil.searchForCustomer(testMap, "ben").size() == 1);
        assertTrue(SearchUtil.searchForCustomer(testMap, "ben").contains(1L));
        assertTrue(SearchUtil.searchForCustomer(testMap, "").size() == 4);
    }
}