package ir.freeland.springboot.controller;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ir.freeland.springboot.model.Gender;
import ir.freeland.springboot.model.Person;
import ir.freeland.springboot.model.Soldier;
import ir.freeland.springboot.model.Wallet;
import ir.freeland.springboot.model.WalletDTO;
import ir.freeland.springboot.service.WalletService;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
	private static final Logger log = LoggerFactory.getLogger(WalletController.class);
	
	public WalletController() {
		super();
	}
	@Autowired
	private WalletService walletservice;

	

	@PostMapping("/create")
	public ResponseEntity<Wallet> createPerson(@RequestBody WalletDTO walletDTO){
		log.debug("Debug log: Recived request with wallet {}", walletDTO);

		try {
			Wallet createdWallet = walletservice.createWallet(walletDTO);
			log.info("Info log: processing request for wallet {}", walletDTO);
			return new ResponseEntity<>(createdWallet,HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Error log: simulating error for wallet {}", walletDTO);
			return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
		}
	}

	
	// read a Wallet by ID
	@GetMapping("/{id}")
	public ResponseEntity<Wallet> getWalletById(@PathVariable Long id){
		log.debug("Debug log: Recived request with ID {}", id);
		log.info("Info log: processing request with ID {}", id);
		log.error("Error log: simulating error for ID {}", id);

		try {
			Wallet wallet = walletservice.getWalletById(id);
			return ResponseEntity.ok(wallet);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	// read all Wallets
	@GetMapping("/all")
	public ResponseEntity<List<Wallet>> getAllPersons(){

		List<Wallet> wallets = walletservice.getAllWallets();
		return ResponseEntity.ok(wallets);
	}
	
	// read a Wallet by ID
	@PutMapping("/update/{id}")
	public ResponseEntity<Wallet> updateWallet(@PathVariable Long id, @RequestBody Wallet newWallet){
		log.debug("Debug log: Recived request with ID {}", id);
		try {
			log.info("Info log: processing request with ID {}", id);
			Wallet wallet = walletservice.getWalletById(id);

			wallet.setPerson(newWallet.getPerson());
			wallet.setAccountNum(newWallet.getAccountNum());
			wallet.setAmount(newWallet.getAmount());
			wallet.setShebaNum(newWallet.getShebaNum());
			
			
			Wallet updateWallet = walletservice.saveWallet(wallet);
			return ResponseEntity.ok(updateWallet);
		}catch(Exception e) {
			log.error("Error log: simulating error for ID {}", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	// delete a Wallet by ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteWallet(@PathVariable Long id){
		log.debug("Debug log: Recived request with ID {}", id);
		try {
			log.info("Info log: processing request with ID {}", id);
			walletservice.deleteWalletById(id);
			return ResponseEntity.ok("Wallet delete successfully");
		}catch(Exception e) {
			log.error("Error log: simulating error for ID {}", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wallet not found");
		}
	}
	
	// Long
	@PutMapping("/increase/{walletId}") 
	public ResponseEntity<Wallet> increaseBalance( @PathVariable Long walletId, @RequestParam double amount, @RequestParam String details) {
		log.debug("Debug log: Recived request with walletId {}", walletId);

		try { 
			log.info("Info log: processing request with walletId {}", walletId);
			Wallet updatedWallet = walletservice.increaseAmount(walletId, amount, details); 
			return new ResponseEntity<>(updatedWallet, HttpStatus.OK); 
			} 
		catch (Exception e) {
			log.error("Error log: simulating error for walletId {}", walletId);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
			} 
		} 
	
	@PutMapping("/decrease/{walletId}") 
	public ResponseEntity<Wallet> decreaseBalance( @PathVariable Long walletId, @RequestParam double amount,@RequestParam String details) {
		log.debug("Debug log: Recived request with walletId {}", walletId);

		try { 
			log.info("Info log: processing request with walletId {}", walletId);
			Wallet updatedWallet = walletservice.decreaseAmount(walletId, amount, details); 
		    return new ResponseEntity<>(updatedWallet, HttpStatus.OK); 
		    } 
		catch (Exception e) { 
			log.error("Error log: simulating error for walletId {}", walletId);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
			} 
		} 
	


}
