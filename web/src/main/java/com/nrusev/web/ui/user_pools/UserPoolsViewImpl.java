package com.nrusev.web.ui.user_pools;

import com.google.common.eventbus.EventBus;
import com.nrusev.domain.Team;
import com.nrusev.domain.TeamPool;
import com.nrusev.web.ui.components.PoolComponent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Vector;
import java.util.stream.IntStream;

/**
 * Created by Nikolay Rusev on 26.10.2016 г..
 */
@SpringComponent
@ViewScope
public class UserPoolsViewImpl extends CssLayout implements UserPoolsView {

    private VerticalLayout mainLayout;
    private HorizontalLayout layout;
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
        pools.forEach(teamPool -> layout.addComponent(createPoolComponent(teamPool,teams)));

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
            content.addComponent(new Button("Add", q -> {
                this.eventBus.post(new TeamPool(name.getValue(), description.getValue(), false));
                window.close();
            }));

            window.setContent(content);
            UI.getCurrent().addWindow(window);
        }));
    }


    @Override
    public void reloadPool(TeamPool pool, List<Team> teams){
        IntStream.range(0,layout.getComponentCount()).forEach(i->{
            Component c = layout.getComponent(i);
            if(c instanceof PoolComponent){
                PoolComponent poolComponent = (PoolComponent) c;
                if(pool.getId().equals(poolComponent.getPool().getId())){
                    layout.replaceComponent(poolComponent, createPoolComponent(pool,teams));
                }
            }
        });
    }

    @Override
    public void addPool(TeamPool pool, List<Team> teams) {
        layout.addComponent(createPoolComponent(pool,teams), layout.getComponentCount()-1);
    }

    @Override
    public void removePool(TeamPool pool) {
        int count = layout.getComponentCount();
        for(int i=0;i<count;i++){
            Component c = layout.getComponent(i);
            if(c instanceof PoolComponent){
                PoolComponent poolComponent = (PoolComponent) c;
                if(pool.getId().equals(poolComponent.getPool().getId())){
                    layout.removeComponent(poolComponent);
                    count--;
                }
            }
        }
    }

    private void buildLayout() {
        layout = new HorizontalLayout();
//        layout.setMargin(true);
//        layout.setSpacing(true);

        mainLayout = new VerticalLayout();
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);

        Label caption = new Label(FontAwesome.HOME.getHtml() + " " + "My Pools", ContentMode.HTML);
        caption.addStyleName(ValoTheme.LABEL_H1);

        mainLayout.addComponent(caption);
        mainLayout.addComponent(layout);
        addComponent(mainLayout);
    }


    private PoolComponent createPoolComponent(TeamPool pool, List<Team> teams){
        PoolComponent poolComponent = new PoolComponent(pool, teams);
        poolComponent.addTeamClickedListener(l -> this.eventBus.post(new PoolComponent.TeamClickedEvent(l.getComponent(), l.getTeam(), pool)));
        poolComponent.addAddTeamListener(l -> this.eventBus.post(new PoolComponent.AddTeamEvent(l.getComponent(), l.getTeam(), pool)));
        poolComponent.addDeleteTeamPoolListener(l -> this.eventBus.post(new PoolComponent.DeleteTeamPoolEvent(l.getComponent(), pool)));
        return  poolComponent;
    }


}
