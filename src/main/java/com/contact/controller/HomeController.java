package com.contact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.contact.entity.Contact;
import com.contact.service.ContactService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private ContactService contactService;

	@GetMapping("/")
	public String index(Model m) {
		List<Contact> list = contactService.getAllContact();
		m.addAttribute("contactList", list);
		return "index";
	}

	@GetMapping("/loadContactSave")
	public String loadContactSave() {
		return "contact_save";
	}

	@GetMapping("/EditContact/{id}")
	public String EditContact(@PathVariable int id, Model m) {
		// System.out.println(id);
		Contact contact = contactService.getContactById(id);
		m.addAttribute("contact", contact);
		return "edit_contact";
	}

	@PostMapping("/saveContact")
	public String saveContact(@ModelAttribute Contact contact, HttpSession session) {
		// System.out.println(contact);

		Contact newContact = contactService.saveContact(contact);

		if (newContact != null) {
			// System.out.println("save success");
			session.setAttribute("msg", "Register sucessfully");
		} else {
			// System.out.println("something wrong on server");
			session.setAttribute("msg", "something wrong on server");
		}

		return "redirect:/loadContactSave";
	}

	@PostMapping("/updateContactDtls")
	public String updateContact(@ModelAttribute Contact contact, HttpSession session) {
		// System.out.println(contact);

		Contact updateContact = contactService.saveContact(contact);

		if (updateContact != null) {
			// System.out.println("save success");
			session.setAttribute("msg", "Update sucessfully");
		} else {
			// System.out.println("something wrong on server");
			session.setAttribute("msg", "something wrong on server");
		}

		return "redirect:/";
	}

	@GetMapping("/deleteContact/{id}")
	public String loadContactSave(@PathVariable int id, HttpSession session) {
		boolean f = contactService.deleteContact(id);
		if (f) {
			session.setAttribute("msg", "Delete sucessfully");
		} else {
			session.setAttribute("msg", "something wrong on server");
		}
		return "redirect:/";
	}

}
