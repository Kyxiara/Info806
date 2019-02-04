import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReaderCSV {

    private ArrayList<Person> listPerson;

    public ReaderCSV(){
        listPerson = new ArrayList<Person>();
    }

    public ArrayList<Person> getListPerson() {
        return listPerson;
    }

    //Takes a csv with a birthday list, and returns a list of Person
    public void csvToListPerson(){
        String csvFile = "CSV_Birthday.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {

            br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] birthday = line.split(cvsSplitBy);

                Date dateBirthday = dateFormat.parse(birthday[3]);

                Person person = new Person(birthday[0], birthday[1], birthday[2], dateBirthday);
                listPerson.add(person);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
