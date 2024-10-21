package ir.freeland.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ir.freeland.springboot.model.Transaction;
import ir.freeland.springboot.service.TransactionService;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
	private static final Logger log = LoggerFactory.getLogger(TransactionController.class);
	public TransactionController() {
		super();
	}
	
	@Autowired
	private TransactionService transactionServive;
	
	
	// create new Transaction
	@PostMapping("/create")
	public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction){
		log.debug("Debug log: Recived request with transaction {}", transaction);
		log.info("Info log: processing request for transaction {}", transaction);
		log.error("Error log: simulating error for transaction {}", transaction);
		Transaction createdTransaction = transactionServive.saveTransaction(transaction);
		return ResponseEntity.ok(createdTransaction);
	}

	// read a Transaction by ID
	@GetMapping("/{id}")
	public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id){
		log.debug("Debug log: Recived request with ID {}", id);
		log.info("Info log: processing request for ID {}", id);
		log.error("Error log: simulating error for ID {}", id);
		try {
			Transaction transaction = transactionServive.getTransactionById(id);
			return ResponseEntity.ok(transaction);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	// read all Transactions
	@GetMapping("/all")
	public ResponseEntity<List<Transaction>> getAllTransactions(){
		List<Transaction> transactions = transactionServive.getAllTransactions();
		return ResponseEntity.ok(transactions);
	}
	
	// read a Transaction by ID
	@PutMapping("/update/{id}")
	public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction newTransaction){
		log.debug("Debug log: Recived request with ID {}", id);
		
		try {
			log.info("Info log: processing request for ID {}", id);
			Transaction transaction = transactionServive.getTransactionById(id);

			transaction.setTransactionDate(newTransaction.getTransactionDate());
			transaction.setType(newTransaction.getType());
			transaction.setAmount(newTransaction.getAmount());
			transaction.setDetails(newTransaction.getDetails());
			transaction.setStatus(newTransaction.getStatus());
			
			
			Transaction updateTransaction = transactionServive.saveTransaction(transaction);
			return ResponseEntity.ok(updateTransaction);
		}catch(Exception e) {
			log.error("Error log: simulating error for ID {}", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	// delete a Transaction by ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTransaction(@PathVariable Long id){
		log.debug("Debug log: Recived request with ID {}", id);
		log.info("Info log: processing request for ID {}", id);
		log.error("Error log: simulating error for ID {}", id);
		try {
			transactionServive.deleteTransactionById(id);
			return ResponseEntity.ok("Transaction delete successfully");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transaction not found");
		}
	}
	
	// read all Transactions for one wallet
	@GetMapping("/wallet/{wallet_id}")
	public ResponseEntity<List<Transaction>> getTransactionsByWallet_id(@PathVariable Long wallet_id){
		log.debug("Debug log: Recived request with wallet_id {}", wallet_id);
		log.info("Info log: processing request for wallet_id {}", wallet_id);
		log.error("Error log: simulating error for wallet_id {}", wallet_id);
		List<Transaction> transactions = transactionServive.getTransactionsByWallet_id(wallet_id);
		return ResponseEntity.ok(transactions);
	
	}
	
	
}
