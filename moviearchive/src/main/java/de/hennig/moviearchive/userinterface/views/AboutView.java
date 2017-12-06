package de.hennig.moviearchive.userinterface.views;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.Version;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = AboutView.VIEW_NAME)
public class AboutView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "ABOUT";

    public AboutView() {
        HorizontalLayout aboutContent = new HorizontalLayout();
        aboutContent.setStyleName("about-content");

        // you can add Vaadin components in predefined slots in the custom
        // layout
        aboutContent.addComponent(
                new Label(VaadinIcons.INFO_CIRCLE.getHtml()
                        + " Filmdatenbank " + VaadinIcons.COPYRIGHT + " Markus Hennig "
                        , ContentMode.HTML));

        setSizeFull();
        setMargin(false);
        addComponent(aboutContent);
        setComponentAlignment(aboutContent, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}