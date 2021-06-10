package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();


    public void addStock(int quantity) {
        this.stockQuantity += quantity;


    }

    public void removeStock(int quantity) {
        int resultStock = this.stockQuantity - quantity;
        if (resultStock < 0) {
            throw new NotEnoughStockException("need more stock");

        }
        this.stockQuantity= resultStock;

    }
}
