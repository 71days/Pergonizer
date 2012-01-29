/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarView;

import java.util.*;
import java.util.Calendar;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    String currentUser = new String("Test");

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
            calEv.persons.add(currentUser);
            EventList.add(calEv);
            Collections.sort(EventList);
            saveList();
            return true;
        }
        return false;
    }

    /**
     * Returns the event list in which the current user is involved.
     * @return 
     */
    List getEventList() {
        List<CalendarEvent> curUserList = new ArrayList();
        CalendarEvent cal;
        Iterator<CalendarEvent> iter = EventList.iterator();
        while (iter.hasNext()) {
            cal = iter.next();
            if (cal.hasPerson(currentUser)) {
                curUserList.add(cal);
            }
        }

        return curUserList;
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
            /* the user participates at the event */
            if (cal.hasPerson(currentUser)) {
                evTime = cal.calStart.getTimeInMillis();

                /* the start time of the current event is similiar to our searched event
                we delete the event*/
                if ((time <= evTime) && (evTime < time + precisionMs)) {
                    /* we have found the event we want to delete */
                    cal.removePerson(currentUser);
                    if (cal.getNumOfPersons() == 0) /* no user is connected to the event */ {
                        iterator.remove();
                    }
                    saveList();
                    return true;
                }
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
            if ((time <= evTime) && (evTime < time + precisionMs)) {
                /* we have found the event we want to delete */
                return cal;
            }
        }
        calendarEvent.setValidity(false);
        return calendarEvent;
    }

    void saveList() {
        // Write to disk with FileOutputStream
        FileOutputStream f_out = null;
        try {
            f_out = new FileOutputStream("myobject.data");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CalendarModel.class.getName()).log(Level.SEVERE, null, ex);
        }

// Write object with ObjectOutputStream
        ObjectOutputStream obj_out = null;
        try {
            obj_out = new ObjectOutputStream(f_out);
        } catch (IOException ex) {
            Logger.getLogger(CalendarModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // Write object out to disk
            obj_out.writeObject(EventList);
        } catch (IOException ex) {
            Logger.getLogger(CalendarModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void loadList() {

        // Read from disk using FileInputStream
        FileInputStream f_in = null;
        try {
            f_in = new FileInputStream("myobject.data");
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(CalendarModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No DB found");
            return;
        }

// Read object using ObjectInputStream
        ObjectInputStream obj_in = null;
        try {
            obj_in = new ObjectInputStream(f_in);
        } catch (IOException ex) {
            Logger.getLogger(CalendarModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // Read an object
            EventList = (List) obj_in.readObject();
        } catch (IOException ex) {
            Logger.getLogger(CalendarModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CalendarModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    List<String> getUserList() {
        List<String> userList = new ArrayList();
        CalendarEvent cal;
        List<String> participantsList;
        String s;

        for (int i = 0; i < EventList.size(); i++) {
            cal = (CalendarEvent) EventList.get(i);
            participantsList = cal.getParticipants();

            for (int j = 0; j < participantsList.size(); j++) {

                s = participantsList.get(j);
                if (!userList.contains(s) && s != currentUser) {
                    userList.add(s);
                }
            }


        }
        return userList;
    }

    void setCurrentUser(String username) {
        currentUser = username;
    }
}
