package util;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Contains some utility method for search.
 */
public class SearchUtil {

    private SearchUtil() {
        throw new UnsupportedOperationException("You cannot instantiate a utility class.");
    }

    /**
     * Search in the given customers map for the given text in the customers' names.
     *
     * @param strippedCustomer The customers to search in.
     * @param rawSearchText    The text to search.
     * @return The IDs of the founded customers.
     */
    public static Set<Long> searchForCustomer(Map<Long, String> strippedCustomer, String rawSearchText) {
        final String searchText = rawSearchText.toLowerCase();
        Set<Long> founded = new HashSet<>();
        strippedCustomer.keySet().stream().filter(id -> strippedCustomer.get(id).toLowerCase().contains(searchText))
                .forEach(id -> founded.add(id));
        return founded;
    }
}
