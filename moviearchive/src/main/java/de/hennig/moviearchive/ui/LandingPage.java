package de.hennig.moviearchive.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

import de.hennig.moviearchive.services.MovieService;

@Theme("valo")
@SpringUI(path = "/home")
public class LandingPage extends UI {

	private static final long serialVersionUID = 1L;

	@Autowired
	MovieService movieService;

	@Override
	protected void init(VaadinRequest request) {
		setContent(new Label("Test"));
		setContent(new Button("Click me", e -> Notification.show("Hello Spring+Vaadin user!")));

	}

}
