package db.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalRunner {

    public static void main(String[] args) {

        Locale locale = new Locale("ru", "RU");
        Locale us = Locale.US;

        var translations = ResourceBundle.getBundle("translation", locale);
        translations.getString("page.login.password");

    }
}

