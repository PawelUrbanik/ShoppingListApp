package pl.prk.dao;

import pl.prk.exception.NoSuchDbTypeExpetion;

public abstract class DaoFactory {
    public static final int MYSQL_DAO_FACTORY = 1;
    public abstract ShoppingListDao getShoppingListDao();


    public static DaoFactory getDAOFactory()
    {
        DaoFactory factory = null;
        try
        {
            factory = getDAOFactory(MYSQL_DAO_FACTORY);
        } catch (NoSuchDbTypeExpetion noSuchDbTypeExpetion) {
            noSuchDbTypeExpetion.printStackTrace();
        }
        return factory;
    }

    private static DaoFactory getDAOFactory(int type) throws NoSuchDbTypeExpetion
    {
        switch (type)
        {
            case MYSQL_DAO_FACTORY:
                return new MysqlDAOFactory();
            default:
                throw new NoSuchDbTypeExpetion();

        }
    }
}
