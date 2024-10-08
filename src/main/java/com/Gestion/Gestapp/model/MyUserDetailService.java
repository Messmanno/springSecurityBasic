package com.Gestion.Gestapp.model;

import com.Gestion.Gestapp.repository.MyUserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MyUserDetailService implements UserDetailsService {

    private MyUserRepository myUserRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //recupere le username
        Optional<MyUser> user = myUserRepository.findByUsername(username);

        //verifie si il est present ou pas
        if (user.isPresent()) {
            var object = user.get();
            return  User.builder()
                    .username(object.getUsername())
                    .password(object.getPassword()) //12345
                    .roles(getRoles(object))
                    .build();
        }else{
            throw new UsernameNotFoundException(username);
        }
    }

    private String[] getRoles(MyUser user) {
        if (user.getRole() == null){
        return new String[]{"USER"};
        }
        return user.getRole().split(",");

    }
}
