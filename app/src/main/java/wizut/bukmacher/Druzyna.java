package wizut.bukmacher;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;

class MySQLite extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	
	public MySQLite(Context context) {
		super(context, "druzynaDB", null, DATABASE_VERSION);
	}

	public void add(Druzyna druzyna) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put("id_dyscypliny", druzyna.getIdDyscypliny());
		values.put("nazwa", druzyna.getNazwa());
		values.put("kraj", druzyna.getKraj());
		values.put("liga", druzyna.getLiga());
		
		db.insert("druzyna", null, values);
		db.close();
	}
	
	public void delete(String idDruzyny) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete("druzyna", "id_druzyny = ?",
				new String[] { idDruzyny }
		);
		db.close();
	}
	
	public int update(Druzyna druzyna) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put("id_dyscypliny", druzyna.getIdDyscypliny());
		values.put("nazwa", druzyna.getNazwa());
		values.put("kraj", druzyna.getKraj());
		values.put("liga", druzyna.getLiga());
		
		int i = db.update("druzyna", values, "id_druzyny = ?",
				new String[] { String.valueOf(druzyna.getIdDruzyny()) });
		db.close();
		return i;
	}
	
	public Druzyna get(int idDruzyny){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(
				"druzyna", // table name
				new String[] { // column names
						"id_druzyny", "id_dyscypliny",
						"nazwa", "kraj", "liga"
				},
				" id_druzyny = ?", // selections
				new String[] { // selections' args
						String.valueOf(idDruzyny)
				},
				null, // group by
				null, // having
				null, // order by
				null // limit
		);
		
		if (cursor != null) { cursor.moveToFirst(); }
		Druzyna druzyna = new Druzyna(
				cursor.getInt(1), cursor.getString(2),
				cursor.getString(3), cursor.getString(4)
		);

		druzyna.setIdDruzyny(Integer.parseInt(cursor.getString(0)));
		return druzyna;
	}
	
	@Override
	public void onCreate(SQLiteDatabase	database) {
		String DATABASE_CREATE =
				"CREATE TABLE druzyna (" +
				"id_druzyny INTEGER PRIMARY KEY autoincrement, " +
				"id_dyscypliny INTEGER NOT NULL, " +
				"nazwa TEXT NOT NULL, " +
				"kraj TEXT NOT NULL, " +
				"liga TEXT NOT NULL);";
				// FOREIGN KEY(id_dyscypliny) REFERENCES dyscyplina(id_dyscypliny)
		database.execSQL(DATABASE_CREATE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS druzyna");
		onCreate(db);
	}
}

public class Druzyna implements Serializable {
	private int idDruzyny, idDyscypliny;
	private String nazwa, kraj, liga;
	
	public Druzyna() {}
	public Druzyna(int idDyscypliny, String nazwa, String kraj, String liga) {
		this.idDyscypliny = idDyscypliny;
		this.nazwa = nazwa;
		this.kraj = kraj;
		this.liga = liga;
	}
	
	public int getIdDruzyny() {	return idDruzyny; }
	public int getIdDyscypliny() { return idDyscypliny; }
	public String getNazwa() { return nazwa; }
	public String getKraj() { return kraj; }
	public String getLiga() { return liga; }
		
	public void setIdDruzyny(int idDruzyny) {
		this.idDruzyny = idDruzyny;
	}
	
	@Override
	public String toString() {
		return String.format("Druzyna [idDruzyny=%d, idDyscypliny=%d, nazwa=%s, kraj=%s, liga=%s]",
				idDruzyny, idDyscypliny, nazwa, kraj, liga);
	}
}
