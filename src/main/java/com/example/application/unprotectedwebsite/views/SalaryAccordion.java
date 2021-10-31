package com.example.application.unprotectedwebsite.views;

import com.example.application.backend.data.Employee;
import com.example.application.backend.data.Salary;
import com.example.application.unprotectedwebsite.database.EmployeeService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

import java.util.*;

public class SalaryAccordion extends HorizontalLayout {

    private final EmployeeService employeeService;
    private final Employee employee;
    private List<Salary> salaries;
    private final Div employeeSkillsLayout = new Div();
    private Div accordion = new Div();
    private final Map<Integer, VerticalLayout> accordionComponents = new HashMap<>();

    public SalaryAccordion(EmployeeService employeeService, Employee employee) {
        this.employeeService = employeeService;
        this.employee = employee;
        this.salaries = employeeService.getSalaries(employee.getId());
        initView();
    }

    private void initView() {
        setPadding(false);
        setSpacing(false);
        setWidthFull();

        employeeSkillsLayout.addClassNames("items-center", "justify-between", "w-full", "pt-s");

        accordion.add(getSalaryAccordion());
        employeeSkillsLayout.add(getSalaryHeader(), getSalaryOptions(), accordion);
        add(employeeSkillsLayout);
    }

    private Component getSalaryHeader() {
        Span header = new Span("Salaries:");
        header.addClassNames("profile-info-header");
        return header;
    }

    private Component getSalaryOptions() {
        HorizontalLayout parentLayout = new HorizontalLayout();
        parentLayout.setSpacing(false);
        parentLayout.setPadding(false);
        parentLayout.addClassNames("items-end", "flex", "justify-between", "w-full");

        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(false);
        layout.setPadding(false);
        layout.addClassNames("items-end");

        TextField search = new TextField();
        search.setLabel("Search For Month");
        search.addClassNames("pr-l");
        search.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        search.setValueChangeMode(ValueChangeMode.EAGER);
        search.addValueChangeListener(textFieldStringComponentValueChangeEvent -> {
            salaries = employeeService.getSalariesByMonth(employee.getId(), search.getValue().toLowerCase());
            redrawSalary();
        });

        layout.add(search, getAddButton());

        parentLayout.add(new Paragraph(), layout);

        return parentLayout;
    }

    private Component getAddButton() {
        Button addSkillButton = new Button();
        addSkillButton.addClassNames("clickable");
        addSkillButton.setText("Add Salary");
        addSkillButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        addSkillButton.addClickListener(buttonClickEvent -> {
            VerticalLayout popupLayout = new VerticalLayout();
            popupLayout.setPadding(false);

            new AddSalaryPopup(employeeService, employee, this);
        });

        return addSkillButton;
    }

    private Component getSalaryAccordion() {
        Accordion employeeSkills = new Accordion();
        employeeSkills.addClassNames("pt-l");

        salaries.forEach(salary ->
                        employeeSkills.add(salary.getHeader(), getAccordionComponent(salary))
                                .addThemeVariants(DetailsVariant.FILLED)
        );

        if (accordionComponents.isEmpty())
            return new Paragraph("No Salaries fit the requirements");

        return employeeSkills;
    }

    public void redrawSalary() {
        accordion.removeAll();
        accordion.add(getSalaryAccordion());
    }

    private Component getAccordionComponent(Salary salary) {
        VerticalLayout skillContent = new VerticalLayout();
        skillContent.setPadding(false);
        skillContent.setSpacing(false);
        skillContent.addClassNames("pl-l");

        Optional<Employee> salaryEmployee = employeeService.getUser(salary.getEmployee_id());
        if (salaryEmployee.isPresent()) {
            skillContent.add(getAccordionContentRow("Name", salaryEmployee.get().getFirstname() + " " + salaryEmployee.get().getLastname()));
            skillContent.add(getAccordionContentRow("Id", salaryEmployee.get().getId()));
        } else {
            skillContent.add(getAccordionContentRow("Name", "Could not find associated employee"));
            skillContent.add(getAccordionContentRow("Id", "Could not find associated employee"));
        }

        skillContent.add(getAccordionContentRow("Salary", String.valueOf(salary.getAmount())));
        skillContent.add(getAccordionContentRow("Taxes", String.valueOf(salary.getTaxes())));
        skillContent.add(getAccordionContentRow("Children", String.valueOf(salary.getChildren())));
        skillContent.add(getAccordionContentRow("Married", String.valueOf(salary.isMarried())));

        accordionComponents.put(salary.getSalaryId(), skillContent);
        return skillContent;
    }

    private Component getAccordionContentRow(String leading, String trailing) {
        Paragraph salaryDescription = new Paragraph(leading);
        Paragraph salaryInformation = new Paragraph(trailing);

        Div contentRow = new Div();
        contentRow.addClassNames("items-center", "flex", "justify-between", "w-full");
        contentRow.add(salaryDescription, salaryInformation);

        return contentRow;
    }
}
