package com.nrusev.web.ui.home;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.google.common.eventbus.EventBus;
import com.nrusev.domain.Game;
import com.nrusev.domain.League;
import com.nrusev.web.ui.components.MyComponent;
import com.nrusev.web.ui.components.PoolComponent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import static com.nrusev.support.TextUtils.getGameCaption;

@SuppressWarnings("serial")
@SpringComponent
@ViewScope
public class HomeViewImpl extends CssLayout implements HomeView {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private VerticalLayout layout;

	private final EventBus eventBus;

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
		layout.setSpacing(true);
		addComponent(layout);

		Label caption = new Label(FontAwesome.HOME.getHtml() + " Welcome to my awesome App", ContentMode.HTML);
		caption.addStyleName(ValoTheme.LABEL_H1);
		layout.addComponent(caption);

//		Button b = new Button("caption");
//		b.addClickListener(l->{
//			this.eventBus.post("caption click listener");
//		});
//		layout.addComponent(b);

//		for(int i = 0; i<3;i++) {
//			MyComponent myComponent = new MyComponent("oh my component " + i);
//
//            myComponent.addAllIsOkListener(event ->{
//				System.out.println("really everything is ok...");
//			});
//			layout.addComponent(myComponent);
//		}
	}


	@Override
	public void displayTodaysGames(List<Game> todaysGames) {

		Map<League, List<Game>> collect = todaysGames.stream().collect(Collectors.groupingBy(g -> g.getRound().getEvent().getLeague()));
		collect.forEach((league, games) -> {
			Table table = new Table(league.getTitle());
			table.addContainerProperty("Time",String.class,null);
			table.addContainerProperty("Home",Button.class,null);
			table.addContainerProperty("Away",Button.class,null);
			table.addContainerProperty("Result",String.class,null);

			games.forEach(game -> {
				SimpleDateFormat format = new SimpleDateFormat("HH:mm");

				final Button btnHome= new Button(game.getHomeTeam().getTitle(),l-> this.eventBus.post(new TeamClickedEvent(game.getHomeTeam())));
				btnHome.addStyleName(ValoTheme.BUTTON_LINK);

				final Button btnAway = new Button(game.getVisitorTeam().getTitle(),l-> this.eventBus.post( new TeamClickedEvent(game.getVisitorTeam())));
				btnAway.addStyleName(ValoTheme.BUTTON_LINK);

				table.addItem(new Object[]{
								format.format(game.getPlayAt()),
								btnHome,
								btnAway,
								questionIfNull(game.getScore1() ) +" : " + questionIfNull(game.getScore2() )
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

	private String questionIfNull(Long score){
		if(Objects.isNull(score))
			return "?";
		return String.valueOf(score);
	}

}
