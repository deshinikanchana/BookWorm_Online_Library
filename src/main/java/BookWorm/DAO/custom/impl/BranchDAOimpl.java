package BookWorm.DAO.custom.impl;

import BookWorm.Config.SessionFactoryConfig;
import BookWorm.DAO.custom.BranchDAO;
import BookWorm.DTO.BranchDto;
import BookWorm.Entity.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class BranchDAOimpl implements BranchDAO {
    @Override
    public int Save(Branch br) throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        Transaction transaction= session.beginTransaction();
        try {
            int branchId = (int) session.save(br);
            transaction.commit();
            System.out.println("Branch save : " + br.toString());
            session.close();
            return branchId;
        }catch (Exception ex){
            transaction.rollback();
            session.close();
            ex.printStackTrace();
            return -1;
        }
    }

    @Override
    public Branch Get(int id) throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        try{
            Branch branch = session.get(Branch.class,id);
            session.close();
            return branch;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean Update(Branch br) throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        Transaction trans = session.beginTransaction();
        try{
            session.update(br);
            trans.commit();
            session.close();
            return true;
        }catch (Exception e){
            trans.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean Delete(Branch br) throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        Transaction delTrans = session.beginTransaction();
        try{
            session.delete(br);
            delTrans.commit();
            session.close();
            return true;
        }catch (Exception e){
            delTrans.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Branch> GetAll() throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        String sql = "SELECT C FROM Branch As C";
        Query query = session.createQuery(sql);
        List list = query.list();
        session.close();
        return list;
    }

}
