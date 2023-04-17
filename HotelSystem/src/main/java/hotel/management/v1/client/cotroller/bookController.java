package hotel.management.v1.client.cotroller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hotel")
public class bookController {
	@GetMapping("/client/roombook")
	public void bookpage() {
	}
	
	@PostMapping("/client/roombook")
	public void roombook(Principal pal) {
		String username =  pal.getName();
		
	}
}
