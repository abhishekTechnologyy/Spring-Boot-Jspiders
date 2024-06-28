package org.jsp.springbootcrud.service;

import java.util.List;
import java.util.Optional;

import org.jsp.springbootcrud.dto.Admin;
import org.jsp.springbootcrud.dto.ResponseStructure;
import org.jsp.springbootcrud.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin) {
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		structure.setMessage("Admin Saved Succesfully");
		structure.setData(adminRepository.save(admin));
		structure.setStatusCode(HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
	}

	public ResponseEntity<ResponseStructure<Admin>> findById(int id) {
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminRepository.findById(id);
		if (recAdmin.isPresent()) {
			structure.setData(recAdmin.get());
			structure.setMessage("Admin Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}

		structure.setData(null);
		structure.setMessage("Admin not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
	}

	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(Admin admin) {
		Optional<Admin> recAdmin = adminRepository.findById(admin.getId());
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		if (recAdmin.isPresent()) {
			Admin dbAdmin = recAdmin.get();
			dbAdmin.setEmail(admin.getEmail());
			dbAdmin.setName(admin.getName());
			dbAdmin.setPassword(admin.getPassword());
			dbAdmin.setPhone(admin.getPhone());
			structure.setMessage("Admin Updated");
			structure.setData(adminRepository.save(admin));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		structure.setMessage("Cannot Update Admin as Id is Invalid");
		structure.setData(null);
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
	}

	public ResponseEntity<ResponseStructure<List<Admin>>> findByName(String name) {
		ResponseStructure<List<Admin>> structure = new ResponseStructure<>();
		List<Admin> admins = adminRepository.findByName(name);
		structure.setData(admins);
		if (admins.isEmpty()) {
			structure.setMessage("No Admins with entered name");
		}
		structure.setMessage("List of Admins with entered name");
		structure.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}

	public ResponseEntity<ResponseStructure<Admin>> verify(long phone, String password) {
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminRepository.findByPhoneAndPassword(phone, password);
		if (recAdmin.isPresent()) {
			structure.setMessage("Verification Successful and Admin Found");
			structure.setData(recAdmin.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		structure.setMessage("Invalid phone number or password");
		structure.setData(null);
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);

	}

	public ResponseEntity<ResponseStructure<String>> delete(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminRepository.findById(id);
		if (recAdmin.isPresent()) {
			structure.setData("Admin Found");
			structure.setMessage("Admin Deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			adminRepository.delete(recAdmin.get());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		structure.setData("Admin Not Found");
		structure.setMessage("Cannot delete Admin as Id is Invalid");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
	}
}
