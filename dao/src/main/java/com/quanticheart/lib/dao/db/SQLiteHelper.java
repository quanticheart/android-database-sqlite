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
 *  * Copyright(c) Developed by John Alves at 2019/7/14 at 5:51:58 for quantic heart studios
 *
 */

package com.quanticheart.lib.dao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.quanticheart.lib.dao.constants.contants.*;
import static com.quanticheart.lib.dao.util.MsgUtil.log;

class SQLiteHelper extends SQLiteOpenHelper {

    SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createDatabase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgradeDatabse(db, oldVersion, newVersion);
        onCreate(db);
    }

    /**
     * for update data base
     *
     * @param db         for update
     * @param oldVersion old version create database
     * @param newVersion new version database
     */
    private void onUpgradeDatabse(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("Old databese version", String.valueOf(oldVersion));
        Log.w("new database version", String.valueOf(newVersion));
        try {
            String[] comandos = QUERY_CREATE_TABLE_MOVIES.split(";");
            for (String comando : comandos) {
                db.execSQL(comando.toLowerCase());
            }
        } catch (Exception e) {
            log("create table", e);
        }
    }

    /**
     * Create database
     *
     * @param db for create
     */
    private void createDatabase(SQLiteDatabase db) {
        try {
            String[] comandos = QUERY_CREATE_TABLE_MOVIES.split(";");
            for (String comando : comandos) {
                db.execSQL(comando.toLowerCase());
            }
        } catch (Exception e) {
            log("create table", e);
        }
    }

}