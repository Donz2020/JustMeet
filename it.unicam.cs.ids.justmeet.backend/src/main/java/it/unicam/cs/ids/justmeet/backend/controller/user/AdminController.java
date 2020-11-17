package it.unicam.cs.ids.justmeet.backend.controller.user;


import it.unicam.cs.ids.justmeet.backend.service.UserDetailsServiceImpl;
import it.unicam.cs.ids.justmeet.backend.model.UserRole;
import it.unicam.cs.ids.justmeet.backend.model.enumeration.EnumUserRole;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IPhysicalUser;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import it.unicam.cs.ids.justmeet.backend.payload.request.AuthRequest;
import it.unicam.cs.ids.justmeet.backend.payload.request.RoleEditRequest;
import it.unicam.cs.ids.justmeet.backend.payload.response.MessageResponse;
import it.unicam.cs.ids.justmeet.backend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController extends ModeratorController {

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected boolean validateRole() {
      return SecurityContextHolder.getContext().getAuthentication()
              .getAuthorities().contains(Utils.generateAuthority(EnumUserRole.ADMIN));
    }

    @PostMapping(path ="/setRole", consumes = "application/json")
    public ResponseEntity<?> editPhysicalUserRole(@Valid @RequestBody RoleEditRequest roleEditRequest) {
        IUser temp = findUser(roleEditRequest.getUsername());
        IPhysicalUser user;

        if(Utils.isPhysicalUser(temp))
            user = (IPhysicalUser) temp;
        else
            return ResponseEntity.ok(new MessageResponse("No physical user"));

        user.setRole(UserRole.fromString(roleEditRequest.getRoles()));

        return response(user);
    }

    @PostMapping(path ="/setPass", consumes = "application/json")
    public ResponseEntity<?> setUserPass(@Valid @RequestBody AuthRequest authRequest) {
        IUser user = findUser(authRequest.getUsername());

        user.setPassword(authRequest.getPassword());

        return response(user);
    }

}
