package ba.sinergija.projekatpj;


public class Radnik {
    private int id;
    private String imePrezime;
    private String korisnickoIme;
    
    public Radnik(int id, String imePrezime, String korisnickoIme) {
        this.id = id;
        this.imePrezime = imePrezime;
        this.korisnickoIme = korisnickoIme;
    }

    public int getId() {
        return id;
    }
    
    
}
