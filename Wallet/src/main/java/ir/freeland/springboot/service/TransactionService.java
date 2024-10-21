package ir.freeland.springboot.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ir.freeland.springboot.model.Transaction;
import ir.freeland.springboot.repository.TransactionRepository;

@Service
public class TransactionService {
	private static final Logger log = LoggerFactory.getLogger(TransactionService.class);
	@Autowired
	private TransactionRepository transactionRepository;
	
	// create or update a Transaction
	public Transaction saveTransaction(Transaction transaction) {
		log.info("Save Transaction is being performed");
		log.debug("Save Transaction debug info");

		return transactionRepository.save(transaction);
	}
	
	// read a Transaction by id
	public Transaction getTransactionById(Long id) throws Exception{
		log.info("Read Transaction is being performed");
		log.debug("Read Transaction debug info");
		return transactionRepository.findById(id).orElseThrow(() -> new Exception("Transaction not found"));
	}
		
	// read a Transaction by id
	public List<Transaction> getAllTransactions(){
		log.info("Read Transaction is being performed");
		log.debug("Read Transaction debug info");

		return transactionRepository.findAll();
	}

	// delete a Transaction by id
	public void deleteTransactionById(Long id){
		log.info("Delete Transaction is being performed");
		log.debug("Delete Transaction debug info");

		transactionRepository.deleteById(id);
	}
	
	// read a Transaction by id
	public List<Transaction> getAllTransactionsUser(){
		log.info("Read Transaction is being performed");
		log.debug("Read Transaction debug info");
		return transactionRepository.findAll();
	}
	
	// read all transaction for especial wallet
	public List<Transaction> getTransactionsByWallet_id(Long wallet_id){
		log.info("Read all Transaction is being performed");
		log.debug("Read all Transaction debug info");
		return transactionRepository.findByWalletId(wallet_id);
	}
}
