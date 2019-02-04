import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {

    private String firstName;
    private String lastName;
    private String email;
    private Date birthday;

    public Person(String firstName, String lastName, String email, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String toString(){
        return firstName + " " + lastName + " (" + email + ") a son anniversaire le " + birthday;
    }

    //Test if his birthday is today
    public boolean birthdayToday(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        return dateFormat.format(today).equals(dateFormat.format(birthday));
    }

}
