package BookWorm.Repository;

import BookWorm.Config.SessionFactoryConfig;
import BookWorm.Entity.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class BranchRepository {

    private final Session session;

    public BranchRepository() throws IOException {
        session= SessionFactoryConfig
                .getInstance()
                .getSession();
    }

    public int  SaveBranch(Branch branch){
        Transaction transaction= session.beginTransaction();
        try {
            int branchId = (int) session.save(branch);
            transaction.commit();
            System.out.println("Branch save : " + branch.toString());
            session.close();
            return branchId;
        }catch (Exception ex){
            transaction.rollback();
            session.close();
            ex.printStackTrace();
            return -1;
        }
    }

    public Branch GetBranch(int id){
        try{
            Branch branch = session.get(Branch.class,id);
            session.close();
            return branch;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public boolean UpdateBranch(Branch branch){
        Transaction trans = session.beginTransaction();
        try{
            session.update(branch);
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

    public boolean DeleteBranch(Branch branch){
        Transaction delTrans = session.beginTransaction();
        try{
            session.delete(branch);
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

    //jpql - java persistance query language
    public List<Branch> getAllBranches(){
        String sql = "SELECT C FROM Branch As C";
        Query query = session.createQuery(sql);
        List list = query.list();
        session.close();
        return list;

    }
}
