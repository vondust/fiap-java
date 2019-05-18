package br.com.fiap.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(customerService.findById(id));
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerCreateReponse> createCustomer(@RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.status(CREATED).body(customerService.create(customerRequest));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerException(HttpServerErrorException hsee) {
        return ResponseEntity.status(hsee.getStatusCode()).body(new ErrorResponse(hsee.getStatusText()));
    }
}
