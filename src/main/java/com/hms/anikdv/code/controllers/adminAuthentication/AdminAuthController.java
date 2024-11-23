package com.hms.anikdv.code.controllers.adminAuthentication;


import com.hms.anikdv.code.configuration.ModelMapperConfiguration;
import com.hms.anikdv.code.controllers.patientAuthentication.PatientAuthController;
import com.hms.anikdv.code.entities.Admin;
import com.hms.anikdv.code.entities.User;
import com.hms.anikdv.code.payloads.*;
import com.hms.anikdv.code.repositories.AdminRepository;
import com.hms.anikdv.code.repositories.UserRepository;
import com.hms.anikdv.code.security.JwtUtils;
import com.hms.anikdv.code.services.AdminService;
import com.hms.anikdv.code.services.DoctorService;
import com.hms.anikdv.code.services.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/admin")
public class AdminAuthController {
    private static final Logger logger = LoggerFactory.getLogger(AdminAuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private ModelMapperConfiguration modelMapperConfiguration;

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DoctorService doctorService;

    /**
     * Admin can also Allow for Register a new Admin
     * @param userPayload containing admin details
     * @return registered Admin
     */
    @Tag(name = "Authentication Admin User", description = "POST Methods of Admin Login & Register APIs")
    @Operation(summary = "POST Method Admin User Register", description = "Post Method Admin User Register. The Response is Registered User Object and Other Details.")
    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody UserPayload userPayload) {
        final String METHOD_NAME = "registerAdmin";
        logger.info("Method Invoked: " + this.getClass().getName() + ":" + METHOD_NAME);
        try {
            logger.info("Response The Method: " + this.getClass().getName() + ":" + METHOD_NAME);
            UserPayload registerAdmin = this.adminService.registerAdmin(userPayload);
            return ResponseEntity.status(HttpStatus.CREATED).body(registerAdmin);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse("Invalid Admin Credentials!", false));
        }
    }

    /**
     * This Method For Admin User Login
     *
     * @param authRequest
     * @return user authorities
     */
    @Tag(name = "Authentication Admin User", description = "POST Methods of Admin Login & Register APIs")
    @Operation(summary = "POST Method Admin User Login", description = "Post Method Admin User Login. The Response is Login User Object and Other Details.")
    @PostMapping("/login")
    public ResponseEntity<?> AdminLoginHandler(final @Valid @RequestBody JwtAuthRequest authRequest) throws Exception {
        final String METHOD_NAME = "AdminLoginHandler";
        logger.info("Method Invoked: " + this.getClass().getName() + ":" + METHOD_NAME);
        try {
            this.doAuthentication(authRequest.getUsername(), authRequest.getPassword());
            // Fetch user details
            User adminUser = this.userRepository.findByName(authRequest.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found!"));
            logger.info("user details {} :", adminUser);
            String token = this.jwtUtil.generateToken(adminUser);
            // setting jwt response
            JwtAuthResponse response = new JwtAuthResponse();
            // set current user in response
            UserPayload currentUser = this.modelMapperConfiguration.modelMapper().map(adminUser, UserPayload.class);
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


    /*
    * I’ll add an endpoint for the admin to register a doctor. Only an admin can access this endpoint,
    * and the doctor won’t have permission to register themselves.
    * Instead, the doctor can only log in with the credentials provided by the admin.
    * */
    /**
     * This Method For Doctor Register
     *
     * @param userPayload
     * @return user authorities
     */
    @Tag(name = "Authentication Admin User.", description = "POST Methods of Admin Login & Register APIs")
    @Operation(summary = "POST Method Admin Can Register an Doctor.", description = "The Response is Registered Doctor Object and Other Details.")
    @PostMapping("/register-doctor")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registerDoctor(final @Valid @RequestBody UserPayload userPayload) {
        final String METHOD_NAME = "registerDoctor";
        logger.info("Method Invoked: " + this.getClass().getName() + ":" + METHOD_NAME);

        try{
            UserPayload registerDoctor = this.doctorService.registerDoctor(userPayload);
            // logger.info("doctor id is : {} " + userPayload.getDoctorPayload().getDoctorId());
            logger.info("Response The Method: " + this.getClass().getName() + ":" + METHOD_NAME);
            return ResponseEntity.status(HttpStatus.CREATED).body(registerDoctor);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(ex.getMessage() + "| Invalid Request!", false));
        }
    }

}
