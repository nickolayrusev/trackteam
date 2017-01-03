package com.nrusev.web.ui.user_pools;

import com.nrusev.domain.TeamPool;
import com.nrusev.domain.User;
import com.nrusev.web.ui.mvp.MvpView;

import java.util.List;

/**
 * Created by Nikolay Rusev on 26.10.2016 г..
 */
public interface UserPoolsView extends MvpView {
    void initLayout();
    void loadData(List<TeamPool> user);
}
