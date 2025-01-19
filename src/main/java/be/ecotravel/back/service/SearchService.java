package be.ecotravel.back.service;

import be.ecotravel.back.entity.Destination;
import be.ecotravel.back.entity.DestinationTypeEnum;
import be.ecotravel.back.repository.PageDestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final PageDestinationRepository pageDestinationRepository;

    @Autowired
    public SearchService(PageDestinationRepository pageDestinationRepository) {
        this.pageDestinationRepository = pageDestinationRepository;
    }

    public Page<Destination> searchDestinations(String query, List<String> tags, String type, PageRequest pageRequest) {
        DestinationTypeEnum destinationTypeEnum = null;
        long tagCount = (tags == null) ? 0 : tags.size();
        if (type != null) {
            try {
                destinationTypeEnum = DestinationTypeEnum.valueOf(type);
            } catch (IllegalArgumentException ignored) {
            }
        }
        if (tagCount > 0) {
            return pageDestinationRepository.findAllSortedByAverageScoreWithTag(query,destinationTypeEnum, tags, tagCount , pageRequest);
        }
        else return pageDestinationRepository.findAllSortedByAverageScore(query,destinationTypeEnum, pageRequest);

    }
}
