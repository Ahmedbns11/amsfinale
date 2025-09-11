package com.sip.controllers;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sip.entities.Provider;
import com.sip.services.ProviderService;

@RestController
@RequestMapping({ "/providers" })
@CrossOrigin(origins = "*")
public class ProviderRestController {

	@Autowired
	private ProviderService providerService;

	@GetMapping("")
	public List<Provider> getAllProvider() {
		return providerService.GetAllProviders();
	}

	@PostMapping("")
	public Provider createProvider(@Valid @RequestBody Provider provider) {
		return providerService.addProvider(provider);
	}

	@PutMapping("/{providerId}")
	public Provider updateProvider(@PathVariable Long providerId, @Valid @RequestBody Provider providerRequest) {
		return providerService.updateProvider(providerId, providerRequest);
	}

	@DeleteMapping("/{providerId}")
	public Provider deleteProvider(@PathVariable Long providerId) {
		return providerService.deleteProvider(providerId);
	}
	
	@GetMapping("/{providerId}")
	public Provider getProviderById(@PathVariable Long providerId) {
		return providerService.getProviderById(providerId);
	}

}
