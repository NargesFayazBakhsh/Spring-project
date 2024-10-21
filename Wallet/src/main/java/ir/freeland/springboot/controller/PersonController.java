package ir.freeland.springboot.controller;

import java.sql.Date;
import java.util.Calendar;
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

import ir.freeland.springboot.model.Gender;
import ir.freeland.springboot.model.Person;
import ir.freeland.springboot.model.Soldier;
import ir.freeland.springboot.service.PersonService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/person")
public class PersonController {
	private static final Logger log = LoggerFactory.getLogger(PersonController.class);
	public PersonController() {
		super();
	}
	
	@Autowired
	private PersonService personServive;
	
	
	// calculate age
	public int calculateAge(Date birthDate) {
		if(birthDate == null) {
			throw new IllegalArgumentException("birthDate can not be null");
		}
		Calendar birthCal = Calendar.getInstance();
		birthCal.setTime(birthDate);
		
		Calendar todayCal = Calendar.getInstance();
		int age = todayCal.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);
		
		// if the birth date hasn't occurred yet this year
		if(todayCal.get(Calendar.MONTH) < birthCal.get(Calendar.MONTH) || 
				(todayCal.get(Calendar.MONTH) == birthCal.get(Calendar.MONTH) &&
				todayCal.get(Calendar.DAY_OF_MONTH) < birthCal.get(Calendar.DAY_OF_MONTH))){
			age--;
		}
		return age;
	}
	
	// create new Person
	@PostMapping("/create")
	public ResponseEntity<?> createPerson(@Valid @RequestBody Person person){
		log.debug("Debug log: Recived request with person {}", person);
		log.info("Info log: processing request for person {}", person);
		
		if(calculateAge(person.getBirthDate())>18) {
			person.setFatherNationalCode("0000000000");
		}
		
		
		// Male more than 18 must define soldier status
		if((Gender.MALE) == (person.getGender())&& calculateAge(person.getBirthDate())>=18) {
			if(person.getSoldier()==null || person.getSoldier()==Soldier.NOT_APPLICABLE) {
				log.error("Error log: simulating error for Soldier person {}", person);
				
				return ResponseEntity.badRequest().body("Soldier is required for males");
			}
		}else {
			// for female and children 
			person.setSoldier(Soldier.NOT_APPLICABLE);
		}
		if(person.getFatherNationalCode()==null) {
			log.error("Error log: simulating error for FatherNationalCode person {}", person);
			return ResponseEntity.badRequest().body("FatherNationalCode is required for child");
		}

		Person createdPerson = personServive.savePerson(person);
		return ResponseEntity.ok(createdPerson);
		
	}
	

	// read a Person by ID
	@GetMapping("/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable Long id){
		log.debug("Debug log: Recived request with id {}", id);

		try {
			log.info("Info log: processing request for id {}", id);
			Person person = personServive.getPersonById(id);
			return ResponseEntity.ok(person);
		}catch(Exception e) {
			log.error("Error log: simulating error for id {}", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	// read all Persons
	@GetMapping("/all")
	public ResponseEntity<List<Person>> getAllPersons(){
		
		List<Person> persons = personServive.getAllPersons();
		return ResponseEntity.ok(persons);
	}
	
	// read a Person by ID
	@PutMapping("/update/{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person newPerson){
		log.debug("Debug log: Recived request with id {}", id);
		try {
			log.info("Info log: processing request for id {}", id);
			
			Person person = personServive.getPersonById(id);

			person.setNationalCode(newPerson.getNationalCode());
			person.setMobileNum(newPerson.getMobileNum());
			person.setFirstname(newPerson.getFirstname());
			person.setLastname(newPerson.getLastname());
			person.setPassword(newPerson.getPassword());
			person.setBirthDate(newPerson.getBirthDate());
			person.setFatherNationalCode(newPerson.getFatherNationalCode());
			person.setGender(newPerson.getGender());
			person.setSoldier(newPerson.getSoldier());
			person.setEmail(newPerson.getEmail());
			
			
			Person updatePerson = personServive.savePerson(person);
			return ResponseEntity.ok(updatePerson);
		}catch(Exception e) {
			log.error("Error log: simulating error for id {}", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	// delete a Person by ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePerson(@PathVariable Long id){
		log.debug("Debug log: Recived request with id {}", id);
		try {
			    log.info("Info log: processing request for id {}", id);
				personServive.deletePersonById(id);
			return ResponseEntity.ok("Person delete successfully");
		}catch(Exception e) {
			log.error("Error log: simulating error for id {}", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found");
		}
	}
	
}
