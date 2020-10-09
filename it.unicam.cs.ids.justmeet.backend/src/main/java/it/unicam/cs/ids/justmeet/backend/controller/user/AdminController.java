package it.unicam.cs.ids.justmeet.backend.controller.user;


import it.unicam.cs.ids.justmeet.backend.model.UserRole;
import it.unicam.cs.ids.justmeet.backend.model.enumeration.EnumUserRole;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IPhysicalUser;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import it.unicam.cs.ids.justmeet.backend.payload.request.ActRequest;
import it.unicam.cs.ids.justmeet.backend.payload.request.AuthRequest;
import it.unicam.cs.ids.justmeet.backend.payload.request.RoleEditRequest;
import it.unicam.cs.ids.justmeet.backend.payload.request.UserRequest;
import it.unicam.cs.ids.justmeet.backend.payload.response.MessageResponse;
import it.unicam.cs.ids.justmeet.backend.repository.UserRepository;
import it.unicam.cs.ids.justmeet.backend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    UserRepository userRepository;

    private IUser findUser(@RequestBody UserRequest userRequest) {
        return userRepository.findById(userRequest.getUsername()).get();
    }

    private boolean replaceUser(IUser user) {

        userRepository.delete(user);

        userRepository.save(user);

        return true;
    }

    private boolean validateAdminRole() {
      return SecurityContextHolder.getContext().getAuthentication()
              .getAuthorities().contains(Utils.generateAuthority(EnumUserRole.ADMIN));
    }

    private ResponseEntity<?> response(IUser user) {
        if(validateAdminRole()) {
            replaceUser(user);
            return ResponseEntity.ok(new MessageResponse("Updated successfully!"));
        }
        else
            return ResponseEntity.ok(new MessageResponse("Update fail"));
    }


    @PostMapping(path ="/setRole", consumes = "application/json")
    public ResponseEntity<?> editPhysicalUserRole(@Valid @RequestBody RoleEditRequest roleEditRequest) {
        IUser temp = findUser(roleEditRequest);
        IPhysicalUser user;

        if(Utils.isPhysicalUser(temp))
            user = (IPhysicalUser) temp;
        else
            return ResponseEntity.ok(new MessageResponse("No physical user"));

        user.setRole(UserRole.fromString(roleEditRequest.getRoles()));

        return response(user);
    }

    @PostMapping(path ="/setStatus", consumes = "application/json")
    public ResponseEntity<?> editUserStatus(@Valid @RequestBody ActRequest actRequest) {
        IUser user = findUser(actRequest);

        user.setActive(actRequest.isActive());

        return response(user);
    }

    @PostMapping(path ="/setPass", consumes = "application/json")
    public ResponseEntity<?> editUserPass(@Valid @RequestBody AuthRequest authRequest) {
        IUser user = findUser(authRequest);

        user.setPassword(authRequest.getPassword());

        return response(user);
    }

    @PostMapping(path = "/delete", consumes = "application/json")
    public ResponseEntity<?> deleteUser(@Valid @RequestBody UserRequest userRequest) {
        if(validateAdminRole())
            userRepository.delete(findUser(userRequest));
        return ResponseEntity.ok(new MessageResponse("Deleted"));
    }

}
