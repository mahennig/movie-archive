package de.hennig.moviearchive.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import de.hennig.moviearchive.services.MovieService;

@Theme("valo")
@SpringUI
@Title("Movie Archive")
public class LandingPage extends UI {

	private static final long serialVersionUID = 1L;

	@Autowired
	MovieService movieService;

	private VerticalLayout layout;

	@Override
	protected void init(VaadinRequest request) {
		setContent(new Button("Click me", e -> Notification.show("Hello Spring+Vaadin user!")));
	    setupLayout();
        addHeader();
	}

	private void setupLayout() {
		layout = new VerticalLayout();
		layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		setContent(layout);
	}

	private void addHeader() {
		Label header = new Label("TODO");
		header.addStyleName(ValoTheme.LABEL_H1);
		layout.addComponent(header);

	}

}
