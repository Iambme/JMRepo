package service;

import model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarServiceImp implements CarService {
    @Override
    public List<Car> getCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Audi", 8, 2010));
        cars.add(new Car("BMW", 6, 2014));
        cars.add(new Car("Lincoln", 7, 2017));
        cars.add(new Car("Subaru", 5, 2020));
        return cars;
    }
}
