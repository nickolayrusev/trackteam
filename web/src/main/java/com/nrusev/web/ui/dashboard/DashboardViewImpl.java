package com.nrusev.web.ui.dashboard;

import com.nrusev.domain.Game;
import com.nrusev.domain.TeamPool;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * Created by Nikolay Rusev on 26.10.2016 г..
 */
@SpringComponent
@ViewScope
public class DashboardViewImpl extends CssLayout implements DashboardView {

    private VerticalLayout layout;
    private Table table;

    @Override
    public void initData(Map<Game, Collection<TeamPool>> userGames) {
        initLayout();

        table = new Table("My Games");
        table.setWidth(800, Unit.PIXELS);
        table.addContainerProperty("game", String.class, null);
        table.addContainerProperty("pools", HorizontalLayout.class, null);
        userGames.forEach((k, v) -> {
            HorizontalLayout form = new HorizontalLayout();
//            form.setHeight(60, Unit.PIXELS);
            v.stream().map(p -> {
                Button button = new Button(p.getName());
                button.addStyleName(ValoTheme.BUTTON_LINK);
                return button;
            }).forEach(form::addComponent);

            table.addItem(new Object[]{k.getHomeTeam().getTitle() + " vs " + k.getVisitorTeam().getTitle(), form }, k.getId());
        });
        table.setPageLength(userGames.size());
        layout.addComponent(table);
        addComponent(layout);
    }

    private void initLayout(){
        layout = new VerticalLayout();
        layout.setWidth("100%");
        layout.setMargin(true);
        layout.setSpacing(true);
    }
}
