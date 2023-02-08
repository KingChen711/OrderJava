package lists;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import models.Product;

final public class ProductManagement {

  private static ProductManagement instance;

  private ProductManagement() {

  }

  public static ProductManagement getInstance() {
    if (instance == null) {
      instance = new ProductManagement();
    }
    return instance;
  }

  private ArrayList<Product> productList = readProductList();

  public void listAllProducts() {

    productList.forEach(product -> {
      System.out.println(product.toString());
    });

  }

  public ArrayList<Product> readProductList() {
    ArrayList<Product> products = new ArrayList<>();
    try {
      File file = new File("../resources/products.txt");
      System.out.println(file.getAbsolutePath());
      FileReader fr = new FileReader(file);
      BufferedReader br = new BufferedReader(fr);
      String line;

      while (true) {
        line = br.readLine();
        if (line == null) {
          break;
        }
        String[] texts = line.split(",");
        Product newProduct = new Product();
        newProduct.setId(texts[0]);
        newProduct.setName(texts[1]);
        newProduct.setUnit(texts[2]);
        newProduct.setOrigin(texts[3]);
        newProduct.setPrice(Double.parseDouble(texts[4]));
        products.add(newProduct);
      }
      br.close();
      fr.close();
    } catch (IOException | NumberFormatException e) {
      System.out.println(e);
    }
    return products;
  }
}
