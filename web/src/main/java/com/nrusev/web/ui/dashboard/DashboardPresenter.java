package com.nrusev.web.ui.dashboard;

import com.google.common.eventbus.EventBus;
import com.nrusev.web.ui.mvp.MvpPresenter;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

/**
 * Created by Nikolay Rusev on 26.10.2016 г..
 */
public class DashboardPresenter extends MvpPresenter<DashboardView> {

    public DashboardPresenter(DashboardView view, EventBus eventBus) {
        super(view, eventBus);
    }

    @Override
    public void enter(ViewChangeEvent viewChangeEvent) {

    }
}
