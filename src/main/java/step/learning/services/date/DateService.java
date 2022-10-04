package step.learning.services.date;

import javax.inject.Singleton;
import java.text.SimpleDateFormat;
import java.util.Date;

@Singleton
public class DateService {
    // creates datePrinter using simpledateformat
    static private final SimpleDateFormat datePrinter = new SimpleDateFormat("dd.MM.yyyy");
    // creates timePrinter using simpledateformat
    static private final SimpleDateFormat timePrinter = new SimpleDateFormat("hh.mm.ss");

    /**
     * returns date in format dd.MM.yyyy
     * @return
     */
    public String getDate() {
        return datePrinter.format(new Date());
    }

    /**
     * returns date in format hh:mm:ss
     * @return
     */
    public String getTime() {
        return timePrinter.format(new Date());
    }
}
