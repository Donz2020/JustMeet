package it.unicam.cs.ids.justmeet.backend.controller.user;


import it.unicam.cs.ids.justmeet.backend.model.UserRole;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IPhysicalUser;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import it.unicam.cs.ids.justmeet.backend.payload.request.ActRequest;
import it.unicam.cs.ids.justmeet.backend.payload.request.RoleEditRequest;
import it.unicam.cs.ids.justmeet.backend.payload.response.MessageResponse;
import it.unicam.cs.ids.justmeet.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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


    @PostMapping("/editRole")
    public ResponseEntity<?> editPhysicalUserRole(@Valid @RequestBody RoleEditRequest roleEditRequest) {

        IPhysicalUser user = (IPhysicalUser) userRepository.findById(roleEditRequest.getUsername()).get();

        user.setRole(UserRole.fromString(roleEditRequest.getRoles()));

        return response(user);
    }

    @PostMapping("/editStatus")
    public ResponseEntity<?> editUserStatus(@Valid @RequestBody ActRequest actRequest) {

        IUser user = userRepository.findById(actRequest.getUsername()).get();

        user.setActive(actRequest.isActive());

        return response(user);
    }


}
