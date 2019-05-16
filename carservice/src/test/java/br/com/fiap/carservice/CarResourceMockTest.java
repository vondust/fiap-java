package br.com.fiap.carservice;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CarResourceMockTest {

    @Mock
    private CarResource carResource;

    @Test
    public void shouldFindCarByColor(){

        //Stub
        String color = "azul";
        String model = "fusca";

        Car car = new Car();
        car.setColor(color);
        car.setModel(model);

        List<Car> cars = Arrays.asList(car);

        // when -> then
        Mockito.when(carResource.findCarByColor(color)).thenReturn(cars);

        // execute
        List<Car> carsResponse = carResource.findCarByColor(color);

        assertEquals(carsResponse.get(0).getColor(), color);
        assertEquals(carsResponse.get(0).getModel(), model);

    }


}
