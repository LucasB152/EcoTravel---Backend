package be.ecotravel.back.search;

import be.ecotravel.back.entity.Destination;

/**
 * StrategyInterface du DP strategy des critères de recherche
 */
public interface SearchCriteriaStrategy {
    boolean matches(Destination destination);
}