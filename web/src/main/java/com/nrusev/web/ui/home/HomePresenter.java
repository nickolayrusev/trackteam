package com.nrusev.web.ui.home;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.google.common.eventbus.EventBus;
import com.nrusev.service.CountryService;
import com.nrusev.web.ui.mvp.MvpPresenter;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;


@SuppressWarnings("serial")
@SpringView(name="home")
public class HomePresenter extends MvpPresenter<HomeView> {

	private final CountryService countryService;

	@Autowired
	public HomePresenter(HomeView view, EventBus eventBus, CountryService countryService) {
		super(view, eventBus);
		this.countryService = countryService;
	}
	
	@PostConstruct
	public void postContruct() {
		initLayout();
		loadCountries();
        attachEventHandlers();
	}

	@PreDestroy
	public void preDestroy() {
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

	private void initLayout(){
		getView().initLayout();
	}

	private void loadCountries() {
		getView().displayCountries(countryService.findAll());
	}

	private void attachEventHandlers(){
		getView().getCountriesButtons().forEach(b->b.addClickListener(l->{ doSomething(l.getButton().getCaption()); }));
	}

	public void doSomething(String s){
		getEventBus().post(s);
		if(s.equalsIgnoreCase("austria")){
            disableAll();
		}else {
			enableAll();
		}
	}

	private void disableAll(){
		getView().getCountriesButtons().forEach(b->b.setEnabled(false));
	}

	private void enableAll(){
		getView().getCountriesButtons().forEach(b->b.setEnabled(true));
	}


}
