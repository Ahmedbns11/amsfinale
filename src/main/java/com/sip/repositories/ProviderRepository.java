package com.sip.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sip.entities.Provider;

@Repository
public interface ProviderRepository extends CrudRepository<Provider, Long> {

}
