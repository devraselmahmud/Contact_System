package com.contact.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contact.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
