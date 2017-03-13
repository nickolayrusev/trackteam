package com.nrusev.web.ui.user_pools;

import com.nrusev.domain.Team;
import com.nrusev.domain.TeamPool;
import com.nrusev.web.ui.mvp.MvpView;

import java.util.List;

/**
 * Created by Nikolay Rusev on 10.3.2017 г..
 */
public interface PoolView extends MvpView {
    void init(TeamPool pool, List<Team> allTeams);
}
