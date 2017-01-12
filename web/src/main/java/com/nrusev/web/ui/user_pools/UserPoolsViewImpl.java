package com.nrusev.web.ui.user_pools;

import com.nrusev.domain.Team;
import com.nrusev.domain.TeamPool;
import com.nrusev.domain.User;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import eu.maxschuster.vaadin.autocompletetextfield.AutocompleteSuggestionProvider;
import eu.maxschuster.vaadin.autocompletetextfield.AutocompleteTextField;
import eu.maxschuster.vaadin.autocompletetextfield.provider.CollectionSuggestionProvider;
import eu.maxschuster.vaadin.autocompletetextfield.provider.MatchMode;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * Created by Nikolay Rusev on 26.10.2016 г..
 */
@SpringComponent
@ViewScope
public class UserPoolsViewImpl extends CssLayout implements UserPoolsView {

    private VerticalLayout layout;
    private List<Team> teams;

    AutocompleteTextField field = new AutocompleteTextField();

    Collection<String> theJavas = Arrays.asList("Java",
            "JavaScript",
            "Join Java",
            "JavaFX Script");

    AutocompleteSuggestionProvider suggestionProvider = new
            CollectionSuggestionProvider(theJavas, MatchMode.CONTAINS, true, Locale.US);

    @Override
    public void initLayout() {
        buildLayout();
        field.setSuggestionProvider(suggestionProvider);
        layout.addComponent(field);
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

    @Override
    public void setUpSearchQuery(List<Team> teams) {
        this.teams = teams;
    }

    private void buildLayout() {
        layout = new VerticalLayout();
        layout.setWidth("100%");
        layout.setMargin(true);
        layout.setSpacing(true);
        addComponent(layout);
    }


}
