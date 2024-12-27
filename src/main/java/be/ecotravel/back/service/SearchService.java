package be.ecotravel.back.service;

import be.ecotravel.back.entity.Destination;
import be.ecotravel.back.repository.DestinationRepository;
import be.ecotravel.back.search.SearchCriteriaStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * context du DP strategy des critères de recherche
 */
@Service
public class SearchService {

    private final DestinationRepository destinationRepository;

    @Autowired
    public SearchService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public List<Destination> search(List<SearchCriteriaStrategy> criteriaList) {
        List<Destination> destinations = destinationRepository.findAll();
        for (SearchCriteriaStrategy criteria : criteriaList) {
            destinations = destinations.stream()
                    .filter(criteria::matches)
                    .collect(Collectors.toList());
        }
        return destinations;
    }
}
