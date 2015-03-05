package model;


import exception.PersonNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MockPersonFacade implements IPersonFacade {

  private final Map<Integer, Person> persons = new HashMap();
  private static int nextId;
  private static IPersonFacade instance = new MockPersonFacade();

  private MockPersonFacade() {
    nextId = 0;
    addTestData();
  }
  
  //Usefull both for unit testing and testing the REST API
  private void addTestData(){
    persons.clear();
    nextId = 0;
    addPerson(new Person("Lars", "Mortensen", "1234"));
    addPerson(new Person("John", "Handsen", "2345"));
    addPerson(new Person("Peter", "Olsen", "3456"));
    addPerson(new Person("John", "McDonald", "4567"));
    addPerson(new Person("George", "Peterson", "5678"));
  }

  public static IPersonFacade getFacade(boolean reseet,boolean testData) {
    if (true) {
      instance = new MockPersonFacade();
    }
    if(testData){
      ((MockPersonFacade)instance).addTestData();
    }
    return instance;
  }

  @Override
  public Person addPerson(Person p) {
    int id = nextId++;
    p.id = id;
    persons.put(id, p);
    return p;
  }

  @Override
  public Person deletePerson(int id){
    Person p = persons.remove(id);
    return p;
  }

  @Override
  public Person getPerson(int id) throws PersonNotFoundException{
    Person p = persons.get(id);
    if(p == null){
    throw new PersonNotFoundException("Person not found");
    }
    
    
    return p;
  }

  @Override
  public Collection<Person> getPersons() {
    return  persons.values();
    
  }

  @Override
  public Person editPerson(Person p) throws PersonNotFoundException  {
    if (persons.get(p.id) == null) {
      throw new PersonNotFoundException("Person not found");
    }
    return persons.put(p.id, p);
  }
//    public static void main(String[] args) {
//        IPersonFacade facade = MockPersonFacade.getFacade(true, true);
//        facade.getPerson(10);
//    }
//        
}

