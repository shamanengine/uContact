package com.validators;

import com.dao.entities.User;
import com.services.Checker;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author A.Tymchenko
 * @version 1.0, 16.11.2016.
 */
public class UserValidator implements Validator {

    private static final int LOGIN_MINLEN = 3;
    private static final int LOGIN_MAXLEN = 45;

    private static final int PASSWORD_MINLEN = 5;
    private static final int PASSWORD_MAXLEN = 45;

    private static final int FIRST_NAME_MINLEN = 0;
    private static final int FIRST_NAME_MAXLEN = 45;

    private static final int LAST_NAME_MINLEN = 0;
    private static final int LAST_NAME_MAXLEN = 45;

    private static final int PATRONYMIC_MINLEN = 0;
    private static final int PATRONYMIC_MAXLEN = 45;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserValidator.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        if (obj == null) throw new NullPointerException();

        User user = (User) obj;

        String login = user.getLogin();
        String password = user.getPassword();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String patronymic = user.getPatronymic();

        if (login == null || login.length() < LOGIN_MINLEN || login.length() > LOGIN_MAXLEN
                || !Checker.checkLogin(login)) {

            errors.rejectValue("login", "login", "Login should contain " +
                    LOGIN_MINLEN + " - " + LOGIN_MAXLEN + " characters in English or numbers, " +
                    "no supplementary symbols are allowed.");
        }

        if (password == null || password.length() < PASSWORD_MINLEN || password.length() > PASSWORD_MAXLEN
                || !Checker.checkPassword(password)) {

            errors.rejectValue("password", "password.length", "Password should contain " +
                    PASSWORD_MINLEN + " - " + PASSWORD_MAXLEN + " characters in English, numbers or punctuation");
        }

        if (firstName == null || firstName.length() < FIRST_NAME_MINLEN || firstName.length() > FIRST_NAME_MAXLEN) {
            errors.rejectValue("firstName", "firstName.length", "First Name should contain " +
                    FIRST_NAME_MINLEN + " - " + FIRST_NAME_MAXLEN + " characters");
        }
        if (lastName == null || lastName.length() < LAST_NAME_MINLEN || lastName.length() > LAST_NAME_MAXLEN) {
            errors.rejectValue("lastName", "lastName.length", "Last Name should contain " +
                    LAST_NAME_MINLEN + " - " + LAST_NAME_MAXLEN + " characters");
        }
        if (patronymic == null || patronymic.length() < PATRONYMIC_MINLEN || patronymic.length() > PATRONYMIC_MAXLEN) {
            errors.rejectValue("patronymic", "patronymic.length", "Patronymic should contain " +
                    PATRONYMIC_MINLEN + " - " + PATRONYMIC_MAXLEN + " characters");
        }

    }
}
