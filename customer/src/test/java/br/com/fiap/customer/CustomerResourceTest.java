package br.com.fiap.customer;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CustomerResourceTest {

    @Autowired
    private CustomerRepository customerRepository;

    @LocalServerPort
    private Integer port;

    @Before
    public void setup() {
        stubCreateCustomer();

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = this.port;
        RestAssured.requestSpecification = new RequestSpecBuilder()
            .addHeader("Content-Type", "application/json").build();
    }

    private void stubCreateCustomer() {
        Customer customer = new Customer();
        customer.setName("Raphael");
        customer.setLastName("Lagreca");
        customer.setGender("male");
        customer.setAge(25);

        customerRepository.save(customer);
    }

    @Test
    public void shouldFindCustomerById(){

        RestAssured.get("/customer/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", Matchers.is("Raphael"))
                .body("lastName", Matchers.is("Lagreca"))
                .body("gender", Matchers.is("male"))
                .body("age", Matchers.is(25));
    }

    @Test
    public void cannotFindCustomerById() {
        RestAssured.get("/customer/300")
                .then()
                .assertThat()
                .statusCode(404)
                .body("messageError", Matchers.is("Customer Not Found"));

    }

//    @Test
//    public void cannotFindCustomerWhenGenderIsInvalid() {
//        RestAssured.get("/customer/300")
//                .then()
//                .assertThat()
//                .statusCode(404)
//                .body("messageError", Matchers.is("Customer Not Found"));

//    }

    @Test
    public void shouldCreateCustomer() {
        CustomerRequest customerRequest = new CustomerRequest();

        customerRequest.setName("Raphael");
        customerRequest.setLastname("Lagreca");
        customerRequest.setGender("INVALID");
        customerRequest.setAge(25);

        RestAssured.given()
                .body(customerRequest)
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(422)
                .body("messageError", Matchers.is("Gender is Invalid"));



    }

//    @Test
//    public void shouldCreateCustomer() {
//
//
//    }
}
