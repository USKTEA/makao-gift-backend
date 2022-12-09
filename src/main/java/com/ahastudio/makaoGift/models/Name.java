package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.exceptions.IncorrectSignUpName;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
public class Name {
    @Column(name = "name")
    private String value;

    public Name() {
    }

    public Name(String value) {
        Pattern pattern = Pattern.compile("^[ㄱ-ㅎㅏ-ㅣ가-힣]{3,7}$");
        Matcher matcher = pattern.matcher(value);

        if (!matcher.find()) {
            throw new IncorrectSignUpName();
        }

        this.value = value;
    }

    public String value() {
        return value;
    }
}
