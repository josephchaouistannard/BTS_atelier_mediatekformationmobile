package com.example.mediatekformationmobile.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mediatekformationmobile.model.Formation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FormationDAO extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mediatekformationsmobile.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_FAVORITE = "favorite";
    private static final String COL_ID = "id";

    /**
     * Constructeur de classe
     * @param context
     */
    public FormationDAO(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_FAVORITE + " (" +
                COL_ID + " INTEGER)";
        db.execSQL(createTable);
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     * <p>
     * <em>Important:</em> You should NOT modify an existing migration step from version X to X+1
     * once a build has been released containing that migration step.  If a migration step has an
     * error and it runs on a device, the step will NOT re-run itself in the future if a fix is made
     * to the migration step.</p>
     * <p>For example, suppose a migration step renames a database column from {@code foo} to
     * {@code bar} when the name should have been {@code baz}.  If that migration step is released
     * in a build and runs on a user's device, the column will be renamed to {@code bar}.  If the
     * developer subsequently edits this same migration step to change the name to {@code baz} as
     * intended, the user devices which have already run this step will still have the name
     * {@code bar}.  Instead, a NEW migration step should be created to correct the error and rename
     * {@code bar} to {@code baz}, ensuring the error is corrected on devices which have already run
     * the migration step with the error.</p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITE);
        onCreate(db);
    }

    /**
     * Retourne la liste des id des formations favorites depuis la BDD locale
     * @return ids des formations
     */
    public List<Integer> getFavorites() {
        List<Integer> idsFavorites = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_FAVORITE;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID));
                idsFavorites.add(id);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return idsFavorites;
    }

    /**
     * Ajouter l'id d'une formation dans la BDD locale des favorites
     * @param formation
     */
    public void ajouterFavorite(Formation formation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ID, formation.getId());
        db.insert(TABLE_FAVORITE, null, values);
        db.close();
    }

    /**
     * Supprime un id de formation dans la BDD locale
     * @param idFormation
     */
    public void supprimerFavorite(Integer idFormation) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COL_ID + " = ?";
        String[] whereArgs = { String.valueOf(idFormation) };
        db.delete(TABLE_FAVORITE, whereClause, whereArgs);
        db.close();
    }

}
