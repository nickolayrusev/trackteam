package com.nrusev.web.ui.match;

import com.nrusev.domain.Game;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.List;

import static com.nrusev.support.TextUtils.getGameCaption;

/**
 * Created by Nikolay Rusev on 27.10.2016 г..
 */
@SpringComponent
@ViewScope
public class MatchViewImpl  extends CssLayout implements MatchView {

    private VerticalLayout layout;

    @Override
    public void initLayout() {
        buildLayout();

    }

    @Override
    public void loadInitialData(Game g) {
        Label game = new Label(g.getHomeTeam().getTitle() + " vs. " + g.getVisitorTeam().getTitle(), ContentMode.HTML);
        game.addStyleName(ValoTheme.LABEL_H2);
        layout.addComponent(game);

    }

    @Override
    public void displayPreviousMeetings(List<Game> previousGames) {
        Label subCaption = new Label("Previous matches: ", ContentMode.HTML);
        subCaption.addStyleName(ValoTheme.LABEL_LIGHT);
        layout.addComponent(subCaption);

        previousGames.forEach(g->{
            final Button button = new Button();
            button.setCaption(getGameCaption(g, UI.getCurrent().getLocale()));
            button.setStyleName(ValoTheme.BUTTON_LINK);
            layout.addComponent(button);
        });
    }

    private void buildLayout() {
        layout = new VerticalLayout();
        layout.setWidth("100%");
        layout.setMargin(true);
        layout.setSpacing(true);
        addComponent(layout);

        Label caption = new Label(FontAwesome.HOME.getHtml() + " Welcome to match view", ContentMode.HTML);
        caption.addStyleName(ValoTheme.LABEL_H1);
        layout.addComponent(caption);

        Label subCaption = new Label("Match detailed view", ContentMode.HTML);
        subCaption.addStyleName(ValoTheme.LABEL_LIGHT);
        layout.addComponent(subCaption);



    }

}
