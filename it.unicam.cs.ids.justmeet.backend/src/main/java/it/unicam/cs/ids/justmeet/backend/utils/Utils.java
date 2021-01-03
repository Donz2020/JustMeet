package it.unicam.cs.ids.justmeet.backend.utils;

import it.unicam.cs.ids.justmeet.backend.model.BusinessUser;
import it.unicam.cs.ids.justmeet.backend.model.PhysicalUser;
import it.unicam.cs.ids.justmeet.backend.model.UserRole;
import it.unicam.cs.ids.justmeet.backend.model.enumeration.EnumUserRole;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IPhysicalUser;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Utils {

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

    public static SimpleGrantedAuthority generateAuthority(EnumUserRole userRole) {
        return new SimpleGrantedAuthority(new UserRole(userRole).toString());
    }

    public static boolean isPhysicalUser(IUser user) {
        return !user.getRole().stream().anyMatch(n -> n.getName() == EnumUserRole.VRF);
    }

    public static String getCurrentUser(SecurityContext context) {
        UserDetails principal = (UserDetails) context.getAuthentication().getPrincipal();
        return principal.getUsername();
    }

}
