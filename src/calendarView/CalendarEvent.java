/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarView;

import java.util.Calendar;

/**
 *
 * @author FRC2010-Winner2
 */
public class CalendarEvent {

    Calendar calStart;
    Calendar calStop;
    String name;
    String description;
    
    /**
     * 
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public CalendarEvent() {
        calStart = Calendar.getInstance();
        calStop = Calendar.getInstance();

    }

    public void setStartCalendar(Calendar x)
    {
        calStart = x;
    }
    
    public void setStopCalendar(Calendar x)
    {
        calStop = x;
    }
    public void setStartTime(int hour, int minute) {
        calStart.set(Calendar.HOUR_OF_DAY, hour);
        calStart.set(Calendar.MINUTE, minute);
    }

    public void setStopTime(int hour, int minute) {
        calStop.set(Calendar.HOUR_OF_DAY, hour);
        calStop.set(Calendar.MINUTE, minute);
    }

    public void setDate(int day, int month, int year) {
        calStart.set(Calendar.YEAR, year);
        calStart.set(Calendar.MONTH, month);
        calStart.set(Calendar.DATE, day);

        calStop.set(Calendar.YEAR, year);
        calStop.set(Calendar.MONTH, month);
        calStop.set(Calendar.DATE, day);
    }
    
    public void setDayOfWeek(int day)
    {
        calStart.set(Calendar.DAY_OF_WEEK, day);
        calStop.set(Calendar.DAY_OF_WEEK, day);
    }
    public int getDay() {
        return calStart.get(Calendar.DATE);      
    }
    public int getDayOfWeek() {
        return calStart.get(Calendar.DAY_OF_WEEK);      
    }
    public int getMonth() {
        return calStart.get(Calendar.MONTH);      
    }
    public int getYear() {
        return calStart.get(Calendar.YEAR);    
    }
    public int getStartHour() {
        return calStart.get(Calendar.HOUR_OF_DAY);    
    }
    public int getStartMinute() {
        return calStart.get(Calendar.MINUTE);    
    }
    public int getStopHour() {
        return calStop.get(Calendar.HOUR_OF_DAY);    
    }
    public int getStopMinute() {
        return calStop.get(Calendar.MINUTE);    
    }
}
