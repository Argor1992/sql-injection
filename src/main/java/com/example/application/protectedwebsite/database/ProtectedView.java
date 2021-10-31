package com.example.application.protectedwebsite.database;

import com.example.application.unprotectedwebsite.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;

@PageTitle("Protected")
@Route(value = "protected", layout = MainLayout.class)
public class ProtectedView extends VerticalLayout {

    public ProtectedView() {
    }
}
