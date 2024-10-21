package ir.freeland.springboot.model;


import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Customer {
	@Id
	@GeneratedValue
	private Long recordNumber;
	
	private Long customerId;
	private String name;
	private String surname;
	private String address;
	private String zipcode;
	private String nationalId;
	private LocalDate birthDate;
	public Long getRecordNumber() {
		return recordNumber;
	}
	public void setRecordNumber(Long recordNumber) {
		this.recordNumber = recordNumber;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getNationalId() {
		return nationalId;
	}
	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	@Override
	public String toString() {
		return "Customer [recordNumber=" + recordNumber + ", customerId=" + customerId + ", name=" + name + ", surname="
				+ surname + ", address=" + address + ", zipcode=" + zipcode + ", nationalId=" + nationalId
				+ ", birthDate=" + birthDate + "]";
	}
	
}
