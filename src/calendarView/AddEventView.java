/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NumberAdditionUI.java
 *
 * Created on Jan 5, 2012, 7:19:30 PM
 */
package calendarView;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.*;
import javax.swing.DefaultListModel;

/**
 *
 * @author FRC2010-Winner2
 */
public class AddEventView extends javax.swing.JFrame {

    private List<String> userList;
    DefaultListModel listModel = new DefaultListModel();

    /** Creates new form NumberAdditionUI */
    public AddEventView() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AddButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDescription = new javax.swing.JTextArea();
        label1 = new java.awt.Label();
        jTextFieldStartTime = new javax.swing.JTextField();
        jTextFieldStopTime = new javax.swing.JTextField();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        textFieldEvName = new java.awt.TextField();
        label4 = new java.awt.Label();
        jScrollPane2 = new javax.swing.JScrollPane();
        jUserList = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        AddButton.setText("Add");

        jButton2.setText("Cancel");
        jButton2.setActionCommand("addEventCancel");

        jTextAreaDescription.setColumns(20);
        jTextAreaDescription.setRows(5);
        jTextAreaDescription.setText("Add description here");
        jScrollPane1.setViewportView(jTextAreaDescription);

        label1.setText("Description");

        jTextFieldStartTime.setText("jTextField1");
        jTextFieldStartTime.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextFieldStartTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldStartTimeActionPerformed(evt);
            }
        });

        jTextFieldStopTime.setText("jTextField2");
        jTextFieldStopTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldStopTimeActionPerformed(evt);
            }
        });

        label2.setText("Start time");

        label3.setText("End time");

        textFieldEvName.setText(".....");
        textFieldEvName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldEvNameActionPerformed(evt);
            }
        });

        label4.setText("Event Name");

        jUserList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "listModel" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jUserList);

        jLabel1.setText("Select other participants");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldStartTime, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                            .addComponent(jTextFieldStopTime, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                            .addComponent(textFieldEvName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                        .addGap(79, 79, 79)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1)))
                .addGap(40, 40, 40))
            .addGroup(layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldStopTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(textFieldEvName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 11, 11))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddButton)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        label1.getAccessibleContext().setAccessibleName("label11");

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void textFieldEvNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldEvNameActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_textFieldEvNameActionPerformed

private void jTextFieldStopTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldStopTimeActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jTextFieldStopTimeActionPerformed

private void jTextFieldStartTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldStartTimeActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jTextFieldStartTimeActionPerformed
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(NumberAdditionUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(NumberAdditionUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(NumberAdditionUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(NumberAdditionUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        System.out.print("Hello \n"); 
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            public void run() {
//                new NumberAdditionUI().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton AddButton;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaDescription;
    private javax.swing.JTextField jTextFieldStartTime;
    private javax.swing.JTextField jTextFieldStopTime;
    private javax.swing.JList jUserList;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.TextField textFieldEvName;
    // End of variables declaration//GEN-END:variables
    private CalendarEvent event;

    void setStartTime(int hour, int minute) {
        String s = String.format("%02d:%02d", hour, minute);
        jTextFieldStartTime.setText(s);
    }

    void setStopTime(int hour, int minute) {
        String s = String.format("%02d:%02d", hour, minute);
        jTextFieldStopTime.setText(s);
    }

    CalendarEvent getCalendarEvent(/*CalendarEvent calEv*/) {
        int startMinute, stopMinute;
        int startHour, stopHour;

        /* if the event is returned without validity, it shouldn't be added */
        event.setValidity(false);

        Pattern p = Pattern.compile("\\d+"); /*find numbers into a string */

        /* Read start and stop hour */
        /* Data is expected to be received in the format hour:minute*/
        Matcher m = p.matcher(jTextFieldStartTime.getText());
        if (!m.find()) {
            return event; /* we didn't find an int so we leave */
        }
        startHour = Integer.parseInt(m.group());

        if (!(startHour >= 0 && startHour <= 23)) /*sanitize data */ {
            return event;
        }

        if (!m.find()) {
            return event;
        }
        startMinute = Integer.parseInt(m.group());

        if (!(startMinute >= 0 && startMinute < 60)) /*sanitize data */ {
            return event;
        }


        event.setStartTime(startHour, startMinute);


        m = p.matcher(jTextFieldStopTime.getText());
        if (!m.find()) {
            return event; /* we didn't find an int so we leave */
        }
        stopHour = Integer.parseInt(m.group());

        if (!(stopHour >= 0 && stopHour <= 23)) /*sanitize data */ {
            return event;
        }

        if (!m.find()) {
            return event;
        }
        stopMinute = Integer.parseInt(m.group());

        if (!(stopMinute >= 0 && stopMinute < 60)) /*sanitize data */ {
            return event;
        }

        event.setStopTime(stopHour, stopMinute);

        if (stopHour < startHour) {
            return event;
        }
        if ((stopHour == startHour) && (stopMinute < startMinute)) {
            return event;
        }
        event.setName(textFieldEvName.getText());
        event.setDescription(jTextAreaDescription.getText());
        List<String> users = jUserList.getSelectedValuesList();
        for (int i = 0; i < users.size(); i++) {
            if ( !event.hasPerson(users.get(i)) )
            {
                event.addParticipant(users.get(i));
            }
        }
        event.setValidity(true);

        return event;
    }

    void setCalendarEvent(CalendarEvent calEv) {
        int dStartHour, dStartMinute, dStopHour, dStopMinute;
        event = calEv;

        dStopHour = calEv.getStopHour();
        dStopMinute = calEv.getStopMinute();
        dStartHour = calEv.getStartHour();
        dStartMinute = calEv.getStartMinute();

        setStartTime(dStartHour, dStartMinute);
        setStopTime(dStopHour, dStopMinute);
    }

    void addButtonActionListeners(ActionListener al) {
        AddButton.addActionListener(al);
        jButton2.addActionListener(al);
    }

    void initJList() {
        jUserList.setModel(new javax.swing.AbstractListModel() {

            @Override
            public int getSize() {
                return userList.size();
            }

            @Override
            public Object getElementAt(int i) {
                return userList.get(i);
            }
        });
    }

    void setUserList(List<String> userList) {
        this.initJList();
        this.userList = userList;
        //String s = "ss";
        // jUserList.add(s);
    }
}
