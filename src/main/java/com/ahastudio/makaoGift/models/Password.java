package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.exceptions.IncorrectSignUpPassword;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Password {
    private String value;

    public Password() {
    }

    public Password(String value) {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}");
        Matcher matcher = pattern.matcher(value);

        if (!matcher.find()) {
            throw new IncorrectSignUpPassword();
        }

        this.value = value;
    }

    public String value() {
        return value;
    }
}
