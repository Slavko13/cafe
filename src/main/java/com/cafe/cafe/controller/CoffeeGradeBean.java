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
public class CoffeeGradeBean
{


    @ManagedProperty("#{coffeeGrade}")
    private CoffeeGrade coffeeGrade = new CoffeeGrade();

    private Boolean editMode = false;

    private List<CoffeeGrade> selectedGrades = new ArrayList<>();

    private final CoffeeGradeServiceImpl coffeeGradeService;

    private List<CoffeeGrade> menuList;


    public CoffeeGradeBean(CoffeeGradeServiceImpl coffeeGradeService)
    {
        this.coffeeGradeService = coffeeGradeService;
    }

    public List<CoffeeGrade> getMenu()
    {

        if(menuList == null)
        {
            menuList = coffeeGradeService.getAllCoffeeGrades();
        }
        return menuList;
    }

    public void deleteAllHighlighted()
    {
        coffeeGradeService.deleteCoffeeGrade(selectedGrades);
        menuList = null;
    }

    public void updateChanges()
    {

    }

    public void addCoffeeGrade()
    {
        coffeeGradeService.addCoffeeGrade(coffeeGrade);
        menuList = null;
        coffeeGrade = new CoffeeGrade();
    }

    public void updateEditMode()
    {
        editMode = !editMode;
    }

    public void onEdit(RowEditEvent<CoffeeGrade> event)
    {
        coffeeGradeService.updateCoffeeGrade(event.getObject());
        FacesMessage msg = new FacesMessage("Изменено успешно");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent<CoffeeGrade> event)
    {
        //updatedGrades.remove(event.getObject());
    }


    public void onload()
    {
        editMode = false;
    }


    public CoffeeGrade getCoffeeGrade()
    {
        return coffeeGrade;
    }

    public void setCoffeeGrade(CoffeeGrade coffeeGrade)
    {
        this.coffeeGrade = coffeeGrade;
    }

    public Boolean getEditMode()
    {
        return editMode;
    }

    public void setEditMode(Boolean editMode)
    {
        this.editMode = editMode;
    }

    public List<CoffeeGrade> getSelectedGrades()
    {
        return selectedGrades;
    }

    public void setSelectedGrades(List<CoffeeGrade> selectedGrades)
    {
        this.selectedGrades = selectedGrades;
    }

    public List<CoffeeGrade> getMenuList()
    {
        return menuList;
    }

    public void setMenuList(List<CoffeeGrade> menuList)
    {
        this.menuList = menuList;
    }
}
