package org.jsp.springbootcrud.controller;

import java.util.List;

import org.jsp.springbootcrud.dto.Hospital;
import org.jsp.springbootcrud.dto.ResponseStructure;
import org.jsp.springbootcrud.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

	@Autowired
	private HospitalService hospitalService;

	@PostMapping("/{admin_id}")
	public ResponseEntity<ResponseStructure<Hospital>> saveHospital(@RequestBody Hospital hospital,
			@PathVariable int admin_id) {
		return hospitalService.saveHospital(hospital, admin_id);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<Hospital>> updateHospital(@RequestBody Hospital hospital) {
		return hospitalService.updateHospital(hospital);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Hospital>> findById(@PathVariable int id) {
		return hospitalService.findById(id);
	}

	@GetMapping("/find-by-admin/{admin_id}")
	public ResponseEntity<ResponseStructure<List<Hospital>>> findByAdminId(@PathVariable int admin_id) {
		return hospitalService.findByAdminId(admin_id);
	}

	@GetMapping("/find-by-name/{name}")
	public ResponseEntity<ResponseStructure<List<Hospital>>> findByName(@PathVariable String name) {
		return hospitalService.findByName(name);
	}

	@PostMapping("/find-by-admin")
	public ResponseEntity<ResponseStructure<List<Hospital>>> findByAdmin(@RequestParam long phone,
			@RequestParam String password) {
		return hospitalService.findByAdmin(phone, password);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteHospital(@PathVariable int id) {
		return hospitalService.deleteHospital(id);
	}
}
