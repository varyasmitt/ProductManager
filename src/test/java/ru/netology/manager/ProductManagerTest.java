package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);

    Product first = new Book(1, "Анна Каренина", 1000, "Толстой");
    Product second = new Book(2, "Война и мир", 1100, "Толстой");
    Product third = new Book(3, "Человек футляр", 800, "Чехов");
    Product fourth = new Smartphone(4, "Apple", 60_000, "IND");
    Product fifth = new Smartphone(5, "Samsung", 53_000, "RTF");

    @BeforeEach
    public void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(fifth);
    }

    @Test
    public void searchByName() {

        Product[] expected = new Product[]{third};
        Product[] actual = manager.searchBy("Человек футляр");
        assertArrayEquals(expected, actual);
    }

    @Test
   public void searchMatchesNameBook() {

        Product[] expected = new Product[]{second};
        Product[] actual = manager.searchBy("Война и мир");
        assertArrayEquals(expected, actual);
    }

    @Test
   public void searchMatchesAuthor() {

        Product[] expected = new Product[]{third};
        Product[] actual = manager.searchBy("Чехов");
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchMatchesManufacture() {


        Product[] expected = new Product[]{fifth};
        Product[] actual = manager.searchBy("RTF");
        assertArrayEquals(expected, actual);
    }

    @Test
   public void searchMatchesNameSmartphone() {

        Product[] expected = new Product[]{fourth};
        Product[] actual = manager.searchBy("Apple");
        assertArrayEquals(expected, actual);
    }

    //    поиск всех книг одного автора
    @Test
   public void searchAllByAuthor() {

        Product[] expected = new Product[]{second, first};
        Product[] actual = manager.searchBy("Толстой");
        assertArrayEquals(expected, actual);
    }

    //    Запрос, на который нет ответа
    @Test
  public void searchAll() {

        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("null");
        assertArrayEquals(expected, actual);
    }


}