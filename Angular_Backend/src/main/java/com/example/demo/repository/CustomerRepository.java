package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Customer;

/**
 * This interface is provide the list of repository.
 * 
 * @author Krushali Talaviya
 * @version openjdk "11.0.16.1"
 * @since March 2023
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	boolean existsByEmail(String email);

	boolean existsByMobileNo(String mobileNo);

	boolean existsByIdNotAndEmail(int id, String email);

	boolean existsByIdNotAndMobileNo(int id, String mobileNo);

}