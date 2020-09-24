package it.unicam.cs.ids.justmeet.backend.controller;

import it.unicam.cs.ids.justmeet.backend.configuration.jwt.JwtUtils;
import it.unicam.cs.ids.justmeet.backend.configuration.service.IUserDetailsImpl;
import it.unicam.cs.ids.justmeet.backend.model.BusinessUser;
import it.unicam.cs.ids.justmeet.backend.model.PhysicalUser;
import it.unicam.cs.ids.justmeet.backend.model.UserRole;
import it.unicam.cs.ids.justmeet.backend.model.enumeration.EnumUserRole;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IPhysicalUser;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import it.unicam.cs.ids.justmeet.backend.payload.request.LoginRequest;
import it.unicam.cs.ids.justmeet.backend.payload.request.SignupRequest;
import it.unicam.cs.ids.justmeet.backend.payload.response.JwtResponse;
import it.unicam.cs.ids.justmeet.backend.payload.response.MessageResponse;
import it.unicam.cs.ids.justmeet.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

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

    @PostMapping("/signup")
    public ResponseEntity<?> registerPhysicalUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.findById(signUpRequest.getUsername()).get() != null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        // Create new user's account
        IPhysicalUser user = new PhysicalUser();
        user.setUniqueID(signUpRequest.getUsername());
        user.setPassword(signUpRequest.getPassword());

        //Set<String> strRoles = signUpRequest.getRoles();
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(EnumUserRole.STD));

        user.setRole(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


    @PostMapping("/signupBusiness")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.findById(signUpRequest.getUsername()).get() != null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        // Create new user's account
        IUser user = new BusinessUser();
        user.setUniqueID(signUpRequest.getUsername());
        user.setPassword(signUpRequest.getPassword());

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
