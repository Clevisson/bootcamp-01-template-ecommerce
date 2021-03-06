package com.zup.mercadolivre.request;

import com.zup.mercadolivre.model.User;
import com.zup.mercadolivre.validations.UniqueValue;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class newUserRequest {
    @NotEmpty
    @Email
    @UniqueValue(domainClass = User.class, fieldName = "login")
    private final String login;
    @Length(min = 6)
    private final String senha;

    public newUserRequest(@NotEmpty @Email String login, @Length(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public User toModel(){
        return new User(this.login, this.senha);
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
