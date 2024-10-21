package ir.freeland.springboot.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ir.freeland.springboot.model.Person;
import ir.freeland.springboot.repository.PersonRepository;

@Service
public class PersonService {
	private static final Logger log = LoggerFactory.getLogger(PersonService.class);	
	@Autowired
	private PersonRepository personRepository;
	
	// create or update a Person
	public Person savePerson(Person person) {
		log.info("Create Person is being performed");
		log.debug("Create Person debug info");
		
		return personRepository.save(person);
	}
	
	// read a Person by id
	public Person getPersonById(Long id) throws Exception{
		log.info("Read Person is being performed");
		log.debug("Read Person debug info");
		return personRepository.findById(id).orElseThrow(() -> new Exception("Person not found"));
	}
		
	// read all Persons
	public List<Person> getAllPersons(){
		log.info("Read all Persons is being performed");
		log.debug("Read all Persons debug info");
		return personRepository.findAll();
	}

	// delete a Person by id
	public void deletePersonById(Long id){
		log.info("Delete Person is being performed");
		log.debug("Delete Person debug info");
		personRepository.deleteById(id);
	}
}
