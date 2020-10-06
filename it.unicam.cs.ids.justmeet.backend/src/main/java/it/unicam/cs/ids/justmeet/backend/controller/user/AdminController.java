package it.unicam.cs.ids.justmeet.backend.controller.user;


import it.unicam.cs.ids.justmeet.backend.model.UserRole;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IPhysicalUser;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import it.unicam.cs.ids.justmeet.backend.payload.request.ActRequest;
import it.unicam.cs.ids.justmeet.backend.payload.request.RoleEditRequest;
import it.unicam.cs.ids.justmeet.backend.payload.request.UserRequest;
import it.unicam.cs.ids.justmeet.backend.payload.response.MessageResponse;
import it.unicam.cs.ids.justmeet.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    UserRepository userRepository;

    private boolean replaceUser(IUser user) {

        userRepository.delete(user);

        userRepository.save(user);
        return true;
    }

    private ResponseEntity<?> response(IUser user) {
        if(replaceUser(user))
            return ResponseEntity.ok(new MessageResponse("Updated successfully!"));
        else
            return ResponseEntity.ok(new MessageResponse("Update fail"));
    }


    @PostMapping(path ="/editRole", consumes = "application/json")
    public ResponseEntity<?> editPhysicalUserRole(@Valid @RequestBody RoleEditRequest roleEditRequest) {

        IPhysicalUser user = (IPhysicalUser) userRepository.findById(roleEditRequest.getUsername()).get();

        user.setRole(UserRole.fromString(roleEditRequest.getRoles()));

        return response(user);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path ="/editStatus", consumes = "application/json")
    public ResponseEntity<?> editUserStatus(@Valid @RequestBody ActRequest actRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!authentication.getAuthorities().contains("ADMIN")) {

            return ResponseEntity.ok(new MessageResponse("Update fail"));
        }

        IUser user = userRepository.findById(actRequest.getUsername()).get();

        user.setActive(actRequest.isActive());

        return response(user);
    }


    @PostMapping(path = "/delete", consumes = "application/json")
    public ResponseEntity<?> deleteUser(@Valid @RequestBody UserRequest userRequest) {
        userRepository.delete(userRepository.findById(userRequest.getUsername()).get());
        return ResponseEntity.ok(new MessageResponse("Deleted"));
    }

}
