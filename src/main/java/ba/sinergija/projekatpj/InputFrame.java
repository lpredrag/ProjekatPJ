package ba.sinergija.projekatpj;

import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;

public class InputFrame extends javax.swing.JFrame {

    public InputFrame() {
        initComponents();
        fillComboBox();
    }
    
    private void fillComboBox() {
        ResultSet nazivi = DatabaseQuery.getUslugaNaziv();
        jComboBox1.removeAllItems();
        try {
            while (nazivi.next()) {
                String naziv = nazivi.getString("naziv");
                jComboBox1.addItem(naziv);
            }
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, null, exception);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        gotovoButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novi posao");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(178, 209, 255));
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pencil.png")));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(178, 209, 255));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Usluga:");

        jLabel2.setText("Ime i prezime:");

        gotovoButton.setText("Gotovo");
        gotovoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gotovoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(gotovoButton))
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(gotovoButton)
                .addContainerGap(75, Short.MAX_VALUE))
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

    private void gotovoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gotovoButtonActionPerformed
        try {
            String selectedUslugaNaziv = (String) jComboBox1.getSelectedItem();
            if (selectedUslugaNaziv.isEmpty()) {
                return;
            }

            ResultSet uslugaIds = DatabaseQuery.getUslugaIdByNaziv(selectedUslugaNaziv);
            if (uslugaIds == null) {
                return;
            }

            int uslugaId = 0;
            while (uslugaIds.next()) {
                uslugaId = uslugaIds.getInt("id");
            }
            if (uslugaId == 0) {
                return;
            }
            
            int radnikId = LoginFrame.currentRadnik.getId();
            
            Timestamp datumVrijeme = new Timestamp(System.currentTimeMillis());

            String imePrezime = jTextField1.getText();
            if (imePrezime.isEmpty()) {
                return;
            }

            ResultSet klijentIds = DatabaseQuery.getKlijentByImePrezime(imePrezime);
            if (klijentIds == null) {
                return;
            }

            int klijentId = 0;
            int poslovi = 0;
            while (klijentIds.next()) {
                klijentId = klijentIds.getInt("id");
                poslovi = klijentIds.getInt("poslovi");
            }
            if (klijentId == 0) {
                int newKlijentId = DatabaseQuery.insertKlijent(imePrezime);
                if (newKlijentId == 0) {
                    return;
                }
                DatabaseQuery.insertPosao(datumVrijeme, radnikId, uslugaId, newKlijentId, false);
                this.dispose();
                
            } else {
                DatabaseQuery.updateKlijentPoslovi(klijentId);
                poslovi += 1;
                if (poslovi % 10 == 0) {
                    DatabaseQuery.insertPosao(datumVrijeme, radnikId, uslugaId, klijentId, true);
                } else {
                    DatabaseQuery.insertPosao(datumVrijeme, radnikId, uslugaId, klijentId, false);
                }
                this.dispose();
            }
            
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, null, exception);
        }
        
    }//GEN-LAST:event_gotovoButtonActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton gotovoButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
