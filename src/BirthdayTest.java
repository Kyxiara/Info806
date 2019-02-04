import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * Unit test for simple App.
 */
public class BirthdayTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testBirthday() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateBirthday = dateFormat.parse("04/02/2018");
        Person p = new Person("Toto", "Test", "toto.test@gmail.com", dateBirthday);
        Date today = new Date();

        assertTrue(!p.birthdayToday()); //bad date birthday

        p.setBirthday(today);
        assertTrue(p.birthdayToday()); //good date birthday
    }
}
