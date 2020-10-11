package it.unicam.cs.ids.justmeet.backend.controller.user;

import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import it.unicam.cs.ids.justmeet.backend.payload.response.DetailsResponse;
import it.unicam.cs.ids.justmeet.backend.payload.response.MessageResponse;
import it.unicam.cs.ids.justmeet.backend.repository.UserRepository;
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

    @GetMapping(path ="/getDetails", consumes = "application/json")
    public ResponseEntity<?> getDeatils() {

        IUser user = findUser(getCurrentUser());

        return ResponseEntity.ok(new DetailsResponse(user.getUniqueID(), user.getDetails(), user.getRole().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toList()))
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
