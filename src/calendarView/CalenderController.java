/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarView;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import my.numberaddition.*;

/**
 *
 * @author Bogdan Sandoi
 */
public class CalenderController implements MouseListener, ActionListener {
    // CalendarModel model;

    CalendarView tableView;
    AddEventView addEventView;
    CalendarModel calendarModel;
    EventDescriptionView eventDescriptionView;
    LogInView   loginView;

    /**
     * Calendar Controller constructor. Registers the model and the views
     * 
     * @param calendarModel 
     * @param tableView
     * @param addEventView 
     */
    public CalenderController(CalendarModel calendarModel, CalendarView tableView,
            AddEventView addEventView, EventDescriptionView eventDescriptionView, LogInView   loginView) {
        //this.model = ;
        this.tableView = tableView;
        this.addEventView = addEventView;
        this.calendarModel = calendarModel;
        this.eventDescriptionView = eventDescriptionView;
        this.loginView = loginView; 

        /* register as an action listener with the tableVeiw and addEventView */
        tableView.addButtonActionListeners(this);
        tableView.addMouseActionListeners(this);
        addEventView.addButtonActionListeners(this);
        addEventView.AddButton.addActionListener(this);
        eventDescriptionView.addButtonActionListeners(this);
        loginView.addButtonActionListeners(this);
        calendarModel.loadList(); /* load events */
        loginView.setVisible(true);

    }

    /**
     * Function which handles button actions from tableVeiw and addEventView
     * @param ActionEvent
     */
    public void actionPerformed(ActionEvent ae) {
        String action_com = ae.getActionCommand();
        int dStartHour, dStartMinute, dStopHour, dStopMinute;

        System.out.println(action_com);
        
        if (action_com.equals("Login"))
        {
            
            calendarModel.setCurrentUser(loginView.getUsername());
            tableView.resetTable();
            tableView.splitCellsIntoHours(calendarModel.getEventList());
            tableView.setVisible(true);
            return;
        }
        /* AddEvent button pressed in the CalendarView */
        if (action_com.equals("addEvent")) {

            CalendarEvent calEv = new CalendarEvent();

            if (tableView.checkSelection()) { // see if the selection is valid
                calEv = tableView.getCalendarEvent();
                if (calendarModel.checkEvent(calEv)) { // check for conflicts
                    /**Pass the calenderEvent to the addEventView 
                     * This view will gather the needed event data from the user 
                     */
                    addEventView.setUserList(calendarModel.getUserList());
                    addEventView.setCalendarEvent(calEv);
                    addEventView.setVisible(true);
                }
            }
            return;

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
            return;

        }
        
        if (action_com.equals("addEventCancel"))
        {
            addEventView.setVisible(false);
            return;
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
            return;
        }
        if (action_com.equals("nextWeek")) {
            tableView.goToNextWeek();
            tableView.resetTable();
            tableView.repaint();
            tableView.splitCellsIntoHours(calendarModel.getEventList());
            return;

        }
        if (action_com.equals("previousWeek")) {
            tableView.goToPrevWeek();
            tableView.resetTable();
            tableView.repaint();
            tableView.splitCellsIntoHours(calendarModel.getEventList());
            return;

        }

        if (action_com.equals("evDescriptorOK")) {
            eventDescriptionView.setVisible(false);
            return;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        CalendarEvent ev;
        if (e.getClickCount() == 2) {
            ev = calendarModel.getEvent(tableView.getCalendarEvent(), tableView.getPrecisionMs());
            if (ev.getValidity()) {
                eventDescriptionView.setEventDetails(ev);
                eventDescriptionView.setVisible(true);
                System.out.println("Description");
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
