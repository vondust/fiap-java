package br.com.fiap.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.stream.Stream;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerResponse findById(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(NOT_FOUND, "Customer Not Found"));

        CustomerResponse response = new CustomerResponse();
        response.setName(customer.getName());
        response.setLastName(customer.getLastName());
        response.setGender(customer.getGender());
        response.setAge(customer.getAge());

        return response;
    }

    @Override
    public CustomerCreateReponse create(CustomerRequest customerRequest) {
        validateGender(customerRequest.getGender());

        Customer customer = new Customer();
        customer.setName(customerRequest.getName());
        customer.setLastName(customerRequest.getLastname());
        customer.setGender(customerRequest.getGender());
        customer.setAge(customerRequest.getAge());

        Customer createdCustomer = customerRepository.save(customer);
        CustomerCreateReponse customerCreateReponse = new CustomerCreateReponse();
        customerCreateReponse.setCustomerId(createdCustomer.getId());

        return customerCreateReponse;
    }

    private void validateGender(String gender) {
        Stream.of("MALE", "FEMALE")
                .filter(s -> s.equals(gender.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new HttpClientErrorException(UNPROCESSABLE_ENTITY, "Gender Is Invalid"));
    }
}
