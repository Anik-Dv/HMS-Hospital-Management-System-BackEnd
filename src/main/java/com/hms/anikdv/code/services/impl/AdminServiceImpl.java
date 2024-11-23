package com.hms.anikdv.code.services.impl;

import com.hms.anikdv.code.configuration.ModelMapperConfiguration;
import com.hms.anikdv.code.entities.Admin;
import com.hms.anikdv.code.entities.Role;
import com.hms.anikdv.code.entities.User;
import com.hms.anikdv.code.exceptions.ResourceNotFoundException;
import com.hms.anikdv.code.payloads.AdminPayload;
import com.hms.anikdv.code.payloads.UserPayload;
import com.hms.anikdv.code.repositories.AdminRepository;
import com.hms.anikdv.code.repositories.RoleRepository;
import com.hms.anikdv.code.repositories.UserRepository;
import com.hms.anikdv.code.services.AdminService;
import com.hms.anikdv.code.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapperConfiguration modelMapperConfiguration;

    /**
     * This Method For Registration Admin
     *
     * @param userPayload
     * @return a new registered Admin
     */
    @Override
    public UserPayload registerAdmin(UserPayload userPayload) throws Exception {
        // Create User for Admin
        User adminUser = new User();
        adminUser.setName(userPayload.getName());
        adminUser.setDob(userPayload.getDob());
        adminUser.setAddress(userPayload.getAddress());
        adminUser.setPhoneNumber(userPayload.getPhoneNumber());
        adminUser.setEmail(userPayload.getEmail());
        adminUser.setPassword(passwordEncoder.encode(userPayload.getPassword()));

        // set user into patient
        Admin admin = new Admin();
        admin.setUser(adminUser);
        admin.setDepartment(admin.getDepartment());
        admin.setCreatedAt(admin.getCreatedAt().now());

        // Set Admin Role
        Role adminRole = this.roleRepository.findById(AppConstants.ADMIN)
                .orElseThrow(() -> new RuntimeException("Admin role not found"));
        adminRole.setUser(adminUser);
        adminUser.getRoles().add(adminRole);

        // Save User
        User savedUser = this.userRepository.save(adminUser);
        this.adminRepository.save(admin);

        return this.modelMapperConfiguration.modelMapper().map(savedUser, UserPayload.class);
    }

    /**
     * Update Admin Information
     * @param adminId
     * @param adminPayload containing updated data
     * @return updated Admin
     * @throws Exception if Admin not found
     */
    @Override
    public AdminPayload updateAdmin(Long adminId, AdminPayload adminPayload) throws Exception {
        Admin existingAdmin  = this.adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "not found with ID: ", adminId));

        // Update associated user information
        User adminUser = existingAdmin.getUser();
        if (adminPayload.getUserPayload().getName() != null) {
            adminUser.setName(adminPayload.getUserPayload().getName());
        }
        if (adminPayload.getUserPayload().getEmail() != null) {
            adminUser.setEmail(adminPayload.getUserPayload().getEmail());
        }
        if (adminPayload.getUserPayload().getPassword() != null) {
            adminUser.setPassword(passwordEncoder.encode(adminPayload.getUserPayload().getPassword()));
        }
        // Update admin-specific information
        if (adminPayload.getDepartment() != null) {
            existingAdmin.setDepartment(adminPayload.getDepartment());
        }

        this.userRepository.save(adminUser);

        // save admin
        return this.modelMapperConfiguration.modelMapper().map(existingAdmin, AdminPayload.class);
    }

    /**
     * This Method For Delete Admin
     *
     * @param adminId
     * @return a Delete Admin | True/False
     */
    @Override
    public boolean deleteAdmin(Long adminId) throws Exception {
        boolean flag = false;
        try {
            Admin admin = this.adminRepository.findById(adminId)
                    .orElseThrow(() -> new UsernameNotFoundException("Admin Not Found!"));
            this.adminRepository.delete(admin);
            flag = true;
            return flag;
        } catch (ResourceNotFoundException e) {
            e.getMessage();
            return flag;
        }
    }

    /**
     * This Method For Get All Admins
     *
     * @return a All Admins
     */
    @Override
    public List<AdminPayload> getAllAdmin() {
        List<Admin> admins = this.adminRepository.findAll();
        List<AdminPayload> AdminPayloads = admins.stream().map(admin -> this.modelMapperConfiguration.modelMapper().map(admin, AdminPayload.class)).collect(Collectors.toList());
        return AdminPayloads;
    }

    /**
     * This Method For Get Single Admin
     * @param adminId
     * @return a Single Admin
     */
    @Override
    public AdminPayload getSingleAdmin(final Long adminId) {
        Admin admin = this.adminRepository.findById(adminId).orElseThrow(() -> new ResourceNotFoundException("Admin", "not found with Admin_Id! : ", adminId));
        return this.modelMapperConfiguration.modelMapper().map(admin, AdminPayload.class);
    }
}
