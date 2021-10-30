package com.example.application.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.MainLayout;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Unprotected")
@Route(value = "unprotected", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class UnprotectedView extends HorizontalLayout {

    public UnprotectedView() {

    }

}
