package br.com.fiap.customer;

public interface CustomerService {

    CustomerResponse findById(Integer id);

    CustomerCreateReponse create(CustomerRequest customerRequest);
}
