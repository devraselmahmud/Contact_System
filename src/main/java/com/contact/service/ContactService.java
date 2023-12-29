package com.contact.service;

import java.util.List;

import com.contact.entity.Contact;

public interface ContactService {

	public Contact saveContact(Contact contact);

	public List<Contact> getAllContact();

	public Contact getContactById(int id);

	public boolean deleteContact(int id);

}
