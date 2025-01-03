package be.ecotravel.back.repository;


import be.ecotravel.back.entity.Destination;
import be.ecotravel.back.entity.DestinationTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PageDestinationRepository extends PagingAndSortingRepository<Destination, UUID> {

    @Query("""
            SELECT d
            FROM Destination d
            LEFT JOIN Review r ON d.id = r.destination.id
            LEFT JOIN d.tag t
            WHERE (:tags IS NULL OR t.name IN :tags)
            AND (:type IS NULL OR d.destinationType.type = :type)
            GROUP BY d.id
            HAVING COUNT(t.name) = :tagCount OR :tags IS NULL
            ORDER BY COALESCE(AVG(r.score), 0) DESC
            """)
    Page<Destination> findAllSortedByAverageScore(
            @Param("tags") List<String> tags,
            @Param("tagCount") long tagCount,
            @Param("type") DestinationTypeEnum type,
            Pageable pageable
    );

}
