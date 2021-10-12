package com.cafe.cafe.controller;


import lombok.Data;
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

        private String locale = "en";
        private static Locale RUSSIA = new Locale("ru");
        private String currentLanguage = "English";


        private static Map<String,Object> countries;


        static {
                countries = new LinkedHashMap<String,Object>();
                countries.put("Русский", RUSSIA);
                countries.put("English", Locale.ENGLISH);
        }

        public Map<String, Object> getCountries() {
                return countries;
        }

        public String getCurrentLocale() {
                for (Map.Entry<String, Object> entry : countries.entrySet()) {
                        if(entry.getValue().toString().equals(locale)) {
                                return  entry.getKey();
                        }
                }
                return null;
        }

        public void updateCurrentLocale(String language) {
                locale=language;
                for (Map.Entry<String, Object> entry : countries.entrySet()) {

                        if(entry.getValue().toString().equals(language)) {
                                FacesContext.getCurrentInstance()
                                        .getViewRoot().setLocale((Locale) entry.getValue());
                        }
                }


        }


}
