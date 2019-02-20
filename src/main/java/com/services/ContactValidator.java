package com.services;

import com.dao.entities.Contact;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author A.Tymchenko
 * @version 1.0, 14.11.2016.
 */
public class ContactValidator implements Validator {

    private static final int FIRST_NAME_MINLEN = 4;
    private static final int FIRST_NAME_MAXLEN = 45;

    private static final int LAST_NAME_MINLEN = 4;
    private static final int LAST_NAME_MAXLEN = 45;

    private static final int PATRONYMIC_MINLEN = 4;
    private static final int PATRONYMIC_MAXLEN = 45;

    private static final int HOME_PHONE_MAXLEN = 45;
    private static final int ADDRESS_MAXLEN = 255;
    private static final int EMAIL_MAXLEN = 45;

    @Override
    public boolean supports(Class<?> clazz) {
        return ContactValidator.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        /*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "field.required", "This field should not be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "patronymic", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cellPhone", "field.required");*/

        Contact contact = (Contact) obj;

        if (contact.getFirstName().length() < FIRST_NAME_MINLEN || contact.getFirstName().length() > FIRST_NAME_MAXLEN) {
            errors.rejectValue("firstName", "firstName.length", "First Name should contain " +
                    FIRST_NAME_MINLEN + " - " + FIRST_NAME_MAXLEN + " characters");
        }
        if (contact.getLastName().length() < LAST_NAME_MINLEN || contact.getLastName().length() > LAST_NAME_MAXLEN) {
            errors.rejectValue("lastName", "lastName.length", "Last Name should contain " +
                    LAST_NAME_MINLEN + " - " + LAST_NAME_MAXLEN + " characters");
        }
        if (contact.getPatronymic().length() < PATRONYMIC_MINLEN || contact.getPatronymic().length() > PATRONYMIC_MAXLEN) {
            errors.rejectValue("patronymic", "patronymic.length", "Patronymic should contain " +
                    PATRONYMIC_MINLEN + " - " + PATRONYMIC_MAXLEN + " characters");
        }

        if (!Checker.checkTelNumber(contact.getCellPhone())) {
            errors.rejectValue("cellPhone", "cellPhone.format", "Formatting examples: " +
                    "+38 0yy xxx-xx-xx, +380yyxxxxxxx, +38(0yy)xxxxxxx, +380yyxxx-xx-xx, 0yyxxx-xx-xx.");
        }

        if (contact.getHomePhone().length() > HOME_PHONE_MAXLEN) {
            errors.rejectValue("homePhone", "homePhone.length", "Home Phone should be less than " +
                    HOME_PHONE_MAXLEN + " characters");
        }

        if (contact.getAddress().length() > ADDRESS_MAXLEN) {
            errors.rejectValue("address", "address.length", "Address should be less than " +
                    ADDRESS_MAXLEN + " characters");
        }

        if (contact.getEmail().length() > EMAIL_MAXLEN) {
            errors.rejectValue("email", "email.length", "Email should contain less than " +
                    EMAIL_MAXLEN + " characters");
        }

    }

}
