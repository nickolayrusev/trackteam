package com.nrusev.web;

import com.nrusev.domain.Team;
import com.nrusev.service.TeamService;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.NumberRenderer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.text.NumberFormat;
import java.util.Locale;

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
        this.grid = new Grid();
    }

    @PostConstruct
    public void setUp(){
        System.out.println("post construct...");
        setSpacing(true);
        setMargin(true);
        addComponent(new Label("Teams"));
        grid.setSizeFull();
        grid.setContainerDataSource(new BeanItemContainer<>(Team.class,teamService.findAll()));
        grid.removeAllColumns();
        grid.addColumn("id").setRenderer(new NumberRenderer("%d%n"));
        grid.addColumn("shortName");
        grid.addColumn("name");
        grid.setHeightMode(HeightMode.ROW);
        grid.setHeight("10");
        addComponent(grid);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        System.out.println("entering view...");
    }
}
