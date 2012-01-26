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
 * @author Bogdan Sandoi
 */
public class CalenderController implements ActionListener {
    // CalendarModel model;

    CalendarView tableView;
    AddEventView addEventView;
    CalendarModel calendarModel;

    /**
     * Calendar Controller constructor. Registers the model and the views
     * 
     * @param calendarModel 
     * @param tableView
     * @param addEventView 
     */
    public CalenderController(CalendarModel calendarModel, CalendarView tableView, AddEventView addEventView) {
        //this.model = ;
        this.tableView = tableView;
        this.addEventView = addEventView;
        this.calendarModel = calendarModel;

        /* register as an action listener with the tableVeiw and addEventView */
        tableView.addButtenActionListeners(this);
        addEventView.AddButton.addActionListener(this);
    }

    /**
     * Function which handles button actions from tableVeiw and addEventView
     * @param ActionEvent
     */
    public void actionPerformed(ActionEvent ae) {
        String action_com = ae.getActionCommand();
        int dStartHour, dStartMinute, dStopHour, dStopMinute;

        System.out.println(action_com);
        /* AddEvent button pressed in the CalendarView */
        if (action_com.equals("addEvent")) {

            CalendarEvent calEv = new CalendarEvent();
            calEv = tableView.getCalendarEvent();

            if (calendarModel.checkEvent(calEv)) {
                /*Pass the calenderEvent to the addEventView 
                 * This view will gather the needed event data from the user 
                 */
                addEventView.setCalendarEvent(calEv);
                addEventView.setVisible(true);
            }

        }
        /* Add button pushed in the AddEventView */
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

            addEventView.setVisible(false); /* we don't need the addEventView at this point */


        }
        /* deleteEvent button pressed in the Calendar View */
        if (action_com.equals("deleteEvent")) {

            /* try to delete the event */
            if (calendarModel.deleteEvent(tableView.getCalendarEvent(), tableView.getPrecisionMs())) {
                /*if an event was deleted update the view */
                tableView.resetTable();
                tableView.repaint();
                tableView.splitCellsIntoHours(calendarModel.getEventList());

            }
        }
        if (action_com.equals("nextWeek")) {
            tableView.goToNextWeek();
            tableView.resetTable();
            tableView.repaint();
            tableView.splitCellsIntoHours(calendarModel.getEventList());

        }
        if (action_com.equals("previousWeek")) {
            tableView.goToPrevWeek();
            tableView.resetTable();
            tableView.repaint();
            tableView.splitCellsIntoHours(calendarModel.getEventList());

        }
    }
}
