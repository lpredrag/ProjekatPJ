package ba.sinergija.projekatpj;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends javax.swing.JFrame {
    
    private static int offset = 0;

    public MainFrame() {
        initComponents();
        loadPoslovi();
    }
    
    private void loadPoslovi() {
        
        DefaultTableModel tableModel = (DefaultTableModel)posaoTable.getModel();
        tableModel.setRowCount(0);
        
        ResultSet poslovi = DatabaseQuery.getPosao(offset);
        if (poslovi == null) {
            return;
        }
        
        try {
            while(poslovi.next()) {
                int id = poslovi.getInt("id");
                
                Timestamp datumVrijeme = poslovi.getTimestamp("datum_vrijeme");
                
                int radnikId = poslovi.getInt("radnik_id");
                String imePrezimeRadnik = DatabaseQuery.getRadnikImePrezimeById(radnikId);
                if (imePrezimeRadnik == null) {
                    return;
                }
                
                int uslugaId = poslovi.getInt("usluga_id");
                String uslugaCijena[] = DatabaseQuery.getUslugaNazivById(uslugaId);
                if (uslugaCijena == null) {
                    return;
                }
                String nazivUsluge = uslugaCijena[0];
                String cijena = uslugaCijena[1];
                
                int klijentId = poslovi.getInt("klijent_id");
                String imePrezimeKlijent = DatabaseQuery.getKlijentImePrezimeById(klijentId);
                if (imePrezimeKlijent == null) {
                    return;
                }
                
                String posao[] = {String.valueOf(id), datumVrijeme.toString(), nazivUsluge, cijena, imePrezimeKlijent, imePrezimeRadnik};
                tableModel.addRow(posao);
            }
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, null, exception);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        posloviPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        posaoTable = new javax.swing.JTable();
        sljedecaButton = new javax.swing.JButton();
        prethodnaButton = new javax.swing.JButton();
        noviPosaoButton = new javax.swing.JButton();
        osvjeziButton = new javax.swing.JButton();
        gotovoButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hemijska Čistionica");
        setResizable(false);

        posaoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Datum i vrijeme", "Usluga", "Cijena", "Klijent", "Radnik"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(posaoTable);

        sljedecaButton.setText("Sljedeća");
        sljedecaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sljedecaButtonActionPerformed(evt);
            }
        });

        prethodnaButton.setText("Prethodna");
        prethodnaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prethodnaButtonActionPerformed(evt);
            }
        });

        noviPosaoButton.setText("Novi posao");
        noviPosaoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noviPosaoButtonActionPerformed(evt);
            }
        });

        osvjeziButton.setText("Osvježi");
        osvjeziButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                osvjeziButtonActionPerformed(evt);
            }
        });

        gotovoButton.setText("Gotovo");
        gotovoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gotovoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout posloviPanelLayout = new javax.swing.GroupLayout(posloviPanel);
        posloviPanel.setLayout(posloviPanelLayout);
        posloviPanelLayout.setHorizontalGroup(
            posloviPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(posloviPanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(posloviPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, posloviPanelLayout.createSequentialGroup()
                        .addComponent(prethodnaButton)
                        .addGap(226, 226, 226)
                        .addGroup(posloviPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(posloviPanelLayout.createSequentialGroup()
                                .addComponent(noviPosaoButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, posloviPanelLayout.createSequentialGroup()
                                .addComponent(osvjeziButton)
                                .addGap(248, 248, 248)))
                        .addGroup(posloviPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gotovoButton)
                            .addComponent(sljedecaButton))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        posloviPanelLayout.setVerticalGroup(
            posloviPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(posloviPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(posloviPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sljedecaButton)
                    .addComponent(prethodnaButton)
                    .addComponent(noviPosaoButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(posloviPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(osvjeziButton)
                    .addComponent(gotovoButton))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Poslovi", posloviPanel);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 795, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 572, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab2", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void sljedecaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sljedecaButtonActionPerformed
        offset += 50;
        loadPoslovi();
    }//GEN-LAST:event_sljedecaButtonActionPerformed

    private void prethodnaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prethodnaButtonActionPerformed
        if (offset > 49) {
            offset -= 50;
            loadPoslovi();
        }
    }//GEN-LAST:event_prethodnaButtonActionPerformed

    private void noviPosaoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noviPosaoButtonActionPerformed
        InputFrame frame = new InputFrame();
        frame.setVisible(true);
    }//GEN-LAST:event_noviPosaoButtonActionPerformed

    private void osvjeziButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_osvjeziButtonActionPerformed
        offset = 0;
        loadPoslovi();
    }//GEN-LAST:event_osvjeziButtonActionPerformed

    private void gotovoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gotovoButtonActionPerformed
        int row = posaoTable.getSelectedRow();
        int index = posaoTable.getColumnModel().getColumnIndex("ID");
        int id = Integer.parseInt((String) posaoTable.getValueAt(row, index));
        DatabaseQuery.updatePosaoGotovo(id);
        loadPoslovi();
    }//GEN-LAST:event_gotovoButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton gotovoButton;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton noviPosaoButton;
    private javax.swing.JButton osvjeziButton;
    private javax.swing.JTable posaoTable;
    private javax.swing.JPanel posloviPanel;
    private javax.swing.JButton prethodnaButton;
    private javax.swing.JButton sljedecaButton;
    // End of variables declaration//GEN-END:variables
}
