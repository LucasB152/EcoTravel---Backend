package be.ecotravel.back.search;

import be.ecotravel.back.entity.Destination;

/**
 * ConcreteStrategies du DP strategy des critères de recherche
 */
public class QuerySearchCriteriaStrategy implements SearchCriteriaStrategy {

    private final String query;

    public QuerySearchCriteriaStrategy(String query) {
        this.query = query;
    }

    @Override
    public boolean matches(Destination destination) {
        //if query is empty, return true
        if (query == null || query.isEmpty()) return true;



//        if (destination.getName().contains(query) || destination.getDescription().contains(query)) {
//            return true;
//        }
//        if (destination.getAddress().getCountry().contains(query) || destination.getAddress().getLocation().contains(query)) {
//            return true;
//        }
//        return false;

        //error if a destination.value() is null
        //correction:
        if (destination.getName() != null && destination.getName().contains(query)) {
            return true;
        }
        if (destination.getDescription() != null && destination.getDescription().contains(query)) {
            return true;
        }
        if (destination.getAddress() != null) {
            if (destination.getAddress().getCountry() != null && destination.getAddress().getCountry().contains(query)) {
                return true;
            }
            return destination.getAddress().getLocation() != null && destination.getAddress().getLocation().contains(query);
        }
        return false;

    }
}
