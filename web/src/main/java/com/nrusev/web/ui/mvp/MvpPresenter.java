package com.nrusev.web.ui.mvp;

import com.google.common.eventbus.EventBus;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;

import javax.annotation.PreDestroy;

@SuppressWarnings("serial")
public abstract class MvpPresenter<T extends MvpView> implements View {
	
	private final T view;

	private final EventBus eventBus;

	public MvpPresenter(final T view, final EventBus eventBus) {
		this.view = view;
		this.eventBus = eventBus;
		this.eventBus.register(this);
	}
	
	public T getView() {
		return view;
	}

	public EventBus getEventBus(){
		return eventBus;
	}

	@PreDestroy
	private void preDestroy(){
		this.eventBus.unregister(this);
		System.out.println("pre destroy on abstract class...");
	}

}
