package com.nrusev.web.ui.components;


import com.nrusev.domain.Team;
import com.nrusev.domain.TeamPool;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.*;
import com.vaadin.util.ReflectTools;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Nikolay Rusev on 13.1.2017 г..
 */
public class PoolComponent extends CustomComponent {

    public interface TeamClickListener extends Serializable {
        Method CLICK_METHOD = ReflectTools.findMethod(TeamClickListener.class, "click", new Class[]{TeamClickedEvent.class});

        void click(TeamClickedEvent var1);
    }

    public static class TeamClickedEvent extends Event {
        private Team team;
        private TeamPool pool;

        public TeamClickedEvent(Component source) {
            super(source);
        }

        public TeamClickedEvent(Component source, Team team, TeamPool pool) {
            super(source);
            this.team = team;
            this.pool = pool;
        }

        public Team getTeam() {
            return team;
        }

        public TeamPool getPool() {
            return pool;
        }
    }


    public interface AddTeamListener extends Serializable {
        Method ADD_METHOD = ReflectTools.findMethod(AddTeamListener.class, "add", new Class[]{AddTeamEvent.class});
        void add(AddTeamEvent var1);
    }

    public static class AddTeamEvent extends Event {
        private Team team;
        private TeamPool pool;

        public AddTeamEvent(Component source) {
            super(source);
        }

        public AddTeamEvent(Component source, Team team, TeamPool pool) {
            super(source);
            this.team = team;
            this.pool = pool;
        }

        public Team getTeam() {
            return team;
        }

        public TeamPool getPool() {
            return pool;
        }
    }


    private TeamPool pool;
    private List<Team> teams;
    private Panel panel;
    private VerticalLayout panelContent;

    public PoolComponent(TeamPool pool, List<Team> teamList) {
        this.pool = pool;
        this.teams = teamList;
        initLayout();
        loadInitialPools();
        loadAutocompleteData();
    }

    private void initLayout() {
        panel = new Panel("My Custom Component");
        panelContent = new VerticalLayout();
        panelContent.setMargin(true); // Very useful
        panel.setContent(panelContent);

        // Compose from multiple components
//        Label label = new Label("");
//        label.setSizeUndefined(); // Shrink
//        panelContent.addComponent(label);
//        panelContent.addComponent(new Button("Ok"));

        // Set the size as undefined at all levels
        panelContent.setSizeUndefined();
        panel.setSizeUndefined();
        setSizeUndefined();

        // The composition root MUST be set
        setCompositionRoot(panel);
    }

    private void loadInitialPools() {
        panel.setCaption(pool.getName() + " " + pool.getDescription());
        pool.getTeams().forEach(team -> {
            panelContent.addComponent(new Button(team.getTitle(), clickEvent -> this.fireEvent(new TeamClickedEvent(this, team, pool))));
        });
    }

    private void loadAutocompleteData() {
        BeanItemContainer<Team> container =
                new BeanItemContainer<Team>(
                        Team.class);
        teams.forEach(container::addItem);

        // Create a selection component bound
        // to the container
        ComboBox select = new ComboBox("Planets",
                container);

        // Set the caption mode to read the
        // caption directly from the 'name'
        // property of the bean
        select.setItemCaptionMode(
                AbstractSelect.ItemCaptionMode.PROPERTY);
        select.setItemCaptionPropertyId("title");
        select.setFilteringMode(FilteringMode.CONTAINS);
//        select.addValueChangeListener(l->{ eventBus.post((Team)l.getProperty().getValue());});
        select.addValueChangeListener(l-> this.fireEvent(new AddTeamEvent(this, (Team) l.getProperty().getValue(), pool)));
        panelContent.addComponent(select);
    }

    public void addTeamClickedListener(TeamClickListener okListener) {
        this.addListener(TeamClickedEvent.class, okListener, TeamClickListener.CLICK_METHOD);
    }

    public void addAddTeamListener(AddTeamListener addTeamListener){
        this.addListener(AddTeamEvent.class, addTeamListener, AddTeamListener.ADD_METHOD);
    }


    public void addTeam(Team team) {
        this.pool.getTeams().add(team);
    }

}
