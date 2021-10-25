package com.cafe.cafe.controller.util;


import com.cafe.cafe.dto.CoffeeGradeViewDTO;
import com.cafe.cafe.exceptions.simpleException.NotFoundException;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.util.Locale;

@Component
@ViewScoped
@Data
public class LocaleBean
{


    private Locale locale = new Locale("ru");

    public void updateCurrentLocale(String language)
    {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

    public String getLocalName(CoffeeGradeViewDTO coffeeGradeViewDTO)
    {
        switch(locale.getLanguage())
        {
            case ("ru"):
                return coffeeGradeViewDTO.getGradeNameRu();
            case ("en"):
                return coffeeGradeViewDTO.getGradeNameEng();
            default:
                throw new NotFoundException("");
        }
    }
}
