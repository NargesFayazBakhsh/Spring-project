package ir.freeland.springboot.validator;

import java.time.LocalDate;

import org.springframework.batch.item.ItemProcessor;

import ir.freeland.springboot.model.Customer;

public class CustomerValidator{
	/*
	public static boolean isValid(Customer customer) {
		return (customer.getBirthDate().isAfter(LocalDate.of(1995,01,01))
				&& customer.getNationalId().length() == 10);
	}*/
/*
	
	0 Long customerId;
	1 String name;
	2 String surname;
	3 String address;
	4 String zipcode;
	5 String nationalId;
	6 LocalDate birthDate;
 */
	
	
	public static boolean isValid(Customer customer) throws Exception{
		// check customer BirthDate is after 1995/01/01
				
		if(customer.getBirthDate().isAfter(LocalDate.of(1995,01,01))
				&& customer.getNationalId().length() == 10)
		return true;
				
		return false;	
	}


}
