package ro.msg.learning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.model.Location;
import ro.msg.learning.repository.LocationRepository;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public Location save(Location location){
        return locationRepository.save(location);
    }

    public void deleteAll(){
        locationRepository.deleteAll();
    }
}