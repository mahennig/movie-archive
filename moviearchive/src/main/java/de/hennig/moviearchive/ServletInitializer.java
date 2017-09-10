
package de.hennig.moviearchive;

import javax.servlet.annotation.WebServlet;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import de.hennig.moviearchive.ui.LandingPage;

@WebServlet(value = "/*" ,asyncSupported = true)
@VaadinServletConfiguration(productionMode = false, ui = LandingPage.class)
public class ServletInitializer extends VaadinServlet {


}
