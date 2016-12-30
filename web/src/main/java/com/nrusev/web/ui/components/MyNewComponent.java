package com.nrusev.web.ui.components;

import com.google.common.eventbus.EventBus;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * Created by Nikolay Rusev on 30.12.2016 г..
 */

@Scope("prototype")
@SpringComponent
public class MyNewComponent extends CustomComponent {
    private Button ok, cancel;
    private final EventBus eventBus;

    @Autowired
    public MyNewComponent(EventBus eventBus){
        this.eventBus = eventBus;
        // A layout structure used for composition
        Panel panel = new Panel("My Custom Component");
        VerticalLayout panelContent = new VerticalLayout();
        panelContent.setMargin(true); // Very useful
        panel.setContent(panelContent);

        // Compose from multiple components
        Label label = new Label("message");
        label.setSizeUndefined(); // Shrink
        panelContent.addComponent(label);

        ok = new Button("Ok", this::ok );
        cancel = new Button("Cancel",this::cancel);

        panelContent.addComponent(ok);
        panelContent.addComponent(cancel);

        // Set the size as undefined at all levels
        panelContent.setSizeUndefined();
        panel.setSizeUndefined();
        setSizeUndefined();

        // The composition root MUST be set
        setCompositionRoot(panel);
    }

    public void cancel(Button.ClickEvent event) {
        event.getButton().setCaption ("Cancel");
        System.out.println("cancel clicked..." );
    }

    public void ok(Button.ClickEvent event) {
        event.getButton().setCaption ("OK!");
        System.out.println("ok clicked...");
        eventBus.post("publishing event");
    }
}
