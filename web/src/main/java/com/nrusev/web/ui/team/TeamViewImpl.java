package com.nrusev.web.ui.team;

import com.google.common.eventbus.EventBus;
import com.nrusev.domain.Game;
import com.nrusev.domain.Team;
import com.vaadin.data.Buffered;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.nrusev.support.TextUtils.getGameCaption;
import static com.nrusev.support.TextUtils.score;
import static java.util.stream.Collectors.joining;

/**
 * Created by Nikolay Rusev on 1.11.2016 г..
 */
@SpringComponent
@ViewScope
public class TeamViewImpl extends CssLayout implements TeamView {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private VerticalLayout layout;

    private Table grid;

    private final EventBus eventBus;

    @Autowired
    public TeamViewImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }


    @PostConstruct
    public void postConstruct() {
        LOG.info("Creating new TeamView");
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
        Label caption = new Label(FontAwesome.HOME.getHtml() + " " + team.getTitle(), ContentMode.HTML);
        caption.addStyleName(ValoTheme.LABEL_H1);
        layout.addComponent(caption);
    }

    @Override
    public void loadPreviousGames(List<Game> games) {
        Label subCaption = new Label("Previous matches: ", ContentMode.HTML);
        subCaption.addStyleName(ValoTheme.LABEL_LIGHT);
        layout.addComponent(subCaption);

        grid = new Table(){
            @Override
            protected String formatPropertyValue(Object rowId,
                                                 Object colId, Property property) {
                // Format by property type
                if (property.getType() == Date.class) {
                    SimpleDateFormat df =
                            new SimpleDateFormat("dd MMM,yyyy", Locale.ENGLISH);
                    return df.format((Date)property.getValue());
                }

                return super.formatPropertyValue(rowId, colId, property);
            }
        };
        grid.addContainerProperty("Date", Date.class, null);
        grid.addContainerProperty("League", String.class, null);
        grid.addContainerProperty("Round", String.class, null);
        grid.addContainerProperty("Home", String.class, null);
        grid.addContainerProperty("Result", Button.class, null);
        grid.addContainerProperty("Away", String.class, null);

        games.forEach(g -> {
            final Button btnScore = new Button(score(g),l->eventBus.post(new GameClickedEvent(g)));
            btnScore.addStyleName(ValoTheme.BUTTON_LINK);

            grid.addItem(new Object[]{g.getPlayAt(), g.getRound().getTitle(),
                    g.getRound().getEvent().getLeague().getTitle(),
                    g.getHomeTeam().getTitle(),
                    btnScore,
                    g.getVisitorTeam().getTitle()
            }, g.getId());
        });
        layout.addComponent(grid);

    }

    @Override
    public void loadFormOfLastGames(List<String> formOfLastGames) {
        layout.addComponent(new Label(formOfLastGames.stream().collect(joining("-"))));
    }

}
