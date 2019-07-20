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
 *  * Copyright(c) Developed by John Alves at 2019/7/14 at 5:50:52 for quantic heart studios
 *
 */

package com.quanticheart.lib.dao.model;

import java.io.Serializable;

@SuppressWarnings("unused")
public class BestMovieModel implements Serializable {

    private String id = "";
    private String titleMovie;
    private String descriptionMovie;
    private Float rattingMovie;

    public BestMovieModel(String titleMovie, String descriptionMovie, Float rattingMovie) {
        this.titleMovie = titleMovie;
        this.descriptionMovie = descriptionMovie;
        this.rattingMovie = rattingMovie;
    }

    public BestMovieModel(String id, String titleMovie, String descriptionMovie, Float rattingMovie) {
        this.id = id;
        this.titleMovie = titleMovie;
        this.descriptionMovie = descriptionMovie;
        this.rattingMovie = rattingMovie;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitleMovie() {
        return titleMovie;
    }

    public void setTitleMovie(String titleMovie) {
        this.titleMovie = titleMovie;
    }

    public String getDescriptionMovie() {
        return descriptionMovie;
    }

    public void setDescriptionMovie(String descriptionMovie) {
        this.descriptionMovie = descriptionMovie;
    }

    public Float getRattingMovie() {
        return rattingMovie;
    }

    public void setRattingMovie(Float rattingMovie) {
        this.rattingMovie = rattingMovie;
    }

    @Override
    public String toString() {
        return "BestMovieModel{" +
                "id='" + id + '\'' +
                ", titleMovie='" + titleMovie + '\'' +
                ", descriptionMovie='" + descriptionMovie + '\'' +
                ", rattingMovie=" + rattingMovie +
                '}';
    }
}
