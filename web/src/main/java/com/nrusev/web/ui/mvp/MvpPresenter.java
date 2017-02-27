package com.nrusev.web.ui.mvp;

import com.google.common.eventbus.EventBus;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.UI;

import javax.annotation.PostConstruct;
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

	@PostConstruct
	private void init(){
	}

	@PreDestroy
	private void preDestroy(){
		this.eventBus.unregister(this);
		System.out.println("pre destroy on abstract class...");
	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
		if(viewChangeEvent.getNewView().equals(viewChangeEvent.getOldView())){
			this.getEventBus().unregister(viewChangeEvent.getOldView());
		}
	}
}
