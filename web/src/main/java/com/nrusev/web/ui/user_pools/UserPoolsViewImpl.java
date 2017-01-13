package com.nrusev.web.ui.user_pools;

import com.google.common.eventbus.EventBus;
import com.nrusev.domain.Team;
import com.nrusev.domain.TeamPool;
import com.nrusev.web.ui.components.PoolComponent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Nikolay Rusev on 26.10.2016 г..
 */
@SpringComponent
@ViewScope
public class UserPoolsViewImpl extends CssLayout implements UserPoolsView {

    private VerticalLayout layout;

    private List<Team> teams;

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
    public void loadData(List<TeamPool> pools) {
        pools.forEach(teamPool -> {
            layout.addComponent(new PoolComponent(teamPool));
        });
    }

    @Override
    public void loadDataForAutoComplete(List<Team> teams) {
        this.teams = teams;

        BeanItemContainer<Team> container =
                new BeanItemContainer<Team>(
                        Team.class);
        teams.forEach(container::addItem);

        // Create a selection component bound
        // to the container
        ComboBox select = new ComboBox("Planets",
                container);

        // Set the caption mode to read the
        // caption directly from the 'name'
        // property of the bean
        select.setItemCaptionMode(
                AbstractSelect.ItemCaptionMode.PROPERTY);
        select.setItemCaptionPropertyId("title");
        select.setFilteringMode(FilteringMode.CONTAINS);
        select.addValueChangeListener(l->{ eventBus.post((Team)l.getProperty().getValue());});
        layout.addComponent(select);

    }

    private void buildLayout() {
        layout = new VerticalLayout();
        layout.setWidth("100%");
        layout.setMargin(true);
        layout.setSpacing(true);
        addComponent(layout);
    }


}
