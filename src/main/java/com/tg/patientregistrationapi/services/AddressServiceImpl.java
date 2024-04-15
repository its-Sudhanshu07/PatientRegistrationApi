package com.tg.patientregistrationapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.tg.patientregistrationapi.exceptions.AddressNotFoundException;
import com.tg.patientregistrationapi.exceptions.PatientNotFoundException;
import com.tg.patientregistrationapi.models.Address;
import com.tg.patientregistrationapi.repositories.AddressRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private AddressRepository addressRepo;

	@Override
	public Address addAddress(Address address) {
		// TODO Auto-generated method stub
		log.info("Adding new address");
		return this.addressRepo.save(address);
	}

	@Override
	@Cacheable(value = "Address")
	public List<Address> getAllAddress() {
		// TODO Auto-generated method stub
		log.info("Fetching all addresses");
		return this.addressRepo.findAll();
	}

	@Override
	@Cacheable(value = "Address", key = "#addressId", condition = "#addressId > 0")
	public Address getAddressById(long addressId) {
		// TODO Auto-generated method stub
		log.info("Fetching address by Id:",addressId);
		return this.addressRepo.findById(addressId)
				.orElseThrow(()->new AddressNotFoundException("Address not found with given ID"));
	}

	@Override
	@CachePut(value = "Address", key = "#addressId")
	public Address updateAddress(long addressId,Address address) {
		// TODO Auto-generated method stub
		Address existingAddress=this.getAddressById(addressId);
		
		if (existingAddress != null) {
			existingAddress.setDoorNo(address.getDoorNo());
			existingAddress.setStreetName(address.getStreetName());
			existingAddress.setCity(address.getCity());
			existingAddress.setState(address.getState());
			existingAddress.setCountry(address.getCountry());
			existingAddress.setPostalCode(address.getPostalCode());
			existingAddress.setLongitude(address.getLongitude());
			existingAddress.setLatitude(address.getLatitude());
			return this.addressRepo.save(existingAddress);
		}else
			throw new AddressNotFoundException("Address not "
					+ "found with given Id");
	}

	@Override
	@CacheEvict(value = "Address", key = "#addressId")
	public boolean deleteAddress(long addressId) {
		// TODO Auto-generated method stub
		log.info("Deleting address with ID: {}", addressId);
        Address address = getAddressById(addressId);
        addressRepo.delete(address);
        return true;
	}
	
	
}
