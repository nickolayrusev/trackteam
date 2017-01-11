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
import com.zybnet.autocomplete.server.AutocompleteField;
import com.zybnet.autocomplete.server.AutocompleteQueryListener;
import com.zybnet.autocomplete.server.AutocompleteSuggestionPickedListener;

import java.util.List;

/**
 * Created by Nikolay Rusev on 26.10.2016 г..
 */
@SpringComponent
@ViewScope
public class UserPoolsViewImpl extends CssLayout implements UserPoolsView{

    private VerticalLayout layout;
    private final AutocompleteField<Team> search = new AutocompleteField<Team>();

    @Override
    public void initLayout() {
        buildLayout();
        setUpAutocomplete(search);
        layout.addComponent(search);
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

    private void setUpAutocomplete(AutocompleteField<Team> search) {
        search.setQueryListener((field, query) -> handleSearchQuery(field, query));
        search.setSuggestionPickedListener(page -> handleSuggestionSelection(page));
    }

    private void handleSuggestionSelection(Team page) {
        System.out.println("team chosen " + page);

    }

    private void handleSearchQuery(AutocompleteField<Team> field, String query) {

//        field.addSuggestion(,"Arsenal");
    }

}
