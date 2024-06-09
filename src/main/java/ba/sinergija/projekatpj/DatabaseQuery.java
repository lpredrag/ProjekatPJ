package ba.sinergija.projekatpj;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, "SHA256 nije dostupan", exception);
            return null;
        }
    }
    
    public static ResultSet getRadnikByUsername(String username) {
        try {
            String query = "select * from radnik where korisnicko_ime = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            return rs;
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, null, exception);
            return null;
        }
    }
    
    public static void insertRadnik(String imePrezime, String username, String password) {
        try {
            String query = "insert into radnik(ime_prezime, korisnicko_ime, lozinka) values(?,?,?)";
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setString(1, imePrezime);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.executeUpdate();
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, null, exception);
        }
    }
    
    public static ResultSet getUslugaNaziv() {
        try {
            Statement statement = db.getConnection().createStatement();
            ResultSet resSet = statement.executeQuery("select naziv from usluga");
            return resSet;
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, null, exception);
            return null;
        }
    }
    
    public static ResultSet getUslugaIdByNaziv(String naziv) {
        try {
            String query = "select id from usluga where naziv = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setString(1, naziv);
            ResultSet resSet = statement.executeQuery();
            return resSet;
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, null, exception);
            return null;
        }
    }
    
    public static ResultSet getKlijentByImePrezime(String imePrezime) {
        try {
            String query = "select * from klijent where ime_prezime = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setString(1, imePrezime);
            ResultSet resSet = statement.executeQuery();
            return resSet;
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, null, exception);
            return null;
        }
    }
    
    public static void updateKlijentPoslovi(int klijentId) {
        try {
            String query = "update klijent set poslovi = poslovi + 1 where id = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setInt(1, klijentId);
            statement.executeUpdate();
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, null, exception);
        }
    }
    
    public static int insertKlijent(String imePrezime) {
        try {
            String query = "insert into klijent(ime_prezime, poslovi) values(?,?)";
            PreparedStatement statement = db.getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, imePrezime);
            statement.setInt(2, 1);
            statement.executeUpdate();
            ResultSet resSet = statement.getGeneratedKeys();
            resSet.next();
            return resSet.getInt(1);
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, null, exception);
            return 0;
        }
    }
    
    public static void insertPosao(Timestamp datumVrijeme, int radnikId, int uslugaId, int klijentId, boolean popust) {
        try {
            String query = "insert into posao(datum_vrijeme, radnik_id, usluga_id, klijent_id, popust) values(?,?,?,?,?)";
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setTimestamp(1, datumVrijeme);
            statement.setInt(2, radnikId);
            statement.setInt(3, uslugaId);
            statement.setInt(4, klijentId);
            statement.setBoolean(5, popust);
            statement.executeUpdate();
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, null, exception);
        }
    }
    
    public static ResultSet getPosao(int offset) {
        try {
            String query = "select * from posao where gotovo = 0 limit 50 offset ?";
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setInt(1, offset);
            ResultSet resSet = statement.executeQuery();
            return resSet;
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, null, exception);
            return null;
        }
    }
    
    public static String getRadnikImePrezimeById(int id) {
        try {
            String query = "select ime_prezime from radnik where id = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resSet = statement.executeQuery();
            while (resSet.next()) {
                String imePrezime = resSet.getString("ime_prezime");
                return imePrezime;
            }
            return null;
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, null, exception);
            return null;
        }
    }
    
    public static String[] getUslugaNazivById(int id) {
        try {
            String query = "select * from usluga where id = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resSet = statement.executeQuery();
            while (resSet.next()) {
                String naziv = resSet.getString("naziv");
                String cijena = String.valueOf(resSet.getInt("cijena"));
                String result[] = {naziv, cijena};
                return result;
            }
            return null;
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, null, exception);
            return null;
        }
    }
    
    public static ResultSet getUsluga() {
        try {
            String query = "select * from usluga";
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            ResultSet resSet = statement.executeQuery();
            return resSet;
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, null, exception);
            return null;
        }
    }
    
    public static void insertUsluga(String naziv, String cijena) {
        try {
            String query = "insert into usluga(naziv, cijena) values(?,?)";
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setString(1, naziv);
            statement.setInt(2, Integer.parseInt(cijena));
            statement.executeUpdate();
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, null, exception);
        }
    }
    
    public static String getKlijentImePrezimeById(int id) {
        try {
            String query = "select * from klijent where id = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resSet = statement.executeQuery();
            while (resSet.next()) {
                String imePrezime = resSet.getString("ime_prezime");
                return imePrezime;
            }
            return null;
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, null, exception);
            return null;
        }
    }
    
    public static void updatePosaoGotovo(int id) {
        try {
            String query = "update posao set gotovo = true where id = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(SEVERE, null, exception);
        }
    }
    
}
