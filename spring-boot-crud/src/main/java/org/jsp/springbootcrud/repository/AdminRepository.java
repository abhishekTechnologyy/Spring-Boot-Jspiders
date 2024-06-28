package org.jsp.springbootcrud.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.springbootcrud.dto.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

//	@Query("select a from Admin a where a.name=?1")
//	public List<Admin> findbyName(String name);
	
	public List<Admin> findByName(String name);
	
//	@Query("select a from admin a where a.phone=?1 and a.password=?2")
//	public Optional<Admin> verify(long phone , String password);
//	
	public Optional<Admin> findByPhoneAndPassword(long phone , String password);
	
}
