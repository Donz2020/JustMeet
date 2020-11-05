package it.unicam.cs.ids.justmeet.backend.controller.user;

import it.unicam.cs.ids.justmeet.backend.service.UserDetailsServiceImpl;
import it.unicam.cs.ids.justmeet.backend.model.UserRole;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IPhysicalUser;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import it.unicam.cs.ids.justmeet.backend.payload.response.DetailsResponse;
import it.unicam.cs.ids.justmeet.backend.payload.response.MessageResponse;
import it.unicam.cs.ids.justmeet.backend.payload.response.PhysicalDetailsResponse;
import it.unicam.cs.ids.justmeet.backend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/usr")
public class UserController {

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    private String getCurrentUser() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUsername();
    }

    private List<String> rolesToString(Set<UserRole> roles) {
        return roles.stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toList());
    }

    private IUser findUser(String userName) {
        return userDetailsServiceImpl.getUserInstance(userName);
    }

    private void replaceUser(IUser user) {
        userDetailsServiceImpl.replaceUser(user);
    }

    @GetMapping(path ="/getDetailsPhy", produces = "application/json")
    public ResponseEntity<?> getDetailsPhysical() {
        IUser user = findUser(getCurrentUser());
        IPhysicalUser temp;

        if(Utils.isPhysicalUser(user))
            temp = (IPhysicalUser) user;
        else
            return ResponseEntity.ok(new MessageResponse("No physical user"));

        return ResponseEntity.ok(new PhysicalDetailsResponse(temp.getUsername(),
                temp.getDetails(),
                rolesToString(temp.getRole()),
                temp.getName(),
                temp.getSurname(),
                temp.getBirthDate())
        );
    }

    @GetMapping(path ="/getDetails", produces = "application/json")
    public ResponseEntity<?> getDetails() {
        IUser user = findUser(getCurrentUser());

        return ResponseEntity.ok(new DetailsResponse(user.getUsername(),
                user.getDetails(),
                rolesToString(user.getRole()),
                user.getName())
        );
    }

    @PatchMapping(path ="/setPass/{pass}", produces = "application/json")
    public ResponseEntity<?> setPass(@PathVariable String pass) {
        IUser user = findUser(getCurrentUser());

        user.setPassword(pass);

        replaceUser(user);

        return ResponseEntity.ok(new MessageResponse("Updated"));
    }

    @PostMapping(path = "/delete", produces = "application/json")
    public ResponseEntity<?> deleteUser() {
        userDetailsServiceImpl.deleteUser(findUser(getCurrentUser()));
        return ResponseEntity.ok(new MessageResponse("Deleted"));
    }
}
