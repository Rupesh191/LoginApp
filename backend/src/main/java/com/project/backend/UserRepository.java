package com.project.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository 
public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUsername(String Username);
	User findByUsernameAndPassword(String Username, String Password);
	
	@Modifying
	@Transactional
	@Query(value="alter table user AUTO_INCREMENT=1", nativeQuery = true)
	void resetIdValue();
}
