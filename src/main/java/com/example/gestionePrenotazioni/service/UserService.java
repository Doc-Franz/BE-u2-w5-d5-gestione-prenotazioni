package com.example.gestionePrenotazioni.service;

import com.example.gestionePrenotazioni.model.User;
import com.example.gestionePrenotazioni.repository.UserDAORepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service

public class UserService {

    // iniezione di una istanza della classe UserAdmin
    @Autowired
    @Qualifier("userAdmin")
    ObjectProvider<User> userAdminProvider;

    // iniezione di una istanza della classe User
    @Autowired
    @Qualifier("user")
    ObjectProvider<User> userProvider;

    @Autowired
    UserDAORepository userDAO;

    // restituzione dell'istanza di userAdmin quando il metodo viene invocato
    public User createUserAdmin(){
        return userAdminProvider.getObject();
    }

    // restituzione dell'istanza di userAdmin quando il metodo viene invocato
    public User createUser(){
        return userProvider.getObject();
    }

    // salvataggio dello user nel db
    public void saveUser(User u){
        userDAO.save(u);
    }
}
