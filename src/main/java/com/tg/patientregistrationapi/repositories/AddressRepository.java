package com.tg.patientregistrationapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tg.patientregistrationapi.models.Address;


public interface AddressRepository extends JpaRepository<Address, Long>{

}
