package sem.nik.demo.entiities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private int price;
    @Column(name = "count")
    private int countViews;



    public Product(Long id, String title, int price, int countViews) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.countViews=countViews;
    }



    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", countViews=" + countViews +
                '}';
    }
}
