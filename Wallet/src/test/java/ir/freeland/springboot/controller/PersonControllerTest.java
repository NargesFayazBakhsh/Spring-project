package ir.freeland.springboot.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ir.freeland.springboot.controller.PersonControllerTest;
import ir.freeland.springboot.model.Person;
import ir.freeland.springboot.service.PersonService;

@RestController
@RequestMapping("/api/person")
public class PersonControllerTest {
	
	private MockMvc mockMvc;
	
	
	@Autowired
	private PersonService personServive;
	
	@InjectMocks
	private PersonController personController;
	
	@Mock
	private PersonService personService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
		}
	
	@Test
	public void testGetPersonById() throws Exception {
		// Arrange        
		Person person = new Person();
		person.setId(1L);

		when(personService.getPersonById(1L)).thenReturn(person);
		 
	       }
	
	@Test
	public void testCreatePerson() throws Exception {

		// Arrange        
		Person person = new Person();
		person.setId(1L);
		when(personService.savePerson(any(Person.class))).thenReturn(person);
		
	}
}
