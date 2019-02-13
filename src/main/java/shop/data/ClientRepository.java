package shop.data;

import org.springframework.data.repository.CrudRepository;
import shop.domens.Customer;

public interface ClientRepository extends CrudRepository<Customer,Long> {
}
