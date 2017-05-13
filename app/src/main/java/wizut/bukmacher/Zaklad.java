package wizut.bukmacher;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;

class MySQLitee extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    public MySQLitee(Context context) {
        super(context, "zakladDB", null, DATABASE_VERSION);
    }

    public void add(Zaklad zaklad) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id_zakladu", zaklad.getIdZakladu());
        values.put("stan", zaklad.getStan());
        values.put("stawka", zaklad.getStawka());

        db.insert("zaklad", null, values);
        db.close();
    }


    public void delete(String idZakladu) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("zaklad", "id_zakladu = ?",
                new String[] { idZakladu }
        );
        db.close();
    }

    public int update(Zaklad zaklad) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id_zakladu", zaklad.getIdZakladu());
        values.put("stan", zaklad.getStan());
        values.put("stawka", zaklad.getStawka());

        int i = db.update("zaklad", values, "id_zakladu = ?",
                new String[] { String.valueOf(zaklad.getIdZakladu()) });
        db.close();
        return i;
    }

    public Zaklad get(int idZakladu){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                "zaklad", // table name
                new String[] { // column names
                        "id_zakladu", "id_uzytkownika", "id_meczu",
                        "stan", "stawka"
                },
                " id_zakladu = ?", // selections
                new String[] { // selections' args
                        String.valueOf(idZakladu)
                },
                null, // group by
                null, // having
                null, // order by
                null // limit
        );

        if (cursor != null) { cursor.moveToFirst(); }
        Zaklad zaklad = new Zaklad(
                cursor.getInt(1), cursor.getString(2),
                cursor.getString(3)
        );

        zaklad.setIdZakladu(Integer.parseInt(cursor.getString(0)));
        return zaklad;
    }

    @Override
    public void onCreate(SQLiteDatabase	database) {
        String DATABASE_CREATE =
                "CREATE TABLE zaklad (" +
                        "id_zakladu INTEGER PRIMARY KEY autoincrement, " +
                        "id_uzytkownika INTEGER NOT NULL, " +
                        "id_meczu INTEGER NOT NULL, " +
                        "stan TEXT NOT NULL, " +
                        "stawka TEXT NOT NULL, ";

        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS zaklad");
        onCreate(db);
    }
}

public class Zaklad implements Serializable {
    private int idZakladu, idUzytkownika, idMeczu;
    private String stan, stawka;

    public Zaklad() {}
    public Zaklad(int idZakladu, String stan, String stawka) {
        this.idZakladu = idZakladu;
        this.stan = stan;
        this.stawka = stawka;
    }

    public int getIdZakladu() {	return idZakladu; }
    public int getIdUzytkownika() { return idUzytkownika; }
    public int getIdMeczu() { return idMeczu; }
    public String getStan() { return stan; }
    public String getStawka() { return stawka; }

    public void setIdZakladu(int idZakladu) {
        this.idZakladu = idZakladu;
    }

    @Override
    public String toString() {
        return String.format("Zaklad [idZakladu=%d, idUzytkownika=%d, idMeczu=%d, stan=%s, stawka=%s]",
                idZakladu, idUzytkownika, idMeczu, stan, stawka);
    }
}
