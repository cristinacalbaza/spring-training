package ro.msg.learning.service.exception;

public class ProductNotFoundException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    public ProductNotFoundException(Integer id){
        super("Could not find product with id: " + id);
    }
}
