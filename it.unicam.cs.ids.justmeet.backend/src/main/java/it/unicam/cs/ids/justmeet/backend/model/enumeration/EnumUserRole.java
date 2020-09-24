package it.unicam.cs.ids.justmeet.backend.model.enumeration;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


public enum EnumUserRole {
    ADMIN,
    MOD,
    STD,
    VRF
}

