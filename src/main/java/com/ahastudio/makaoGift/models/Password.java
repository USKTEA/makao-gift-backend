package com.ahastudio.makaoGift.models;

import com.ahastudio.makaoGift.exceptions.IncorrectSignUpPassword;
import com.ahastudio.makaoGift.exceptions.MatchPasswordFailed;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
public class Password {
    @Column(name = "encodedPassword")
    private String number;

    @Transient
    private PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();

    public Password() {
    }

    public Password(String number) {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}");
        Matcher matcher = pattern.matcher(number);

        if (!matcher.find()) {
            throw new IncorrectSignUpPassword();
        }

        this.number = number;
    }

    public void match(Password password) {
        if (!passwordEncoder.matches(password.number, this.number)) {
            throw new MatchPasswordFailed();
        }
    }

    public Password encode() {
        return new Password(passwordEncoder.encode(number));
    }

    public String number() {
        return number;
    }

    @Override
    public boolean equals(Object other) {
        Password otherPassword = (Password) other;

        return Objects.equals(this.number, otherPassword.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
