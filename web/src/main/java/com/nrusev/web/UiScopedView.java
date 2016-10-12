package com.nrusev.web;

import com.nrusev.domain.League;
import com.nrusev.service.LeagueService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by nikolayrusev on 2/29/16.
 */
@SpringView
public class UiScopedView extends VerticalLayout implements View {
	private final LeagueService leagueService;
	
	@Autowired
	public UiScopedView(LeagueService leagueService) {
		this.leagueService = leagueService;
	}
	
    @PostConstruct
    void init() {
        setMargin(true);
        setSpacing(true);
        List<League> findAllClubLeagues = leagueService.findAllClubLeagues();
        
        findAllClubLeagues.forEach(l->{addComponent(new Button(l.getTitle()));});

    }

    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
