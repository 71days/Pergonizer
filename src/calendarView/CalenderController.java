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
public class CalenderController implements ActionListener {
    // CalendarModel model;

    CalendarView tableView;
    AddEventView addEventView;
    CalendarModel calendarModel;

    public CalenderController(CalendarModel calendarModel, CalendarView tableView, AddEventView addEventView) {
        //this.model = ;
        this.tableView = tableView;
        this.addEventView = addEventView;
        this.calendarModel = calendarModel;

        tableView.addButtenActionListeners(this);
        addEventView.AddButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        String action_com = ae.getActionCommand();
        int dStartHour, dStartMinute, dStopHour, dStopMinute;

        System.out.println(action_com);
        if (action_com.equals("addEvent")) {

            CalendarEvent calEv = new CalendarEvent();
            calEv = tableView.getCalendarEvent();

            if (calendarModel.checkEvent(calEv)) {
                addEventView.setCalendarEvent(calEv);
                addEventView.setVisible(true);
            }

        }
        if (action_com.equals("Add")) {
            CalendarEvent calEv = new CalendarEvent();

            calEv = addEventView.getCalendarEvent();

            if (calEv.getValidity()) //data is ok from the perspective of the view
            {
                /* we try to add the event 
                 * if data is ok from the perspective of the model, the function will return true
                 * and the event will be added
                 */
                if (calendarModel.addEvent(calEv)) {
                    tableView.resetTable();
                    tableView.repaint();
                    tableView.splitCellsIntoHours(calendarModel.getEventList());
                }
            }






            addEventView.setVisible(false);


        }
        if (action_com.equals("deleteEvent")) {
        }
        if (action_com.equals("cancelAddEvent")) {
        }
    }
}
