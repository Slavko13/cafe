package com.cafe.cafe.controller;


import com.cafe.cafe.domain.CoffeeGrade;
import com.cafe.cafe.domain.Order;
import com.cafe.cafe.dto.RowCoffeeGradeDTO;
import com.cafe.cafe.service.coffeGrade.CoffeeGradeServiceImpl;
import lombok.Data;
import org.primefaces.event.RowEditEvent;
import org.richfaces.component.Row;
import org.springframework.context.annotation.Scope;

import javax.annotation.ManagedBean;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@Scope(value = "session")
@Data
public class CoffeeGradeBean {


    @ManagedProperty("#{coffeeGrade}")
    private CoffeeGrade coffeeGrade = new CoffeeGrade();

    private Boolean editMode = false;

    private List<CoffeeGrade> selectedGrades = new ArrayList<>();

    private List<CoffeeGrade> updatedGrades = new ArrayList<>();

    private final CoffeeGradeServiceImpl coffeeGradeService;


    private List<CoffeeGrade> menuList;


    public CoffeeGradeBean(CoffeeGradeServiceImpl coffeeGradeService) {
        this.coffeeGradeService = coffeeGradeService;
    }

    public List<CoffeeGrade> getMenu() {

        if (menuList == null) {
            menuList = coffeeGradeService.getAllCoffeeGrades();
        }

        return menuList;
    }

    public void deleteAllHighlighted() {
        coffeeGradeService.deleteCoffeeGrade(selectedGrades);
    }

    public void updateChanges() {
        coffeeGradeService.updateListCoffeeGrade(updatedGrades);
        FacesMessage msg = new FacesMessage("Изменено успешно");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void addCoffeeGrade() {
        coffeeGradeService.addCoffeeGrade(coffeeGrade);
        coffeeGrade = new CoffeeGrade();
    }

    public void updateEditMode() {
        editMode= !editMode;
    }

    public void onEdit(RowEditEvent<CoffeeGrade> event) {
        updatedGrades.add(event.getObject());
    }

    public void onCancel(RowEditEvent<CoffeeGrade> event) {
        updatedGrades.remove(event.getObject());
    }
}
