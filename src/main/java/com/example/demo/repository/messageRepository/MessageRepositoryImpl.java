package com.example.demo.repository.messageRepository;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.model.Message;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class MessageRepositoryImpl implements CustomMessageRepository {

    @PersistenceContext
    private EntityManager em;

    public MessageRepositoryImpl(EntityManager emParam) {
        this.em = emParam;
    }

    @Override
    public void saveMessage(Message message) {
        this.em.persist(message);
    }

    @Override
    public List<Message> getMessageFromId(int id) {
        TypedQuery<Message> query = (TypedQuery<Message>) this.em
                .createQuery("SELECT m FROM Message m WHERE m.userSender = : id OR m.userReceiver = :id");
        query.setParameter("id", id);
        List<Message> listMessages = query.getResultList();

        return listMessages;
    }

    @Override
    public List<Message> getMessagesFromConversation(int idUser, int idOtherUser) {
        TypedQuery<Message> query = (TypedQuery<Message>) this.em.createQuery(
                "SELECT m FROM Message m WHERE (m.userSender = : id AND m.userReceiver = :idOther) OR (m.userSender = : idOther AND m.userReceiver = :id)");
        query.setParameter("id", idUser);
        query.setParameter("idOther", idOtherUser);
        List<Message> listMessages = query.getResultList();

        return listMessages;
    }

    @Override
    public List<Integer> getContacts(int idUser) {
        TypedQuery<Integer> query = (TypedQuery<Integer>) this.em.createQuery(
            "SELECT DISTINCT m.userReceiver AS contactId FROM Message m WHERE m.userSender = :id UNION SELECT DISTINCT m.userSender AS contactId FROM Message m WHERE m.userReceiver = :id");
   
            query.setParameter("id", idUser);

        List<Integer> listContacts = query.getResultList();

        return listContacts;
    }

}
