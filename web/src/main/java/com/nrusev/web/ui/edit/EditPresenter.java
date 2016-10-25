package com.nrusev.web.ui.edit;

import com.nrusev.web.ui.mvp.MvpPresenter;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;


@SuppressWarnings("serial")
@SpringView(name = "edit")
public class EditPresenter extends MvpPresenter<EditView> {

	@Autowired
	public EditPresenter(EditView view) {
		super(view);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		
	}

}
