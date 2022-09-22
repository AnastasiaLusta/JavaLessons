package step.learning.oop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Newspaper extends Literature implements Printable, Periodic{
    private Date date;
    private Calendar calendar;
    private LocalDate localDate;
    static private final SimpleDateFormat
            dateParser = new SimpleDateFormat("yyyy-MM-dd");
    static private final SimpleDateFormat
            datePrinter = new SimpleDateFormat("dd.MM.yy");
    static private final SimpleDateFormat
            datePrinterShort = new SimpleDateFormat("dd.MM");

    public Date getDate() {
        return date;
    }

    public Newspaper setDate(String date) throws ParseException {
        this.date = dateParser.parse(date);
        this.calendar = Calendar.getInstance();
        return this;
    }

    @Override
    public Newspaper setTitle(String title) {
        super.setTitle(title);
        return this;
    }

    @Override
    public void print() {
        Calendar now = Calendar.getInstance(); // calendar to compare with now and another date
        String formatter = (this.date.getDate() == calendar.get(Calendar.DATE))
                ? "today"
                : (now.get(Calendar.YEAR) == calendar.get(Calendar.YEAR))
                ? datePrinterShort.format(this.getDate()) // shows short format if its current year
                : datePrinter.format(this.getDate()); // shows full format if its not current year


        System.out.printf("Newspaper '%s' %s%n", super.getTitle(), formatter);
    }


}
