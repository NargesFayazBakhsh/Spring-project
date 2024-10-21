package ir.freeland.springboot.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "Person")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY
	private Long id;
	
	// کد ملی
//	@NotBlank(message="nationalCode can not be empty")
	@Column(unique = true , nullable = false)
	@Size(min = 10 , max =10)
	@Pattern(regexp = "[0-9]{10}", message="nationalCode must be 10 number")
	private String nationalCode;
	
	
	//@NotBlank(message="mobileNumber can not be empty")
	@Column(unique = true ,nullable = false)
	@Size(min = 11 , max =11)
	@Pattern(regexp = "09[0-9]{9}", message="mobileNumber must be valid")
	private String mobileNum;
	
	//@NotBlank(message="firstname can not be empty")
	@Column(nullable = false)
	@Pattern(regexp="[a-zA-Z]{3,15}", message="firstname must not have number or special character")
    private String firstname;
    
//	@NotBlank(message="lastname can not be empty")
	@Column(nullable = false)
	@Pattern(regexp="[a-zA-Z]{3,15}", message="lastname must not have number or special character")
	private String lastname;

	//@NotBlank(message="password can not be empty")
	@Column(nullable = false)
	@Pattern(regexp="[a-zA-Z0-9]{8,}")
	private String password;
	
	
	// yyyy-MM-dd	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date birthDate ;
	
	
	// if less than 18 need fatherNationalCode
	@Column
	@Size(min = 10 , max =10)
	@Pattern(regexp = "[0-9]{10}", message="nationalCode must be 10 number")
	private String fatherNationalCode;
	
	
//	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;
    
	
	@Column
	@Enumerated(EnumType.STRING)
	private Soldier soldier;
    
	
	//@NotBlank(message="email can not be empty")
	@Email(message="Invalid email format")
	@Column(nullable = false)
	private String email;
    

	// "person" is a field in the Wallet class
	@OneToMany(mappedBy = "person", cascade= CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Wallet> wallets = new HashSet<>();

	
    // setter and getter

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNationalCode() {
		return nationalCode;
	}


	public void setNationalCode(String nationalCode) {
		this.nationalCode = nationalCode;
	}


	public String getMobileNum() {
		return mobileNum;
	}


	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public String getFatherNationalCode() {
		return fatherNationalCode;
	}


	public void setFatherNationalCode(String fatherNationalCode) {
		this.fatherNationalCode = fatherNationalCode;
	}


	public Gender getGender() {
		return gender;
	}


	public void setGender(Gender gender) {
		this.gender = gender;
	}


	public Soldier getSoldier() {
		return soldier;
	}


	public void setSoldier(Soldier soldier) {
		this.soldier = soldier;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Set<Wallet> getWallets() {
		return wallets;
	}


	public void setWallets(Set<Wallet> wallets) {
		this.wallets = wallets;
	}

	public void addWallet(Wallet wallet) {
		this.wallets.add(wallet);
		wallet.setPerson(this);
	}
	
	public void removeWallet(Wallet wallet) {
		this.wallets.remove(wallet);
		wallet.setPerson(null);
	}
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", nationalCode=" + nationalCode + ", mobileNum=" + mobileNum + ", firstname="
				+ firstname + ", lastname=" + lastname + ", password=" + password + ", birthDate=" + birthDate
				+ ", fatherNationalCode=" + fatherNationalCode + ", gender=" + gender + ", soldier=" + soldier
				+ ", email=" + email + ", wallets=" + wallets + "]";
	}

    
    
}
