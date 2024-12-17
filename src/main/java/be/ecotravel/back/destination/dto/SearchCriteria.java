package be.ecotravel.back.destination.dto;

import java.util.List;


/**
 * criteria for searching destinations
 * !!!!! ATTENTION version temporaire (tags et types peuvent changer) !!!!!
 */
public class SearchCriteria {
    //query peut etre: Liege, belgique; ou bien: Liege
    private String query;
    //tags sont les tags lier a la bd destination
    private List<String> tags;
    //host ou activity (ou les deux)
    private String type;
    private Integer page;
    //nb d'élément dans une pages
    private Integer size;

    // Getters and setters
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}