public class Student {
  
  private String firstName;
  private String lastName;
  private int idNumber;
  private int hashCode = 0;
  
  public Student(String firstName, String lastName, int idNumber) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.idNumber = idNumber;
  }
  
  public String getFullName() {
    return firstName + " " + lastName;
  }
  
  public int getIdNumber() {
    return idNumber;
  }
  
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  public int hashCode() {//modding by a prime number to hash
    hashCode = idNumber % 31;//using idNumber since those are unique to every student and most likely not changing
    return hashCode;
  }
}