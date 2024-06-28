package org.jsp.springbootcrud.service;

import java.util.List;
import java.util.Optional;

import org.jsp.springbootcrud.dto.Admin;
import org.jsp.springbootcrud.dto.Hospital;
import org.jsp.springbootcrud.dto.ResponseStructure;
import org.jsp.springbootcrud.repository.AdminRepository;
import org.jsp.springbootcrud.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {
	@Autowired
	private HospitalRepository hospitalRepository;
	@Autowired
	private AdminRepository adminRepository;

	public ResponseEntity<ResponseStructure<Hospital>> saveHospital(Hospital hospital, int admin_id) {
		ResponseStructure<Hospital> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminRepository.findById(admin_id);
		if (recAdmin.isPresent()) {
			Admin dbAdmin = recAdmin.get();
			dbAdmin.getHospitals().add(hospital);// Adding Hospital to Admin
			hospital.setAdmin(dbAdmin);// Assigning Admin to Hospital
			adminRepository.save(dbAdmin);// Updating Admin
			structure.setData(hospitalRepository.save(hospital));// Adding Hospital
			structure.setMessage("Hospital Added succesfully");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return ResponseEntity.status(HttpStatus.CREATED).body(structure);
		}
		structure.setMessage("Cannot add Hospital as Admin Id is Invalid");
		structure.setData(null);
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
	}

	public ResponseEntity<ResponseStructure<Hospital>> updateHospital(Hospital hospital) {
		ResponseStructure<Hospital> structure = new ResponseStructure<>();
		Optional<Hospital> recHospital = hospitalRepository.findById(hospital.getId());
		if (recHospital.isPresent()) {
			Hospital dbHospital = recHospital.get();
			dbHospital.setFounder(hospital.getFounder());
			dbHospital.setName(hospital.getName());
			dbHospital.setGstNumber(hospital.getGstNumber());
			dbHospital.setYearOfEstablishment(hospital.getYearOfEstablishment());
			structure.setData(hospitalRepository.save(dbHospital));
			structure.setMessage("Hospital Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		structure.setData(null);
		structure.setMessage("Cannot Update Hospital as Id is Invalid");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
	}

	public ResponseEntity<ResponseStructure<Hospital>> findById(int id) {
		ResponseStructure<Hospital> structure = new ResponseStructure<>();
		Optional<Hospital> recHospital = hospitalRepository.findById(id);
		if (recHospital.isPresent()) {
			structure.setData(recHospital.get());
			structure.setMessage("Hospital Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		structure.setData(null);
		structure.setMessage("Hospital Not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
	}

	public ResponseEntity<ResponseStructure<String>> deleteHospital(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Hospital> recHospital = hospitalRepository.findById(id);
		if (recHospital.isPresent()) {
			hospitalRepository.delete(recHospital.get());
			structure.setData("Hospital Found");
			structure.setMessage("Hospital deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		structure.setData("Hospital Not Found");
		structure.setMessage("Cannot delete hospital as Id is Invalid");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
	}

	public ResponseEntity<ResponseStructure<List<Hospital>>> findByAdminId(int admin_id) {
		ResponseStructure<List<Hospital>> structure = new ResponseStructure<>();
		List<Hospital> hospitals = hospitalRepository.findByAdminId(admin_id);
		structure.setData(hospitals);
		if (hospitals.isEmpty())
			structure.setMessage("No Hospitals found for entered Admin Id");
		else
			structure.setMessage("List of Hospitals for entered Admin Id");
		structure.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}

	public ResponseEntity<ResponseStructure<List<Hospital>>> findByName(String name) {
		ResponseStructure<List<Hospital>> structure = new ResponseStructure<>();
		List<Hospital> hospitals = hospitalRepository.findByName(name);
		structure.setData(hospitals);
		if (hospitals.isEmpty())
			structure.setMessage("No Hospitals found for entered Name");
		else
			structure.setMessage("List of Hospitals for entered name");
		structure.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}

	public ResponseEntity<ResponseStructure<List<Hospital>>> findByAdmin(long phone, String password) {
		ResponseStructure<List<Hospital>> structure = new ResponseStructure<>();
		List<Hospital> hospitals = hospitalRepository.findByAdmin(phone, password);
		structure.setData(hospitals);
		if (hospitals.isEmpty())
			structure.setMessage("No Hospitals found for entered Admin");
		else
			structure.setMessage("List of Hospitals for entered Admin");
		structure.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}
}