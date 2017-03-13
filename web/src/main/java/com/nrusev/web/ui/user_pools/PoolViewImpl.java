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

        TextField tf1 = new TextField("Name");
        tf1.setIcon(FontAwesome.USER);
        tf1.setRequired(true);
        tf1.setValue(pool.getName());

        addComponent(tf1);

        TextField tf2 = new TextField("Description");
        tf2.setIcon(FontAwesome.BOOK);
        tf2.setRequired(true);
        tf2.setValue(pool.getDescription());

        addComponent(tf2);

        TeamSelectComponent select = new TeamSelectComponent(allTeams);

        select.addValueChangeListener(l-> this.eventBus.post(new PoolComponent.AddTeamEvent(this, (Team) l.getProperty().getValue(), pool)));
        addComponent(select);
    }

    private void initLayout(){
        setSpacing(true);
        setMargin(true);
    }

}
