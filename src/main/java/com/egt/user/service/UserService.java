package com.egt.user.service;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.egt.user.constants.CommonConstants;
import com.egt.user.entity.Address;
import com.egt.user.entity.Customer;
import com.egt.user.entity.request.AddressDetails;
import com.egt.user.entity.request.CustomerDetails;
import com.egt.user.entity.request.UserRequest;
import com.egt.user.entity.response.UserResponse;
import com.egt.user.exception.InvalidUserRequest;
import com.egt.user.repo.AddressRepo;
import com.egt.user.repo.CustomerRepo;
import com.egt.user.utils.CommonUtils;

@Service
public class UserService {

	private final CustomerRepo customerRepo;
	private final AddressRepo addressRepo;

	public UserService(CustomerRepo customerRepo, AddressRepo addressRepo) {
		this.customerRepo = customerRepo;
		this.addressRepo = addressRepo;
	}

	public ResponseEntity<UserResponse> saveCustomerDetails(UserRequest userRequest) throws InvalidUserRequest {
		if (userRequest == null || userRequest.getAddressDetails() == null || userRequest.getCustomerDetails() == null)
			throw new InvalidUserRequest(CommonConstants.ERROR_INVALID_USER_REQUEST);
		var customer = mapCustomerData(userRequest.getCustomerDetails());
		var address = mapAddressData(userRequest.getAddressDetails(), customer);
		customer.setAddresses(address);
		customerRepo.save(customer);
		addressRepo.saveAll(address);
		return getResponse(CommonConstants.RESPONSE_CUSTOMER_DETAILS_SAVED_SUCCESFULLY, true, HttpStatus.OK);
	}

	private List<Address> mapAddressData(List<AddressDetails> addressList, Customer customer)
			throws InvalidUserRequest {
		if (addressList.size() == 0)
			throw new InvalidUserRequest(CommonConstants.ERROR_INVALID_USER_REQUEST);

		return addressList.stream()
				.map(r -> Address.builder().addressLine1(r.getAddressLine1()).addressLine2(r.getAddressLine2())
						.city(r.getCity()).country(r.getCountry()).postalCode(r.getPostalCode()).state(r.getState())
						.customer(customer).build())
				.toList();
	}

	private Customer mapCustomerData(CustomerDetails customerDetails) throws InvalidUserRequest {
		var phoneNumber = CommonUtils.extractExtensionAndNumber(customerDetails.getPhoneNumber());
		var names = CommonUtils.splitNames(customerDetails.getUserName());
		if (names.length == 1) {
			// Always set lastName with SYMBOL_DOT don't leave lastName blank
			return Customer.builder().dob(CommonUtils.getDate(customerDetails.getDob(), CommonConstants.DATE_PATTERN))
					.email(customerDetails.getEmail()).extension(phoneNumber[0])
					.phoneNumber(CommonUtils.getAsLong(phoneNumber[1])).firstName(names[0])
					.lastName(CommonConstants.SYMBOL_DOT).middleName(CommonConstants.EMPTY_STRING)
					.fullName(customerDetails.getUserName()).isActive(true).registrationDate(new Date()).build();
		} else if (names.length == 2) {
			return Customer.builder().dob(CommonUtils.getDate(customerDetails.getDob(), CommonConstants.DATE_PATTERN))
					.email(customerDetails.getEmail()).extension(phoneNumber[0])
					.phoneNumber(CommonUtils.getAsLong(phoneNumber[1])).firstName(names[0]).lastName(names[1])
					.middleName(CommonConstants.EMPTY_STRING).fullName(customerDetails.getUserName()).isActive(true)
					.registrationDate(new Date()).build();
		} else {
			/*@formatter:off
							 It is guaranteed that name length is 3
							 firstName, middleName, lastName
							 		[0] 	[1] 	[2]
			@formatter:on
			*/
			return Customer.builder().dob(CommonUtils.getDate(customerDetails.getDob(), CommonConstants.DATE_PATTERN))
					.email(customerDetails.getEmail()).extension(phoneNumber[0])
					.phoneNumber(CommonUtils.getAsLong(phoneNumber[1])).firstName(names[0]).lastName(names[2])
					.middleName(names[1]).fullName(customerDetails.getUserName()).isActive(true)
					.registrationDate(new Date()).build();
		}
	}

	private ResponseEntity<UserResponse> getResponse(String info, boolean status, HttpStatus httpStatus) {
		return new ResponseEntity<UserResponse>(UserResponse.builder().info(info).status(status).build(), httpStatus);
	}
}
