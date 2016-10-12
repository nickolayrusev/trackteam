package com.nrusev.web;

import com.nrusev.domain.Team;
import com.nrusev.service.TeamService;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.NumberRenderer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by nikolayrusev on 3/1/16.
 */
@SpringView
public class SeasonView extends VerticalLayout implements View {
    private Grid grid;
    private final TeamService teamService;

    @Autowired
    public SeasonView(TeamService teamService){
        System.out.println("constructor...");
        this.teamService = teamService;
    }

    @PostConstruct
    public void setUp(){
        System.out.println("post construct...");
        setSpacing(true);
        setMargin(true);
        addComponent(new Label("Teams"));

        List<Team> allClubTeams = teamService.findAll();
        BeanItemContainer<Team> beanItemContainer = new BeanItemContainer<>(Team.class, allClubTeams.stream().skip(20).limit(40).collect(Collectors.toList()));
        beanItemContainer.addNestedContainerProperty("country.name");

        this.grid = new Grid();
        grid.setSizeFull();
        grid.setContainerDataSource(beanItemContainer);
        grid.removeAllColumns();
        grid.addColumn("id").setRenderer(new NumberRenderer("%d%n"));
        grid.addColumn("title");
        grid.addColumn("country.name");
        grid.setHeightMode(HeightMode.ROW);
        grid.setHeight("10");
        addComponent(grid);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        System.out.println("entering view...");
    }
}
