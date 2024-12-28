package be.ecotravel.back.destination.dto;

import java.util.List;

public record SearchCriteria(String query,
                             List<String> tags,
                             String type) {
}