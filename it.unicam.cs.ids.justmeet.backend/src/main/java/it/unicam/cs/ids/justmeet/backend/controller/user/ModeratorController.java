package it.unicam.cs.ids.justmeet.backend.controller.user;

import it.unicam.cs.ids.justmeet.backend.model.enumeration.EnumUserRole;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import it.unicam.cs.ids.justmeet.backend.payload.request.ActRequest;
import it.unicam.cs.ids.justmeet.backend.payload.request.UserRequest;
import it.unicam.cs.ids.justmeet.backend.payload.response.MessageResponse;
import it.unicam.cs.ids.justmeet.backend.utils.Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/mod")
public class ModeratorController extends UserController {

    protected boolean validateRole() {
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().contains(Utils.generateAuthority(EnumUserRole.MOD));
    }

    protected ResponseEntity<?> response(IUser user) {
        if(validateRole()) {
            replaceUser(user);
            return ResponseEntity.ok(new MessageResponse("Updated successfully!"));
        }
        else
            return ResponseEntity.ok(new MessageResponse("Update fail"));
    }

    @PostMapping(path ="/setStatus", consumes = "application/json")
    public ResponseEntity<?> setUserStatus(@Valid @RequestBody ActRequest actRequest) {
        IUser user = findUser(actRequest.getUsername());

        user.setActive(actRequest.isActive());

        return response(user);
    }

    @PostMapping(path = "/delete", consumes = "application/json")
    public ResponseEntity<?> deleteUser(@Valid @RequestBody UserRequest userRequest) {
        if(validateRole())
            deleteUser(findUser(userRequest.getUsername()));

        return ResponseEntity.ok(new MessageResponse("Deleted"));
    }
}
