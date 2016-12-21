package com.nrusev.support;

import com.nrusev.domain.Game;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Nikolay Rusev on 31.10.2016 г..
 */
public final class TextUtils {
    private TextUtils(){}

    public static String getGameCaption(Game game, Locale locale){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MMMM/dd", locale);
        StringBuilder builder = new StringBuilder();
        builder.append(game.getRound().getTitle() )
                .append(" ")
                .append(format.format(game.getPlayAt()))
                .append(" ")
                .append(game.getHomeTeam().getTitle())
                .append(" vs. ")
                .append(game.getVisitorTeam().getTitle())
                .append(" ")
                .append(game.getScore1())
                .append(":")
                .append(game.getScore2());
        return builder.toString();
    }

    public static String getGameCaptionSimple(Game game){
        StringBuilder builder = new StringBuilder();
        builder.append(game.getHomeTeam().getTitle())
                .append(" vs. ")
                .append(game.getVisitorTeam().getTitle())
                .append(" ")
                .append(game.getScore1())
                .append(":")
                .append(game.getScore2());
        return builder.toString();
    }
}
