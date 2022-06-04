package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.payload.CurrencyDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.CurrencyService;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;

    @PostMapping("/add")
    public Result addCurrency(@RequestBody CurrencyDTO currencyDTO) {
        return currencyService.addCurrency(currencyDTO);
    }

    @GetMapping("/get/all")
    public Result getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }

    @GetMapping("/get/{id}")
    public Result getOneCurrency(@PathVariable Integer id) {
        return currencyService.getOneCurrency(id);
    }

    @PutMapping("/edit/{id}")
    public Result editCurrency(@PathVariable Integer id, @RequestBody CurrencyDTO currencyDTO) {
        return currencyService.editCurrency(id, currencyDTO);
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteCurrency(@PathVariable Integer id) {
        return currencyService.deleteCurrency(id);
    }

}
