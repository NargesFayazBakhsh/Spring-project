package ir.freeland.springboot.model;


import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long recordNumber;
	
	private String accountNumber;
	
	@Min(1)
	@Max(3)
	private int accountType;
	// 1: saving
	// 2: Recording deposit
	// 3: fixed deposit account
	
	private Long customerId;
	private Long accuntLimit;
	private LocalDate OpenDate;
	private Long balance;
	
	public Long getRecordNumber() {
		return recordNumber;
	}
	public void setRecordNumber(Long recordNumber) {
		this.recordNumber = recordNumber;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getAccountType() {
		return accountType;
	}
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getAccuntLimit() {
		return accuntLimit;
	}
	public void setAccuntLimit(Long accuntLimit) {
		this.accuntLimit = accuntLimit;
	}
	public LocalDate getOpenDate() {
		return OpenDate;
	}
	public void setOpenDate(LocalDate openDate) {
		OpenDate = openDate;
	}
	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Account [recordNumber=" + recordNumber + ", accountNumber=" + accountNumber + ", accountType="
				+ accountType + ", customerId=" + customerId + ", accuntLimit=" + accuntLimit + ", OpenDate=" + OpenDate
				+ ", balance=" + balance + "]";
	}
	
	
}
