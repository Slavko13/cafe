package com.cafe.cafe.controller.menu;


import com.cafe.cafe.domain.cafe.CoffeeGrade;
import com.cafe.cafe.service.coffeGrade.CoffeeGradeServiceImpl;
import org.primefaces.event.RowEditEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class CoffeeGradeBean
{


    @ManagedProperty("#{coffeeGrade}")
    private CoffeeGrade coffeeGrade = new CoffeeGrade();

    private Boolean editMode = false;

    private List<CoffeeGrade> selectedGrades = new ArrayList<>();

    private final CoffeeGradeServiceImpl coffeeGradeService;

    private List<CoffeeGrade> menuList = new ArrayList<>();

    private Boolean validationAddForm = false;


    public CoffeeGradeBean(CoffeeGradeServiceImpl coffeeGradeService)
    {
        this.coffeeGradeService = coffeeGradeService;
    }

    @PostConstruct
    private void setMenu() {
        List<CoffeeGrade> menuList = new ArrayList<>();
    }


    public void deleteAllHighlighted()
    {
        coffeeGradeService.deleteCoffeeGrade(selectedGrades);
        menuList.clear();
    }

    public void updateChanges()
    {

    }

    public void addCoffeeGrade()
    {
        if(coffeeGrade.getPrice() != null
                && coffeeGrade.getGradeNameRu() != null
                && coffeeGrade.getGradeNameEng() != null)
        {
            coffeeGradeService.addCoffeeGrade(coffeeGrade);
            menuList.clear();
            coffeeGrade = new CoffeeGrade();
        }
        else
        {
            validationAddForm = true;
        }

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
        if(menuList.isEmpty()) {
            editMode = true;
        }
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
        if(menuList.isEmpty())
        {
            menuList = coffeeGradeService.getAllCoffeeGrades();
        }
        return menuList;
    }

    public void setMenuList(List<CoffeeGrade> menuList)
    {
        this.menuList = menuList;
    }

    public Boolean getValidationAddForm()
    {
        return validationAddForm;
    }

    public void setValidationAddForm(final Boolean validationAddForm)
    {
        this.validationAddForm = validationAddForm;
    }
}
