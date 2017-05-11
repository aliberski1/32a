package wizut.bukmacher;

import java.io.Serializable;
        import java.io.Serializable;



public class Dyscyplina implements Serializable {

    private int id;
    private String nazwa;
    private String kategoria;
    private int popularnosc;    //może inny typ, przyjmuje skale od 1-5

    public Dyscyplina() {};

    public Dyscyplina(String nazwa, String kategoria, int popularnosc)
    {
        super();
        this.nazwa = nazwa;
        this.kategoria = kategoria;
        this.popularnosc = popularnosc;
    }

    public String getNazwa()
    {
        return nazwa;
    }

    public String getKategoria()
    {
        return kategoria;
    }

    public int getPopularnosc()
    {
        return popularnosc;
    }

    public int getId()
    {
        return id;
    }

    public void setNazwa(String nazwa)
    {
        this.nazwa = nazwa;
    }

    public void setKategoria(String kategoria)
    {
        this.kategoria = kategoria;
    }

    public void setPopularnosc(int popularnosc)
    {
        this.popularnosc = popularnosc;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
