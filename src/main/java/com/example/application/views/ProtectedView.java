package com.example.application.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.example.application.components.leafletmap.LeafletMap;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.MainLayout;

@PageTitle("Protected")
@Route(value = "protected", layout = MainLayout.class)
public class ProtectedView extends VerticalLayout {

    public ProtectedView() {
    }
}
