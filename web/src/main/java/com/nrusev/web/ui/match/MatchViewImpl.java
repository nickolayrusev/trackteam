package com.nrusev.web.ui.match;

import com.google.common.eventbus.EventBus;
import com.nrusev.domain.Game;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

import static com.nrusev.support.TextUtils.getGameCaption;
import static com.nrusev.support.TextUtils.getGameCaptionSimple;

/**
 * Created by Nikolay Rusev on 27.10.2016 г..
 */
@SpringComponent
@ViewScope
public class MatchViewImpl extends CssLayout implements MatchView {

    private VerticalLayout layout;


    private Button homeTeamButton;

    private Button visitorTeamButton;

    private final EventBus eventBus;

    @Autowired
    public MatchViewImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }


    @PostConstruct
    public void postConstruct() {
    }

    @PreDestroy
    public void onDestroy(){
    }


    @Override
    public void initLayout() {
        buildLayout();
    }

    @Override
    public void loadInitialData(Game g) {
        homeTeamButton = new Button();
        homeTeamButton.setCaption(g.getHomeTeam().getTitle() + " " + g.getScore1());
//        homeTeamButton.setData(g.getHomeTeam());
        homeTeamButton.setStyleName(ValoTheme.BUTTON_LINK);
        homeTeamButton.addClickListener(l->{
            this.eventBus.post(new TeamClickedEvent(g.getHomeTeam()));
        });

        visitorTeamButton = new Button();
        visitorTeamButton.setCaption(g.getVisitorTeam().getTitle() + " " + g.getScore2());
        visitorTeamButton.setStyleName(ValoTheme.BUTTON_LINK);
        visitorTeamButton.addClickListener(l->{
            this.eventBus.post(new TeamClickedEvent(g.getVisitorTeam()));
        });

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
            button.setCaption(getGameCaptionSimple(g));
            button.setStyleName(ValoTheme.BUTTON_LINK);
            button.addClickListener(l->{
                this.eventBus.post(new MatchView.GameClickedEvent(g));
            });

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
    public void clear() {
        removeAllComponents();
        initLayout();
    }

}
