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
        return true;
//        System.out.println("[DEBUG] destination.getTags() = " + destination.getTag().size() + destination.getTag());
//        System.out.println("[DEBUG] tags = " + tags.size() + tags);
//      return destination.getTag().stream().anyMatch(tag -> tags.contains(tag.getName()));
    }
}