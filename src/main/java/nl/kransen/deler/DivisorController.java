package nl.kransen.deler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DivisorController {

    @GetMapping("/divisor")
    public DivisorResponse handle() {
        long before = System.currentTimeMillis();
        long firstDivisor = Divisor.create(25).firstMatch();
        long duration = System.currentTimeMillis() - before;
        return new DivisorResponse(firstDivisor, duration);
    }
}
