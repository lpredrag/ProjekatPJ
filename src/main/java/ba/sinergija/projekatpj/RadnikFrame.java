package ba.sinergija.projekatpj;

import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class RadnikFrame extends javax.swing.JFrame {

    public RadnikFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        imePrezimeField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        dodajButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dodaj radnika");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(178, 209, 255));
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pencil.png")));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(178, 209, 255));

        jLabel1.setText("Ime i prezime:");

        imePrezimeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imePrezimeFieldActionPerformed(evt);
            }
        });

        jLabel2.setText("Korisničko ime:");

        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });

        jLabel3.setText("Lozinka:");

        dodajButton.setText("Dodaj");
        dodajButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodajButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1)
                        .addComponent(imePrezimeField)
                        .addComponent(usernameField)
                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(dodajButton)))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imePrezimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(dodajButton)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dodajButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodajButtonActionPerformed
        String imePrezime = imePrezimeField.getText();
        String username = usernameField.getText();
        char passwordArray[] = passwordField.getPassword();
        String password = new String(passwordArray);
        if (username.isEmpty() || passwordArray.length == 0 || imePrezime.isEmpty()) {
            System.out.println("Pogresan unos.");
            return;
        }
        if (username.length() > 50 || passwordArray.length > 128 || imePrezime.length() > 50) {
            System.out.println("Unos predugacak.");
            JOptionPane.showMessageDialog(rootPane, "Pogrešan unos", "Greška", JOptionPane.ERROR_MESSAGE);
            imePrezimeField.setText("");
            usernameField.setText("");
            passwordField.setText("");
            return;
        }
        String hashedPassword = DatabaseQuery.hashPasswordSHA256(password);
        if (hashedPassword == null) {
            System.out.println("Hesiranje lozinke nije uspjelo.");
            return;
        }
        ResultSet radnik = DatabaseQuery.getRadnikByUsername(username);
        if (radnik == null) {
            return;
        }
        try {
            if (radnik.next()) {
                JOptionPane.showMessageDialog(rootPane, "Radnik već postoji", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                DatabaseQuery.insertRadnik(imePrezime, username, hashedPassword);
                JOptionPane.showMessageDialog(rootPane, "Novi radnik uspješno kreiran", "OK", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
        } catch(SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, null, exception);
        }
        
    }//GEN-LAST:event_dodajButtonActionPerformed

    private void imePrezimeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imePrezimeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_imePrezimeFieldActionPerformed

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dodajButton;
    private javax.swing.JTextField imePrezimeField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
