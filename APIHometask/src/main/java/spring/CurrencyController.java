package spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import spring.entities.Currency;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyService currencyService;
    private final PredictService predictService;

    public CurrencyController(CurrencyService currencyService, PredictService predictService) {
        this.currencyService = currencyService;
        this.predictService = predictService;
    }

    @GetMapping("/{days}")
    ModelAndView getCurrency(@PathVariable int days) throws IOException {
        ArrayList<Currency> currencyHistory = currencyService.getCurrencyHistory(days);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("currency");
        modelAndView.addObject("currency", currencyHistory);
        return modelAndView;
    }

    @GetMapping({"", "/"})
    ModelAndView getCurrency() throws IOException {
        return getCurrency(1);
    }

    @GetMapping({"/predict"})
    ModelAndView predictCurrency() throws IOException {
        ArrayList<Currency> prediction = new ArrayList<>(Collections.singletonList(predictService.predictCurrency()));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("currency");
        modelAndView.addObject("currency", prediction);
        return modelAndView;
    }

}
