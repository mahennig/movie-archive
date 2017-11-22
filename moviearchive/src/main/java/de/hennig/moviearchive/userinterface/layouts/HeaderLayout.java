package de.hennig.moviearchive.userinterface.layouts;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class HeaderLayout extends HorizontalLayout {

    HorizontalLayout titleBar;

    Label title;
    Label titleComment;

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
        this.addComponent(titleBar);
    }


}
