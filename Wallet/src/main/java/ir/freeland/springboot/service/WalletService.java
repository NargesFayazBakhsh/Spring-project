package ir.freeland.springboot.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ir.freeland.springboot.exception.PersonNotFoundException;
import ir.freeland.springboot.model.Person;
import ir.freeland.springboot.model.Transaction;
import ir.freeland.springboot.model.TransactionStatus;
import ir.freeland.springboot.model.TransactionType;
import ir.freeland.springboot.model.Wallet;
import ir.freeland.springboot.model.WalletDTO;
import ir.freeland.springboot.repository.PersonRepository;
import ir.freeland.springboot.repository.TransactionRepository;
import ir.freeland.springboot.repository.WalletRepository;
import jakarta.transaction.Transactional;

@Service
public class WalletService {
	public final double DAILY_TRANSFER_LIMIT = 10000000; 

	private static final Logger log = LoggerFactory.getLogger(WalletService.class);
	@Autowired
	private WalletRepository walletRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	// create or update a Wallet
	public Wallet saveWallet(Wallet wallet) {
		log.info("Create or update Wallet is being performed");
		log.debug("Create Wallet debug info");

		return walletRepository.save(wallet);
	}
	
	// read a Wallet by id
	public Wallet getWalletById(Long id) throws Exception{
		log.info("Read Wallet by id is being performed");
		log.debug("Read Wallet by id debug info");

		return walletRepository.findById(id).orElseThrow(() -> new Exception("Wallet not found"));
	}
		
	// read a Wallet by id
	public List<Wallet> getAllWallets(){
		log.info("Read Wallet is being performed");
		log.debug("Read Wallet debug info");

		return walletRepository.findAll();
	}

	// delete a Wallet by id
	public void deleteWalletById(Long id){
		log.info("Delete Wallet is being performed");
		log.debug("Delete Wallet debug info");

		walletRepository.deleteById(id);
	}

	// this is for in create wallet don't need all person details
	// only need person id
	@Transactional
	public Wallet createWallet(WalletDTO walletDTO) throws Exception{
		log.info("Create Wallet is being performed");
		log.debug("Create Wallet debug info");
		Person person = personRepository.findById(walletDTO.getPerson_id())
				.orElseThrow(() -> new PersonNotFoundException("Person not found"));	
		// create a new wallet
		Wallet wallet = new Wallet();
		wallet.setAccountDate(walletDTO.getAccountDate());
		wallet.setAccountNum(walletDTO.getAccountNum());
		wallet.setAmount(walletDTO.getAmount());
		wallet.setDailyTransferSum(0);
		wallet.setPerson(person);
		wallet.setShebaNum(walletDTO.getShebaNum());
				
		System.out.println(wallet);
		return walletRepository.save(wallet);
	}
	/*
	public Wallet addWallet(WalletDTO walletDTO) {
		Person person = personRepository.findById(personId.)
				.orElseThrow(() -> new Exception("Person not found"));	
		
	}*/
	
	@Transactional
	public Wallet increaseAmount(Long walletId ,double amount, String details) throws Exception{
		log.info("Increase Amount is being performed");
		log.debug("Increase Amount debug info");

		Wallet wallet = walletRepository.findById(walletId)
				.orElseThrow(() -> new Exception("Wallet not found"));
		wallet.setAmount(wallet.getAmount() + amount);
		Wallet walletUpdate = walletRepository.save(wallet);
		
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setTransactionDate(new Timestamp(System.currentTimeMillis()));
		transaction.setDetails(details);
		transaction.setStatus(TransactionStatus.DONE);
		transaction.setType(TransactionType.DEBIT);
		transaction.setWallet(wallet);
		transactionRepository.save(transaction);
		
		return walletUpdate;		
	}

	@Transactional
	public Wallet decreaseAmount(Long walletId ,double amount, String details) throws Exception{
		log.info("Decrease Amount is being performed");
		log.debug("Decrease Amount debug info");

		Wallet wallet = walletRepository.findById(walletId)
				.orElseThrow(() -> new Exception("Wallet not found"));
		
		Date today = new Date(System.currentTimeMillis());
		if(wallet.getLast_transaction_date()==null) {
			wallet.setLast_transaction_date(today);
		}
		// reset if last transaction was on a different day 
		if(!today.equals(wallet.getLast_transaction_date())) {
			wallet.setDailyTransferSum(0);
			wallet.setLast_transaction_date(today);
		}
		
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setTransactionDate(new Timestamp(System.currentTimeMillis()));
		transaction.setDetails(details);
		transaction.setWallet(wallet);
		transaction.setType(TransactionType.CREDIT);
		
		if((wallet.getAmount() - amount) <0) {
			transaction.setStatus(TransactionStatus.FAILED);
			transactionRepository.save(transaction);
			throw new Exception("not enough money");
		}
		if(wallet.getDailyTransferSum()+ amount > DAILY_TRANSFER_LIMIT) {
			transaction.setStatus(TransactionStatus.FAILED);
			transactionRepository.save(transaction);
			throw new Exception("more than daily limit transfer money.");
		}
		
		
		
		wallet.setDailyTransferSum(wallet.getDailyTransferSum()+ amount);
		wallet.setAmount(wallet.getAmount() - amount);
		Wallet walletUpdate = walletRepository.save(wallet);
		
		transaction.setStatus(TransactionStatus.DONE);
		transactionRepository.save(transaction);
		
		return walletUpdate;
	}

}
