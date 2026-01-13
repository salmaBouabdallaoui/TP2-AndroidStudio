package com.example.myapplication;

import java.util.ArrayList;

public class ContactManager {
    private static ContactManager instance;
    private ArrayList<Contact> contacts;

    private ContactManager() {
        contacts = new ArrayList<>();
    }

    public static ContactManager getInstance() {
        if (instance == null) {
            instance = new ContactManager();
        }
        return instance;
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void clearContacts() {
        contacts.clear();
    }
}