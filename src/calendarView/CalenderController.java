/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarView;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import my.numberaddition.*;

/**
 *
 * @author FRC2010-Winner2
 */
public class CalenderController implements  ActionListener{
    // CalendarModel model;
    CalendarView tableView;
    NumberAdditionUI addEventView;
    java.util.List EventList = new ArrayList();
    
    public CalenderController(/*CalendarModel model ,*/ CalendarView tableView, NumberAdditionUI addEventView) {
        //this.model = ;
        this.tableView = tableView;
        this.addEventView = addEventView;
        
        tableView.addButtenActionListeners(this);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        String action_com = ae.getActionCommand();
        int dStartHour, dStartMinute, dStopHour, dStopMinute;
        
        System.out.println(action_com);
        if (action_com.equals("addEvent"))
        {
            //tableView.combineActionPerformed(ae);
            CalendarEvent calEv = new CalendarEvent();
            calEv = tableView.getCalendarEvent();
            dStopHour   = calEv.getStopHour();
            dStopMinute = calEv.getStopMinute(); 
            dStartHour = calEv.getStartHour();
            dStartMinute = calEv.getStartMinute();
            dStopHour = dStopHour + 1;
            calEv.setStartTime(dStartHour, dStartMinute);
            calEv.setStopTime(dStopHour, dStopMinute );
                     
            
            
            EventList.add(calEv);
            Collections.sort(EventList);
            
//        CalendarEvent            calEv = new CalendarEvent();
//        calEv.setDate(2,1,2012);
//        calEv.setDayOfWeek(3);
//        calEv.setStartTime(1, 0);
//        calEv.setStopTime(6, 11);
//        calEv.setName("XXX");
//        tableView.EventList.add(calEv);
//        
//        calEv = new CalendarEvent();
//        calEv.setDate(2,1,2012);
//        calEv.setDayOfWeek(4);
//        calEv.setStartTime(3, 41);
//        calEv.setStopTime(4, 20);
//        calEv.setName("YYY");
//        tableView.EventList.add(calEv);
        
        
            tableView.resetTable();
            tableView.repaint();
            tableView.splitCellsIntoHours(EventList);
            
            //tableView.setCalendarEvent(calEv);
        }
        else if (action_com.equals("deleteEvent"))
        {
            
        }
        else if (action_com.equals("cancelAddEvent"))
        {
        }
    }

}
