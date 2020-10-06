package it.unicam.cs.ids.justmeet.backend.controller.login;

import it.unicam.cs.ids.justmeet.backend.configuration.jwt.JwtUtils;
import it.unicam.cs.ids.justmeet.backend.configuration.service.IUserDetailsImpl;
import it.unicam.cs.ids.justmeet.backend.model.UserRole;
import it.unicam.cs.ids.justmeet.backend.model.enumeration.EnumUserRole;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IPhysicalUser;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import it.unicam.cs.ids.justmeet.backend.payload.request.AuthRequest;
import it.unicam.cs.ids.justmeet.backend.payload.response.JwtResponse;
import it.unicam.cs.ids.justmeet.backend.payload.response.MessageResponse;
import it.unicam.cs.ids.justmeet.backend.repository.UserRepository;
import it.unicam.cs.ids.justmeet.backend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping(path = "/login", consumes = "application/json")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        
        IUserDetailsImpl userDetails = (IUserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUsername(),
                roles));
    }

    private boolean findById(@Valid @RequestBody AuthRequest signUpRequest) {
        return !userRepository.findById(signUpRequest.getUsername()).isEmpty();
    }

    private ResponseEntity<?> signupErr(String username) {
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse(String.format("Error: %s is already taken!", username)));
    }

    private IUser buildUser(IUser user, String username, String password) {
        user.setUniqueID(username);
        user.setPassword(password);
        return user;
    }

    private void saveUser(IUser user) {
        userRepository.save(user);
    }

    private ResponseEntity<?> successReg() {
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping(path = "/register", consumes = "application/json")
    public ResponseEntity<?> registerPhysicalUser(@Valid @RequestBody AuthRequest signUpRequest) {
        if (findById(signUpRequest)) {
            return signupErr(signUpRequest.getUsername());
        }

        // Create new user's account
        saveUser(buildUser(Utils.newPhysicalUserInst(), signUpRequest.getUsername(),
                signUpRequest.getPassword()));

        return successReg();
    }


    @PostMapping(path = "/registerBusiness", consumes = "application/json")
    public ResponseEntity<?> registerUser(@Valid @RequestBody AuthRequest signUpRequest) {
        if (findById(signUpRequest)) {
            return signupErr(signUpRequest.getUsername());
        }

        // Create new user's account
        saveUser(buildUser(Utils.newBusinessUserInst(), signUpRequest.getUsername(),
                signUpRequest.getPassword()));

        return successReg();
    }
}
