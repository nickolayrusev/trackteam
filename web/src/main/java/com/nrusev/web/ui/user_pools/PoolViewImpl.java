package com.nrusev.web.ui.user_pools;

import com.google.common.eventbus.EventBus;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.FormLayout;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Nikolay Rusev on 10.3.2017 г..
 */
@SpringComponent
@ViewScope
public class PoolViewImpl extends FormLayout implements PoolView{
    private final EventBus eventBus;

    @Autowired
    public PoolViewImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }


    @Override
    public void init() {

    }
}
