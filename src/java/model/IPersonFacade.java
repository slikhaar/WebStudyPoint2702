package model;

import exception.PersonNotFoundException;
import java.util.Collection;

public interface IPersonFacade {
 public Person addPerson(Person p);
 public Person deletePerson(int id) throws PersonNotFoundException;
 public Person getPerson(int id) throws PersonNotFoundException;
 public Collection<Person> getPersons();
 public Person editPerson(Person p) throws PersonNotFoundException; 
}
