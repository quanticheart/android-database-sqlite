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
 *  * Copyright(c) Developed by John Alves at 2019/7/20 at 3:29:17 for quantic heart studios
 *
 */

package com.quanticheart.lib.dao;

import com.quanticheart.lib.dao.model.BestMovieModel;

import java.util.ArrayList;

@SuppressWarnings({"unused", "WeakerAccess"})
public class AutoCreate {

    /*
     * insert init list
     */

    static final int LITTLE_LIST = 0;
    static final int LONG_LIST = 1;

    /**
     * create list for list
     *
     * @param listType type for create list
     * @return list with data
     */
    public static ArrayList<BestMovieModel> createInitList(int listType) {

        ArrayList<BestMovieModel> list = new ArrayList<>();

        if (listType == LITTLE_LIST) {
            addLittleList(list);
        }

        if (listType == LONG_LIST) {
            addLittleList(list);
            addBigList(list);
        }

        return list;
    }

    /**
     * fake list
     *
     * @param list list to add
     */
    private static void addBigList(ArrayList<BestMovieModel> list) {
        list.add(new BestMovieModel("Spider-Man", "mysterio's Movie", 4.0f));
        list.add(new BestMovieModel("Toy Store", "LiKE!!", 3.5f));
        list.add(new BestMovieModel("Avengers", "Best Movie Ever", 5.0f));
        list.add(new BestMovieModel("Goonies", "WOW!!!", 3.0f));
        list.add(new BestMovieModel("X-men", "Best Movie", 3.0f));
    }

    /**
     * fake list
     *
     * @param list list to add
     */
    private static void addLittleList(ArrayList<BestMovieModel> list) {
        list.add(new BestMovieModel("Star Wars", "Best Movie", 5.0f));
        list.add(new BestMovieModel("Cars", "Cars Movie", 4.0f));
        list.add(new BestMovieModel("Transformers", "WOW!!", 4.5f));
        list.add(new BestMovieModel("Pets 2", "I like", 3.0f));
        list.add(new BestMovieModel("Annabelle 3", "Like Movie", 2.5f));
    }
}
