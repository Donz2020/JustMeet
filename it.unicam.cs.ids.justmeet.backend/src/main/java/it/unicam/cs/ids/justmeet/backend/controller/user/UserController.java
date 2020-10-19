package it.unicam.cs.ids.justmeet.backend.controller.user;

import it.unicam.cs.ids.justmeet.backend.model.intfc.IPhysicalUser;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import it.unicam.cs.ids.justmeet.backend.payload.response.DetailsResponse;
import it.unicam.cs.ids.justmeet.backend.payload.response.MessageResponse;
import it.unicam.cs.ids.justmeet.backend.payload.response.PhysicalDetailsResponse;
import it.unicam.cs.ids.justmeet.backend.repository.UserRepository;
import it.unicam.cs.ids.justmeet.backend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/usr")
public class UserController {

    @Autowired
    UserRepository userRepository;

    private String getCurrentUser() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUsername();
    }

    private IUser findUser(String userName) {
        return userRepository.findById(userName).get();
    }

    private boolean replaceUser(IUser user) {

        userRepository.delete(user);

        userRepository.save(user);

        return true;
    }

    @GetMapping(path ="/getDetailsPhy", consumes = "application/json")
    public ResponseEntity<?> getDetailsPhysical() {
        IUser user = findUser(getCurrentUser());
        IPhysicalUser temp;

        if(Utils.isPhysicalUser(user))
            temp = (IPhysicalUser) user;
        else
            return ResponseEntity.ok(new MessageResponse("No physical user"));


        return ResponseEntity.ok(new PhysicalDetailsResponse(temp.getUniqueID(),
                temp.getDetails(),
                temp.getRole().stream()
                        .map(role -> role.getName().name())
                        .collect(Collectors.toList()),
                temp.getName(),
                temp.getSurname(),
                temp.getBirthDate())
        );
    }

    @GetMapping(path ="/getDetails", consumes = "application/json")
    public ResponseEntity<?> getDetails() {

        IUser user = findUser(getCurrentUser());

        return ResponseEntity.ok(new DetailsResponse(user.getUniqueID(),
                user.getDetails(),
                user.getRole().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toList()),
                user.getName())
        );
    }

    @PostMapping(path ="/setPass/{pass}", consumes = "application/json")
    public ResponseEntity<?> setPass(@PathVariable String pass) {
        IUser user = findUser(getCurrentUser());

        user.setPassword(pass);

        replaceUser(user);

        return ResponseEntity.ok(new MessageResponse("Updated"));
    }

    @PostMapping(path = "/delete", consumes = "application/json")
    public ResponseEntity<?> deleteUser() {
        userRepository.delete(findUser(getCurrentUser()));
        return ResponseEntity.ok(new MessageResponse("Deleted"));
    }


}
