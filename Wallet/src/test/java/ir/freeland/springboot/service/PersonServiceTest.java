package ir.freeland.springboot.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;

import ir.freeland.springboot.model.Gender;
import ir.freeland.springboot.model.Person;
import ir.freeland.springboot.repository.PersonRepository;

@Service
public class PersonServiceTest {
	private MockMvc mockMvc;

	@InjectMocks
	private PersonService personService;
	
	@Mock
	private PersonRepository personRepository;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testCreatePerson() {
		// arrange
		Person person = new Person();
		person.setBirthDate(null);
		person.setEmail("Test@gmail.com");
		person.setFatherNationalCode(null);
		person.setFirstname("test1");
		person.setGender(Gender.MALE);
		person.setLastname("Last Test");
		person.setMobileNum("09874563214");
		person.setNationalCode("1234567896");
		person.setPassword("1Test");
		person.setSoldier(null);
		
		// Mock the repository behavior 
		when(personRepository.save(person)).thenReturn(person); 
		
		// Act 
		Person createdPerson = personService.savePerson(person); 
		
		// Verify interaction with repository 
		verify(personRepository, times(1)).save(person); 
		} 
		@Test 
		public void testGetPersonById_PersonFound() { 
			// Arrange 
			Person person = new Person();
			person.setId(10L);
			person.setBirthDate(null);
			person.setEmail("Test@gmail.com");
			person.setFatherNationalCode(null);
			person.setFirstname("test1");
			person.setGender(Gender.MALE);
			person.setLastname("Last Test");
			person.setMobileNum("09874563214");
			person.setNationalCode("1234567896");
			person.setPassword("1Test");
			person.setSoldier(null);
		
			
			// Mock repository behavior 
			when(personRepository.findById(1L)).thenReturn(Optional.of(person)); 
			
		} 
		
		@Test 
		public void testGetPersonById_PersonNotFound() { 
			// Mock repository behavior for a non-existent wallet 
			when(personRepository.findById(2L)).thenReturn(Optional.empty()); 
			// Act 
			verify(personRepository, times(1)).findById(2L); 
			}	

}
