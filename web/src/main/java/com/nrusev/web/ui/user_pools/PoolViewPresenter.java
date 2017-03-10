package com.nrusev.web.ui.user_pools;

import com.google.common.eventbus.EventBus;
import com.nrusev.domain.TeamPool;
import com.nrusev.service.TeamPoolService;
import com.nrusev.web.ui.mvp.MvpPresenter;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;

/**
 * Created by Nikolay Rusev on 10.3.2017 г..
 */
@SpringView(name = "pool")
public class PoolViewPresenter extends MvpPresenter<PoolViewImpl> {
    private TeamPool pool;
    private final TeamPoolService teamPoolService;

    public PoolViewPresenter(PoolViewImpl view, EventBus eventBus, TeamPoolService teamPoolService) {
        super(view, eventBus);
        this.teamPoolService = teamPoolService;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        pool = teamPoolService.findById(Long.valueOf(viewChangeEvent.getParameters()));
        System.out.println("entering pool view " + pool);
    }
}
