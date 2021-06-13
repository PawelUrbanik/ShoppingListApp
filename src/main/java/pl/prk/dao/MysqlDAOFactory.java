package pl.prk.dao;
/**
 * The class returning the DAO implementation.
 *
 * @author Paweł Urbanik, Radosław Szajdak
 */
public class MysqlDAOFactory extends DaoFactory {

    @Override
    public ShoppingListDao getShoppingListDao() {
        return new ShoppingListDaoImpl();
    }
}
