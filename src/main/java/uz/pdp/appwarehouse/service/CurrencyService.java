package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.payload.CurrencyDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    public Result addCurrency(CurrencyDTO currencyDTO) {
        boolean existsByName = currencyRepository.existsByName(currencyDTO.getName());
        if (!existsByName)
            return new Result("Currency already added ...", false);
        Currency currency = new Currency();
        currency.setName(currency.getName());
        currency.setActive(currencyDTO.getActive());
        Currency save = currencyRepository.save(currency);
        return new Result("Currency added ...", true, save);
    }

    public Result getAllCurrencies() {
        List<Currency> all = currencyRepository.findAll();
        return new Result("All currensies ...", true, all);
    }

    public Result getOneCurrency(Integer id) {
        Optional<Currency> byId = currencyRepository.findById(id);
        if (!byId.isPresent())
            return new Result("Currency not found ...", false);
        return  new Result("Currency ...", true, byId.get());
    }

    public Result deleteCurrency(Integer id){
        Optional<Currency> byId = currencyRepository.findById(id);
        if (!byId.isPresent())
            return new Result("Currency not found ...", false);
        currencyRepository.deleteById(id);
        return new Result("Currency deleted ...", true);
    }


    public Result editCurrency(Integer id, CurrencyDTO currencyDTO){
        Optional<Currency> byId = currencyRepository.findById(id);
        if (!byId.isPresent())
            return new Result("Currency not found ...", false);
        Currency currency = new Currency();
        currency.setName(currency.getName());
        currency.setActive(currencyDTO.getActive());
        Currency editedCurrency = currencyRepository.save(currency);
        return new Result("Currency added ...", true, editedCurrency);
    }


}
