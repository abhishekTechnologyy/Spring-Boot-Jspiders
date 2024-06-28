package org.jsp.springbootcrud.repository;

import java.util.List;

import org.jsp.springbootcrud.dto.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
	@Query("select h from Hospital h where h.admin.id=?1")
	List<Hospital> findByAdminId(int id);

	List<Hospital> findByName(String name);

	@Query("select h from Hospital h where h.admin.phone=?1 and h.admin.password=?2")
	List<Hospital> findByAdmin(long phone, String password);
}