package model;

import java.util.Objects;


public class Person {
  public String fName;
  public String lName;
  public String phone;
  public int id;

  public Person(String fName, String lName, String phone) {
    this.fName = fName;
    this.lName = lName;
    this.phone = phone;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Person other = (Person) obj;
    if (!Objects.equals(this.fName, other.fName)) {
      return false;
    }
    if (!Objects.equals(this.lName, other.lName)) {
      return false;
    }
    if (!Objects.equals(this.phone, other.phone)) {
      return false;
    }
    if (this.id != other.id) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Person{" + "fName=" + fName + ", lName=" + lName + ", phone=" + phone + ", id=" + id + '}';
  }
  
  
}
