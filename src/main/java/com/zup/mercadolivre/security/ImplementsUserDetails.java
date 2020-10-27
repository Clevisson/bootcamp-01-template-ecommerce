package com.zup.mercadolivre.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zup.mercadolivre.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Repository
@Transactional
public class ImplementsUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String userName;

    @JsonIgnore
    private String passaword;

    private Collection<? extends GrantedAuthority> authorities;

    public ImplementsUserDetails(Long id, String userName, String password,
                                 Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.userName = userName;
        this.passaword = password;
        this.authorities = authorities;
    }

    public static ImplementsUserDetails build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getNameRole().name()))
                .collect(Collectors.toList());
        return new ImplementsUserDetails(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }


    @Override
    public String getPassword() {
        return passaword;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        ImplementsUserDetails user = (ImplementsUserDetails) obj;
        return Objects.equals(id, user.id);
    }
};

