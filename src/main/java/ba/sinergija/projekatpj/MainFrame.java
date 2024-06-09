package ba.sinergija.projekatpj;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends javax.swing.JFrame {
    
    private static int offset = 0;

    public MainFrame() {
        initComponents();
        loadPoslovi();
        loadCjenovnik();
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
                int cijena = Integer.parseInt(uslugaCijena[1]);
                
                boolean popust = poslovi.getBoolean("popust");
                if (popust == true) {
                    cijena = cijena / 2;
                }
                
                int klijentId = poslovi.getInt("klijent_id");
                String imePrezimeKlijent = DatabaseQuery.getKlijentImePrezimeById(klijentId);
                if (imePrezimeKlijent == null) {
                    return;
                }
                
                String posao[] = {String.valueOf(id), datumVrijeme.toString(), nazivUsluge, String.valueOf(cijena), imePrezimeKlijent, imePrezimeRadnik};
                tableModel.addRow(posao);
            }
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, null, exception);
        }
    }
    
    private void loadCjenovnik() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        //cjenovnikTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        cjenovnikTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        
        DefaultTableModel tableModel = (DefaultTableModel)cjenovnikTable.getModel();
        tableModel.setRowCount(0);
        ResultSet usluge = DatabaseQuery.getUsluga();
        if (usluge == null) {
            return;
        }
        try {
            while (usluge.next()) {
                String naziv = usluge.getString("naziv");
                String cijena = String.valueOf(usluge.getInt("cijena")) + " KM";
                String usluga[] = {naziv, cijena};
                tableModel.addRow(usluga);
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
        cjenovnikPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        cjenovnikTable = new javax.swing.JTable();
        uslugaButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        adminPanel = new javax.swing.JPanel();
        odjavaButton = new javax.swing.JButton();
        dodajRadnikaButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hemijska Čistionica");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("wm.png")));
        setResizable(false);

        posloviPanel.setBackground(new java.awt.Color(178, 209, 255));

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

        cjenovnikPanel.setBackground(new java.awt.Color(178, 209, 255));

        cjenovnikTable.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cjenovnikTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Usluga", "Cijena"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(cjenovnikTable);

        uslugaButton.setText("Dodaj uslugu");
        uslugaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uslugaButtonActionPerformed(evt);
            }
        });

        jButton1.setText("Osvježi");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cjenovnikPanelLayout = new javax.swing.GroupLayout(cjenovnikPanel);
        cjenovnikPanel.setLayout(cjenovnikPanelLayout);
        cjenovnikPanelLayout.setHorizontalGroup(
            cjenovnikPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cjenovnikPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(cjenovnikPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(cjenovnikPanelLayout.createSequentialGroup()
                        .addComponent(uslugaButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        cjenovnikPanelLayout.setVerticalGroup(
            cjenovnikPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cjenovnikPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cjenovnikPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uslugaButton)
                    .addComponent(jButton1))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cjenovnik", cjenovnikPanel);

        adminPanel.setBackground(new java.awt.Color(178, 209, 255));

        odjavaButton.setText("Odjavi se");
        odjavaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                odjavaButtonActionPerformed(evt);
            }
        });

        dodajRadnikaButton.setText("Dodaj radnika");
        dodajRadnikaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodajRadnikaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout adminPanelLayout = new javax.swing.GroupLayout(adminPanel);
        adminPanel.setLayout(adminPanelLayout);
        adminPanelLayout.setHorizontalGroup(
            adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dodajRadnikaButton)
                    .addComponent(odjavaButton))
                .addContainerGap(657, Short.MAX_VALUE))
        );
        adminPanelLayout.setVerticalGroup(
            adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(odjavaButton)
                .addGap(38, 38, 38)
                .addComponent(dodajRadnikaButton)
                .addContainerGap(454, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Administracija", adminPanel);

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
        if (posaoTable.getSelectedRowCount() == 0) {
            return;
        }
        int row = posaoTable.getSelectedRow();
        int index = posaoTable.getColumnModel().getColumnIndex("ID");
        int id = Integer.parseInt((String) posaoTable.getValueAt(row, index));
        DatabaseQuery.updatePosaoGotovo(id);
        loadPoslovi();
    }//GEN-LAST:event_gotovoButtonActionPerformed

    private void odjavaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_odjavaButtonActionPerformed
        //DatabaseQuery.db.disconnect();
        LoginFrame.currentRadnik = null;
        LoginFrame newFrame = new LoginFrame();
        newFrame.setVisible(true);
        newFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DatabaseQuery.db.disconnect();
            }
        });
        this.dispose();
    }//GEN-LAST:event_odjavaButtonActionPerformed

    private void dodajRadnikaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodajRadnikaButtonActionPerformed
        RadnikFrame frame = new RadnikFrame();
        frame.setVisible(true);
    }//GEN-LAST:event_dodajRadnikaButtonActionPerformed

    private void uslugaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uslugaButtonActionPerformed
        UslugaFrame frame = new UslugaFrame();
        frame.setVisible(true);
    }//GEN-LAST:event_uslugaButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        loadCjenovnik();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel adminPanel;
    private javax.swing.JPanel cjenovnikPanel;
    private javax.swing.JTable cjenovnikTable;
    private javax.swing.JButton dodajRadnikaButton;
    private javax.swing.JButton gotovoButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton noviPosaoButton;
    private javax.swing.JButton odjavaButton;
    private javax.swing.JButton osvjeziButton;
    private javax.swing.JTable posaoTable;
    private javax.swing.JPanel posloviPanel;
    private javax.swing.JButton prethodnaButton;
    private javax.swing.JButton sljedecaButton;
    private javax.swing.JButton uslugaButton;
    // End of variables declaration//GEN-END:variables
}
