package wizut.bukmacher;

import java.io.Serializable;

/**
 *
 */

public class User implements Serializable {
    private int id;
    private String nick;
    private String password;
    private String email;
    private String name;
    private String surname;
    private String address;
    private String card_number;
    private float balance;
    public User(){}
    public User(String nick, String password, String email, String name, String surname, String address, String card_number, float balance) {
        super();
        this.nick = nick;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.card_number = card_number;
        this.balance = balance;
    }
    @Override
    public String toString() {
        return "User: [id=" + id + ", nick=" + nick + ", password=" + password + ", email=" + email + ", name=" + name + ", surname=" + surname + ", address=" + address + ", card_number=" + card_number + ", balance=" + balance +" ]";
    }
    //Metoda zwracająca opis usera
    public String getNick() { return nick; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getAddress() { return address; }
    public String getCard_number() { return card_number; }
    public float getBalance() { return balance; }
    public void setId(int id) { this.id=id; }
}

