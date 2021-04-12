package pl.prk.dao;

public class MysqlDAOFactory extends DaoFactory {

    @Override
    public ShoppingListDao getShoppingListDao() {
        return new ShoppingListDaoImpl();
    }
}
