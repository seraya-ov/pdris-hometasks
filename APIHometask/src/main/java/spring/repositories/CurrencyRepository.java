package spring.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.entities.Currency;

public interface CurrencyRepository extends CrudRepository<Currency, String> {}
