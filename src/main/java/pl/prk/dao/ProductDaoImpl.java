package pl.prk.dao;

import pl.prk.model.Product;
import pl.prk.util.ConnectionProvider;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao{

    private final String CREATE_PRODUCT = "INSERT INTO products (name, listId, bought, addedBy, count) VALUES (?,?,?,?,?)";
    private final String GET_PRODUCT_BY_LISTID = "SELECT * FROM products WHERE listId=?";
    private final String DELETE_PRODUCT = "DELETE FROM products WHERE id = ?";
    private final String UPDATE_PRODUCT = "UPDATE products set name = ?, count =? WHERE id = ?";
    private final String CHANGE_BOUGHT_PRODUCT = "UPDATE products set bought = ? WHERE id = ?";
    private final DataSource dataSource;

    public ProductDaoImpl() {
        this.dataSource = ConnectionProvider.getDataSource();
    }

    @Override
    public Product save(Product newObject) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_PRODUCT))
        {
            statement.setString(1,newObject.getName());
            statement.setInt(2, newObject.getListId());
            statement.setBoolean(3, newObject.isBought());
            statement.setString(4, newObject.getAddedBy());
            statement.setInt(5, newObject.getCount());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                newObject.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return newObject;
    }

    @Override
    public List<Product> getProductsById(Integer listId) {
        List<Product> products = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_PRODUCT_BY_LISTID, Statement.RETURN_GENERATED_KEYS))
        {
            statement.setInt(1, listId);
            ResultSet resultSet = statement.executeQuery();
            products = mapRow(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return products;
    }

    private List<Product> mapRow(ResultSet resultSet) throws SQLException {
        List<Product> products = new ArrayList<>();
        while (resultSet.next())
        {
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setBought(resultSet.getBoolean("bought"));
            product.setAddedBy(resultSet.getString("addedBy"));
            product.setListId(resultSet.getInt("listId"));
            product.setCount(resultSet.getInt("count"));
            products.add(product);
        }
        return products;
    }

    @Override
    public Product read(Integer primaryKey) {
        return null;
    }

    @Override
    public boolean update(Product updatedObject) {
        int rowUpdated = 0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT))
        {
            statement.setString(1, updatedObject.getName());
            statement.setInt(2, updatedObject.getCount());
            statement.setInt(3, updatedObject.getId());
            rowUpdated= statement.executeUpdate();
        }catch (SQLException e)
        {
            System.out.println("cath");
            System.out.println(e.getMessage());
            return false;
        }

        if (rowUpdated>0) return true;
        return false;
    }

    @Override
    public boolean delete(Integer key) {
        int deletedRows =0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT))
        {
            statement.setInt(1,key);
            deletedRows = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("catch");
            System.out.println(e.getMessage());
            return false;
        }
        if (deletedRows>0) return true;
        return false;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public boolean changeBought(int productId, boolean bought) {
        int rowUpdated = 0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(CHANGE_BOUGHT_PRODUCT))
        {
            statement.setBoolean(1, bought);
            statement.setInt(2, productId);
            rowUpdated= statement.executeUpdate();
        }catch (SQLException e)
        {
            System.out.println("cath");
            System.out.println(e.getMessage());
            return false;
        }

        if (rowUpdated>0) return true;
        return false;
    }
}
