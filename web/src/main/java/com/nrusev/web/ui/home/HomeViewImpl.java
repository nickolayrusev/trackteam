package com.nrusev.web.ui.home;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.nrusev.domain.Country;
import com.nrusev.domain.Game;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.themes.ValoTheme;

import static com.nrusev.support.TextUtils.getGameCaption;

@SuppressWarnings("serial")
@SpringComponent
@ViewScope
public class HomeViewImpl extends CssLayout implements HomeView {
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	private VerticalLayout layout;

    private List<Button>  matchButtons;

	@PostConstruct
	public void postContruct() {
		LOG.info("Creating new MainView");
        matchButtons = new ArrayList<>();
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
	public void displayTodaysGames(List<Game> todaysGames) {
		todaysGames.forEach(game->{
			final Button btnGame = new Button();
			btnGame.addStyleName(ValoTheme.BUTTON_LINK);
			btnGame.setData(game);
			btnGame.setCaption(getGameCaption(game, UI.getCurrent().getLocale()));
			layout.addComponent(btnGame);
			matchButtons.add(btnGame);
		});
	}

	@Override
	public List<Button> getMatchButtons() {
		return this.matchButtons;
	}

}
