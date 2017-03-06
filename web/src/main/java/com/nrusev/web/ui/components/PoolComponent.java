package com.nrusev.web.ui.components;


import com.nrusev.domain.Country;
import com.nrusev.domain.Team;
import com.nrusev.domain.TeamPool;
import com.nrusev.web.ui.match.MatchView;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.*;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.util.ReflectTools;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

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
        loadDeleteButton();
    }

    private void initLayout() {
        panel = new Panel();
        panelContent = new VerticalLayout();
        panelContent.setMargin(true); // Very useful
        panelContent.setSpacing(true);
        panelContent.setResponsive(true);
        panelContent.setWidth(200,Unit.PIXELS);
        panel.setContent(panelContent);

        // Set the size as undefined at all levels
        panelContent.setSizeUndefined();
        panel.setSizeUndefined();
        setSizeUndefined();

        // The composition root MUST be set
        setCompositionRoot(panel);
    }

    private void loadInitialPools() {
        panel.setCaption(pool.getName() + " " + pool.getDescription());
        pool.getTeams().stream().sorted((q,p) ->{ return q.getTitle().compareToIgnoreCase(p.getTitle());}).forEach(team -> {

            Button button = new Button(team.getTitle(), clickEvent -> this.fireEvent(new TeamClickedEvent(this, team, pool)));
            button.addStyleName(ValoTheme.BUTTON_SMALL);
            panelContent.addComponent(button);
        });
    }


    private void loadAutocompleteData() {
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty("title", String.class,
                null);
        container.addContainerProperty("flag", Resource.class,
                null);

        teams.forEach(t -> {
            Item item = container.addItem(t);
            item.getItemProperty("title").setValue(t.getTitle());
            item.getItemProperty("flag").setValue(new ClassResource((imageFlag(t.getCountry()))));
        });


        // Create a selection component bound
        // to the container
        ComboBox select = new ComboBox("Teams",
                container);

        // Set the caption mode to read the
        // caption directly from the 'title'
        // property of the bean
        select.setItemCaptionMode(
                AbstractSelect.ItemCaptionMode.PROPERTY);
        select.setItemCaptionPropertyId("title");
        select.setItemIconPropertyId("flag");

        // Set the appropriate filtering mode for this example
        select.setFilteringMode(FilteringMode.CONTAINS);
        select.setImmediate(true);


        // Disallow null selections
        select.setNullSelectionAllowed(false);

        select.addValueChangeListener(l-> this.fireEvent(new AddTeamEvent(this, (Team) l.getProperty().getValue(), pool)));
        panelContent.addComponent(select);
    }

    private void loadDeleteButton(){
        Button delete = new Button("delete", l -> this.fireEvent(new DeleteTeamPoolEvent(this, pool)));
        delete.addStyleName(ValoTheme.BUTTON_DANGER);
        panelContent.addComponent(delete);
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


    public void addTeam(Team team) {
        this.pool.getTeams().add(team);
    }

    private static String imageFlag(final Country country){
//        "São Tomé and Príncipe";"sao-tome-and-principe";"st"
//        "Cape Verde";"cape-verde";"cv"
//        "European Union";"european-union";"eu"
//        "Cocos (Keeling) Islands";"cocos-keeling-islands";"cc"
//        "England";"england";"eng"
//        "Scotland";"scotland";"sco"
//        "Wales";"wales";"wal"
//        "Northern Ireland";"northern-ireland";"nir"
//        "Tahiti";"tahiti";"pf"
        List<String> strings = Stream.of("st", "cv", "eu", "cc", "pf").collect(toList());

        String prefix = "/static/flags/";
        String suffix = ".gif";

        return Optional.ofNullable(country).map(c->{
            String key = c.getKey();
            if(strings.contains(key))
                return "not";
            if(key.equalsIgnoreCase("eng"))
                return "england";
            if(key.equalsIgnoreCase("wal"))
                return "wales";
            if(key.equalsIgnoreCase("sco"))
                return "scotland";
            if(key.equalsIgnoreCase("nir"))
                return "northern-ireland";
            return country.getAlpha2().toLowerCase();
        }).map(s->prefix + s +suffix).orElse(prefix + "default" + suffix);
    }

    public TeamPool getPool() {
        return pool;
    }

}
