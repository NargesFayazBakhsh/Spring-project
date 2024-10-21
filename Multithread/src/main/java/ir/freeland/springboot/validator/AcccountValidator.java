package ir.freeland.springboot.validator;

import java.time.LocalDate;

import org.springframework.batch.item.ItemProcessor;

import ir.freeland.springboot.model.Account;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class AcccountValidator{
	/*
}
	public static boolean isValid(Account account) {
		// check account is valid
		return(account.getBalance() <= account.getAccuntLimit() 
				&& account.getAccountNumber().length() == 22) ;
	}*/
	
	/*
0	Long recordNumber;
1	String accountNumber;
2	int accountType;
3	Long customerId;
4	Long accuntLimit;
5	LocalDate OpenDate;
6	Long balance;
	 */
	
	public static boolean isValid(String row) throws Exception{
		String[] cols = row.split("[,;]");
		// check account is valid
		if(Long.parseLong(cols[6]) <= Long.parseLong(cols[4]) 
				&& (cols[1]).length() == 22) {
			return true;
		}
		// if account not valid return null
		return false;	
	}

}
