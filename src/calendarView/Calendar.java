/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarView;

import java.awt.*;
import java.awt.event.*;
import javax.swing.WindowConstants.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;
import java.io.*;
import java.util.ArrayList;
import tame.*;
import my.numberaddition.*;
import java.util.Scanner;

/**
 *
 * @author FRC2010-Winner2
 */
public class Calendar {

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {

        java.util.List<String> dummyList = new ArrayList();
        
        CalendarModel model = new CalendarModel();
        CalendarView frame = new CalendarView();
        AddEventView frame2 = new AddEventView();
        EventDescriptionView    eventDescriptionView = new EventDescriptionView();
        LogInView   loginView = new LogInView();

        
        frame2.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        eventDescriptionView.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        frame2.setUserList(dummyList);
        frame2.initJList();
        CalenderController controller = new CalenderController(model, frame, frame2, eventDescriptionView, loginView);

        frame.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.out.println("exit");
                System.exit(0);
            }
        });
    }
}
