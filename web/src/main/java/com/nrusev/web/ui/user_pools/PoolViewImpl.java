package com.nrusev.web.ui.user_pools;

import com.google.common.eventbus.EventBus;
import com.nrusev.domain.Country;
import com.nrusev.domain.Team;
import com.nrusev.domain.TeamPool;
import com.nrusev.web.ui.components.PoolComponent;
import com.nrusev.web.ui.components.TeamSelectComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.ClassResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by Nikolay Rusev on 10.3.2017 г..
 */
@SpringComponent
@ViewScope
public class PoolViewImpl extends FormLayout implements PoolView{
    private final EventBus eventBus;
    private TeamPool pool;
    private TextField tf1;
    private TextField tf2;
    private ComboBox select;
    private Table table;

    @Autowired
    public PoolViewImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @PostConstruct
    public void init(){
        initLayout();
    }

    @Override
    public void init(TeamPool pool, List<Team> allTeams) {
        this.pool = pool;
        Label caption = new Label( "Edit team pool", ContentMode.HTML);
        caption.addStyleName(ValoTheme.LABEL_H1);

        addComponent(caption);

        tf1 = new TextField("Name");
        tf1.setIcon(FontAwesome.USER);
        tf1.setRequired(true);
        tf1.setValue(pool.getName());

        addComponent(tf1);

        tf2 = new TextField("Description");
        tf2.setIcon(FontAwesome.BOOK);
        tf2.setRequired(true);
        tf2.setValue(pool.getDescription());

        addComponent(tf2);

        select = new TeamSelectComponent(FontAwesome.MALE,allTeams);

        select.addValueChangeListener(l-> this.eventBus.post(new PoolComponent.AddTeamEvent(this, (Team) l.getProperty().getValue(), pool)));
        addComponent(select);

        table = new Table("Teams in pool");

        // Define two columns for the built-in container
        table.addContainerProperty("Name", String.class, null);
        table.addContainerProperty("Country",  String.class, null);
        table.addContainerProperty("Delete", Button.class, null);

        pool.getTeams().forEach(t->{
           table.addItem(new Object[]{ t.getTitle() , "", new Button("Delete",l->this.eventBus.post(new PoolComponent.TeamClickedEvent(this,t,pool)))},t.getId());
        });

        table.setPageLength(pool.getTeams().size());

        addComponent(table);
    }

    private void initLayout(){
        setSpacing(true);
        setMargin(true);
    }

    public void addTeam(Team team){
        this.table.addItem(new Object[]{team.getTitle(),""}, team.getId());
        table.setPageLength(table.getPageLength()+1);
    }

    public void removeTeam(Team team){

    }
}
