package com.nrusev.web.ui.dashboard;

import com.nrusev.domain.Game;
import com.nrusev.domain.TeamPool;
import com.nrusev.web.ui.mvp.MvpView;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Nikolay Rusev on 26.10.2016 г..
 */
public interface DashboardView extends MvpView {
    void initData(Map<Game, Collection<TeamPool>> userGames);
}
