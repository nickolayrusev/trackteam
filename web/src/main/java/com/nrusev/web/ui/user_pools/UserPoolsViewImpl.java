package com.nrusev.web.ui.user_pools;

import com.nrusev.domain.Team;
import com.nrusev.domain.TeamPool;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import eu.maxschuster.vaadin.autocompletetextfield.AutocompleteSuggestionProvider;
import eu.maxschuster.vaadin.autocompletetextfield.AutocompleteTextField;
import eu.maxschuster.vaadin.autocompletetextfield.provider.CollectionSuggestionProvider;
import eu.maxschuster.vaadin.autocompletetextfield.provider.MatchMode;

import java.util.List;
import java.util.Locale;

import static java.util.stream.Collectors.toList;

/**
 * Created by Nikolay Rusev on 26.10.2016 г..
 */
@SpringComponent
@ViewScope
public class UserPoolsViewImpl extends CssLayout implements UserPoolsView {

    private VerticalLayout layout;

    private AutocompleteTextField field = new AutocompleteTextField();

    private List<Team> teams;

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

    @Override
    public void loadDataForAutoComplete(List<Team> teams) {
        this.teams = teams;
        AutocompleteSuggestionProvider suggestionProvider = new
                CollectionSuggestionProvider(this.teams.stream().map(Team::getTitle).collect(toList()), MatchMode.CONTAINS, true, Locale.US);
        field.setSuggestionProvider(suggestionProvider);
        field.addValueChangeListener(l -> {
            System.out.println("value changed : " + l.getProperty());
        });

        addComponent(field);
    }

    private void buildLayout() {
        layout = new VerticalLayout();
        layout.setWidth("100%");
        layout.setMargin(true);
        layout.setSpacing(true);
        addComponent(layout);
    }


}
