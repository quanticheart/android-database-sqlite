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
 *  * Copyright(c) Developed by John Alves at 2019/7/20 at 0:49:29 for quantic heart studios
 *
 */

package com.quanticheart.lib.dao;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.quanticheart.lib.dao.model.BestMovieModel;

public class test extends Activity {

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Create
        DatabaseMovie base = new DatabaseMovie(getApplicationContext());
        Log.w("--- Init List", base.getListMovies().toString());

        //insert list
        base.autoCreateList(AutoCreate.LONG_LIST);
        Log.w("--- After Create list with Insert List", base.getListMovies().toString());

        //insert one
        base.insertMovie(new BestMovieModel("TEST MOVIE", "TEST DESCRIPTION", 5f));
        Log.w("--- After insert TEST movie in base", base.getListMovies().toString());

        //Edit one
        BestMovieModel modelEdit = base.getLastRow();
        modelEdit.setTitleMovie(modelEdit.getTitleMovie()+" EDITED");
        modelEdit.setDescriptionMovie(modelEdit.getDescriptionMovie()+" EDITED");
        base.editMovie(modelEdit);
        Log.w("--- After edit TEST movie in base", base.getListMovies().toString());

        // get
        Log.w("--- Get Row By ID 1", base.getRowByID("1").toString());
        Log.w("--- Get First Row", base.getFirstRow().toString());
        Log.w("--- Get Position Row At 0", base.getRowAtPosition(0).toString());
        Log.w("--- Get Last Row", base.getLastRow().toString());

        // delete
        base.deleteRowByID("1");
        Log.w("--- Deleted Row At ID 1, new row at position 1 is", base.getRowAtPosition(1).toString());
        base.deleteFirstRow();
        Log.w("--- Deleted First Row, new first row is", base.getFirstRow().toString());
        base.deleteRowAtPosition(0);
        Log.w("--- Deleted Row By Position 0, new Row at position 0 is", base.getFirstRow().toString());
        base.deleteLastRow();
        Log.w("--- Delete Last Row, new last row is", base.getLastRow().toString());

        // After Delete
        Log.w("--- After Deleted rows in list", base.getListMovies().toString());

        // After Clean all table
        base.cleanTableMovies();
        Log.w("--- After clean Table", base.getListMovies().toString());

        base.createTableMovies();
    }
}
