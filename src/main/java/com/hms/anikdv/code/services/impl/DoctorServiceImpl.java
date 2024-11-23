package com.hms.anikdv.code.services.impl;

import com.hms.anikdv.code.configuration.ModelMapperConfiguration;
import com.hms.anikdv.code.entities.*;
import com.hms.anikdv.code.exceptions.ResourceNotFoundException;
import com.hms.anikdv.code.payloads.AppointmentPayload;
import com.hms.anikdv.code.payloads.DoctorPayload;
import com.hms.anikdv.code.payloads.UserPayload;
import com.hms.anikdv.code.repositories.DoctorRepository;
import com.hms.anikdv.code.repositories.RoleRepository;
import com.hms.anikdv.code.repositories.UserRepository;
import com.hms.anikdv.code.services.DoctorService;
import com.hms.anikdv.code.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @info This Class is implements DoctorService interface
 * @category Service
 * @author AnikDV
 */
@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ModelMapperConfiguration modelMapperConfiguration;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;


    /**
     * This Method For Registration Doctor User
     *
     * @param userPayload
     * @return a new registered doctor user
     */
    @Override
    public UserPayload registerDoctor(UserPayload userPayload) {
        User user = this.modelMapperConfiguration.modelMapper().map(userPayload, User.class);

        // setting properties
        user.setName(userPayload.getName());
        user.setDob(userPayload.getDob());
        user.setPhoneNumber(userPayload.getPhoneNumber());
        user.setAddress(userPayload.getAddress());
        user.setEmail(userPayload.getEmail());
        user.setPassword(this.passwordEncoder.encode(userPayload.getPassword()));

        // set user into doctor
        Doctor doctor = new Doctor();
        doctor.setUser(user);
        user.setDoctor(doctor);
        // setting properties
        doctor.setSpecialization(userPayload.getDoctorPayload().getSpecialization());
        doctor.setAbout(userPayload.getDoctorPayload().getAbout());

         /*
             assign user role as Doctor.
         */
        Role role = this.roleRepository.findById(AppConstants.DOCTOR)
                .orElseThrow(() -> new RuntimeException("Doctor role not found!"));
        role.setUser(user);
        user.getRoles().add(role);

        /*
        * save  user
        * */
        User registeredDoctor = this.userRepository.save(user);
        return this.modelMapperConfiguration.modelMapper().map(registeredDoctor, UserPayload.class);
    }

    /**
     * This Method For Update Doctor
     *
     * @param doctorPayload
     * @param doctorId
     * @return updated doctor user
     * @throws Exception
     */
    @Override
    public DoctorPayload updateDoctor(DoctorPayload doctorPayload, Long doctorId) throws Exception {
        // search user form resource
        Doctor oldDoctor = this.doctorRepository.findById(doctorId).orElseThrow(() -> new UsernameNotFoundException("Doctor Not Found!"));

        // set user new information's
        oldDoctor.setSpecialization(doctorPayload.getSpecialization());
        oldDoctor.setAbout(doctorPayload.getAbout());

        //oldDoctor.setAppointments(Collections.singleton(this.modelMapperConfiguration.modelMapper().map(doctorPayload.getAppointmentPayload(), Appointment.class)));
       // oldDoctor.setMedications(Collections.singleton(this.modelMapperConfiguration.modelMapper().map(doctorPayload.getMedicationsPayload(), Medication.class)));

        // check user new given pws is equals to user already have pws
        // suppose -> password is act like current password. so, then allow for update pws.
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean matches = encoder.matches(doctorPayload.getUser().getPassword(), oldDoctor.getUser().getPassword());

        if (matches) { // if true
            // then update the user pws
            if (doctorPayload.getUser().getPassword() != null || !doctorPayload.getUser().getPassword().isEmpty()) {
                oldDoctor.getUser().setPassword(this.passwordEncoder.encode(doctorPayload.getUser().getPassword()));
            }
        } else {
            // return throw Exception password not matches
            throw new Exception("Your Current Password Not Matches!");
        }

        // if user Given something For update for her/his Email
        if (doctorPayload.getUser().getEmail() != null || !doctorPayload.getUser().getEmail().isEmpty()) {
            oldDoctor.getUser().setEmail(doctorPayload.getUser().getEmail());
        }

        // update the user
        Doctor updatedoctor = this.doctorRepository.save(oldDoctor);
        return this.modelMapperConfiguration.modelMapper().map(updatedoctor, DoctorPayload.class);
    }

    /**
     * This Method For Delete Doctor
     *
     * @param doctorId
     * @return deleted Doctor or not e.g: True/False
     * @throws Exception
     */
    @Override
    public boolean deleteDoctor(Long doctorId) throws Exception {
        boolean flag = false;

        try {
            Doctor doctor = this.doctorRepository.findById(doctorId)
                    .orElseThrow(() -> new UsernameNotFoundException("Doctor Not Found!"));
            this.doctorRepository.delete(doctor);
            flag = true;
            return flag;
        } catch (ResourceNotFoundException e) {
            e.getMessage();
            return flag;
        }
    }

    /**
     * This Method For getting all Doctors
     *
     * @return All Doctors
     */
    @Override
    public List<DoctorPayload> GetAllDoctors() {
        List<Doctor> doctors = this.doctorRepository.findAll();
       return doctors.stream().map(doctor-> this.modelMapperConfiguration.modelMapper().map(doctor, DoctorPayload.class)).collect(Collectors.toList());
    }

    /**
     * This Method For get doctor
     *
     * @param doctorId
     * @return a user.
     */
    @Override
    public DoctorPayload GetDoctorById(Long doctorId) {
        Doctor doctor = this.doctorRepository.findById(doctorId).get();
        return this.modelMapperConfiguration.modelMapper().map(doctor, DoctorPayload.class);
    }
}
