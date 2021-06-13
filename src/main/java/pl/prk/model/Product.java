package pl.prk.model;
/**
 * The product class.
 *
 * @author Paweł Urbanik, Radosław Szajdak
 */
public class Product {

    private Integer id;
    private String name;
    private Integer listId;
    private boolean bought;
    private String addedBy;
    private int count;

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
