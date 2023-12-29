package com.contact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import com.contact.entity.Contact;
import com.contact.repository.ContactRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepo;

	@Override
	public Contact saveContact(Contact contact) {
		Contact newContact = contactRepo.save(contact);
		return newContact;
	}

	@Override
	public List<Contact> getAllContact() {

		return contactRepo.findAll();
	}

	@Override
	public Contact getContactById(int id) {
		return contactRepo.findById(id).get();
	}

	@Override
	public boolean deleteContact(int id) {
		Contact contact = contactRepo.findById(id).get();
		if (contact != null) {
			contactRepo.delete(contact);
			return true;
		}
		return false;
	}

	public void removeSessionMessage() {
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();

		session.removeAttribute("msg");

	}

}
