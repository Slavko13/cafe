package com.cafe.cafe.controller;


import lombok.Data;
import org.springframework.context.annotation.Scope;

import javax.annotation.ManagedBean;

@Scope(value = "session")
@ManagedBean
@Data
public class LocaleBean {

        private String currentLocale = "eng";


        public void updateCurrentLocale(String localeToChange) {
                currentLocale = localeToChange;
        }

}
