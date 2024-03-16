package BookWorm.DAO;

import BookWorm.DAO.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){
    }
    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)?daoFactory
                =new DAOFactory():daoFactory;
    }

    public enum DAOTypes {
        ADMIN,BOOK,BRANCH,USER,TRANSACTION
    }
    public SuperDAO getDAO(DAOFactory.DAOTypes daoTypes){
        switch (daoTypes){

            case ADMIN:
                return new AdminDAOimpl();
            case BOOK:
                return new BookDAOimpl();
            case BRANCH:
                return new BranchDAOimpl();
            case USER:
                return new UserDAOimpl();
            case TRANSACTION:
                return new TransactionDAOimpl();
            default:
                return null;
        }
    }

}
