/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarView;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;
import java.io.*;
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


        CalendarModel model = new CalendarModel();
        CalendarView frame = new CalendarView();
        AddEventView frame2 = new AddEventView();
        EventDescriptionView    eventDescriptionView = new EventDescriptionView();
        
        CalenderController controller = new CalenderController(model, frame, frame2, eventDescriptionView);

        frame.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
