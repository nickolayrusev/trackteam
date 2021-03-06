package com.nrusev.web.ui.user_pools;

import com.google.common.eventbus.EventBus;
import com.nrusev.domain.Team;
import com.nrusev.domain.TeamPool;
import com.nrusev.web.ui.components.TeamSelectComponent;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

/**
 * Created by Nikolay Rusev on 10.3.2017 г..
 */
@SpringComponent
@ViewScope
public class PoolViewImpl extends CssLayout implements PoolView {
    private final EventBus eventBus;

    private TextField tf1;
    private TextField tf2;
    private ComboBox select;
    private Table table;
    private Button saveButton;
    private Button cancelButton;
    private VerticalLayout verticalLayout;
    private FormLayout formLayout;


    @Autowired
    public PoolViewImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @PostConstruct
    private void init() {
        initLayout();
    }

    @Override
    public void init(TeamPool pool, List<Team> allTeams) {

        tf1 = new TextField("Name");
        tf1.setIcon(FontAwesome.USER);
        tf1.setRequired(true);

        formLayout.addComponent(tf1);

        tf2 = new TextField("Description");
        tf2.setIcon(FontAwesome.BOOK);
        tf2.setRequired(true);

        formLayout.addComponent(tf2);

        // Now create the binder and bind the fields
        BeanFieldGroup<TeamPool> binder = new BeanFieldGroup<TeamPool>(TeamPool.class);
        binder.setItemDataSource(pool);
        binder.bind(tf1, "Name");
        binder.bind(tf2, "Description");

        select = new TeamSelectComponent(FontAwesome.MALE, allTeams);

        select.addValueChangeListener(l -> {
            if (Objects.nonNull(l.getProperty().getValue())) {
                this.eventBus.post(new PoolView.AddTeamEvent(this, (Team) l.getProperty().getValue()));
            }
            select.setValue(null);
        });

        formLayout.addComponent(select);

        table = new Table("Teams in pool");
        table.setIcon(FontAwesome.BINOCULARS);

        // Define two columns for the built-in container
        table.addContainerProperty("Name", String.class, null);
        table.addContainerProperty("Country", String.class, null);
        table.addContainerProperty("Delete", Button.class, null);

        pool.getTeams().forEach(t -> table.addItem(new Object[]{t.getTitle(), "", new Button("Delete", l -> this.eventBus.post(new DeleteTeamEvent(this, t)))}, t.getId()));

        table.setPageLength(pool.getTeams().size());

        formLayout.addComponent(table);

        saveButton = new Button("Save", l -> {
            try {
                binder.commit();
            } catch (FieldGroup.CommitException e) {
                System.err.println("exc" + e);
                binder.discard();
            }
            this.eventBus.post(new PoolView.SaveTeamPoolEvent(this, pool));
        });
        saveButton.addStyleName(ValoTheme.BUTTON_PRIMARY);

        cancelButton = new Button("Cancel", l -> {
            binder.discard();
            this.eventBus.post(new PoolView.CancelTeamPoolEvent(this));
        });

        formLayout.addComponent(saveButton);
        formLayout.addComponent(cancelButton);
    }

    private void initLayout() {
        verticalLayout = new VerticalLayout();
        verticalLayout.setMargin(true);

        Label caption = new Label("Edit team pool", ContentMode.HTML);
        caption.addStyleName(ValoTheme.LABEL_H1);
        verticalLayout.addComponent(caption);

        formLayout = new FormLayout();
        verticalLayout.addComponent(formLayout);
        addComponent(verticalLayout);
    }

    public void addTeam(Team team) {
        this.table.addItem(new Object[]{team.getTitle(), "", new Button("Delete", l -> this.eventBus.post(new PoolView.DeleteTeamEvent(this, team)))}, team.getId());
        this.table.setPageLength(table.getPageLength() + 1);
    }

    public void removeTeam(Team team) {
        this.table.removeItem(team.getId());
        this.table.setPageLength(table.getPageLength() - 1);
    }
}
