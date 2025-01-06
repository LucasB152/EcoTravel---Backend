package be.ecotravel.back.entity;

import lombok.Getter;

@Getter
public enum DestinationTypeEnum {
    LODGING("Hébergement"),
    ACTIVITY("Activité");

    public final String value;

    DestinationTypeEnum(String value){
        this.value = value;
    }
}
