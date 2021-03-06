package com.nrusev.web.ui;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.nrusev.web.ui.mvp.MvpPresenter;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.*;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.navigator.SpringNavigator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Locale;
import java.util.Objects;


@SpringUI(path = "")
@Theme("my-theme")
@SuppressWarnings("serial")
//@Widgetset("AppWidgetset")
public class MainUI extends UI {

	private ValoMenuLayout root = new ValoMenuLayout();

	private CssLayout menu = new CssLayout();

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private SpringNavigator navigator;

	private CssLayout menuItemsLayout = new CssLayout();

	@Autowired
	private EventBus eventBus;
	
	/*
	 * Handle View changing
	 */
	public static class MvpViewDisplay implements ViewDisplay {

		private final CssLayout root;
		
		public MvpViewDisplay(CssLayout root) {
			this.root = root;
		}
		
		@SuppressWarnings({"rawtypes" })
		@Override
		public void showView(View view) {
			root.removeAllComponents();
			if (view instanceof MvpPresenter) {
				Component component = (Component) ((MvpPresenter) view).getView();
				root.setLocale(Locale.ENGLISH);
				root.addComponent(component);
			}
		}
		
	}

	@Override
	protected void init(VaadinRequest request) {
		Responsive.makeResponsive(this);
		this.getPage().setTitle("Valo Theme Test");
		this.setContent(root);
		root.setWidth("100%");

        Locale locale = Locale.ENGLISH;
        this.setLocale( locale ); // Call to affect this current UI. Workaround for bug: http://dev.vaadin.com/ticket/12350
        this.getSession().setLocale( locale ); // Affects only future UI instances, not current one because of bug. See workaround in line above.

		MvpViewDisplay mvpViewDisplay = new MvpViewDisplay(root.getContentContainer());
		navigator.init(this,mvpViewDisplay);
		navigator.addViewChangeListener(new ViewChangeListener() {
			@Override
			public boolean beforeViewChange(ViewChangeEvent viewChangeEvent) {
				LOG.debug("before view change " + viewChangeEvent);
				if(Objects.nonNull(viewChangeEvent.getOldView()))
					eventBus.unregister(viewChangeEvent.getOldView());

				if(Objects.nonNull(viewChangeEvent.getNewView()))
					eventBus.register(viewChangeEvent.getNewView());
				return true;
			}

			@Override
			public void afterViewChange(ViewChangeEvent viewChangeEvent) {
				LOG.debug("after view change " + viewChangeEvent);
			}
		});

		root.addMenu(menu);
		menuItemsLayout.setPrimaryStyleName("valo-menuitems");
		buildSidebarMenu();

		eventBus.register(this);
//		boolean loggedIn = UI.getCurrent().getSession().getAttribute("IS_LOGGED_IN") != null ? true : false;
//		if (!loggedIn) {
//			getPage().setLocation("/login");
//		}
		navigator.navigateTo("home");
	}

	private void buildSidebarMenu() {

		HorizontalLayout top = new HorizontalLayout();
		top.setWidth("100%");
		top.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
		top.addStyleName(ValoTheme.MENU_TITLE);
		menu.addComponent(top);

		/*
		 * Responsive layout - show hide menu
		 */
		Button showMenu = new Button("Menu", (ClickListener) event -> {
            if (menu.getStyleName().contains("valo-menu-visible")) {
                menu.removeStyleName("valo-menu-visible");
            } else {
                menu.addStyleName("valo-menu-visible");
            }
        });

		showMenu.addStyleName(ValoTheme.BUTTON_PRIMARY);
		showMenu.addStyleName(ValoTheme.BUTTON_SMALL);
		showMenu.addStyleName("valo-menu-toggle");
		showMenu.setIcon(FontAwesome.LIST);
		menu.addComponent(showMenu);

		/*
		 * User informations
		 */
		Label title = new Label("<h3>Codecentric <strong>Vaadin Demo</strong></h3>", ContentMode.HTML);
		title.setSizeUndefined();
		top.addComponent(title);
		top.setExpandRatio(title, 1);

		MenuBar settings = new MenuBar();
		settings.addStyleName("user-menu");

		MenuItem settingsItem = settings.addItem("Nikolay Rusev", new ExternalResource("/user-avatar.jpg"), null);
		settingsItem.addItem("Edit Profile", null);
		settingsItem.addItem("Preferences", null);
		settingsItem.addSeparator();
		settingsItem.addItem("Sign Out", null);
		menu.addComponent(settings);

		menuItemsLayout.setPrimaryStyleName("valo-menuitems");
		menu.addComponent(menuItemsLayout);

		/*
		 * Menu Items
		 */
		Label label = new Label("Menu group caption", ContentMode.HTML);
		label.setPrimaryStyleName(ValoTheme.MENU_SUBTITLE);
		label.addStyleName(ValoTheme.LABEL_H4);
		label.setSizeUndefined();
		menuItemsLayout.addComponent(label);

		// Home View Button
		Button homeButton = new Button("Home", event -> {
			navigator.navigateTo("home");
		});
		homeButton.setHtmlContentAllowed(true);
		homeButton.setPrimaryStyleName(ValoTheme.MENU_ITEM);
		homeButton.setIcon(FontAwesome.HOME);
		menuItemsLayout.addComponent(homeButton);

		// Edit View Button
		Button editView = new Button("Edit", event -> {
			navigator.navigateTo("edit");
		});
		editView.setHtmlContentAllowed(true);
		editView.setPrimaryStyleName(ValoTheme.MENU_ITEM);
		editView.setIcon(FontAwesome.EDIT);
		menuItemsLayout.addComponent(editView);

		//
		// pools view
		Button pools = new Button("Pools", event -> {
			navigator.navigateTo("user-pools");
		});

		pools.setHtmlContentAllowed(true);
		pools.setPrimaryStyleName(ValoTheme.MENU_ITEM);
		pools.setIcon(FontAwesome.ARCHIVE);

        menuItemsLayout.addComponent(pools);

		//
		// pools view
		Button dashboard = new Button("Dashboard", event -> {
			navigator.navigateTo("dashboard");
		});

		dashboard.setHtmlContentAllowed(true);
		dashboard.setPrimaryStyleName(ValoTheme.MENU_ITEM);
		dashboard.setIcon(FontAwesome.ANGELLIST);
		menuItemsLayout.addComponent(dashboard);

		//
		// Shutdown application
		Button shutdown = new Button("Shutdown", event -> {
			getUI().getSession().close();
			getPage().setLocation("/login");
		});

		shutdown.setHtmlContentAllowed(true);
		shutdown.setPrimaryStyleName(ValoTheme.MENU_ITEM);
		shutdown.setIcon(FontAwesome.POWER_OFF);


		menuItemsLayout.addComponent(shutdown);

	}




	@Subscribe
	public void recordCustomerChange(String e) {
		System.out.println("recorded event " + e);
		// Home View Button
		Button homeButton = new Button(e, event -> {
			navigator.navigateTo("home");
		});
		homeButton.setHtmlContentAllowed(true);
		homeButton.setPrimaryStyleName(ValoTheme.MENU_ITEM);
		homeButton.setIcon(FontAwesome.HOME);
		menuItemsLayout.addComponent(homeButton);
	}
}
