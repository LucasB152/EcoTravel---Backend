package be.ecotravel.back.search;

import be.ecotravel.back.entity.Destination;

import java.util.List;

/**
 * ConcreteStrategies du DP strategy des critères de recherche
 */
public class TagSearchCriteriaStrategy implements SearchCriteriaStrategy {
    private final List<String> tags;

    public TagSearchCriteriaStrategy(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean matches(Destination destination) {
        if (tags == null) {
            return true;
        }
        return this.tags.stream().allMatch(tag -> destination.getTag().stream().anyMatch(t -> t.getName().equals(tag)));
    }
}