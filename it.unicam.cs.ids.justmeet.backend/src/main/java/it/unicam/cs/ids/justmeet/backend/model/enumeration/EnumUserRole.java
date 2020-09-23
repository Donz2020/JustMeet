package it.unicam.cs.ids.justmeet.backend.model.enumeration;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


public enum EnumUserRole {
    ADMIN(1),
    MOD(2),
    STD(3),
    VRF(4);


    private int value;

    private EnumUserRole(int value) {
        this.value = value;
    }

    public int getValue() { return value; }
}

