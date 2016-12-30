package com.nrusev.web.ui.components;


import com.google.common.eventbus.EventBus;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.*;
import com.vaadin.util.ReflectTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by Nikolay Rusev on 29.12.2016 г..
 */
public class MyComponent extends CustomComponent {

    private Button ok, cancel;

    private static int i = 0;

    public interface OkListener extends Serializable {
        Method OK_METHOD = ReflectTools.findMethod(OkListener.class, "ok", new Class[]{OkEvent.class});

        void ok(OkEvent var1);
    }

    public static class OkEvent extends Event {

        public OkEvent(Component source) {
            super(source);
        }
    }



    public MyComponent(String message) {
        // A layout structure used for composition
        Panel panel = new Panel("My Custom Component");
        VerticalLayout panelContent = new VerticalLayout();
        panelContent.setMargin(true); // Very useful
        panel.setContent(panelContent);

        // Compose from multiple components
        Label label = new Label(message);
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

    public void addAllIsOkListener(OkListener okListener){
        this.addListener(OkEvent.class, okListener, OkListener.OK_METHOD);
    }

    /* Forward various methods to the proper subcomponent. */
    public void addOkButtonClickListener(Button.ClickListener listener) {
        ok.addClickListener(listener);
    }

    public void addCancelButtonClickListener(Button.ClickListener listener) {
        ok.addClickListener(listener);
    }


    public void cancel(Button.ClickEvent event) {
        event.getButton().setCaption ("Cancel");
        System.out.println("cancel clicked..." );
    }

    public void ok(Button.ClickEvent event) {
        event.getButton().setCaption ("OK!");
        System.out.println("ok clicked...");
        if( i % 2 == 0)
            this.fireEvent(new OkEvent(this));
        System.out.println(i++);
    }

}
