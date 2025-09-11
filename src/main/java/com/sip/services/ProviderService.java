package com.sip.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sip.entities.Provider;
import com.sip.exceptions.ResourceNotFoundException;
import com.sip.repositories.ProviderRepository;

@Service
public class ProviderService {
	@Autowired
	private ProviderRepository providerRepository;

	public Provider addProvider(Provider provider) {
		return providerRepository.save(provider);
	}

	public List<Provider> GetAllProviders() {
		return (List<Provider>) providerRepository.findAll();
	}

	public Provider updateProvider(long id, Provider provider) {
		return providerRepository.findById(id).map(p -> {
			p.setName(provider.getName());
			p.setEmail(provider.getEmail());
			p.setAddress(provider.getAddress());
			return providerRepository.save(p);
		}).orElseThrow(() -> new ResourceNotFoundException("ProviderId " + id + " not found"));

	}

	public Provider deleteProvider(long id) {
		return providerRepository.findById(id).map(p -> {
			providerRepository.delete(p);
			return p;
		}).orElseThrow(() -> new ResourceNotFoundException("ProviderId " + id + " not found"));

	}

	public Provider getProviderById(long id) {
		return providerRepository.findById(id).map(p -> {
			return p;
		}).orElseThrow(() -> new ResourceNotFoundException("ProviderId " + id + " not found"));

	}
}
