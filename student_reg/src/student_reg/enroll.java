package student_reg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import static student_reg.logIn_frame.user;
import static student_reg.student_tab.loggedIn;

/**
 *
 * @author Third
 */
public class enroll extends javax.swing.JFrame {

    /**
     * Creates new form enroll
     */
    public enroll(String id) {
        initComponents();
        id_num.setText(id);
        showDate();//shows the date
        showPreviewInfo();//shows preview info, but only current_year and current sem
        showTable();//shows the table in which the students will be enrolled in based from current year and current sem
    }

    private enroll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void showDate() {
        //used nby system to tell what date user enrolled
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("MM-dd-yyyy");
        date.setText(s.format(d));
    }

    void showPreviewInfo() {

        //I used an array because I didn't use integers to represent years
        //this allows the system and database to be more easily understood instead of just using numbers to represent years
        String arr_year[] = {"Year 1", "Year 2", "Year 3", "Year 4"};

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password_db);

            //takes out the user id of the user and uses it as reference to the database information of user
            String pid = loggedIn.getText();
            System.out.println(loggedIn.getText());
            System.out.println(loggedIn1.getText());

            pst = con.prepareStatement("SELECT * FROM student_registration WHERE id=?");
            pst.setString(1, pid);
            rs = pst.executeQuery();

            String enroll_year_db = "--";
            String enroll_sem_db = "--";
                   
            if (rs.next() == true) {
                //this takes out the current year and current sem of student
                //this will be used to evaluate what the enroll_year and enroll_sem of student
                current_year.setText(rs.getString(15));
                current_sem.setText(rs.getString(16));

                
                //i used an if-else because there are factors that can't be decided by the usual switch case
                if (rs.getString(16).equals("Summer (For Year 3 only)")) {
                    //for example, not all years have a summer year, so system needs to know what to do in that situation
                    enroll_year_db = "Year 4";
                    enroll_sem_db = "1st Semester";
                } else if (rs.getString(16).equals("2nd Semester") && (rs.getString(15).equals("Year 3") == false) && (rs.getString(15).equals("Year 4") == false)) {
                    //this tells user if it's not year 3, then there are only two semesters
                    for (int j = 0; j < arr_year.length; j++) {
                        if (arr_year[j].equals(rs.getString(15))) {
                            enroll_year_db = arr_year[j + 1];
                            enroll_sem_db = "1st Semester";
                        }
                    }
                } else if (rs.getString(15).equals("Year 4") && rs.getString(16).equals("2nd Semester")) {
//this is applied if user is currently a Year 4 - 2nd Semester, meaning user is a graduate and shouldn't enroll
                    enroll_year_db = "Graduated";
                    enroll_sem_db = "N/A";

                } else if (rs.getString(15).equals("Year 3") && rs.getString(16).equals("2nd Semester")) {
                    //this tells the user that after unlike the other years, after Year 3 2nd Semester, there is a summmer year
                    enroll_year_db = "Year 3";
                    enroll_sem_db = "Summer (For Year 3 only)";

                } else {
                    //this is when user is from 1st semester, then user will only have a change in enroll_sem and no change in enroll_year
                    enroll_year_db = rs.getString(15);
                    enroll_sem_db = "2nd Semester";
                }

                enroll_year.setText(enroll_year_db);
                enroll_sem.setText(enroll_sem_db);
                //provides custom title based from enroll_year and enroll_sem
                table_title.setText("BELOW ARE YOUR SUBJECTS FOR " + enroll_year_db + " - " + enroll_sem_db);
            } else {
                JOptionPane.showMessageDialog(this, "No record found!");
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        loggedIn1 = new javax.swing.JLabel();
        id_num = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        current_year = new javax.swing.JLabel();
        current_sem = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        enroll_year = new javax.swing.JLabel();
        enroll_sem = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        enroll = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        subject_table = new javax.swing.JTable();
        table_title = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ENROLL MENU");
        jLabel2.setToolTipText("");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("UNOFFICIAL Registration System PLM.");

        date.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        date.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        date.setText("Date");

        loggedIn1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        loggedIn1.setForeground(new java.awt.Color(255, 0, 0));
        loggedIn1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        loggedIn1.setText("Logged in as User ID.");

        id_num.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        id_num.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        id_num.setText("-");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 893, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loggedIn1)
                .addGap(18, 18, 18)
                .addComponent(id_num, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date)
                    .addComponent(loggedIn1)
                    .addComponent(id_num))
                .addGap(2, 2, 2))
        );

        jLayeredPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Current Year");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Semester:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Year:");

        current_year.setText("-");

        current_sem.setText("-");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Semester:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Year:");

        enroll_year.setText("-");

        enroll_sem.setText("-");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Enrolling for ");

        enroll.setText("ENROLL");
        enroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enrollActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(current_year, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(current_sem, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(enroll_year, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(enroll_sem, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(enroll, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(enroll))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(enroll_year, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(enroll_sem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 95, Short.MAX_VALUE))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(current_year, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(current_sem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(current_year))
                .addGap(2, 2, 2)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(current_sem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(enroll_year)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(enroll_sem))
                .addGap(30, 30, 30)
                .addComponent(enroll)
                .addGap(24, 24, 24))
        );

        subject_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Subject code", "Subject title", "Units", "PRE(CO)-REQUISITES"
            }
        ));
        jScrollPane1.setViewportView(subject_table);

        table_title.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        table_title.setText("-");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(table_title, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(table_title)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private static final String username = "root";
    private static final String password_db = "R1RGTzop";
    private static final String url = "jdbc:mysql://localhost:3306/mysql";

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    void showTable() {
        //this displayst the subjects based from the enroll year and enroll sem of user
        //this will display null or empty if user is not yet enrolled
        String arr_year[] = {"Year ", "Year 2", "Year 3", "Year 4"};

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password_db);

            Statement stm = con.createStatement();

            String sql = "select * from bscpe_syllabus where year = '" + enroll_year.getText() + "' AND semester ='" + enroll_sem.getText() + "';";

            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String subject_code = rs.getString(4);
                String subject_title = rs.getString(5);
                String units = rs.getString(6);
                String precoreq = rs.getString(7);

                DefaultTableModel tb1Model = (DefaultTableModel) subject_table.getModel();
                tb1Model.addRow(new Object[]{subject_code, subject_title, units, precoreq});
            }
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unsuccessful login. Error accessing records");
        }
    }

    private void enrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enrollActionPerformed

        //launched when enroll button is pressed
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password_db);

            String ref_id = id_num.getText();
            String enroll_year_db = enroll_year.getText();
            String enroll_sem_db = enroll_sem.getText();
            String last_edit = date.getText();//it also updates your date of last_edit

            //upon enrolling, this records your enroll_year and enroll_sem into your database values
            java.sql.Statement s = con.createStatement();
            s.executeUpdate("UPDATE student_registration set enroll_year='" + enroll_year_db + "',enroll_sem='" + enroll_sem_db + "',last_edit='" + last_edit + "',enrolling_status=" + 1 + " WHERE id='" + ref_id + "'");
        } catch (Exception e) {
            System.out.println("Error in connecting to database");
        }
        JOptionPane.showMessageDialog(this, "You have successfully enrolled");
//it calls on student_menu jframae and transfers back the id_num for its reference to who the user is
        student_tab student_menu = new student_tab(id_num.getText());
        student_menu.show();
        student_menu.setVisible(true);
        dispose();
    }//GEN-LAST:event_enrollActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(enroll.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(enroll.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(enroll.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(enroll.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new enroll().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel current_sem;
    private javax.swing.JLabel current_year;
    public javax.swing.JLabel date;
    private javax.swing.JButton enroll;
    private javax.swing.JLabel enroll_sem;
    private javax.swing.JLabel enroll_year;
    private javax.swing.JLabel id_num;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel loggedIn1;
    private javax.swing.JTable subject_table;
    private javax.swing.JLabel table_title;
    // End of variables declaration//GEN-END:variables
}
