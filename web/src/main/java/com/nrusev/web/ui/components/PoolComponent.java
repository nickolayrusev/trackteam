package com.nrusev.web.ui.components;


import com.nrusev.domain.Team;
import com.nrusev.domain.TeamPool;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
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

    public static class TeamClickedEvent extends TeamEvent {
        public TeamClickedEvent(Component source) {
            super(source);
        }

        public TeamClickedEvent(Component source, Team team, TeamPool pool) {
            super(source, team, pool);
        }
    }


    public interface AddTeamListener extends Serializable {
        Method ADD_METHOD = ReflectTools.findMethod(AddTeamListener.class, "add", new Class[]{AddTeamEvent.class});
        void add(AddTeamEvent var1);
    }

    public static class AddTeamEvent extends TeamEvent {
        public AddTeamEvent(Component source) {
            super(source);
        }

        public AddTeamEvent(Component source, Team team, TeamPool pool) {
            super(source, team, pool);
        }
    }

    public interface DeleteTeamPoolListener extends Serializable{
        Method DELETE_METHOD = ReflectTools.findMethod(DeleteTeamPoolListener.class, "delete", new Class[]{DeleteTeamPoolEvent.class});
        void delete(DeleteTeamPoolEvent deleteTeamPoolEvent);

    }

    public static class DeleteTeamPoolEvent extends Event{

        private TeamPool teamPool;

        public DeleteTeamPoolEvent(Component source, TeamPool teamPool) {
            super(source);
            this.teamPool = teamPool;
        }

        public TeamPool getTeamPool() {
            return teamPool;
        }
    }

    private static class TeamEvent extends Event{
        private Team team;
        private TeamPool pool;


        public TeamEvent(Component source) {
            super(source);
        }


        public TeamEvent(Component source, Team team, TeamPool pool) {
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

        @Override
        public String toString() {
            return "TeamEvent{" +
                    "team=" + team +
                    ", pool=" + pool +
                    '}';
        }
    }

    public interface EditTeamPoolListener extends Serializable{
        Method EDIT_METHOD = ReflectTools.findMethod(EditTeamPoolListener.class, "edit", new Class[]{EditTeamPoolEvent.class});
        void edit(EditTeamPoolEvent editTeamPoolEvent);
    }

    public static class EditTeamPoolEvent extends Event{
        private TeamPool teamPool;

        public EditTeamPoolEvent(Component source, TeamPool pool) {
            super(source);
            this.teamPool = pool;
        }

        public TeamPool getTeamPool() {
            return teamPool;
        }
    }

    private TeamPool pool;
    private List<Team> teams;
    private Panel panel;
    private VerticalLayout panelContent;
    private HorizontalLayout buttonHolderLayout;

    public PoolComponent(TeamPool pool, List<Team> teamList) {
        this.pool = pool;
        this.teams = teamList;
        initLayout();
        loadInitialPools();
        loadAutocompleteData();
        initButtonHolderLayout();
        loadDeleteButton();
        loadEditButton();
    }


    private void initLayout() {
        panel = new Panel();
        panelContent = new VerticalLayout();
        panelContent.setMargin(true); // Very useful
        panelContent.setSpacing(true);
        panelContent.setResponsive(true);
        panelContent.setWidth(250,Unit.PIXELS);

        panel.setContent(panelContent);
        // Set the size as undefined at all levels
        panelContent.setSizeUndefined();
        panel.setSizeUndefined();
        setSizeUndefined();

        // The composition root MUST be set
        setCompositionRoot(panel);
    }

    private void initButtonHolderLayout() {
        buttonHolderLayout = new HorizontalLayout();
        panelContent.addComponent(buttonHolderLayout);
    }

    private void loadInitialPools() {
        panel.setCaption(pool.getName() + " " + pool.getDescription());
        pool.getTeams().forEach(team -> {
            Button button = new Button(team.getTitle(), clickEvent -> this.fireEvent(new TeamClickedEvent(this, team, pool)));
            button.addStyleName(ValoTheme.BUTTON_SMALL);
            panelContent.addComponent(button);
        });
    }


    private void loadAutocompleteData() {
        TeamSelectComponent select = new TeamSelectComponent(teams);
        select.addValueChangeListener(l-> this.fireEvent(new AddTeamEvent(this, (Team) l.getProperty().getValue(), pool)));
        panelContent.addComponent(select);
    }

    private void loadDeleteButton(){
        Button delete = new Button("delete", l -> this.fireEvent(new DeleteTeamPoolEvent(this, pool)));
        delete.addStyleName(ValoTheme.BUTTON_LINK);
        buttonHolderLayout.addComponent(delete);
    }

    private void loadEditButton(){
        Button edit = new Button("edit", l -> this.fireEvent(new EditTeamPoolEvent(this, pool)));
        edit.addStyleName(ValoTheme.BUTTON_LINK);
        buttonHolderLayout.addComponent(edit);

    }

    public void addTeamClickedListener(TeamClickListener okListener) {
        this.addListener(TeamClickedEvent.class, okListener, TeamClickListener.CLICK_METHOD);
    }

    public void addAddTeamListener(AddTeamListener addTeamListener){
        this.addListener(AddTeamEvent.class, addTeamListener, AddTeamListener.ADD_METHOD);
    }

    public void addDeleteTeamPoolListener(DeleteTeamPoolListener deleteTeamPoolListener){
        this.addListener(DeleteTeamPoolEvent.class,deleteTeamPoolListener,DeleteTeamPoolListener.DELETE_METHOD);
    }

    public void addEditTeamPoolListener(EditTeamPoolListener editTeamPoolListener){
        this.addListener(EditTeamPoolEvent.class,editTeamPoolListener, EditTeamPoolListener.EDIT_METHOD);
    }


    public void addTeam(Team team) {
        this.pool.getTeams().add(team);
    }


    public TeamPool getPool() {
        return pool;
    }

}
