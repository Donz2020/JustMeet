package it.unicam.ids.justmeet.configuration;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {




    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1,
                                        Authentication authentication) throws IOException, ServletException {


        boolean hasUserRole = false;
        boolean hasAdminRole = false;

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("USER")) {
                hasUserRole = true; //metti a true il booleano
                break;
            } else  if (grantedAuthority.getAuthority().equals("ADMIN")) {
                hasUserRole = true; //metti a true il booleano
                break;
            }

        }

        if (hasUserRole) {
            redirectStrategy.sendRedirect(arg0, arg1, "/home/home");
        //if ruolo, redirecta
        }else if (hasAdminRole) {
            redirectStrategy.sendRedirect(arg0, arg1, "/home/home");
            //if ruolo, redirecta
        }
        else {
            throw new IllegalStateException();
        }
    }


}
