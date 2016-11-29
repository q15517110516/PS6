package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person_Test {
	
	private static PersonDomainModel person1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		Date personDate = null;
		try{
			personDate = new SimpleDateFormat("yyyy-MM-dd").parse("1996-07-21");
			
		}catch (ParseException e){
			e.printStackTrace();
		}
		
		person1 = new PersonDomainModel();
		person1.setFirstName("Mingrui");
		person1.setLastName("Liu");
		person1.setBirthday(personDate);
		person1.setCity("Newark");
		person1.setPostalCode(19711);
		person1.setStreet("One Easton Ct");
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception{
		
	}
		
	@Before
	public void setUp() throws Exception{
		
	}
	
	@After
	public void tearDown() throws Exception{
		PersonDomainModel per;
		PersonDAL.deletePerson(person1.getPersonID());
		per = PersonDAL.getPerson(person1.getPersonID());
		assertNull("The person doesn't belong to the database", per);
		
	}
		
	@Test
	public void AddPersontest(){
		PersonDomainModel per;
		per = PersonDAL.getPerson(person1.getPersonID());
		assertNull("The person doesn't belong to this database", per);
		PersonDAL.addPerson(person1);
		
		per = PersonDAL.getPerson(person1.getPersonID());
		System.out.println(person1.getPersonID() + "found");
		assertNotNull("The person should be in this database ", per);
		
	}
	
	@Test
	public void UpdatePerson(){
		PersonDomainModel per;
		final String C_LASTNAME = "Liu";
		
		per = PersonDAL.getPerson(person1.getPersonID());
		assertNull("The person doesn't belong to this database", per);
		PersonDAL.addPerson(person1);
		
		person1.setLastName(C_LASTNAME);
		PersonDAL.updatePerson(person1);
		
		per = PersonDAL.getPerson(person1.getPersonID());
		assertTrue("Name didn't change", person1.getLastName()== C_LASTNAME);
		
	}
	
	@Test
	public void DeletePerson(){
		PersonDomainModel per;
		per = PersonDAL.getPerson(person1.getPersonID());
		assertNull("The person doesn't belong to this database", per);
		
		PersonDAL.addPerson(person1);
		per = PersonDAL.getPerson(person1.getPersonID());
		System.out.println(person1.getPersonID() + "found");
		assertNotNull("The person should be in this database", per);
		
		PersonDAL.deletePerson(person1.getPersonID());
		per = PersonDAL.getPerson(person1.getPersonID());
		assertNull("The person doesn't belong to this database", per);
		
	}
	

}
