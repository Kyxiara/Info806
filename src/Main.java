import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Main {

    private static ArrayList<Person> listPerson;
    private static String pw;

    public static void displayListPerson(){
        for (int i = 0; i < listPerson.size(); i++){
            System.out.println(listPerson.get(i).toString());
        }
    }

    public static void sendBirthdayMail() throws UnsupportedEncodingException, MessagingException {
        for (int i = 0; i < listPerson.size(); i++){
            if (listPerson.get(i).birthdayToday()){
                SenderMail sender = new SenderMail(pw, listPerson.get(i));
                sender.send();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        pw = args[0]; //password of my E-mail adress
        ReaderCSV reader = new ReaderCSV();
        reader.csvToListPerson();
        listPerson = reader.getListPerson();
        displayListPerson();
        sendBirthdayMail();
    }

}
