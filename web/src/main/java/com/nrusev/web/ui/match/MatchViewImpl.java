package com.nrusev.web.ui.match;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;

/**
 * Created by Nikolay Rusev on 27.10.2016 г..
 */
@SpringComponent
@ViewScope
public class MatchViewImpl  extends CssLayout implements MatchView {

    private VerticalLayout layout;

    @Override
    public void initLayout() {
        buildLayout();
    }

    @Override
    public void loadInitialData() {

    }

    private void buildLayout() {
        layout = new VerticalLayout();
        layout.setWidth("100%");
        layout.setMargin(true);
        layout.setSpacing(true);
        addComponent(layout);

        Label caption = new Label(FontAwesome.HOME.getHtml() + " Welcome to match view", ContentMode.HTML);
        caption.addStyleName(ValoTheme.LABEL_H1);
        layout.addComponent(caption);

        Label subCaption = new Label("Match detailed view", ContentMode.HTML);
        subCaption.addStyleName(ValoTheme.LABEL_LIGHT);
        layout.addComponent(subCaption);

    }
}
