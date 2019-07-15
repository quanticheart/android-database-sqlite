/*
 *
 *  *                                     /@
 *  *                      __        __   /\/
 *  *                     /==\      /  \_/\/
 *  *                   /======\    \/\__ \__
 *  *                 /==/\  /\==\    /\_|__ \
 *  *              /==/    ||    \=\ / / / /_/
 *  *            /=/    /\ || /\   \=\/ /
 *  *         /===/   /   \||/   \   \===\
 *  *       /===/   /_________________ \===\
 *  *    /====/   / |                /  \====\
 *  *  /====/   /   |  _________    /      \===\
 *  *  /==/   /     | /   /  \ / / /         /===/
 *  * |===| /       |/   /____/ / /         /===/
 *  *  \==\             /\   / / /          /===/
 *  *  \===\__    \    /  \ / / /   /      /===/   ____                    __  _         __  __                __
 *  *    \==\ \    \\ /____/   /_\ //     /===/   / __ \__  ______  ____ _/ /_(_)____   / / / /__  ____ ______/ /_
 *  *    \===\ \   \\\\\\\/   ///////     /===/  / / / / / / / __ \/ __ `/ __/ / ___/  / /_/ / _ \/ __ `/ ___/ __/
 *  *      \==\/     \\\\/ / //////       /==/  / /_/ / /_/ / / / / /_/ / /_/ / /__   / __  /  __/ /_/ / /  / /_
 *  *      \==\     _ \\/ / /////        |==/   \___\_\__,_/_/ /_/\__,_/\__/_/\___/  /_/ /_/\___/\__,_/_/   \__/
 *  *        \==\  / \ / / ///          /===/
 *  *        \==\ /   / / /________/    /==/
 *  *          \==\  /               | /==/
 *  *          \=\  /________________|/=/
 *  *            \==\     _____     /==/
 *  *           / \===\   \   /   /===/
 *  *          / / /\===\  \_/  /===/
 *  *         / / /   \====\ /====/
 *  *        / / /      \===|===/
 *  *        |/_/         \===/
 *  *                       =
 *  *
 *  * Copyright(c) Developed by John Alves at 2019/7/14 at 5:11:50 for quantic heart studios
 *
 */

package com.quanticheart.lib.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import com.quanticheart.lib.dao.db.Dao;
import com.quanticheart.lib.dao.model.BestMovieModel;
import com.quanticheart.lib.dao.security.Encrypt;

import java.util.ArrayList;

import static com.quanticheart.lib.dao.constants.contants.*;

@SuppressWarnings("unused")
public class DatabaseMovie extends Dao {

    /**
     * boolean if encrypt or not in access database
     */

    private boolean encrypt = false;

    /**
     * Constructor
     *
     * @param context for init Dao
     */
    public DatabaseMovie(Context context) {
        super(context);
    }

    /**
     * Constructor
     *
     * @param context for init Dao
     * @param encrypt for encrypt data
     */
    public DatabaseMovie(Context context, Boolean encrypt) {
        super(context);
        this.encrypt = encrypt;
    }

    /**
     * Insert new movie in data base
     *
     * @param model data movie
     * @return is success
     */
    public boolean addMovie(BestMovieModel model) {
        boolean b = false;
        openDataBase();
        //
        db.beginTransaction();
        //
        try {
            //
            b = db.insert(TABLE_NAME, null, createValues(model)) > 0;
            db.setTransactionSuccessful();
        } catch (Exception e) {
            log("insert", e);
        } finally {
            db.endTransaction();
        }
        //
        closeDataBase();
        return b;
    }

    /**
     * update movie in data base
     *
     * @param model data movie
     * @return is success
     */
    public boolean editMovie(BestMovieModel model) {
        boolean b = false;
        openDataBase();
        //
        db.beginTransaction();
        //
        try {
            //
            b = db.update(TABLE_NAME, createValues(model), ID + "=?", new String[]{model.getId()}) > 0;
            db.setTransactionSuccessful();
        } catch (Exception e) {
            log("edit", e);
        } finally {
            db.endTransaction();
        }
        //
        closeDataBase();
        return b;
    }

    /**
     * Delete moview in data base
     *
     * @param movieID for delete in table
     * @return is success
     */
    public boolean deleteMovie(String movieID) {
        boolean b = false;
        openDataBase();
        //
        try {
            //
            b = db.delete(TABLE_NAME, ID + "=?", new String[]{movieID}) > 0;
        } catch (Exception e) {
            log("delete", e);
        }
        //
        closeDataBase();
        return b;
    }

    /**
     * get all list movie in data base
     *
     * @return ArrayList<BestMovieModel>
     */
    public ArrayList<BestMovieModel> getListMovies() {

        ArrayList<BestMovieModel> list = new ArrayList<>();
        openDataBase();
        //
        try {
            //
            Cursor cursor = db.query(TABLE_NAME, new String[]{ID, TITLE, DECS, RATING}, null, null, null, null, null);

            while (cursor.moveToNext()) {
                BestMovieModel model = new BestMovieModel(
                        cursor.getString(cursor.getColumnIndex(ID)),
                        cursor.getString(cursor.getColumnIndex(TITLE)),
                        cursor.getString(cursor.getColumnIndex(DECS)),
                        Float.parseFloat(cursor.getString(cursor.getColumnIndex(RATING)))
                );
                list.add(model);
            }

            cursor.close();
            return list;

        } catch (Exception e) {
            log("get list", e);
        }
        //
        closeDataBase();

        return null;
    }

    /**
     * clean table
     *
     * @return clean is success
     */
    public boolean cleanTable() {
        boolean b = false;
        openDataBase();
        try {
            String[] comandos = ("DROP TABLE IF EXISTS " + TABLE_NAME + ";").split(";");
            for (String comando : comandos) {
                db.execSQL(comando.toLowerCase());
            }
            b = true;
        } catch (Exception e) {
            log("delete table", e);
        }
        closeDataBase();
        return b;
    }

    //==============================================================================================
    //
    // ** Utils
    //
    //==============================================================================================

    /**
     * create content value for insert data base
     *
     * @param model datas for content values
     * @return content
     */
    private ContentValues createValues(BestMovieModel model) {
        ContentValues data = new ContentValues();
        data.put(TITLE, model.getTitleMovie());
        data.put(DECS, model.getLitleDescMovie());
        data.put(RATING, model.getRattingMovie().toString());

        return data;
    }

    /*
     * Encrypt e Decrypt code
     */

    /**
     * Encrypt text for database
     *
     * @param text for encrypt
     * @return text or text encrypted
     */
    private String encrypt(String text) {
        if (encrypt) {
            return Encrypt.md5(text);
        } else {
            return text;
        }
    }

    /**
     * Decrypt text for return
     *
     * @param base64 for decode
     * @return text or text decoded
     */
    private String decrypt(String base64) {
        if (encrypt) {
            return Encrypt.md5Decode(base64);
        } else {
            return base64;
        }
    }


    /**
     * show simple msg in log
     *
     * @param title for init log msg
     * @param e     for get error msg
     */
    private void log(String title, Exception e) {
        Log.w(DatabaseMovie.class.getSimpleName() + ": Error " + title, e);
    }
}
