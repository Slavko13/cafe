package com.cafe.cafe.controller;


import com.cafe.cafe.dto.CoffeeGradeViewDTO;
import com.cafe.cafe.exceptions.simpleException.NotFoundException;
import lombok.Data;
import org.apache.tomcat.jni.Local;
import org.springframework.context.annotation.Scope;

import javax.annotation.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@Scope(value = "session")
@ManagedBean
@Data
public class LocaleBean {


        private Locale locale = new Locale("ru");

        public void updateCurrentLocale(String language) {
                locale = new Locale(language);
                FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        }

        public String getLocalName(CoffeeGradeViewDTO coffeeGradeViewDTO) {
                switch (locale.getLanguage()) {
                        case ("ru"):
                                return coffeeGradeViewDTO.getGradeNameRu();
                        case ("en"):
                                return coffeeGradeViewDTO.getGradeNameEng();
                        default: throw new NotFoundException("");
                }
        }
}
