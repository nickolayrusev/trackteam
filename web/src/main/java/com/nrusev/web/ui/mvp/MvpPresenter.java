package com.nrusev.web.ui.mvp;

import com.google.common.eventbus.EventBus;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;

@SuppressWarnings("serial")
public abstract class MvpPresenter<T extends MvpView> implements View {
	
	final T view;

	final EventBus eventBus;

	public MvpPresenter(final T view, final EventBus eventBus) {
		this.view = view;
		this.eventBus = eventBus;
	}
	
	public T getView() {
		return view;
	}

	public EventBus getEventBus(){
		return eventBus;
	}

}
