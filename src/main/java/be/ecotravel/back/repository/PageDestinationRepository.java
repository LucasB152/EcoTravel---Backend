package be.ecotravel.back.repository;


import be.ecotravel.back.entity.Destination;
import be.ecotravel.back.entity.DestinationTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PageDestinationRepository extends JpaRepository<Destination, UUID> {

//    @Query("""
//        SELECT d
//        FROM Destination d
//        LEFT JOIN Review r ON d.id = r.destination.id
//        LEFT JOIN d.tag t
//        LEFT JOIN d.address a ON d.address.id = a.id
//        WHERE (:query IS NULL
//                 OR LOWER(d.name) LIKE LOWER(CONCAT('%', :query, '%'))
//                 OR LOWER(d.description) LIKE LOWER(CONCAT('%', :query, '%'))
//                 OR LOWER(a.street) LIKE LOWER(CONCAT('%', :query, '%'))
//                 OR LOWER(a.country) LIKE LOWER(CONCAT('%', :query, '%'))
//                 OR LOWER(a.location) LIKE LOWER(CONCAT('%', :query, '%')))
//        AND (:tags IS NULL OR t.name IN :tags)
//        AND (:type IS NULL OR d.destinationType.type = :type)
//        GROUP BY d.id
//        HAVING COUNT(t.name) = :tagCount OR :tags IS NULL
//        ORDER BY COALESCE(AVG(r.score), 0) DESC
//        """)
//    Page<Destination> findAllSortedByAverageScoreWithQuery(
//            @Param("query") String query,
//            @Param("tags") List<String> tags,
//            @Param("tagCount") long tagCount,
//            @Param("type") DestinationTypeEnum type,
//            Pageable pageable
//    );

    @Query("""
        SELECT d FROM Destination d
        LEFT JOIN Review r ON r.destination.id = d.id
        WHERE (d.destinationType.type = :type OR :type IS NULL)
            AND (:query IS NULL
                OR LOWER(d.name) LIKE LOWER(CONCAT('%', :query, '%'))
                OR LOWER(d.description) LIKE LOWER(CONCAT('%', :query, '%'))
                OR LOWER(d.address.street) LIKE LOWER(CONCAT('%', :query, '%'))
                OR LOWER(d.address.country) LIKE LOWER(CONCAT('%', :query, '%'))
                OR LOWER(d.address.location) LIKE LOWER(CONCAT('%', :query, '%')))
        GROUP BY d
        ORDER BY AVG(r.score) DESC
    """)
    Page<Destination> findAllSortedByAverageScore(
            @Param("query") String query,
            @Param("type") DestinationTypeEnum type,
            Pageable pageable);


    @Query(value = """
                SELECT d FROM Destination d
                LEFT JOIN Review r ON r.destination.id = d.id
                LEFT JOIN d.tag t
                WHERE (d.destinationType.type = :type OR :type IS NULL)
                    AND (:tags IS NULL OR t.name IN :tags)
                    AND (:query IS NULL
                        OR LOWER(d.name) LIKE LOWER(CONCAT('%', :query, '%'))
                        OR LOWER(d.description) LIKE LOWER(CONCAT('%', :query, '%'))
                        OR LOWER(d.address.street) LIKE LOWER(CONCAT('%', :query, '%'))
                        OR LOWER(d.address.country) LIKE LOWER(CONCAT('%', :query, '%'))
                        OR LOWER(d.address.location) LIKE LOWER(CONCAT('%', :query, '%')))
                GROUP BY d
                HAVING COUNT(DISTINCT t.id) = :tagSize
                ORDER BY AVG(r.score) DESC
            """)
    Page<Destination> findAllSortedByAverageScoreWithTag(
            @Param("query") String query,
            @Param("type") DestinationTypeEnum type,
            @Param("tags") List<String> tags,
            @Param("tagSize") long tagSize,
            Pageable pageable);


}
