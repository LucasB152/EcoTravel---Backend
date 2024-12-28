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
        return true;
//        return destination.getName().contains(query) || destination.getDescription().contains(query);
    }
}
