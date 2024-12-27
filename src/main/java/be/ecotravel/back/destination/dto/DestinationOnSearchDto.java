package be.ecotravel.back.destination.dto;


import java.util.List;
import java.util.UUID;

/**
 * dto de destination pour la recherche et qui contient **TOUTES** les informations d'une destination
 * !!!!! ATTENTION version temporaire et a completer (type, tags et images VONT changer)!!!!!
 */
public record DestinationOnSearchDto(UUID destinationID,
                                     String name,
                                     String description,
                                     double lat,
                                     double lng,
                                     String address,
                                     String type,
                                     List<String> images,
                                     List<String> tags) {
}