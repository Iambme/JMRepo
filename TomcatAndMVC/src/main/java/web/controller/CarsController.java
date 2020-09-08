package web.controller;

import model.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import service.CarServiceImp;


import java.util.List;

@Controller
public class CarsController {
    private CarServiceImp carServiceImp = new CarServiceImp();

    @GetMapping(value = "/cars")
    public String printWelcome(ModelMap model) {
        List<Car> cars = carServiceImp.getCars();
        model.addAttribute("cars", cars);
        return "cars";
    }
}
