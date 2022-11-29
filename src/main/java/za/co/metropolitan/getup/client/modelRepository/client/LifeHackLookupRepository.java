package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.client.LifeHackLookup;

public interface LifeHackLookupRepository extends JpaRepository<LifeHackLookup, Integer> {

    public LifeHackLookup findByLifeHack(String lifeHack);
}

