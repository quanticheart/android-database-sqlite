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
import com.quanticheart.lib.dao.db.Dao;
import com.quanticheart.lib.dao.model.BestMovieModel;
import com.quanticheart.lib.dao.modelDatabase.createFunctionDatabase;

import java.util.ArrayList;

import static com.quanticheart.lib.dao.constants.contants.*;
import static com.quanticheart.lib.dao.security.CryptoUtil.decrypt;
import static com.quanticheart.lib.dao.security.CryptoUtil.encrypt;
import static com.quanticheart.lib.dao.util.MsgUtil.log;

@SuppressWarnings({"unused", "UnusedReturnValue", "WeakerAccess", "SameParameterValue"})
public class DatabaseMovie extends Dao implements createFunctionDatabase {

    //==============================================================================================
    //
    // ** Constructor
    //
    //==============================================================================================

    /**
     * boolean if encryptData or not in access database
     */

    protected boolean encryptData = false;

    /**
     * Constructor
     *
     * @param context for init Dao
     */
    DatabaseMovie(Context context) {
        super(context);
    }

    /**
     * Constructor
     *
     * @param context     for init Dao
     * @param encryptData for encryptData data
     */
    DatabaseMovie(Context context, Boolean encryptData) {
        super(context);
        this.encryptData = encryptData;
    }

    //==============================================================================================
    //
    // ** Insert
    //
    //==============================================================================================

    /**
     * Insert new movie in data base
     *
     * @param model data movie
     * @return is success
     */
    @Override
    public boolean insertMovie(BestMovieModel model) {
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
     * Insert list of new movie in data base
     *
     * @param model data movie list
     * @return is success
     */
    @Override
    public boolean insertListMovie(ArrayList<BestMovieModel> model) {
        boolean b = false;
        openDataBase();
        //
        db.beginTransaction();
        //
        try {
            //
            for (BestMovieModel data : model) {
                b = db.insert(TABLE_NAME, null, createValues(data)) > 0;
            }
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

    //==============================================================================================
    //
    // ** Update
    //
    //==============================================================================================

    /**
     * update movie in data base
     *
     * @param model data movie
     * @return is success
     */
    @Override
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

    //==============================================================================================
    //
    // ** Delete
    //
    //==============================================================================================

    /**
     * Delete moview in data base
     *
     * @param movieID for delete in table
     * @return is success
     */
    @Override
    public boolean deleteRowByID(String movieID) {
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
     * delete Fisrt Row in database
     *
     * @return is success
     */
    @Override
    public boolean deleteFirstRow() {
        boolean b = false;
        openDataBase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{ID}, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            String rowId = cursor.getString(cursor.getColumnIndex(ID));
            b = db.delete(TABLE_NAME, ID + "=?", new String[]{rowId}) > 0;
        }
        closeDataBase(cursor);
        return b;
    }

    /**
     * delete Last Row in database
     *
     * @return is success
     */
    @Override
    public boolean deleteLastRow() {
        boolean b = false;
        openDataBase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{ID}, null, null, null, null, null);
        if (cursor.moveToLast()) {
            String rowId = cursor.getString(cursor.getColumnIndex(ID));
            b = db.delete(TABLE_NAME, ID + "=?", new String[]{rowId}) > 0;
        }
        closeDataBase(cursor);
        return b;
    }

    /**
     * delete Row at position in database
     *
     * @return is success
     */
    @Override
    public boolean deleteRowAtPosition(int position) {
        boolean b = false;
        openDataBase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{ID}, null, null, null, null, null);
        if (cursor.moveToPosition(position)) {
            String rowId = cursor.getString(cursor.getColumnIndex(ID));
            b = db.delete(TABLE_NAME, ID + "=?", new String[]{rowId}) > 0;
        }
        closeDataBase(cursor);
        return b;
    }

    //==============================================================================================
    //
    // ** Get
    //
    //==============================================================================================

    /**
     * get all list movie in data base
     *
     * @return ArrayList<BestMovieModel>
     */
    @Override
    public ArrayList<BestMovieModel> getListMovies() {

        ArrayList<BestMovieModel> list = new ArrayList<>();
        openDataBase();
        //
        try {
            //
            Cursor cursor = db.query(TABLE_NAME, new String[]{ID, TITLE, DECS, RATING}, null, null, null, null, null);

            while (cursor.moveToNext()) {
                BestMovieModel model = getMovie(cursor);
                if (model != null) {
                    list.add(model);
                }
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
     * get movie in data base
     *
     * @param movieID for get in table
     * @return is success
     */
    @Override
    public BestMovieModel getRowByID(String movieID) {
        openDataBase();
        BestMovieModel model = null;
        Cursor cursor = db.query(TABLE_NAME, null, ID + "=?", new String[]{movieID}, null, null, null);
        try {
            if (cursor.moveToNext()) {
                model = getMovie(cursor);
            }
        } catch (Exception e) {
            log("get by id", e);
        }
        //
        closeDataBase(cursor);
        return model;
    }

    /**
     * get Fisrt Row in database
     *
     * @return is success
     */
    @Override
    public BestMovieModel getFirstRow() {
        openDataBase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        BestMovieModel model = null;
        if (cursor.moveToFirst()) {
            model = getMovie(cursor);
        }
        closeDataBase(cursor);
        return model;
    }

    /**
     * get Last Row in database
     *
     * @return is success
     */
    @Override
    public BestMovieModel getLastRow() {
        openDataBase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        BestMovieModel model = null;
        if (cursor.moveToLast()) {
            model = getMovie(cursor);
        }
        closeDataBase(cursor);
        return model;
    }

    /**
     * get  Row in position at database
     *
     * @return is success
     */
    @Override
    public BestMovieModel getRowAtPosition(int position) {
        openDataBase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        BestMovieModel model = null;
        if (cursor.moveToPosition(position)) {
            model = getMovie(cursor);
        }
        closeDataBase(cursor);
        return model;
    }

    //==============================================================================================
    //
    // ** Table Utils
    //
    //==============================================================================================

    /**
     * clean table
     *
     * @return clean is success
     */
    @Override
    public boolean cleanTable() {
        boolean b = false;
        openDataBase();
        try {
            b = db.delete(TABLE_NAME, null, null) > 0;
        } catch (Exception e) {
            log("delete table", e);
        }
        closeDataBase();
        return b;
    }

    /**
     * delete table
     *
     * @return destroy is success
     */
    @Override
    public boolean deleteTable() {
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

    /**
     * Create database
     *
     * @return create table is success
     */
    @Override
    public boolean createTable() {
        boolean b = false;
        openDataBase();
        try {
            String sb = ("CREATE TABLE IF NOT EXISTS [" + TABLE_NAME + "] (\n" +
                    "  [" + ID + "] INTEGER, \n" +
                    "  [" + TITLE + "] TEXT, \n" +
                    "  [" + DECS + "] TEXT, \n" +
                    "  [" + RATING + "] TEXT, \n" +
                    "  CONSTRAINT [] PRIMARY KEY ([" + ID + "]));") +
                    "";
            String[] comandos = sb // for more Tables ;)
                    .split(";");

            for (String comando : comandos) {
                db.execSQL(comando.toLowerCase());
            }
            closeDataBase();
            b = true;
        } catch (Exception e) {
            log("create table", e);
        }
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
        data.put(TITLE, encrypt(encryptData, model.getTitleMovie()));
        data.put(DECS, encrypt(encryptData, model.getDescriptionMovie()));
        data.put(RATING, encrypt(encryptData, model.getRattingMovie().toString()));

        return data;
    }

    /**
     * get and verify movie in database
     *
     * @param cursor create in parent function for get movie in data base
     * @return BestMovieModel with data
     */
    private BestMovieModel getMovie(Cursor cursor) {
        BestMovieModel model = null;
        try {
            model = new BestMovieModel(
                    String.valueOf(cursor.getInt(cursor.getColumnIndex(ID))),
                    decrypt(encryptData, cursor.getString(cursor.getColumnIndex(TITLE))),
                    decrypt(encryptData, cursor.getString(cursor.getColumnIndex(DECS))),
                    Float.parseFloat(decrypt(encryptData, cursor.getString(cursor.getColumnIndex(RATING)))));
        } catch (Exception e) {
            log("Get Data in table error", e);
        }
        return model;
    }

    //==============================================================================================
    //
    // ** Auto Create
    //
    //==============================================================================================

    @Override
    public boolean autoCreateList(int typeList) {
        return insertListMovie(AutoCreate.createInitList(typeList));
    }
}
