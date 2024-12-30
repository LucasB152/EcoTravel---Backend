package be.ecotravel.back.search;


import be.ecotravel.back.entity.Destination;

import java.util.Objects;

/**
 * ConcreteStrategies du DP strategy des critères de recherche
 */
public class TypeSearchCriteriaStrategy implements SearchCriteriaStrategy {
    private final String type;

    public TypeSearchCriteriaStrategy(String type) {
        this.type = type;
    }

    @Override
    public boolean matches(Destination destination) {
        return !Objects.equals(this.type, "ACTIVITY") && !Objects.equals(this.type, "LODGING") || destination.getDestinationType().getType().name().equals(type);
    }
}