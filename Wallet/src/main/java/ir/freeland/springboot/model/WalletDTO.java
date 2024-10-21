package ir.freeland.springboot.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


public class WalletDTO {
	private Long id;

	// "person_id" Foreign key
	private Long person_id;
		
	// شماره حساب
	private String accountNum;
					
	// موجودی 
	private double amount;
		
	// تاریخ ایجاد حساب
	private Timestamp accountDate;
			
	// شماره شبا
	private String shebaNum;

	public WalletDTO(Long id, Long person_id, String accountNum, double amount,String shebaNum) {
		super();
		this.id = id;
		this.person_id = person_id;
		this.accountNum = accountNum;
		this.amount = amount;
		this.accountDate = new Timestamp(System.currentTimeMillis());
		this.shebaNum = shebaNum;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPerson_id() {
		return person_id;
	}

	public void setPerson_id(Long person_id) {
		this.person_id = person_id;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getAccountDate() {
		return accountDate;
	}

	public void setAccountDate(Timestamp accountDate) {
		this.accountDate = accountDate;
	}

	public String getShebaNum() {
		return shebaNum;
	}

	public void setShebaNum(String shebaNum) {
		this.shebaNum = shebaNum;
	}


	@Override
	public String toString() {
		return "WalletDTO [id=" + id + ", person_id=" + person_id + ", accountNum=" + accountNum + ", amount=" + amount
				+ ", accountDate=" + accountDate + ", shebaNum=" + shebaNum + "]";
	}

	
}
