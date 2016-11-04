package com.nrusev.web.ui.match;

import com.nrusev.domain.Game;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static com.nrusev.support.TextUtils.getGameCaption;

/**
 * Created by Nikolay Rusev on 27.10.2016 г..
 */
@SpringComponent
@ViewScope
public class MatchViewImpl extends CssLayout implements MatchView {

    private VerticalLayout layout;

    private List<Button> previousGamesButtons;

    private Button homeTeamButton;

    private Button visitorTeamButton;

    @PostConstruct
    public void postConstruct() {
        previousGamesButtons = new ArrayList<>();
//        setSizeFull();
    }


    @Override
    public void initLayout() {
        buildLayout();
    }

    @Override
    public void loadInitialData(Game g) {
        homeTeamButton = new Button();
        homeTeamButton.setCaption(g.getHomeTeam().getTitle());
        homeTeamButton.setData(g.getHomeTeam());
        homeTeamButton.setStyleName(ValoTheme.BUTTON_LINK);

        visitorTeamButton = new Button();
        visitorTeamButton.setCaption(g.getVisitorTeam().getTitle());
        visitorTeamButton.setData(g.getVisitorTeam());
        visitorTeamButton.setStyleName(ValoTheme.BUTTON_LINK);

        layout.addComponent(new Label("play at "+ g.getPlayAt()));
        layout.addComponent(homeTeamButton);
        layout.addComponent(visitorTeamButton);
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
            button.setData(g);
            previousGamesButtons.add(button);
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

        Label subCaption = new Label("Match detailed view ", ContentMode.HTML);
        subCaption.addStyleName(ValoTheme.LABEL_LIGHT);
        layout.addComponent(subCaption);
    }

    @Override
    public List<Button> getPreviousGamesButtons() {
        return previousGamesButtons;
    }

    @Override
    public Button getHomeTeamButton() {
        return homeTeamButton;
    }

    @Override
    public Button getVisitorTeamButton() {
        return visitorTeamButton;
    }

    @Override
    public void clear() {
        removeAllComponents();
        initLayout();
    }

}
