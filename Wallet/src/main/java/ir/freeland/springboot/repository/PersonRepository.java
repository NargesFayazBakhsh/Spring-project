package ir.freeland.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ir.freeland.springboot.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
//	public Optional<Person> findByNationalCode(String nationalCode);

}
