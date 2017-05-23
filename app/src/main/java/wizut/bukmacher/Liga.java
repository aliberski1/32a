package wizut.bukmacher;

/**
 * Created by Albert on 23.05.2017.
 */

public class Liga {
    private int id;
    private String kraj;
    private String nazwa;
    private String kategoria;
    private int liczba_zespolow;

    public Liga(int id, String kraj, String nazwa, String kategoria, int liczba_zespolow) {
        this.id = id;
        this.kraj = kraj;
        this.nazwa = nazwa;
        this.kategoria = kategoria;
        this.liczba_zespolow = liczba_zespolow;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public int getLiczba_zespolow() {
        return liczba_zespolow;
    }

    public void setLiczba_zespolow(int liczba_zespolow) {
        this.liczba_zespolow = liczba_zespolow;
    }
}
