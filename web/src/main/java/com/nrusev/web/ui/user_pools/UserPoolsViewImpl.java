package com.nrusev.web.ui.user_pools;

import com.nrusev.domain.TeamPool;
import com.nrusev.domain.User;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

/**
 * Created by Nikolay Rusev on 26.10.2016 г..
 */
@SpringComponent
@ViewScope
public class UserPoolsViewImpl extends CssLayout implements UserPoolsView{

    private VerticalLayout layout;

    @Override
    public void initLayout() {
        buildLayout();
    }


    @Override
    public void loadData(List<TeamPool> pools) {
        pools.forEach(teamPool -> {
            Label label = new Label(teamPool.getName(), ContentMode.HTML);
            addComponent(label);
            teamPool.getTeams().forEach(team -> {
                addComponent(new Label(team.getTitle()));
            });
        });
    }

    private void buildLayout() {
        layout = new VerticalLayout();
        layout.setWidth("100%");
        layout.setMargin(true);
        layout.setSpacing(true);
        addComponent(layout);
    }
}
