package com.example.application.unprotectedwebsite.views;

import com.example.application.backend.data.Employee;
import com.example.application.unprotectedwebsite.database.EmployeeService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouteAlias;

import java.util.Optional;

@PageTitle("Unprotected")
@Route(value = "unprotected", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class UnprotectedView extends HorizontalLayout {

    private final EmployeeService employeeService;
    private Employee employee;

    private final VerticalLayout pageContent = new VerticalLayout();

    public UnprotectedView(EmployeeService employeeService) {
        this.employeeService = employeeService;

        addClassNames("justify-center");
        Div mainDiv = new Div();
        mainDiv.addClassNames("profile-main-div", "pt-m");
        pageContent.setPadding(true);
        pageContent.setSpacing(false);

        Optional<Employee> employeeOptional = employeeService.getCurrentUser();
        if (employeeOptional.isPresent()) {
            employee = employeeOptional.get();
            pageContent.add(getProfileHeader(), getMainContent());
        } else {
            pageContent.add(new Paragraph("Employee does not exits"));
        }

        mainDiv.add(pageContent);
        add(mainDiv);
    }

    public Component getProfileHeader() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.addClassNames("items-center", "h-auto", "flex", "pb-m");
        layout.add(getProfilePhoto(), getNameAndEmail());
        return layout;
    }

    private Image getProfilePhoto() {
        Image profilePhoto = new Image();
        profilePhoto.setSrc("/images/default_avatar.png");
        profilePhoto.setAlt("ProfilePhoto");
        profilePhoto.addClassNames("profile-photo");

        return profilePhoto;
    }

    private Component getNameAndEmail() {
        VerticalLayout layout = new VerticalLayout();

        layout.addClassNames("pl-m", "content-between");
        H1 name = new H1(employee.getDisplayName());
        name.addClassNames("mb-0", "text-l", "pt-0");
        Paragraph email = new Paragraph(employee.getEmail());
        email.addClassNames("mt-0", "text-m", "text-secondary");

        layout.add(name, email);
        return layout;
    }

    private Component getMainContent() {
        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.add(getRole(), getAddress(), getSalaries());
        return layout;
    }

    private Component getRole() {
        Div layout = new Div();
        layout.addClassNames("items-center", "flex", "justify-between", "w-full");

        Span header = new Span("Role:");
        header.addClassNames("profile-info-header");

        Span badge = new Span(employee.getRole().getUiText());
        badge.addClassNames("profile-desk-name", "pr-0");

        layout.add(header, badge);
        return layout;
    }

    private Component getAddress() {
        Div layout = new Div();
        layout.addClassNames("items-center", "flex", "justify-between", "w-full");

        Span header = new Span("Address:");
        header.addClassNames("profile-info-header");

        Span badge = new Span("Frankfurter Straße 1, 61118 Bad Vilbel");
        badge.addClassNames("profile-desk-name", "pr-0");

        layout.add(header, badge);
        return layout;
    }

    private Component getSalaries() {
        return new SalaryAccordion(employeeService, employee);
    }

}
