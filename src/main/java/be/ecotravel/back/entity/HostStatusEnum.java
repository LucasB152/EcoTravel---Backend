package be.ecotravel.back.entity;

import lombok.Getter;

@Getter
public enum HostStatusEnum {
    PARTICULIER("Particulier"),
    SARL("SARL"),
    AUTO_ENTREPRENEUR("Auto-entrepreneur"),
    ASSOCIATION("Association");

    private final String value;

    HostStatusEnum(String value){
        this.value = value;
    }
}
