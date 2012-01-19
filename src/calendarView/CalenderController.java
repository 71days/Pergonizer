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
    AddEventView addEventView;
    java.util.List EventList = new ArrayList();
    
    public CalenderController(/*CalendarModel model ,*/ CalendarView tableView, AddEventView addEventView) {
        //this.model = ;
        this.tableView = tableView;
        this.addEventView = addEventView;
        
        tableView.addButtenActionListeners(this);
        addEventView.AddButton.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        String action_com = ae.getActionCommand();
        int dStartHour, dStartMinute, dStopHour, dStopMinute;
        
        System.out.println(action_com);
        if (action_com.equals("addEvent"))
        {
         
            CalendarEvent calEv = new CalendarEvent();
            calEv = tableView.getCalendarEvent();
            dStopHour   = calEv.getStopHour();
            dStopMinute = calEv.getStopMinute(); 
            dStartHour = calEv.getStartHour();
            dStartMinute = calEv.getStartMinute();
                    
            addEventView.setStartTime(dStopHour, dStartMinute);
            addEventView.setStopTime(dStartHour, dStopMinute);
            addEventView.setVisible(true);
        }
        if (action_com.equals("Add"))
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
            
        
        
            tableView.resetTable();
            tableView.repaint();
            tableView.splitCellsIntoHours(EventList);
            addEventView.setVisible(false);
            
            //tableView.setCalendarEvent(calEv);
        }
        if (action_com.equals("deleteEvent"))
        {
            
        }
        if (action_com.equals("cancelAddEvent"))
        {
        }
    }

}
