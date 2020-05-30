package ro.msg.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.model.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {

}
