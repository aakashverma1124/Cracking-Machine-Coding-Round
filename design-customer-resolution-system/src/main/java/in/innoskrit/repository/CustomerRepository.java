package in.innoskrit.repository;

import in.innoskrit.exception.CustomerNotFoundException;
import in.innoskrit.model.Customer;
import in.innoskrit.service.AgentService;
import in.innoskrit.service.IAssignStrategy;

import java.util.HashMap;
import java.util.Map;

public class CustomerRepository {
    Map<String, Customer> customerMap;

    public CustomerRepository() {
        customerMap = new HashMap<>();
    }

    public void addCustomer(String email, String name) {
        Customer customer = new Customer(email, name);
        customerMap.put(customer.getCustomerEmail(), customer);
    }

    public Customer getCustomerByEmail(String email) {
        if(!customerMap.containsKey(email)) throw new CustomerNotFoundException("Customer Not Found");
        return customerMap.get(email);
    }


}
