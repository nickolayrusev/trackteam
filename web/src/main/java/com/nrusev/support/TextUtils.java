package com.nrusev.support;

import com.nrusev.domain.Game;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * Created by Nikolay Rusev on 31.10.2016 г..
 */
public final class TextUtils {
    private TextUtils() {
    }

    public static String getGameCaption(Game game, Locale locale) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm", locale);
        StringBuilder builder = new StringBuilder();
//        builder.append(game.getRound().getTitle() )
//                .append(" ")
        builder.append(format.format(game.getPlayAt()))
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

    public static String getGameCaptionSimple(Game game) {
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

    public static String questionIfNull(Long score) {
        if (Objects.isNull(score))
            return "?";
        return String.valueOf(score);
    }

    public static String score(final Game game) {
        return questionIfNull(game.getScore1()) + " : " + questionIfNull(game.getScore2());
    }
}
