package pl.prk.model;

import java.io.Serializable;
import java.util.Objects;

public class SharedList implements Serializable {

    private Integer id;
    private Integer listId;
    private Integer ownerId;
    private Integer userId;
    private boolean updateList;
    private boolean addingProducts;
    private boolean updateProducts;
    private boolean changingState;
    private boolean deleteProducts;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean isUpdateList() {
        return updateList;
    }

    public void setUpdateList(boolean updateList) {
        this.updateList = updateList;
    }

    public boolean isAddingProducts() {
        return addingProducts;
    }

    public void setAddingProducts(boolean addingProducts) {
        this.addingProducts = addingProducts;
    }

    public boolean isUpdateProducts() {
        return updateProducts;
    }

    public void setUpdateProducts(boolean updateProducts) {
        this.updateProducts = updateProducts;
    }

    public boolean isChangingState() {
        return changingState;
    }

    public void setChangingState(boolean changingState) {
        this.changingState = changingState;
    }

    public boolean isDeleteProducts() {
        return deleteProducts;
    }

    public void setDeleteProducts(boolean deleteProducts) {
        this.deleteProducts = deleteProducts;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SharedList that = (SharedList) o;
        return updateList == that.updateList && addingProducts == that.addingProducts && updateProducts == that.updateProducts && changingState == that.changingState && deleteProducts == that.deleteProducts && Objects.equals(id, that.id) && Objects.equals(listId, that.listId) && Objects.equals(ownerId, that.ownerId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, listId, ownerId, userId, updateList, addingProducts, updateProducts, changingState, deleteProducts);
    }
}
