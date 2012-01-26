/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarView;

import java.util.*;
import java.util.Calendar;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;
import java.io.*;
import tame.*;
import my.numberaddition.*;
import java.util.Scanner;
import java.lang.String;

/**
 * 
 * @author Bogdan Sandoi
 */
public class CalendarView extends JFrame {

    static final int numOfRowsPerHour = 6; /* 10minutes resolution */

    CellAttribute cellAtt;
    MultiSpanCellTable table;
    JButton b_one;
    JButton b_split;
    JButton b_nextWeek;
    JButton b_prevWeek;
    Calendar cal = Calendar.getInstance();

    CalendarView() {
        super("Main View");

        AttributiveCellTableModel ml = new AttributiveCellTableModel(24 * numOfRowsPerHour, 8);


        cellAtt = ml.getCellAttribute();
        table = new MultiSpanCellTable(ml);
        table.setRowHeight(5);
        JScrollPane scroll = new JScrollPane(table);

        b_one = new JButton("addEvent");
        b_split = new JButton("deleteEvent");
        b_nextWeek = new JButton("nextWeek");
        b_prevWeek = new JButton("previousWeek");
        JPanel p_buttons = new JPanel();
        JPanel p_buttons1 = new JPanel();
        p_buttons.add(b_one);
        p_buttons.add(b_split);

        p_buttons1.add(b_nextWeek);
        p_buttons1.add(b_prevWeek);



        Box box = new Box(BoxLayout.Y_AXIS);
        box.add(scroll);
        box.add(p_buttons1);
        box.add(p_buttons);
        getContentPane().add(box);
        setSize(800, 400);
        setVisible(true);

        java.util.List EventList = new ArrayList();
        splitCellsIntoHours(EventList); /* Send a dummy event list*/
    }

    /**
     * Function which is called to register an external action listener
     * @param al
     */
    public void addButtenActionListeners(ActionListener al) {
        b_one.addActionListener(al);
        b_split.addActionListener(al);
        b_nextWeek.addActionListener(al);
        b_prevWeek.addActionListener(al);

    }

    /**
     * Transform the user cell selection into a CalendarEvent
     * @return
     */
    public CalendarEvent getCalendarEvent() {
        int startHour, startMinute, stopHour, stopMinute;
        CalendarEvent calEv = new CalendarEvent();
        int[] columns = table.getSelectedColumns();
        int[] rows = table.getSelectedRows();

        /*if the user hasn't selected anything, make default select */
        if (columns.length == 0) {
            columns = new int[1];
            rows = new int[1];

            rows[0] = 0;
            columns[0] = 0;
        }
        /* initialize the start and stop calendars */
        calEv.setStartCalendar(cal);
        calEv.setStopCalendar(cal);
        /*transform cell selection into day and hours */
        calEv.setDayOfWeek(columns[0]);
        startHour = rows[0] / numOfRowsPerHour;
        startMinute = 10 * (rows[0] % numOfRowsPerHour);

        stopHour = startHour;
        stopMinute = startMinute;

        stopMinute += 30; /*choose by default the stop time to be the start time + 30minutes*/
        stopHour += stopMinute / 60;
        stopMinute %= 60;

        calEv.setStartTime(startHour, startMinute);
        calEv.setStopTime(stopHour, stopMinute);
        //rows.length
        return calEv;
    }

    /**
     * Not used anymore, to be removed
     * @param e
     */
    public void combineActionPerformed(ActionEvent e) {
        int[] columns = table.getSelectedColumns();
        int[] rows = table.getSelectedRows();
        AddEventView setEventUI = new AddEventView();
        setEventUI.setVisible(true);

        String name;
        Scanner in = new Scanner(System.in);
        //name = in.nextLine();
        //System.out.println(name);
        ((CellSpan) cellAtt).combine(rows, columns);
        table.clearSelection();
        table.revalidate();
        table.repaint();
    }

    /**
     * Not used anymore, to be removed
     * @param e
     */
    public void splitActionPerformed(ActionEvent e) {
        int column = table.getSelectedColumn();
        int row = table.getSelectedRow();
        ((CellSpan) cellAtt).split(row, column);
        table.clearSelection();
        table.revalidate();
        table.repaint();
    }

    /**
     * Split Table into hours and events
     * @param calList List which contains the calendar events
     */
    final void splitCellsIntoHours(java.util.List calList) {
        int[] columns;
        int[] rows;
        int numOfEvents = calList.size();
        int eventNum = 0;
        int day, hour;
        int eventDay, startRow, endRow, row;
        CalendarEvent calEv;
        columns = new int[1];


        day = 0;
        row = 0;
        // events are received sorted by date
        for (int i = 0; i < numOfEvents; i++) {
            calEv = (CalendarEvent) calList.get((i));
            if (calEv.hasSameWeek(cal)) {
                eventDay = calEv.getDayOfWeek();
                System.out.println("" + calEv.getStartHour() + ":" + calEv.getStartMinute());
                System.out.println("" + calEv.getStopHour() + ":" + calEv.getStopMinute());

                startRow = transformToRowNumber(calEv.getStartHour(), calEv.getStartMinute(), "start");
                endRow = transformToRowNumber(calEv.getStopHour(), calEv.getStopMinute(), "stop");
                /* Merge the cells till the event */
                splitCells(day, eventDay, row, startRow); // startRow - 1 ? 
                table.repaint(); //debug

                /* Add Event */
                table.setValueAt(calEv.getName(), startRow, eventDay);
                rows = new int[endRow - startRow + 1];
                columns[0] = eventDay;
                rows[0] = startRow;
                ((ColoredCell) cellAtt).setBackground(Color.BLACK, 0, 0);
                ((ColoredCell) cellAtt).setForeground(Color.yellow, 0, 0);
                table.repaint(); //debug
                ((CellSpan) cellAtt).combine(rows, columns);

                day = eventDay;
                row = Math.min(endRow + 1, 24 * numOfRowsPerHour - 1);
            }
        }
        /* Merge the remaining cells*/
        splitCells(day, 6, row, 24 * numOfRowsPerHour - 1);
        table.repaint();//debug
    }

    /**
     * Transform time into a cell coordonate
     * 
     * @param hours
     * @param minutes
     * @param typeOfRow "start" if the row number will be used for the start time
     *                  "stop" if the row number will be used for the stop time
     * @return 
     */
    int transformToRowNumber(int hours, int minutes, String typeOfRow) {
        float result;
        result = hours * numOfRowsPerHour + (float) minutes * numOfRowsPerHour / 60; //60 minutes per hour
        result = (float) Math.ceil(result);

        if (typeOfRow.equals("start")) {
            return (int) result;
        } else if (typeOfRow.equals("stop")) {
            return (int) result - 1;
        }

        assert (false);
        return 0;
    }

    /**
     * Split multiple days into hours
     * @param startDay the first day(column) to be split
     * @param stopDay  the last day(column) to be split
     * @param startRow the first row(column) to be split
     * @param endRow   the last row(column) to be split
     */
    final void splitCells(int startDay, int stopDay, int startRow, int endRow) {
        int[] columns;
        int[] rows;
        int numOfRows;


        if (startDay != stopDay) {
            splitDay(startDay, startRow, 24 * numOfRowsPerHour - 1);
            table.repaint();//debug
            splitDay(stopDay, 0, endRow);
            table.repaint();//debug
        } else {
            splitDay(startDay, startRow, endRow);
            table.repaint();//debug
        }
        for (int i = startDay + 1; i < stopDay; i++) {
            splitDay(i, 0, 24 * numOfRowsPerHour - 1);
            table.repaint();//debug
        }

    }

    /**
     * Split a single day into hours
     * @param day
     * @param startRow 
     * @param stopRow 
     */
    final void splitDay(int day, int startRow, int stopRow) {
        int[] columns;
        int[] firstRows, middleRows, lastRows;
        int iter;
        int aux;

        if (startRow == stopRow) {
            return;
        }
        middleRows = new int[numOfRowsPerHour];

        columns = new int[1];
        columns[0] = day;
        iter = startRow + numOfRowsPerHour;
        iter = iter - (iter % numOfRowsPerHour);

        //merge fractional at the begining
        aux = Math.min(iter, stopRow);
        aux -= startRow;

        firstRows = new int[aux];
        firstRows[0] = startRow;
        ((CellSpan) cellAtt).combine(firstRows, columns);
        table.repaint();//debug
        //merge full hours
        while (iter + numOfRowsPerHour < stopRow) {
            middleRows[0] = iter;
            ((CellSpan) cellAtt).combine(middleRows, columns);
            iter += numOfRowsPerHour;
        }
        //merge fractional at the end
        if (iter < stopRow) {
            lastRows = new int[stopRow - iter];
            lastRows[0] = iter;
            ((CellSpan) cellAtt).combine(lastRows, columns);
            table.repaint();//debug
        }
    }

    /**
     * Reset the table cells
     */
    void resetTable() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 24 * numOfRowsPerHour; j++) {
                ((CellSpan) cellAtt).split(j, i);
                table.setValueAt("", j, i);
            }
        }
    }

    long getPrecisionMs() {
        /* return the time precision of one basic table cell, in ms format*/
        return 60 * 60 * 1000 / numOfRowsPerHour;
    }

    void goToNextWeek() {
        cal.roll(Calendar.WEEK_OF_YEAR, true);
    }

    void goToPrevWeek() {
        cal.roll(Calendar.WEEK_OF_YEAR, false);
    }
}
