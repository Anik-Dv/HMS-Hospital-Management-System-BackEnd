package com.hms.anikdv.code.controllers.patientAuthentication;


import com.hms.anikdv.code.configuration.ModelMapperConfiguration;
import com.hms.anikdv.code.entities.User;
import com.hms.anikdv.code.payloads.*;
import com.hms.anikdv.code.repositories.UserRepository;
import com.hms.anikdv.code.security.CustomUserDetailsService;
import com.hms.anikdv.code.security.JwtUtils;
import com.hms.anikdv.code.services.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/patient")
public class PatientAuthController {
    private Logger logger = LoggerFactory.getLogger(PatientAuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private PatientService patientService;

    @Autowired
    private ModelMapperConfiguration modelMapperConfiguration;

    @Autowired
    private UserRepository userRepository;


    /**
     * This Method For Patient User Registration
     *
     * @param userPayload
     * @return patient user authorities
     */
    @Tag(name = "Authentication Patient User", description = "POST Methods of Login & Signing APIs")
    @Operation(summary = "POST Method Patient User Register", description = "Post Method Patient User Register. The Response is Registered User Object and Other Details.")
    @PostMapping("/signin")
    public ResponseEntity<?> patientRegistrationHandler(final @Valid @RequestBody UserPayload userPayload) {
        final String METHOD_NAME = "patientRegistrationHandler";
        logger.info("Method Invoked: " + this.getClass().getName() + ":" + METHOD_NAME);
        try {
                logger.info("Response The Method: " + this.getClass().getName() + ":" + METHOD_NAME);
                UserPayload registeredPatient = this.patientService.registerPatient(userPayload);
                return ResponseEntity.status(HttpStatus.CREATED).body(registeredPatient);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse("Invalid User Credentials!", false));
        }
    }

    /**
     * This Method For Patient User Login
     *
     * @param authRequest
     * @return user authorities
     */
    @Tag(name = "Authentication User", description = "POST Methods of Login & Signin APIs")
    @Operation(summary = "POST Method User Authentication Login", description = "Post Method User Authentication. The Response is an Access Token.")
    @PostMapping("/login")
    public ResponseEntity<?> PatientLoginHandler(final @Valid @RequestBody JwtAuthRequest authRequest) {
        final String METHOD_NAME = "PatientLoginHandler";
        logger.info("Method Invoked: " + this.getClass().getName() + ":" + METHOD_NAME);
        try {
            this.doAuthentication(authRequest.getUsername(), authRequest.getPassword());
            // Fetch user details
            User user = this.userRepository.findByName(authRequest.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found!"));
            logger.info("user details {} :", user);
            String token = this.jwtUtil.generateToken(user);
            // setting jwt response
            JwtAuthResponse response = new JwtAuthResponse();
            // set current user in response
            UserPayload currentUser = this.modelMapperConfiguration.modelMapper().map(user, UserPayload.class);
            response.setToken(token);
            response.setCurrentUser(currentUser);

            logger.info("Response The Method: " + this.getClass().getName() + ":" + METHOD_NAME);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse("Invalid username or password!", false));
        }
    }


    /* This Method For Authenticate User */
    private void doAuthentication(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                password);
        try {
            this.authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException ex) {
            logger.error("Invalid Username or Password!");
            throw new BadCredentialsException("Invalid Username or Password!");
        }
    }

}
