package com.nrusev.support;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;

/**
 * Created by nikolayrusev on 2/28/17.
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import com.vaadin.navigator.NavigationStateManager;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.Page.UriFragmentChangedEvent;
import com.vaadin.server.Page.UriFragmentChangedListener;
import com.vaadin.shared.util.SharedUtil;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.SingleComponentContainer;
import com.vaadin.ui.UI;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyOwnNavigator extends Navigator implements Serializable {
    private UI ui;
    private NavigationStateManager stateManager;
    private ViewDisplay display;
    private View currentView;
    private List<ViewChangeListener> listeners;
    private List<ViewProvider> providers;
    private String currentNavigationState;
    private ViewProvider errorProvider;

    public MyOwnNavigator(UI ui, ComponentContainer container) {
        this(ui, (ViewDisplay)(new com.vaadin.navigator.Navigator.ComponentContainerViewDisplay(container)));
    }

    public MyOwnNavigator(UI ui, SingleComponentContainer container) {
        this(ui, (ViewDisplay)(new com.vaadin.navigator.Navigator.SingleComponentContainerViewDisplay(container)));
    }

    public MyOwnNavigator(UI ui, ViewDisplay display) {
        this(ui, new com.vaadin.navigator.Navigator.UriFragmentManager(ui.getPage()), display);
    }

    public MyOwnNavigator(UI ui, NavigationStateManager stateManager, ViewDisplay display) {
        this.currentView = null;
        this.listeners = new LinkedList();
        this.providers = new LinkedList();
        this.currentNavigationState = null;
        this.init(ui, stateManager, display);
    }

    protected MyOwnNavigator() {
        this.currentView = null;
        this.listeners = new LinkedList();
        this.providers = new LinkedList();
        this.currentNavigationState = null;
    }

    protected void init(UI ui, NavigationStateManager stateManager, ViewDisplay display) {
        this.ui = ui;
        this.ui.setNavigator(this);
        if(stateManager == null) {
            stateManager = new com.vaadin.navigator.Navigator.UriFragmentManager(ui.getPage());
        }

        this.stateManager = (NavigationStateManager)stateManager;
        this.stateManager.setNavigator(this);
        this.display = display;
    }

    public void navigateTo(String navigationState) {
        ViewProvider longestViewNameProvider = this.getViewProvider(navigationState);
        String longestViewName = longestViewNameProvider == null?null:longestViewNameProvider.getViewName(navigationState);
        View viewWithLongestName = null;
        if(longestViewName != null) {
            viewWithLongestName = longestViewNameProvider.getView(longestViewName);
        }

        if(viewWithLongestName == null && this.errorProvider != null) {
            longestViewName = this.errorProvider.getViewName(navigationState);
            viewWithLongestName = this.errorProvider.getView(longestViewName);
        }

        if(viewWithLongestName == null) {
            throw new IllegalArgumentException("Trying to navigate to an unknown state \'" + navigationState + "\' and an error view provider not present");
        } else {
            String parameters = "";
            if(navigationState.length() > longestViewName.length() + 1) {
                parameters = navigationState.substring(longestViewName.length() + 1);
            } else if(navigationState.endsWith("/")) {
                navigationState = navigationState.substring(0, navigationState.length() - 1);
            }

            if(this.getCurrentView() != null && SharedUtil.equals(this.getCurrentView(), viewWithLongestName) && SharedUtil.equals(this.currentNavigationState, navigationState)) {
                this.updateNavigationState(new ViewChangeEvent(this, this.getCurrentView(), viewWithLongestName, longestViewName, parameters));
            } else {
                this.navigateTo(viewWithLongestName, longestViewName, parameters);
            }

        }
    }

    protected void navigateTo(View view, String viewName, String parameters) {
        ViewChangeEvent event = new ViewChangeEvent(this, this.currentView, view, viewName, parameters);
        boolean navigationAllowed = this.beforeViewChange(event);
        if(!navigationAllowed) {
            this.revertNavigation();
        } else {
            this.updateNavigationState(event);
            if(this.getDisplay() != null) {
                this.getDisplay().showView(view);
            }

            this.switchView(event);
            view.enter(event);
            this.fireAfterViewChange(event);
        }
    }

    protected boolean beforeViewChange(ViewChangeEvent event) {
        return this.fireBeforeViewChange(event);
    }

    protected void revertNavigation() {
        if(this.currentNavigationState != null) {
            this.getStateManager().setState(this.currentNavigationState);
        }

    }

    protected void updateNavigationState(ViewChangeEvent event) {
        String viewName = event.getViewName();
        String parameters = event.getParameters();
        if (null != viewName && getStateManager() != null) {
            String navigationState = viewName;
            if (!parameters.isEmpty()) {
                navigationState += "/" + parameters;
            }
            if (!navigationState.equals(getStateManager().getState())) {
                getStateManager().setState(navigationState);
            }
            currentNavigationState = navigationState;
        }
    }


        protected void switchView(ViewChangeEvent event) {
        this.currentView = event.getNewView();
    }

    protected boolean fireBeforeViewChange(ViewChangeEvent event) {
        Iterator var2 = (new ArrayList(this.listeners)).iterator();

        ViewChangeListener l;
        do {
            if(!var2.hasNext()) {
                return true;
            }

            l = (ViewChangeListener)var2.next();
        } while(l.beforeViewChange(event));

        return false;
    }

    protected NavigationStateManager getStateManager() {
        return this.stateManager;
    }

    public String getState() {
        return this.getStateManager().getState();
    }

    public ViewDisplay getDisplay() {
        return this.display;
    }

    public UI getUI() {
        return this.ui;
    }

    public View getCurrentView() {
        return this.currentView;
    }

    protected void fireAfterViewChange(ViewChangeEvent event) {
        Iterator var2 = (new ArrayList(this.listeners)).iterator();

        while(var2.hasNext()) {
            ViewChangeListener l = (ViewChangeListener)var2.next();
            l.afterViewChange(event);
        }

    }

    public void addView(String viewName, View view) {
        if(viewName != null && view != null) {
            this.removeView(viewName);
            this.addProvider(new com.vaadin.navigator.Navigator.StaticViewProvider(viewName, view));
        } else {
            throw new IllegalArgumentException("view and viewName must be non-null");
        }
    }

    public void addView(String viewName, Class<? extends View> viewClass) {
        if(viewName != null && viewClass != null) {
            this.removeView(viewName);
            this.addProvider(new com.vaadin.navigator.Navigator.ClassBasedViewProvider(viewName, viewClass));
        } else {
            throw new IllegalArgumentException("view and viewClass must be non-null");
        }
    }

    public void removeView(String viewName) {
        Iterator it = this.providers.iterator();

        while(it.hasNext()) {
            ViewProvider provider = (ViewProvider)it.next();
            if(provider instanceof com.vaadin.navigator.Navigator.StaticViewProvider) {
                com.vaadin.navigator.Navigator.StaticViewProvider classBasedProvider = (com.vaadin.navigator.Navigator.StaticViewProvider)provider;
                if(classBasedProvider.getViewName().equals(viewName)) {
                    it.remove();
                }
            } else if(provider instanceof com.vaadin.navigator.Navigator.ClassBasedViewProvider) {
                com.vaadin.navigator.Navigator.ClassBasedViewProvider classBasedProvider1 = (com.vaadin.navigator.Navigator.ClassBasedViewProvider)provider;
                if(classBasedProvider1.getViewName().equals(viewName)) {
                    it.remove();
                }
            }
        }

    }

    public void addProvider(ViewProvider provider) {
        if(provider == null) {
            throw new IllegalArgumentException("Cannot add a null view provider");
        } else {
            this.providers.add(provider);
        }
    }

    public void removeProvider(ViewProvider provider) {
        this.providers.remove(provider);
    }

    public void setErrorView(final Class<? extends View> viewClass) {
        this.setErrorProvider(new ViewProvider() {
            public View getView(String viewName) {
                try {
                    return (View)viewClass.newInstance();
                } catch (Exception var3) {
                    throw new RuntimeException(var3);
                }
            }

            public String getViewName(String navigationState) {
                return navigationState;
            }
        });
    }

    public void setErrorView(final View view) {
        this.setErrorProvider(new ViewProvider() {
            public View getView(String viewName) {
                return view;
            }

            public String getViewName(String navigationState) {
                return navigationState;
            }
        });
    }

    public void setErrorProvider(ViewProvider provider) {
        this.errorProvider = provider;
    }

    public void addViewChangeListener(ViewChangeListener listener) {
        this.listeners.add(listener);
    }

    public void removeViewChangeListener(ViewChangeListener listener) {
        this.listeners.remove(listener);
    }

    private ViewProvider getViewProvider(String state) {
        String longestViewName = null;
        ViewProvider longestViewNameProvider = null;
        Iterator var4 = this.providers.iterator();

        while(true) {
            ViewProvider provider;
            String viewName;
            do {
                do {
                    if(!var4.hasNext()) {
                        return longestViewNameProvider;
                    }

                    provider = (ViewProvider)var4.next();
                    viewName = provider.getViewName(state);
                } while(null == viewName);
            } while(longestViewName != null && viewName.length() <= longestViewName.length());

            longestViewName = viewName;
            longestViewNameProvider = provider;
        }
    }

    public void destroy() {
        this.stateManager.setNavigator((com.vaadin.navigator.Navigator)null);
        this.ui.setNavigator((com.vaadin.navigator.Navigator)null);
    }

    public static class ClassBasedViewProvider implements ViewProvider {
        private final String viewName;
        private final Class<? extends View> viewClass;

        public ClassBasedViewProvider(String viewName, Class<? extends View> viewClass) {
            if(null != viewName && null != viewClass) {
                this.viewName = viewName;
                this.viewClass = viewClass;
            } else {
                throw new IllegalArgumentException("View name and class should not be null");
            }
        }

        public String getViewName(String navigationState) {
            return null == navigationState?null:(!navigationState.equals(this.viewName) && !navigationState.startsWith(this.viewName + "/")?null:this.viewName);
        }

        public View getView(String viewName) {
            if(this.viewName.equals(viewName)) {
                try {
                    View e = (View)this.viewClass.newInstance();
                    return e;
                } catch (InstantiationException var3) {
                    throw new RuntimeException(var3);
                } catch (IllegalAccessException var4) {
                    throw new RuntimeException(var4);
                }
            } else {
                return null;
            }
        }

        public String getViewName() {
            return this.viewName;
        }

        public Class<? extends View> getViewClass() {
            return this.viewClass;
        }
    }

    public static class StaticViewProvider implements ViewProvider {
        private final String viewName;
        private final View view;

        public StaticViewProvider(String viewName, View view) {
            this.viewName = viewName;
            this.view = view;
        }

        public String getViewName(String navigationState) {
            return null == navigationState?null:(!navigationState.equals(this.viewName) && !navigationState.startsWith(this.viewName + "/")?null:this.viewName);
        }

        public View getView(String viewName) {
            return this.viewName.equals(viewName)?this.view:null;
        }

        public String getViewName() {
            return this.viewName;
        }
    }

    public static class SingleComponentContainerViewDisplay implements ViewDisplay {
        private final SingleComponentContainer container;

        public SingleComponentContainerViewDisplay(SingleComponentContainer container) {
            this.container = container;
        }

        public void showView(View view) {
            if(view instanceof Component) {
                this.container.setContent((Component)view);
            } else {
                throw new IllegalArgumentException("View is not a component: " + view);
            }
        }
    }

    public static class ComponentContainerViewDisplay implements ViewDisplay {
        private final ComponentContainer container;

        public ComponentContainerViewDisplay(ComponentContainer container) {
            this.container = container;
        }

        public void showView(View view) {
            if(view instanceof Component) {
                this.container.removeAllComponents();
                this.container.addComponent((Component)view);
            } else {
                throw new IllegalArgumentException("View is not a component: " + view);
            }
        }
    }

    public static class UriFragmentManager implements NavigationStateManager, UriFragmentChangedListener {
        private final Page page;
        private com.vaadin.navigator.Navigator navigator;

        public UriFragmentManager(Page page) {
            this.page = page;
        }

        public void setNavigator(com.vaadin.navigator.Navigator navigator) {
            if(this.navigator == null && navigator != null) {
                this.page.addUriFragmentChangedListener(this);
            } else if(this.navigator != null && navigator == null) {
                this.page.removeUriFragmentChangedListener(this);
            }

            this.navigator = navigator;
        }

        public String getState() {
            String fragment = this.getFragment();
            return fragment != null && fragment.startsWith("!")?fragment.substring(1):"";
        }

        public void setState(String state) {
            this.setFragment("!" + state);
        }

        public void uriFragmentChanged(UriFragmentChangedEvent event) {
            this.navigator.navigateTo(this.getState());
        }

        protected String getFragment() {
            return this.page.getUriFragment();
        }

        protected void setFragment(String fragment) {
            this.page.setUriFragment(fragment, false);
        }
    }

    public static class EmptyView extends CssLayout implements View {
        public EmptyView() {
            this.setWidth("0px");
            this.setHeight("0px");
        }

        public void enter(ViewChangeEvent event) {
        }
    }
}

