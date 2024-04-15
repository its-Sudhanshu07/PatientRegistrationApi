package com.tg.patientregistrationapi.services;

import java.util.List;
import com.tg.patientregistrationapi.models.Address;


public interface AddressService {

	Address addAddress(Address address);
	List<Address>getAllAddress();
	Address getAddressById(long addressId);
	Address updateAddress(long addressId, Address address);
	boolean deleteAddress(long addressId);
}
