package BookWorm.DAO;

import BookWorm.Config.SessionFactoryConfig;
import BookWorm.DTO.BranchDto;
import BookWorm.Entity.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class BranchDAOimpl implements BranchDAO{
    @Override
    public int Save(BranchDto dto) throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        Transaction transaction= session.beginTransaction();
        try {
            int branchId = (int) session.save(dto);
            transaction.commit();
            System.out.println("Branch save : " + dto.toString());
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
    public BranchDto Get(int id) throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        try{
            Branch branch = session.get(Branch.class,id);
            session.close();
            BranchDto dto = new BranchDto(branch.getBranchId(),branch.getAddress(),branch.getEmail(),branch.getAdmin(),branch.getBookList());
            return dto;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean Update(BranchDto dto) throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        Transaction trans = session.beginTransaction();
        try{
            session.update(dto);
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
    public boolean Delete(BranchDto dto) throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        Transaction delTrans = session.beginTransaction();
        try{
            Branch branch = new Branch(dto.getBranchId(),dto.getAddress(),dto.getEmail(),dto.getAdmin(),dto.getBookList());
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

    @Override
    public List<BranchDto> GetAll() throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        String sql = "SELECT C FROM Branch As C";
        Query query = session.createQuery(sql);
        List list = query.list();
        session.close();
        return list;
    }

}
