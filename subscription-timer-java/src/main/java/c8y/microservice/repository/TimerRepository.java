package c8y.microservice.repository;

import c8y.microservice.models.TimerModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimerRepository extends CrudRepository<TimerModel, String> {
}
