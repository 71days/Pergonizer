/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarView;

import java.util.*;
import java.util.Calendar;
import java.io.*;
import tame.*;
import my.numberaddition.*;
import java.util.Scanner;
import java.lang.String;

/**
 *
 * @author Bogdan Sandoi
 */
public class CalendarModel {

    java.util.List EventList = new ArrayList();

    boolean checkEvent(CalendarEvent calEv) {
        CalendarEvent cal;
        Iterator<CalendarEvent> iterator = EventList.iterator();
        while (iterator.hasNext()) {
            cal = iterator.next();
            /* check to see if the events dont overlap*/
            if (!cal.disjointTo(calEv)) {
                return false;
            }
        }
        return true;
    }

    boolean addEvent(CalendarEvent calEv) {

        if (checkEvent(calEv)) {
            calEv.setValidity(true);
            EventList.add(calEv);
            Collections.sort(EventList);
            return true;
        }
        return false;
    }

    List getEventList() {
        return EventList;
    }

    /**
     * This function tries to delete an event if it exists
     * @param calendarEvent - if the start time of this event is similar by a 
     * precision margin "precisionMs", with an existing event, the existing event will be deleted
     * @param precisionMs  - precision margin
     * @return true if an event was deleted, false otherwise
     */
    boolean deleteEvent(CalendarEvent calendarEvent, long precisionMs) {
        long evTime;
        long time = calendarEvent.calStart.getTimeInMillis();
        CalendarEvent cal;
        Iterator<CalendarEvent> iterator = EventList.iterator();
        while (iterator.hasNext()) {
            cal = iterator.next();
            evTime = cal.calStart.getTimeInMillis();
            
            /* the start time of the current event is similiar to our searched event
             we delete the event*/
            if ((time <= evTime) && (evTime < time + precisionMs))
            {
                /* we have found the event we want to delete */   
                iterator.remove();
                return true;
            }
        }
        return false;
    }
    
    CalendarEvent getEvent(CalendarEvent calendarEvent, long precisionMs) {
        long evTime;
        long time = calendarEvent.calStart.getTimeInMillis();
        CalendarEvent cal;
        Iterator<CalendarEvent> iterator = EventList.iterator();
        while (iterator.hasNext()) {
            cal = iterator.next();
            evTime = cal.calStart.getTimeInMillis();
            
            /* the start time of the current event is similiar to our searched event
             we delete the event*/
            if ((time <= evTime) && (evTime < time + precisionMs))
            {
                /* we have found the event we want to delete */   
                return cal;
            }
        }
        calendarEvent.setValidity(false);
        return calendarEvent;
    }
}
