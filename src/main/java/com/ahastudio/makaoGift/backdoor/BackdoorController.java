package com.ahastudio.makaoGift.backdoor;

import com.ahastudio.makaoGift.dtos.ProductsDto;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@Transactional
@RequestMapping("backdoor")
public class BackdoorController {
    private final JdbcTemplate jdbcTemplate;
    private PasswordEncoder passwordEncoder;

    public BackdoorController(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("setup-products")
    @ResponseStatus(HttpStatus.CREATED)
    public String setupProducts(
            @RequestBody ProductsDto productsDto
    ) {
        jdbcTemplate.execute("DELETE FROM product");

        productsDto.getProducts().stream().forEach((productDto -> {
            jdbcTemplate.update("" +
                            "INSERT INTO product(" +
                            "id, name, manufacturer, price, description, image_url" +
                            ")" +
                            " VALUES(?, ?, ?, ?, ?, ?)",
                    productDto.getId(), productDto.getName(), productDto.getManufacturer(),
                    productDto.getPrice(), productDto.getDescription(), productDto.getImageUrl());
        }));

        return "OK";
    }

    @PostMapping("setup-user")
    @ResponseStatus(HttpStatus.CREATED)
    public String setupUser() {
        jdbcTemplate.execute("DELETE FROM member");

        jdbcTemplate.update("" +
                "INSERT INTO member(" +
                " id, member_name, encoded_password, name, amount" +
                ")" +
                " VALUES(1, ?, ?, ?, ?)",
                "ashal1234", passwordEncoder.encode("Password1234!"), "김이박최아샬", 50_000
        );

        jdbcTemplate.update("" +
                        "INSERT INTO member(" +
                        " id, member_name, encoded_password, name, amount" +
                        ")" +
                        " VALUES(2, ?, ?, ?, ?)",
                "jocker1234", passwordEncoder.encode("Password1234!"), "김조커", 100_000
        );

        return "Ok";
    }

    @DeleteMapping("setup-products")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String clear() {
        jdbcTemplate.execute("DELETE FROM product");

        return "OK";
    }


}
