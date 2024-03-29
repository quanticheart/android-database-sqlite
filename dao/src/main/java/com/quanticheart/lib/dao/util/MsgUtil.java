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
 *  * Copyright(c) Developed by John Alves at 2019/7/15 at 7:15:54 for quantic heart studios
 *
 */

package com.quanticheart.lib.dao.util;

import android.util.Log;
import com.quanticheart.lib.dao.BuildConfig;
import com.quanticheart.lib.dao.DatabaseMovie;

public class MsgUtil {

    /**
     * show simple msg in log
     *
     * @param title for init log msg
     * @param e     for get error msg
     */
    public static void log(String title, Exception e) {
        if (BuildConfig.DEBUG)
            Log.w(DatabaseMovie.class.getSimpleName() + ": Error " + title, e);
    }

    /**
     * show simple msg in log
     *
     * @param title for init log msg
     * @param msg   Log msg
     */
    public static void logI(String title, String msg) {
        if (BuildConfig.DEBUG)
            Log.i(DatabaseMovie.class.getSimpleName() + ": Msg " + title, msg);
    }

    /**
     * show simple msg in log
     *
     * @param title for init log msg
     * @param msg   Log msg
     */
    public static void logE(String title, String msg) {
        if (BuildConfig.DEBUG)
            Log.e(DatabaseMovie.class.getSimpleName() + ": Msg " + title, msg);
    }
}
