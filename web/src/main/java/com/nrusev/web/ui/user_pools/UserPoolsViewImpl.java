package com.nrusev.web.ui.user_pools;

import com.google.common.eventbus.EventBus;
import com.nrusev.domain.Team;
import com.nrusev.domain.TeamPool;
import com.nrusev.web.ui.components.PoolComponent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Nikolay Rusev on 26.10.2016 г..
 */
@SpringComponent
@ViewScope
public class UserPoolsViewImpl extends CssLayout implements UserPoolsView {

    private HorizontalLayout layout;

    private List<TeamPool> pools;

    private final EventBus eventBus;

    @Autowired
    public UserPoolsViewImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void initLayout() {
        buildLayout();
    }

    @Override
    public void loadData(List<TeamPool> pools, List<Team> teams) {
        layout.removeAllComponents();
        this.pools = pools;
        this.pools.forEach(teamPool -> {
            PoolComponent poolComponent = new PoolComponent(teamPool, teams);
            poolComponent.addTeamClickedListener(l-> this.eventBus.post(new PoolComponent.TeamClickedEvent(l.getComponent(),l.getTeam(), teamPool)));
            poolComponent.addAddTeamListener(l-> this.eventBus.post(new PoolComponent.AddTeamEvent(l.getComponent(),l.getTeam(), teamPool)));
            layout.addComponent(poolComponent);
        });

        layout.addComponent(new Button("Add pool",l->{
            final Window window = new Window("Add new pool");
            window.setWidth(300.0f, Unit.PIXELS);
            window.center();
            final FormLayout content = new FormLayout();
            content.setSpacing(true);
            content.setMargin(true);

            TextField name = new TextField("name");
            content.addComponent(name);
            TextField description = new TextField("description");
            content.addComponent(description);
            content.addComponent(new Button("Add", q-> this.eventBus.post(new TeamPool(name.getValue(),description.getValue(),false))));

            window.setContent(content);
            UI.getCurrent().addWindow(window);
        }));
    }


    @Override
    public boolean addTeam(TeamPool pool, Team team) {
        return false;
    }

    private void buildLayout() {
        layout = new HorizontalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        addComponent(layout);
    }


}
