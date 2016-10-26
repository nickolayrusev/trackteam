package com.nrusev.web.ui.edit;

import com.google.common.eventbus.EventBus;
import com.nrusev.web.ui.mvp.MvpPresenter;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;


@SuppressWarnings("serial")
@SpringView(name = "edit")
public class EditPresenter extends MvpPresenter<EditView> {

	@Autowired
	public EditPresenter(EditView view, EventBus eventBus) {
		super(view, eventBus);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		
	}

}
