package ir.freeland.springboot.model;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Transaction")
public class Transaction {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// شیوه ارسال
	@Column(nullable = false)
	private Timestamp transactionDate;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TransactionType type;
	
	@Column(nullable = false)
	private double amount;
	
	@Column(nullable = false)
	private String details;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="wallet_id", nullable = false)
	private Wallet wallet;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TransactionStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Timestamp date) {
		this.transactionDate = date;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public TransactionStatus getStatus() {
		return status;
	}

	public void setStatus(TransactionStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", transactionDate=" + transactionDate + ", type=" + type + ", amount="
				+ amount + ", details=" + details + ", wallet=" + wallet + ", status=" + status + "]";
	}
	
	
}
