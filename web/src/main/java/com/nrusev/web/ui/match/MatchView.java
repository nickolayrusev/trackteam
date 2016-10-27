package com.nrusev.web.ui.match;

import com.nrusev.web.ui.mvp.MvpView;

/**
 * Created by Nikolay Rusev on 27.10.2016 г..
 */
public interface MatchView extends MvpView {
    void initLayout();
    void loadInitialData();
}
