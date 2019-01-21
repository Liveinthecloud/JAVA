package service;

import java.util.List;
import testRPC.Product;


public interface ReadData {

	Product findProductById(int id) throws Exception;

	List<Product> findProductByName(String keyword) throws Exception;

}
