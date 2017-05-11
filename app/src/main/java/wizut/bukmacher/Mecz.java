package wizut.bukmacher;

import java.io.Serializable;

/**
 * Created by Patryk on 2017-05-11.
 */

public class Mecz implements Serializable{
    private int id_meczu;
    private int id_dyscypliny;
    private int id_druzyny;
    private String data;        //wstepnie data typu String, wrazie lepszych propoycji mozna zmienic

    public int getId_meczu() {
        return id_meczu;
    }

    public void setId_meczu(int id_meczu) {
        this.id_meczu = id_meczu;
    }

    public int getId_dyscypliny() {
        return id_dyscypliny;
    }

    public void setId_dyscypliny(int id_dyscypliny) {
        this.id_dyscypliny = id_dyscypliny;
    }

    public int getId_druzyny() {
        return id_druzyny;
    }

    public void setId_druzyny(int id_druzyny) {
        this.id_druzyny = id_druzyny;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Mecz(){}
    public Mecz(int id_meczu,Dyscyplina id_dyscypliny,Druzyna id_druzyny,String data){
        this.id_meczu = id_meczu;
        this.id_dyscypliny = id_dyscypliny.getId();
        this.id_druzyny = id_druzyny.getIdDruzyny();
        this.data = data;

        }

    @Override
    public String toString() {
        return String.format("Mecz [id_meczu=%d, idDyscypliny=%d,idDruzyny=%d, data=%s]",
                id_meczu,id_dyscypliny,id_druzyny,data);
    }




}
