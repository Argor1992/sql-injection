package com.example.application.unprotectedwebsite.views;

import com.example.application.backend.data.Employee;
import com.example.application.unprotectedwebsite.database.EmployeeService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;

public class AddSalaryPopup extends VerticalLayout {
    private final EmployeeService employeeService;
    private final SalaryAccordion accordion;
    private final Employee employee;
    private final Dialog popup;

    private final TextField amount = new TextField("Amount");
    private final TextField taxes = new TextField("Taxes");
    private final TextField children = new TextField("Number of children");
    private final Select<Boolean> married = new Select<>();
    private final TextField month = new TextField("Month");
    private final TextField year = new TextField("Year");

    public AddSalaryPopup(EmployeeService employeeService, Employee employee, SalaryAccordion accordion) {
        this.employeeService = employeeService;
        this.employee = employee;
        this.accordion = accordion;
        popup = new Dialog(this);
        setPadding(false);

        Span description = new Span("Add a new Salary");

        HorizontalLayout layout1 = new HorizontalLayout();
        layout1.setWidthFull();
        layout1.setMargin(false);
        layout1.add(amount, taxes);

        married.setLabel("Married");
        married.setItems(true, false);
        HorizontalLayout layout2 = new HorizontalLayout();
        layout2.setWidthFull();
        layout2.setMargin(false);
        layout2.add(children, married);

        HorizontalLayout layout3 = new HorizontalLayout();
        layout3.setWidthFull();
        layout3.setMargin(false);
        layout3.add(month, year);

        add(description, layout1, layout2, layout3, getSaveAndCancelButton());
        popup.open();
    }

    private Component getSaveAndCancelButton() {
        Button saveSkill = new Button("Save", VaadinIcon.CHECK.create(), e -> {
            if (employeeService.storeNewSalary(employee.getId(), amount.getValue(), taxes.getValue(), children.getValue(),
                    married.getValue(), month.getValue(), year.getValue())) {
                accordion.redrawSalary();
                popup.close();
            } else {
                Notification.show("Please enter valid values!");
            }
        });
        saveSkill.addClickShortcut(Key.ENTER);


        Button cancel = new Button("Cancel", e -> popup.close());
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        cancel.getStyle().set("margin-left", "auto");

        HorizontalLayout buttons = new HorizontalLayout(saveSkill, cancel);
        buttons.setWidthFull();
        buttons.setMargin(false);

        return buttons;
    }
}
