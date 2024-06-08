package ba.sinergija.projekatpj;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LoginFrame extends javax.swing.JFrame {
    
    public static Radnik currentRadnik;

    public LoginFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Prijavi se");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("key.png")));
        setResizable(false);

        loginButton.setText("Prijavi se");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        usernameLabel.setText("Korisničko ime:");

        passwordLabel.setText("Lozinka:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(loginButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameLabel)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(usernameField)
                                .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                            .addComponent(passwordLabel))))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(usernameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(passwordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(loginButton)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        String username = usernameField.getText();
        char[] passwordArray = passwordField.getPassword();
        if (username.isEmpty() || passwordArray.length == 0) {
            System.out.println("Pogresan unos.");
            return;
        }
        if (username.length() > 50 || passwordArray.length > 128) {
            System.out.println("Unos predugacak.");
            inputError();
            return;
        }
        String password = new String(passwordArray);
        
        String hashedPassword = DatabaseQuery.hashPasswordSHA256(password);
        if (hashedPassword == null) {
            System.out.println("Hesiranje lozinke nije uspjelo.");
            return;
        }
        ResultSet set = DatabaseQuery.getRadnikByUsername(username);
        if (set == null) {
            return;
            //TODO
        }
        int id = 0;
        String imePrezime = "";
        String korisnickoIme = "";
        String databaseHashedPassword = "";
        try {
            while (set.next()) {
                id = set.getInt("id");
                imePrezime = set.getString("ime_prezime");
                korisnickoIme = set.getString("korisnicko_ime");
                databaseHashedPassword = set.getString("lozinka");
            }
        } catch(SQLException exc) {
            return;
            //TODO
        }
        
        if (hashedPassword.equals(databaseHashedPassword)) {
            System.out.println("Prijava uspjesna.");
            currentRadnik = new Radnik(id, imePrezime, korisnickoIme);
            MainFrame newFrame = new MainFrame();
            newFrame.setVisible(true);
            newFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    DatabaseQuery.db.disconnect();
                }
}           );
            this.dispose();
        } else {
            inputError();
        }
        
    }//GEN-LAST:event_loginButtonActionPerformed

    private void inputError() {
        JOptionPane.showMessageDialog(rootPane, "Pogrešan unos", "Greška", JOptionPane.ERROR_MESSAGE);
        usernameField.setText("");
        passwordField.setText("");
    }
    
    public static void main(String args[]) {
        
        if (!DatabaseQuery.db.connect()) {
            return;
        }
		
	//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(SEVERE, null, ex);
        }
        //</editor-fold>
        
	//</editor-fold>
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginFrame frame = new LoginFrame();
                frame.setVisible(true);
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        DatabaseQuery.db.disconnect();
                    }
}               );
            }
        });
		
    }
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables

}
