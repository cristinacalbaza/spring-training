package ro.msg.learning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.model.Supplier;
import ro.msg.learning.repository.SupplierRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public Supplier save(Supplier supplier){
        return supplierRepository.save(supplier);
    }

    public List<Supplier> getAll(){
        return supplierRepository.findAll();
    }

    public void deleteAll(){
        supplierRepository.deleteAll();
    }
}
