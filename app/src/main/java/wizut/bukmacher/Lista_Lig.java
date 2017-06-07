package wizut.bukmacher;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Albert on 23.05.2017.
 */


public class Lista_Lig{

    private int id;


   // private Liga liga_polska;
    List<Liga> liga_polska;

    private Liga liga_angielska;




    private Liga liga_amerykanska;

    public void setLigaPolska(Liga liga_polska) {
        liga_polska.setKategoria("Piłka nożna");
    }


}
