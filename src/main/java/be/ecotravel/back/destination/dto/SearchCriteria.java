package be.ecotravel.back.destination.dto;

import java.util.List;

/**
 * criteria for searching destinations
 * !!!!! ATTENTION version temporaire (tags et types peuvent changer) !!!!!
 */
public record SearchCriteria(String query,
                             List<String> tags,
                             String type,
                             Integer page,
                             Integer size) {
}