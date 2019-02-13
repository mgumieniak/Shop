package shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.data.ClientRepository;
import shop.domens.Customer;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private ClientRepository clientRepository;

    @Autowired
    public CustomerServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> listCustomer = new ArrayList<>();
        clientRepository.findAll().forEach(client->listCustomer.add(client));
        return listCustomer;
    }
}
