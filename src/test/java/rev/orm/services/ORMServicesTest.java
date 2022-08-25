package rev.orm.services;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rev.orm.models.Account;

public class ORMServicesTest {

	private ORMServices ormService = new ORMServices();

	private Account account;
	//Implement Mockito 
	@BeforeEach
	public void setUp() {
		account = new Account("TestFirstName", "TestLastName","TestUser","Test@Test.com",111111 );
	}
	
	@Test
	public void insertNewObjectTest() {
		ormService.insertNewObject(account);
	}
	
	@Test
	public void retriveRowContentByColumnTest() {
		
		assertNotNull(ormService.retriveRowContentByColumn("admin", 3, account));
		assertNull(ormService.retriveRowContentByColumn("RandomUser", 3, account));
	}
	
	@Test
	public void retriveAllTest() {
		assertNotNull(ormService.retriveAll(account));
	}
	
	@Test
	public void updateRowContentByColumnTest() {
		ormService.updateRowContentByColumn("Updated", 1,"Testuser", 3, account);
	}
	
	@AfterEach
	public void endOfTests() {
		ormService.deleteRowContentByColumn("TestUser", 3, account);
	}

	
}
