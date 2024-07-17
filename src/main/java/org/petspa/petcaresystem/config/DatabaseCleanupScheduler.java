package org.petspa.petcaresystem.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DatabaseCleanupScheduler {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private VertifyRepository vertifyRepository;

//    @Scheduled(fixedRate = 30 * 60 * 1000) // 30 phút
    @Scheduled(fixedRate = 10 * 1000) // 10 giây
    @Transactional
    public void cleanupDatabase() {
        Query query = entityManager.createQuery("SELECT e FROM Vertify e ORDER BY e.id ASC");
        query.setMaxResults(1);
        Vertify oldestEntity = (Vertify) query.getSingleResult();

        try{
            if (oldestEntity != null) {
                entityManager.remove(oldestEntity);
                entityManager.flush();
                System.out.println("Schedule task: Deleted oldest row in vertify table");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
