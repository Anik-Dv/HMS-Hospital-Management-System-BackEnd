package com.hms.anikdv.code.controllers.doctorAuthentication;


import com.hms.anikdv.code.configuration.ModelMapperConfiguration;
import com.hms.anikdv.code.controllers.patientAuthentication.PatientAuthController;
import com.hms.anikdv.code.entities.User;
import com.hms.anikdv.code.payloads.ApiResponse;
import com.hms.anikdv.code.payloads.JwtAuthRequest;
import com.hms.anikdv.code.payloads.JwtAuthResponse;
import com.hms.anikdv.code.payloads.UserPayload;
import com.hms.anikdv.code.repositories.UserRepository;
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

/*
 *  I’ll add an endpoint for the admin to register a doctor. Only an admin can access this endpoint,
 * and the doctor won’t have permission to register themselves.
 * Instead, the doctor can only log in with the credentials provided by the admin.
 *
 * */

@RestController
@RequestMapping("/api/v1/auth/doctor")
public class DoctorAuthController {
    private Logger logger = LoggerFactory.getLogger(DoctorAuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private ModelMapperConfiguration modelMapperConfiguration;

    @Autowired
    private UserRepository userRepository;



    /**
     * This Method For Doctor User Login
     *
     * @param authRequest
     * @return doctor authorities
     */
    @Tag(name = "Authentication Doctor", description = "POST Methods of Doctor For Login & Signin APIs")
    @Operation(summary = "POST Method Doctor Authentication Login", description = "Post Method Doctor Authentication. The Response is an Access Token.")
    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<?> doctorLoginHandler(final @Valid @RequestBody JwtAuthRequest authRequest) {
        final String METHOD_NAME = "doctorLoginHandler";
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
