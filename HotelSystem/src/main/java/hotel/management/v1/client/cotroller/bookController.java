package hotel.management.v1.client.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hotel")
public class bookController {
	@GetMapping("/client/roombook")
	public void book() {
	}
}
