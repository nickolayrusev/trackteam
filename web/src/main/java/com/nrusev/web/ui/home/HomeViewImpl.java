package com.nrusev.web.ui.home;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.google.common.eventbus.EventBus;
import com.nrusev.domain.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("serial")
@SpringComponent
@ViewScope
public class HomeViewImpl extends CssLayout implements HomeView {
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	private VerticalLayout layout;

	private List<Button> countries;

	@PostConstruct
	public void postContruct() {
		LOG.info("Creating new MainView");
		setSizeFull();
	}

	@PreDestroy
	public void preDestroy() {
		LOG.info("Destroying MainView");
	}

	@Override
	public void initLayout() {
		buildLayout();
	}
	
	private void buildLayout() {
		countries = new ArrayList<>();
		layout = new VerticalLayout();
		layout.setWidth("100%");
		layout.setMargin(true);
		layout.setSpacing(true);
		addComponent(layout);
		
		Label caption = new Label(FontAwesome.HOME.getHtml() + " Welcome to my awesome App", ContentMode.HTML);
		caption.addStyleName(ValoTheme.LABEL_H1);
		layout.addComponent(caption);
		
		Label subCaption = new Label("These are my friends: ", ContentMode.HTML);
		subCaption.addStyleName(ValoTheme.LABEL_LIGHT);
		layout.addComponent(subCaption);
		
	}


	@Override
	public void displayCountries(List<Country> countries) {
		countries.forEach(country -> {
			final Button btnFriend = new Button(country.getName(), event -> {
//				executeEditEventListeners(event.getButton().getCaption());
//				executeEditEventListeners((String) event.getButton().getData());
			});
			btnFriend.setData(country.getName().toUpperCase());
			this.countries.add(btnFriend);
			layout.addComponent(btnFriend);

		});
	}

	@Override
	public List<Button> getCountriesButtons() {
		return this.countries;
	}

}
