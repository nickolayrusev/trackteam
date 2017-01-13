package com.nrusev.web.ui.components;


import com.nrusev.domain.TeamPool;
import com.vaadin.ui.*;

import java.util.List;

/**
 * Created by Nikolay Rusev on 13.1.2017 г..
 */
public class PoolComponent extends CustomComponent {
    private TeamPool pool;
    private Panel panel;
    private VerticalLayout panelContent;

    public PoolComponent(TeamPool pool){
        this.pool = pool;
        initLayout();
        loadInitialPools();
    }

    private void initLayout() {
        panel = new Panel("My Custom Component");
        panelContent = new VerticalLayout();
        panelContent.setMargin(true); // Very useful
        panel.setContent(panelContent);

        // Compose from multiple components
//        Label label = new Label("");
//        label.setSizeUndefined(); // Shrink
//        panelContent.addComponent(label);
//        panelContent.addComponent(new Button("Ok"));

        // Set the size as undefined at all levels
        panelContent.setSizeUndefined();
        panel.setSizeUndefined();
        setSizeUndefined();

        // The composition root MUST be set
        setCompositionRoot(panel);
    }

    private void loadInitialPools(){
        panel.setCaption(pool.getName() +  " " + pool.getDescription());
        pool.getTeams().forEach(p->{
            panelContent.addComponent(new Button(p.getTitle()));
        });
    }

    private void add(TeamPool pool){

    }


}
