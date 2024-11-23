package com.hms.anikdv.code.services.impl;

import com.hms.anikdv.code.configuration.ModelMapperConfiguration;
import com.hms.anikdv.code.entities.*;
import com.hms.anikdv.code.exceptions.ResourceNotFoundException;
import com.hms.anikdv.code.payloads.PatientPayload;
import com.hms.anikdv.code.payloads.UserPayload;
import com.hms.anikdv.code.repositories.PatientRepository;
import com.hms.anikdv.code.repositories.RoleRepository;
import com.hms.anikdv.code.repositories.UserRepository;
import com.hms.anikdv.code.services.PatientService;
import com.hms.anikdv.code.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @info This Class is implements PatientService interface
 * @category Service
 * @author AnikDV
 */
@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private ModelMapperConfiguration modelMapperConfiguration;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;


    /**
     * This Method For Registration Patient
     *
     * @param userPayload
     * @return a new registered patient
     */
    @Override
    public UserPayload registerPatient(UserPayload userPayload) {
        // convert the dto to entity
        User user = this.modelMapperConfiguration.modelMapper().map(userPayload, User.class);

        // setting properties
        user.setName(userPayload.getName());
        user.setDob(userPayload.getDob());
        user.setAddress(userPayload.getAddress());
        user.setPhoneNumber(userPayload.getPhoneNumber());
        user.setEmail(userPayload.getEmail());
        user.setPassword(this.passwordEncoder.encode(userPayload.getPassword()));

        // set user into patient
        Patient patient = new Patient();
        patient.setUser(user);

        // set user role as patient
        Role patient_role = this.roleRepository.findById(AppConstants.PATIENT)
                .orElseThrow(() -> new RuntimeException("Patient role not found"));
        patient_role.setUser(user);
        user.getRoles().add(patient_role);

        // Save the user (cascades to roles if properly configured)
        User registeredUser = this.userRepository.save(user);
        // and also save patient
        this.patientRepository.save(patient);

        return this.modelMapperConfiguration.modelMapper().map(registeredUser, UserPayload.class);
    }

    /**
     * This Method For Update Patient
     *
     * @param patientPayload
     * @param patientId
     * @return updated Patient
     * @throws Exception
     */
    @Override
    public PatientPayload updatePatient(final Long patientId, final Long userId, final PatientPayload patientPayload) throws Exception {
        // Retrieve the patient using the patientId
        Patient existingPatient = patientRepository.findById(patientId)
                .orElseThrow(() -> new Exception("Patient with ID " + patientId + " not found!"));

        // Verify if the userId associated with this patient matches the userId parameter
        if (existingPatient.getUser() == null || existingPatient.getUser().getUserid() != userId) {
            throw new Exception("Unauthorized update attempt or user not associated with this patient");
        }

        // Update patient fields with data from patientPayload
        if (patientPayload.getCurrentHealthStatus() != null) {
            existingPatient.setCurrentHealthStatus(patientPayload.getCurrentHealthStatus());
        }
        if (patientPayload.getBloodGroup() != null) {
            existingPatient.setBloodGroup(patientPayload.getBloodGroup());
        }
        if (patientPayload.getUrgency() != null) {
            existingPatient.setUrgency(patientPayload.getUrgency());
        }

        // Save the updated patient in the repository
        Patient updatedPatient = patientRepository.save(existingPatient);

        // Convert to PatientPayload for response
        return this.modelMapperConfiguration.modelMapper().map(updatedPatient, PatientPayload.class);
    }

    /**
     * This Method For Delete Patient
     *
     * @param patientId
     * @return Delete Patient
     * @throws Exception
     */
    @Override
    public boolean deletePatient(Long patientId) throws ResourceNotFoundException {
        boolean flag = false;
        try {
            Patient patient = this.patientRepository.findById(patientId)
                    .orElseThrow(() -> new UsernameNotFoundException("Patient Not Found!"));
            this.patientRepository.delete(patient);
            flag = true;
            return flag;
        } catch (ResourceNotFoundException e) {
            e.getMessage();
            return flag;
        }
    }

    /**
     * This Method For getting all Patient
     *
     * @return All Patients
     */
    @Override
    public List<PatientPayload> GetAllPatients() {
        List<Patient> patients = this.patientRepository.findAll();
        List<PatientPayload> patientPayloads = patients.stream().map(patient -> this.modelMapperConfiguration.modelMapper().map(patient, PatientPayload.class)).collect(Collectors.toList());
        return patientPayloads;
    }

    /**
     * This Method For get Patient
     *
     * @param patientId
     * @return a Patient.
     */
    @Override
    public PatientPayload GetPatientById(Long patientId) {
        Patient patient = this.patientRepository.findById(patientId).get();
        return this.modelMapperConfiguration.modelMapper().map(patient, PatientPayload.class);
    }
}
