package ba.sinergija.projekatpj;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;

public class DatabaseQuery {
    
    public static Database db = new Database();
    
    public static String hashPasswordSHA256(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * hashBytes.length);
            for (int i = 0; i < hashBytes.length; i++) {
                String hex = Integer.toHexString(0xff & hashBytes[i]);
                if(hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(NoSuchAlgorithmException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, "SHA256 is not available", exception);
            return null;
        }
    }
    
    public static String getPasswordByUsername(String username) {
        try {
            String query = "SELECT lozinka FROM radnik WHERE korisnicko_ime = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            String hashedPassword = null;
            if (rs.next()) {
                hashedPassword = rs.getString("lozinka");
            }
            return hashedPassword;
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, "Failed to query database", exception);
            return null;
        }
    }
    
    public static ResultSet getUsluga() {
        try {
            Statement statement = db.getConnection().createStatement();
            ResultSet resSet = statement.executeQuery("select * from usluga");
            return resSet;
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, "Failed to query database", exception);
            return null;
        }
    }
    /*
    public static void getKlijent() {
        try {
            Statement statement = db.getConnection().createStatement();
            ResultSet resSet = statement.executeQuery("select * from klijent");
            while (resSet.next()) {
                int id = resSet.getInt("id");
                String ime = resSet.getString("ime");
                String prezime = resSet.getString("prezime");
                new Klijent(id, ime, prezime);
            }
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, "Failed to query database", exception);
        }
    }
    
    public static void getRadnik() {
        try {
            Statement statement = db.getConnection().createStatement();
            ResultSet resSet = statement.executeQuery("select * from radnik");
            while (resSet.next()) {
                int id = resSet.getInt("id");
                String ime = resSet.getString("ime");
                String prezime = resSet.getString("prezime");
                String korisnickoIme = resSet.getString("korisnicko_ime");
                new Radnik(id, ime, prezime, korisnickoIme);
            }
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, "Failed to query database", exception);
        }
    }
    */
    public static ResultSet getPosao(int offset) {
        try {
            String query = "select * from posao where gotovo = 0 limit 50 offset " + String.valueOf(offset);
            Statement statement = db.getConnection().createStatement();
            ResultSet resSet = statement.executeQuery(query);
            return resSet;
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, "Failed to query database", exception);
            return null;
        }
    }
    
    public static ResultSet getAllPosao(int offset) {
        try {
            String query = "select * from posao limit 50 offset " + String.valueOf(offset);
            Statement statement = db.getConnection().createStatement();
            ResultSet resSet = statement.executeQuery(query);
            return resSet;
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, "Failed to query database", exception);
            return null;
        }
    }
    
    public static String getRadnikById(int id) {
        try {
            String query = "select * from radnik where id = " + String.valueOf(id);
            Statement statement = db.getConnection().createStatement();
            ResultSet resSet = statement.executeQuery(query);
            while (resSet.next()) {
                String imePrezime = resSet.getString("ime") + " " + resSet.getString("prezime");
                return imePrezime;
            }
            return null;
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, "Failed to query database", exception);
            return null;
        }
    }
    
    public static String getUslugaById(int id) {
        try {
            String query = "select * from usluga where id = " + String.valueOf(id);
            Statement statement = db.getConnection().createStatement();
            ResultSet resSet = statement.executeQuery(query);
            while (resSet.next()) {
                String naziv = resSet.getString("naziv");
                return naziv;
            }
            return null;
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, "Failed to query database", exception);
            return null;
        }
    }
    
    public static String getKlijentById(int id) {
        try {
            String query = "select * from klijent where id = " + String.valueOf(id);
            Statement statement = db.getConnection().createStatement();
            ResultSet resSet = statement.executeQuery(query);
            while (resSet.next()) {
                String imePrezime = resSet.getString("ime") + " " + resSet.getString("prezime");
                return imePrezime;
            }
            return null;
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, "Failed to query database", exception);
            return null;
        }
    }
    
}
