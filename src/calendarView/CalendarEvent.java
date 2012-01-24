/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarView;

import java.util.Calendar;

/**
 *
 * @author Bogdan Sandoi
 */
public class CalendarEvent implements Comparable<CalendarEvent> {

    Calendar calStart;
    Calendar calStop;
    String name;
    String description;
    boolean validity;

    /**
     * 
     * @return
     */
    public boolean getValidity() {
        return validity;
    }

    /**
     * 
     * @param validity
     */
    public void setValidity(boolean validity) {
        this.validity = validity;
    }

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

    /**
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     */
    public CalendarEvent() {
        calStart = Calendar.getInstance();
        calStop = Calendar.getInstance();

    }

    /**
     * 
     * @param x
     */
    public void setStartCalendar(Calendar x) {
        calStart = (Calendar) x.clone();
    }

    /**
     * 
     * @param x
     */
    public void setStopCalendar(Calendar x) {
        calStop = (Calendar) x.clone();
    }

    /**
     * 
     * @param hour
     * @param minute
     */
    public void setStartTime(int hour, int minute) {
        calStart.set(Calendar.HOUR_OF_DAY, hour);
        calStart.set(Calendar.MINUTE, minute);
    }

    /**
     * 
     * @param hour
     * @param minute
     */
    public void setStopTime(int hour, int minute) {
        calStop.set(Calendar.HOUR_OF_DAY, hour);
        calStop.set(Calendar.MINUTE, minute);
    }

    /**
     * 
     * @param day
     * @param month
     * @param year
     */
    public void setDate(int day, int month, int year) {
        calStart.set(Calendar.YEAR, year);
        calStart.set(Calendar.MONTH, month);
        calStart.set(Calendar.DATE, day);

        calStop.set(Calendar.YEAR, year);
        calStop.set(Calendar.MONTH, month);
        calStop.set(Calendar.DATE, day);
    }

    /**
     * 
     * @param day
     */
    public void setDayOfWeek(int day) {
        calStart.set(Calendar.DAY_OF_WEEK, day);
        calStop.set(Calendar.DAY_OF_WEEK, day);
    }

    /**
     * 
     * @return
     */
    public int getDay() {
        return calStart.get(Calendar.DATE);
    }

    /**
     * 
     * @return
     */
    public int getDayOfWeek() {
        return calStart.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 
     * @return
     */
    public int getMonth() {
        return calStart.get(Calendar.MONTH);
    }

    /**
     * 
     * @return
     */
    public int getYear() {
        return calStart.get(Calendar.YEAR);
    }

    /**
     * 
     * @return
     */
    public int getStartHour() {
        return calStart.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 
     * @return
     */
    public int getStartMinute() {
        return calStart.get(Calendar.MINUTE);
    }

    /**
     * 
     * @return
     */
    public int getStopHour() {
        return calStop.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 
     * @return
     */
    public int getStopMinute() {
        return calStop.get(Calendar.MINUTE);
    }

    Calendar getCalStart() {
        return calStart;
    }

    Calendar getCalStop() {
        return calStop;
    }
    @Override
    /*
     * This function is used by the java List type, to sort the list
     */
    public int compareTo(CalendarEvent x) {
        return calStart.compareTo(x.getCalStart());
        
    }
    
    /**
     * Function which checks if the calendar Events overlap or not
     * @param x
     * @return true if the events don't overlap(are disjoint), false otherwise
     */
    public boolean disjointTo(CalendarEvent x)
    {
        int comp1, comp2;
        
        comp1 = calStart.compareTo(x.getCalStop()); 
        comp2 = calStop.compareTo(x.getCalStart());
        /* */
        if ((comp1 >= 0) || ( comp2 <= 0))
        {
            return true;
        }
        
        return false;
    }
//    @Override
//    public int compareTo(Object o) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
}
