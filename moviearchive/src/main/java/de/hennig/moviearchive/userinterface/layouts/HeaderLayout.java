package de.hennig.moviearchive.userinterface.layouts;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class HeaderLayout extends HorizontalLayout {

    HorizontalLayout titleBar;

    Label title;

    public HeaderLayout(){
        initComponents();
        buildLayout();
    }

    private void initComponents() {
        titleBar = new HorizontalLayout();
        titleBar.setSizeFull();
        title = new Label("Film Datenbank");
    }

    private void buildLayout(){
        titleBar.addComponent(title);
        titleBar.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
        this.addComponent(titleBar);
    }


}
