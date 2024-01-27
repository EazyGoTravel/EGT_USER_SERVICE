package com.egt.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "ADDRESS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@ToString
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ADDRESS_ID")
	Long adddressId;

	@Column(name = "ADDRESS_LINE_1")
	String addressLine1;

	@Column(name = "ADDRESS_LINE_2")
	String addressLine2;

	@Column(name = "CITY")
	String city;

	@Column(name = "STATE")
	String state;

	@Column(name = "POSTAL_CODE")
	Integer postalCode;

	@Column(name = "COUNTRY")
	String country;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

}
