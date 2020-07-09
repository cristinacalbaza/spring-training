package ro.msg.learning.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.dto.ProductDto;
import ro.msg.learning.dto.StockDto;
import ro.msg.learning.model.Customer;
import ro.msg.learning.model.Location;
import ro.msg.learning.model.ProductCategory;
import ro.msg.learning.model.Supplier;
import ro.msg.learning.service.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Profile("TEST")
public class PopulatingController {

    private final ProductService productService;
    private final StockService stockService;
    private final CustomerService customerService;
    private final LocationService locationService;
    private final ProductCategoryService productCategoryService;
    private final SupplierService supplierService;
    private final OrderService orderService;

    @PostMapping("/addProduct")
    public void addProduct(@RequestBody ProductDto productDto){
        productService.save(productDto);
    }

    @DeleteMapping("/deleteAllProducts")
    public void deleteProducts(){
        productService.deleteAll();
    }

    @PostMapping("/addStock")
    public void addStock(@RequestBody StockDto stockDto){
        stockService.save(stockDto);
    }

    @DeleteMapping("/deleteAllStocks")
    public void deleteStocks(){
        stockService.deleteAll();
    }

    @PostMapping("/addCustomer")
    public void addCustomer(@RequestBody Customer customer){
        customerService.save(customer);
    }

    @DeleteMapping("/deleteCustomers")
    public void deleteCustomers(){
        customerService.deleteAll();
    }

    @PostMapping("/addLocation")
    public void addLocation(@RequestBody Location location){
        locationService.save(location);
    }

    @DeleteMapping("/deleteLocations")
    public void deleteLocations(){
        locationService.deleteAll();
    }

    @PostMapping("/addProductCategory")
    public void addProductCategory(@RequestBody ProductCategory productCategory){
        productCategoryService.save(productCategory);
    }

    @DeleteMapping("/deleteProductCategories")
    public void deleteProductCategories(){
        productCategoryService.deleteAll();
    }

    @PostMapping("/addSupplier")
    public void addSupplier(@RequestBody Supplier supplier){
        supplierService.save(supplier);
    }

    @DeleteMapping("/deleteSuppliers")
    public void deleteSuppliers(){
        supplierService.deleteAll();
    }

    @GetMapping("/productCategories")
    public List<ProductCategory> getAllCategoris(){
        return productCategoryService.getAll();
    }

    @DeleteMapping("/deleteOrders")
    public void deleteOrders(){
        orderService.deleteAll();
    }
}
