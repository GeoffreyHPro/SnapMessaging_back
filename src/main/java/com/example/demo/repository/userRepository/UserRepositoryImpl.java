package com.example.demo.repository.userRepository;


import org.springframework.stereotype.Service;

import com.example.demo.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserRepositoryImpl implements CustomUserRepository {
    @PersistenceContext
    private EntityManager em;

    public UserRepositoryImpl(EntityManager emParam) {
        this.em = emParam;
    }

    @Override
    public boolean saveUser(User user) {
        this.em.persist(user);
        return true;
    }

    @Override
    public boolean changePassword(User user, String password) {
        user.updatePassword(password);
        this.em.merge(user);
        return true;
    }

    @Override
    public void changePseudo(User user, String pseudo) {
        user.setPseudo(pseudo);
        this.em.merge(user);
    }

    @Override
    public void updateImage(String email, byte[] image, String nameImage) throws Exception{
        TypedQuery<User> query = this.em.createQuery("SELECT u FROM User u WHERE u.email = : email", User.class);
        query.setParameter("email", email);
        User user = query.getSingleResult();
        user.setImage(image, nameImage);
    }

}
