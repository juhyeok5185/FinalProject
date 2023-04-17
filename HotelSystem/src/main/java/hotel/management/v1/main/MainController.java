package hotel.management.v1.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hotel")
public class MainController {
    
    @GetMapping("/main")
    public void viewMain(){}

    @GetMapping("/contact")
    public void contact(){}
    
    @GetMapping("/hotelintroduce")
    public void hotelIntroduce(){}
    
    @GetMapping("/hotelstay")
    public void hotelStay(){}
    
    @GetMapping("/internalfacilities")
    public void internalFacilities(){}
    
    @GetMapping("/report")
    public void report(){}
    
    @GetMapping("/luxuryhall")
    public void luxuryHall(){}
    
    @GetMapping("/howtocome")
    public void howToCome(){}
    
    @GetMapping("/test")
    public void index(){}
}
