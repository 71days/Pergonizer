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
 * @author FRC2010-Winner2
 */
public class CalendarModel {
    java.util.List EventList = new ArrayList();
    
    boolean checkEvent(CalendarEvent calEv) {
        /* to be implemented */
        return true;
    }

    boolean addEvent(CalendarEvent calEv) {
                    EventList.add(calEv);
                    Collections.sort(EventList);
                    return true;
    }

    List getEventList() {
        return EventList;
    }
    
}
