package com.nrusev.web.ui.team;

import com.nrusev.domain.Game;
import com.nrusev.domain.Team;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.nrusev.support.TextUtils.getGameCaption;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * Created by Nikolay Rusev on 1.11.2016 г..
 */
@SpringComponent
@ViewScope
public class TeamViewImpl extends CssLayout implements TeamView {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private VerticalLayout layout;

    private List<Button> previousGamesButtons;

    @PostConstruct
    public void postContruct() {
        LOG.info("Creating new TeamView");
        previousGamesButtons = new ArrayList<>();
//        setSizeFull();
    }

    @PreDestroy
    public void preDestroy() {
        LOG.info("Destroying TeamView");
    }


    @Override
    public void initLayout() {
        buildLayout();
    }

    private void buildLayout() {
        layout = new VerticalLayout();
        layout.setWidth("100%");
        layout.setMargin(true);
        layout.setSpacing(true);
        addComponent(layout);
    }

    @Override
    public void loadData(Team team) {
        Label caption = new Label(FontAwesome.HOME.getHtml() + team.getTitle(), ContentMode.HTML);
        caption.addStyleName(ValoTheme.LABEL_H1);
        layout.addComponent(caption);
    }

    @Override
    public void loadPreviousGames(List<Game> games) {
        Label subCaption = new Label("Previous matches: ", ContentMode.HTML);
        subCaption.addStyleName(ValoTheme.LABEL_LIGHT);
        layout.addComponent(subCaption);

        games.forEach(g->{
            final Button button = new Button();
            button.setCaption(getGameCaption(g, UI.getCurrent().getLocale()));
            button.setStyleName(ValoTheme.BUTTON_LINK);
            button.setData(g);
            previousGamesButtons.add(button);
            layout.addComponent(button);
        });
    }

    @Override
    public void loadFormOfLastGames(List<String> formOfLastGames) {
        layout.addComponent(new Label(formOfLastGames.stream().collect(joining("-"))));
    }

    @Override
    public List<Button> getPreviosGamesButtons() {
        return previousGamesButtons;
    }
}
