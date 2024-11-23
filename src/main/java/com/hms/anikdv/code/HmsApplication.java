package com.hms.anikdv.code;

import com.hms.anikdv.code.entities.Role;
import com.hms.anikdv.code.repositories.RoleRepository;
import com.hms.anikdv.code.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class HmsApplication implements CommandLineRunner {
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(HmsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// if when application is if role database table is not exist then create it.
		// FOR ADMIN ROLE
		Role adminRole = new Role();
		adminRole.setId(AppConstants.ADMIN);
		adminRole.setName("ADMIN");

		// FOR PATIENT ROLE
		Role patientRole = new Role();
		patientRole.setId(AppConstants.PATIENT);
		patientRole.setName("PATIENT");

		// FOR DOCTOR ROLE
		Role doctorRole = new Role();
		doctorRole.setId(AppConstants.DOCTOR);
		doctorRole.setName("DOCTOR");

		// save roles
		List<Role> roles = List.of(adminRole, patientRole, doctorRole);
		this.roleRepository.saveAll(roles);
	}
}
