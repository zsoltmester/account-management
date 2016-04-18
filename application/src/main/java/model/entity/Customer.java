package model.entity;

import java.util.List;

/**
 * The customer entity.
 */
public class Customer {

    private long id;

    private String name;

    private String address;

    private String phone;

    private List<Account> accounts;

    /**
     * Creates a new customer based on the given params.
     *
     * @param id       The ID of the customer.
     * @param name     The name of the customer.
     * @param address  The address of the customer.
     * @param phone    The phone number of the customer.
     * @param accounts The accounts of the customer.
     */
    public Customer(long id, String name, String address, String phone, List<Account> accounts) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.accounts = accounts;
    }

    /**
     * Returns the ID of this customer.
     *
     * @return The ID of this customer.
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the name of this customer.
     *
     * @return The name of this customer.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the address of this customer.
     *
     * @return The address of this customer.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the phone number of this customer.
     *
     * @return The phone number of this customer.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Returns the accounts of this customer.
     *
     * @return The accounts of this customer.
     */
    public List<Account> getAccounts() {
        return accounts;
    }
}
