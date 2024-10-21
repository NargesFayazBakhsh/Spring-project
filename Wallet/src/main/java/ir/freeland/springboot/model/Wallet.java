package ir.freeland.springboot.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Wallet")
public class Wallet {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		// "person_id" Foreign key
		@ManyToOne(fetch=FetchType.LAZY)
		@JoinColumn(name="person_id", nullable = false)
		private Person person;
	
		
		// شماره حساب
		@Column(nullable = false)
		@Size(min = 13 , max =13)
		@Pattern(regexp = "[0-9]{13}")
		private String accountNum;
		
		// مبلغ انتقال در روز
		@Column
		private double dailyTransferSum = 0.0;
				
		
		// موجودی 
		@Column(nullable = false)
		@Min(10000)
		private double amount;
			
		
		// تاریخ ایجاد حساب
		@Column(nullable = false)
		private Timestamp accountDate;
			
		
		// شماره شبا
		@Column(nullable = false)
		@Pattern(regexp = "IR[0-9]{24}")
		private String shebaNum;
			

		@OneToMany(mappedBy="wallet",cascade = CascadeType.ALL)
		private Set<Transaction> Transactions = new HashSet<>();


		// تاریخ آخرین تراکنش
		@Column
		private Date last_transaction_date;
			

		public Date getLast_transaction_date() {
			return last_transaction_date;
		}


		public void setLast_transaction_date(Date last_transaction_date) {
			this.last_transaction_date = last_transaction_date;
		}


		// setter and getter
		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public Person getPerson() {
			return person;
		}


		public void setPerson(Person person) {
			this.person = person;
		}


		public String getAccountNum() {
			return accountNum;
		}


		public void setAccountNum(String accountNum) {
			this.accountNum = accountNum;
		}


		public double getDailyTransferSum() {
			return dailyTransferSum;
		}


		public void setDailyTransferSum(double dailyTransferSum) {
			this.dailyTransferSum = dailyTransferSum;
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


		public Set<Transaction> getTransactions() {
			return Transactions;
		}


		public void setTransactions(Set<Transaction> transactions) {
			Transactions = transactions;
		}


		@Override
		public String toString() {
			return "Wallet [id=" + id + ", person=" + person + ", accountNum=" + accountNum + ", dailyTransferSum="
					+ dailyTransferSum + ", amount=" + amount + ", accountDate=" + accountDate + ", shebaNum="
					+ shebaNum + ", Transactions=" + Transactions + "]";
		}
		
		
}
