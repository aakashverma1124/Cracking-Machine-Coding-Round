package in.innoskrit.service;

import in.innoskrit.exception.CustomerNotFoundException;
import in.innoskrit.model.Customer;
import in.innoskrit.repository.CustomerRepository;

import java.util.HashMap;

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void addCustomer(String email, String name) {
        customerRepository.addCustomer(email, name);
    }

    public Customer getCustomerByEmail(String email) {
        try {
            return customerRepository.getCustomerByEmail(email);
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
