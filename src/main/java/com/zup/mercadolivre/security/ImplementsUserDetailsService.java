package com.zup.mercadolivre.security;

import com.zup.mercadolivre.model.User;
import com.zup.mercadolivre.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ImplementsUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    UserRepository repository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = repository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return ImplementsUserDetails.build(user);
    }
}
