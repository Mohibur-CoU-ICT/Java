package library.management.system;

import java.util.Date;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is for practice purpose
 *
 * @author hp
 */
public class Test {

    private static String getTodaysDate() {

        Calendar c = Calendar.getInstance();

        Date date = c.getTime();
//        System.out.println(date);
        System.out.println(date.toGMTString());
        System.out.println(date.toLocaleString().substring(0, 11));

        int day = date.getDate();
        int month = date.getMonth();
        int year = date.getYear();

        String m[] = {"Jan", "Feb", "Mar", "April", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String todaysDate = m[month] + " " + Integer.toString(day) + ", " + Integer.toString(1900 + year);

        return todaysDate;
    }

    private static void checkPassword(String password) {
        String ragex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()+=_-])(?=\\S+$).{8,20}$";
        String ragey = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&_+=])(?=\\S+$).{8,20}$";
        Pattern pattern1 = Pattern.compile(ragex);
        Pattern pattern2 = Pattern.compile(ragey);
        Matcher matcher1 = pattern1.matcher(password);
        Matcher matcher2 = pattern2.matcher(password);
        System.out.println(password + " " + matcher1.matches() + " " + matcher2.matches());
    }

    public static void main(String[] args) {
        System.out.println(getTodaysDate());
        String num = "12";
        System.out.println(Integer.parseInt(num));

        checkPassword("Mosiur123");
        checkPassword("Mosiur!123");
        checkPassword("Mosiur@123");
        checkPassword("Mosiur#123");
        checkPassword("Mosiur$123");
        checkPassword("Mosiur%123");
        checkPassword("Mosiur^123");
        checkPassword("Mosiur&123");
        checkPassword("Mosiur*123");
        checkPassword("Mosiur(123");
        checkPassword("Mosiur)123");
        checkPassword("Mosiur-123");
        checkPassword("Mosiur_123");
        checkPassword("Mosiur+123");
        checkPassword("Mosiur=123");
        checkPassword("Mosiur 123");
        checkPassword("Mos iur");
        checkPassword("osiur123@");

    }
}
