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

import java.util.ArrayList;

import static com.quanticheart.lib.dao.constants.contants.*;

@SuppressWarnings("unused")
public class DatabaseMovie extends Dao {

    /**
     * Constructor
     *
     * @param context for init Dao
     */
    public DatabaseMovie(Context context) {
        super(context);
    }

    /**
     * Insert new movie in data base
     *
     * @param model data movie
     */
    public void addMovie(BestMovieModel model) {
        openDataBase();
        //
        db.beginTransaction();
        //
        try {
            //
            db.insert(TABLE_NAME, null, createValues(model));
            db.setTransactionSuccessful();
        } catch (Exception e) {
            log("insert", e);
        } finally {
            db.endTransaction();
        }
        //
        closeDataBase();
    }

    /**
     * update movie in data base
     *
     * @param model data movie
     */
    public void editMovie(BestMovieModel model) {
        openDataBase();
        //
        db.beginTransaction();
        //
        try {
            //
            db.update(TABLE_NAME, createValues(model), ID + "=?", new String[]{model.getId()});
            db.setTransactionSuccessful();
        } catch (Exception e) {
            log("edit", e);
        } finally {
            db.endTransaction();
        }
        //
        closeDataBase();
    }

    /**
     * Delete moview in data base
     *
     * @param movieID for delete in table
     */
    public void deleteMovie(String movieID) {
        openDataBase();
        //
        try {
            //
            db.delete(TABLE_NAME, ID + "=?", new String[]{movieID});
        } catch (Exception e) {
            log("delete", e);
        }
        //
        closeDataBase();
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
                        Double.parseDouble(cursor.getString(cursor.getColumnIndex(RATING)))
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

    private void log(String title, Exception e) {
        Log.w(DatabaseMovie.class.getSimpleName() + ": Error " + title, e);
    }
}
