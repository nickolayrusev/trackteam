package com.nrusev.web.ui.dashboard;

import com.nrusev.domain.Game;
import com.nrusev.domain.TeamPool;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Nikolay Rusev on 26.10.2016 г..
 */
@SpringComponent
@ViewScope
public class DashboardViewImpl extends CssLayout implements DashboardView {

    private VerticalLayout layout;

    @Override
    public void initData(Map<Game, Collection<TeamPool>> userGames) {
        layout = new VerticalLayout();
        userGames.forEach((k,v)->{
            layout.addComponent(new Label(k.getHomeTeam().getTitle() + " " + k.getVisitorTeam().getTitle()));
            v.forEach(p->{
                layout.addComponent(new Label(p.getName()));
            });
        });
        addComponent(layout);
    }
}
