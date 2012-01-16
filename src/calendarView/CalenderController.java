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
        
        System.out.println(action_com);
        if (action_com.equals("addEvent"))
        {
            //tableView.combineActionPerformed(ae);
            CalendarEvent calEv = tableView.getCalendarEvent();
            calEv.getStopHour();
            calEv.getStopMinute();   
            calEv.setStopTime(calEv.getStopHour() + 3, calEv.getStopMinute());
            EventList.add(calEv);
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
