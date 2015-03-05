package model;

import exception.PersonNotFoundException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MockPersonFacadeTest {
  
  IPersonFacade facade;
   public MockPersonFacadeTest() {
  }

  @Before
  public void init() {
    //true will create a new facade instance for each test case
    facade = MockPersonFacade.getFacade(true,true);
  }

  @Test
  public void testAddPerson() throws PersonNotFoundException  {
    Person p = new Person("bbb", "bbb", "bbb");
    Person person = facade.addPerson(p);
    p.id=5;
    assertEquals(person, p);
    //Verify that it really was inserted into the list
    assertEquals(p, facade.getPerson(5));
  }
  
  @Test
  public void testGetPerson() throws Exception {
    testAddPerson();
  }
  
    @Test
  public void testGetPersons() {
      assertEquals("Expects 5 Persons", facade.getPersons().size(), 5);
  }
  

  public void testDeletePerson() throws PersonNotFoundException{
    facade.deletePerson(4);
    assertNull(facade.getPerson(4));
  }
  
       
  @Test()
  public void testEditPerson() throws PersonNotFoundException{
    Person p = facade.getPerson(0);
    p.fName = "xxxx";
    facade.editPerson(p);
    assertEquals("Expects first name to be xxxx","xxxx",facade.getPerson(0).fName);
  }
  
}
