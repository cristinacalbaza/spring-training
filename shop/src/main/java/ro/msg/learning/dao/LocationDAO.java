package ro.msg.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.model.Location;

public interface LocationDAO extends JpaRepository<Location, Integer> {

}
