package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.exceptions.IncorrectSignUpMemberName;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemberName {
    private String value;

    public MemberName() {
    }

    public MemberName(String value) {
        Pattern pattern = Pattern.compile("^[a-z|0-9]{4,16}$");
        Matcher matcher = pattern.matcher(value);

        if (!matcher.find()) {
            throw new IncorrectSignUpMemberName();
        }

        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        MemberName that = (MemberName) other;

        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
