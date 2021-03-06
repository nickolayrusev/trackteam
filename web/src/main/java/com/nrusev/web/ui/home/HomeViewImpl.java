package com.nrusev.web.ui.home;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.google.common.eventbus.EventBus;
import com.nrusev.domain.Game;
import com.nrusev.domain.Round;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import static com.nrusev.support.TextUtils.score;

@SuppressWarnings("serial")
@SpringComponent
@ViewScope
public class HomeViewImpl extends CssLayout implements HomeView {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private VerticalLayout layout;

	private final EventBus eventBus;

	private Table table;

	@Autowired
	public HomeViewImpl(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	@PostConstruct
	public void postConstruct() {
		LOG.info("Creating new MainView");
//		setSizeFull();
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
//		layout.setSpacing(true);
		addComponent(layout);

		Label caption = new Label(FontAwesome.HOME.getHtml() + " Welcome to my awesome App", ContentMode.HTML);
		caption.addStyleName(ValoTheme.LABEL_H1);
		layout.addComponent(caption);

	}


	@Override
	public void displayTodaysGames(List<Game> todaysGames) {

		Map<Round, List<Game>> collect = todaysGames.stream().collect(Collectors.groupingBy(Game::getRound));
		collect.forEach((round, games) -> {

			table = new Table(round.getEvent().getLeague().getCountry().getName()
					+ " " + round.getEvent().getLeague().getTitle()
					+ " " + round.getEvent().getSeason().getTitle()
					+ " " + round.getTitle() + " " + LocalDate.now(ZoneOffset.UTC).toString());
			table.setWidth(600,Unit.PIXELS);
			table.addContainerProperty("Time",String.class,null);
			table.addContainerProperty("Home",Button.class,null);
			table.addContainerProperty("Result",String.class,null);
			table.addContainerProperty("Away",Button.class,null);

			games.forEach(game -> {
				SimpleDateFormat format = new SimpleDateFormat("HH:mm");

				final Button btnHome = new Button(game.getHomeTeam().getTitle(),l-> this.eventBus.post(new TeamClickedEvent(game.getHomeTeam())));
				btnHome.addStyleName(ValoTheme.BUTTON_LINK);

				final Button btnAway = new Button(game.getVisitorTeam().getTitle(),l-> this.eventBus.post( new TeamClickedEvent(game.getVisitorTeam())));
				btnAway.addStyleName(ValoTheme.BUTTON_LINK);

				table.addItem(new Object[]{
								format.format(game.getPlayAt()),
								btnHome,
								score(game),
								btnAway
						},
						game.getId());
				table.setPageLength(games.size());
			});
			table.addItemClickListener(s-> games.stream().filter(game-> game.getId().equals(s.getItemId())).findFirst().ifPresent(r->{
				this.eventBus.post(new GameClickedEvent(r));
			}));
			table.setReadOnly(true);
			table.setColumnCollapsingAllowed(false);
			table.setColumnReorderingAllowed(false);
			layout.addComponent(table);
		});
	}


}
