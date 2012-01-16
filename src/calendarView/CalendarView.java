/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarView;

// File: NumberAddition.java
/*
 * (swing1.1beta3) jfc#96
 */

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


public class CalendarView extends JFrame {

    static final int numOfRowsPerHour = 6;
    CellAttribute cellAtt;
    MultiSpanCellTable table;
    JButton b_one;
    JButton b_split;

    Calendar cal = Calendar.getInstance();

    CalendarView() {
        super("Main View");
        java.util.List EventList = new ArrayList();
        CalendarEvent calEv = new CalendarEvent();
        
        calEv.setDate(2,1,2012);
        calEv.setStartTime(1, 0);
        calEv.setStopTime(3, 11);
        calEv.setName("Event1");
        EventList.add(calEv);
        
        calEv = new CalendarEvent();
        calEv.setDate(2,1,2012);
        calEv.setStartTime(3, 41);
        calEv.setStopTime(4, 20);
        calEv.setName("Event2");
        EventList.add(calEv);

//        calEv.setDate(2,1,2012);
//        calEv.setDayOfWeek(3);
//        calEv.setStartTime(1, 0);
//        calEv.setStopTime(3, 11);
//        calEv.setName("Event1");
//        EventList.add(calEv);
//        
//        calEv = new CalendarEvent();
//        calEv.setDate(2,1,2012);
//        calEv.setDayOfWeek(4);
//        calEv.setStartTime(3, 41);
//        calEv.setStopTime(4, 20);
//        calEv.setName("Event2");
//        EventList.add(calEv);       
        
        System.out.print("1 \n");
        AttributiveCellTableModel ml = new AttributiveCellTableModel(24 * numOfRowsPerHour, 8);


        cellAtt =  ml.getCellAttribute();
        table = new MultiSpanCellTable(ml);
        table.setRowHeight(5);
        JScrollPane scroll = new JScrollPane(table);

        b_one = new JButton("addEvent");
        //b_one.addActionListener(this);
        b_split = new JButton("deleteEvent");
        //b_split.addActionListener(this);
        JPanel p_buttons = new JPanel();
        //p_buttons.setLayout(new GridLayout(2, 1));
        p_buttons.add(b_one);
        p_buttons.add(b_split);

        Box box = new Box(BoxLayout.X_AXIS);
        box.add(scroll);
        //box.add(new JSeparator(SwingConstants.HORIZONTAL));
        box.add(p_buttons);
        getContentPane().add(box);
        setSize(800, 400);
        setVisible(true);
        splitCellsIntoHours(EventList);
    }

    public void addButtenActionListeners(ActionListener al) {
        b_one.addActionListener(al);
        b_split.addActionListener(al);
    }
    /*    public void actionPerformed(ActionEvent e) {
    System.out.println("Iepure");
    if (e.getSource() == b_one) {
    combineActionPerformed(e); 
    }
    else if (e.getSource() == b_split) {
    splitActionPerformed(e);
    }
    
    }
     */

    public CalendarEvent getCalendarEvent() {
        int startHour, startMinute, stopHour, stopMinute;
        CalendarEvent calEv = new CalendarEvent();
        int[] columns = table.getSelectedColumns();
        int[] rows = table.getSelectedRows();

        if (columns.length == 0)
        {
            columns = new int[1];
            rows    = new int[1];
            
            rows[0]     = 0;
            columns[0]  = 0;
        }
        calEv.setStartCalendar(cal);
        calEv.setStopCalendar(cal);
        calEv.setDayOfWeek(columns[0]);
        startHour = rows[0] / numOfRowsPerHour;
        startMinute = rows[0] % numOfRowsPerHour;

        stopHour = rows[0] / numOfRowsPerHour;
        stopMinute = rows[0] % numOfRowsPerHour;

        //calEv.IncDate(columns[0]);
        calEv.setStartTime(startHour, startMinute);
        calEv.setStopTime(stopHour, stopMinute);
        //rows.length
        return calEv;
    }

    public void combineActionPerformed(ActionEvent e) {
        int[] columns = table.getSelectedColumns();
        int[] rows = table.getSelectedRows();
        NumberAdditionUI setEventUI = new NumberAdditionUI();
        setEventUI.setVisible(true);

        String name;
        Scanner in = new Scanner(System.in);
        //name = in.nextLine();
        //System.out.println(name);
        ((CellSpan)cellAtt).combine(rows, columns);
        table.clearSelection();
        table.revalidate();
        table.repaint();
    }

    public void splitActionPerformed(ActionEvent e) {
        int column = table.getSelectedColumn();
        int row = table.getSelectedRow();
        ((CellSpan)cellAtt).split(row, column);
        table.clearSelection();
        table.revalidate();
        table.repaint();
    }

    final void splitCellsIntoHours(java.util.List calList) {
        int[] columns;
        int[] rows;
        int numOfEvents = calList.size();
        int eventNum = 0;
        int day, hour;
        int eventDay, startRow, endRow, row;
        CalendarEvent cal;
        columns = new int[1];


        day = 0;
        row = 0;
        // events should be sorted by date
        for (int i = 0; i < numOfEvents; i++) {
            cal = (CalendarEvent)calList.get((i));
            eventDay = cal.getDayOfWeek();
            System.out.println(""+cal.getStartHour()+":"+cal.getStartMinute());
            System.out.println(""+cal.getStopHour()+":"+cal.getStopMinute());
            
            startRow = transformToRowNumber(cal.getStartHour(), cal.getStartMinute());
            endRow = transformToRowNumber(cal.getStopHour(), cal.getStopMinute());
            //endRow = endRow - 1;
            splitCells(day, eventDay, row, startRow);
            table.repaint(); //debug
            //to do merge event
            
            table.setValueAt(cal.getName(), startRow, eventDay);
            rows = new int[endRow - startRow + 1];
            columns[0] = eventDay;
            rows[0] = startRow;
            ((ColoredCell)cellAtt).setBackground(Color.BLACK, 0, 0);
            ((ColoredCell)cellAtt).setForeground(Color.yellow, 0, 0);
            table.repaint(); //debug
            ((CellSpan)cellAtt).combine(rows, columns);
            //splitCells(eventDay, eventDay, startRow, endRow+1);
            day = eventDay;
            row = Math.min(endRow + 1, 24 * numOfRowsPerHour - 1);
        }

        splitCells(day, 6, row, 24 * numOfRowsPerHour - 1);
        table.repaint();//debug
    }

    int transformToRowNumber(int hours, int minutes) {
        return hours * numOfRowsPerHour + minutes * numOfRowsPerHour / 60; //60 minutes per hour
    }

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

    final void splitDay(int day, int startRow, int stopRow) {
        int[] columns;
        int[] firstRows, middleRows, lastRows;
        int iter;
        int aux;

        if (startRow == stopRow)
        {
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
        ((CellSpan)cellAtt).combine(firstRows, columns);
        table.repaint();//debug
        //merge full hours
        while (iter + numOfRowsPerHour < stopRow) {
            middleRows[0] = iter;
            ((CellSpan)cellAtt).combine(middleRows, columns);
            iter += numOfRowsPerHour;
        }
        //merge fractional at the end
        if (iter < stopRow) {
            lastRows = new int[stopRow - iter + 1];
            lastRows[0] = iter;
            ((CellSpan)cellAtt).combine(lastRows, columns);
            table.repaint();//debug
        }
    }
    void resetTable()
    {
        for (int i = 0; i < 7; i++)
            for (int j = 0; j < 24*numOfRowsPerHour; j++)
            {
                ((CellSpan)cellAtt).split(j, i);
            }
    }
}
