package it.unicam.cs.ids.justmeet.backend.utils;

import it.unicam.cs.ids.justmeet.backend.model.BusinessUser;
import it.unicam.cs.ids.justmeet.backend.model.PhysicalUser;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IPhysicalUser;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Utils {

    public static IUser newBusinessUserInst() {
        return new BusinessUser();
    }

    public static IPhysicalUser newPhysicalUserInst() {
        return new PhysicalUser();
    }

    private static boolean matchRegex(String value, String ePattern) {
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(value);
        return m.matches();
    }

    public static boolean isValidEmailAddress(String email) {
        return matchRegex(email,
                "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
    }

    public static boolean isValidVATNumber(String vatNumber) {
        return matchRegex(vatNumber,
                "^[A-Za-z]{2,4}(?=.{2,12}$)[-_\\s0-9]*(?:[a-zA-Z][-_\\s0-9]*){0,2}$");
    }
}
