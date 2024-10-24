package ir.freeland.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ir.freeland.springboot.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	List<Transaction> findByWalletId(Long walletId);

}
