import java.util.ArrayList;
public class Model {

    ArrayList<Contact> contacts;
    int nbContacts;

    public Model() {
        contacts = new ArrayList<>();
        contacts.add(new Contact("John", "Doe", "0123456789", 0));
    }
    public void addContact(Contact contact) {
        contacts.add(contact);
        nbContacts++;
    }

    public void removeContact(int id) {
        for (int i = id; i < nbContacts - 1; i++) {
            contacts.set(i, contacts.get(i + 1));
        }
        nbContacts--;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }
    public void setFirstName(String text) {
        contacts.get(nbContacts).setFirstName(text);
    }

    public void setLastName(String text) {
        contacts.get(nbContacts).setLastName(text);
    }

    public void setPhoneNumber(String text) {
        contacts.get(nbContacts).setPhoneNumber(text);
    }

    public String getFirstName() {
        return contacts.get(nbContacts).getFirstName();
    }

    public String getLastName() {
        return contacts.get(nbContacts).getLastName();
    }

    public String getPhoneNumber() {
        return contacts.get(nbContacts).getPhoneNumber();
    }

    public int getContactList() {
        return contacts.get(nbContacts).getId();
    }

    public void updateContact(Contact contact) {
        contacts.set(nbContacts, contact);
    }

    public void setSelectedContact(Contact contact) {
        contacts.set(nbContacts, contact);
    }
}
class Contact {
    String firstName, lastName, phoneNumber;
    int id;

    public Contact(String firstName, String lastName, String phoneNumber, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    public String toString() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }
}