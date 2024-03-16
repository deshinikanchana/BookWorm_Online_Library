package BookWorm.BO.impl;

import BookWorm.BO.custom.BranchesBO;
import BookWorm.DAO.DAOFactory;
import BookWorm.DAO.custom.BranchDAO;
import BookWorm.DAO.custom.impl.BranchDAOimpl;
import BookWorm.DTO.BranchDto;
import BookWorm.Entity.Branch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BranchesBOimpl implements BranchesBO {

    BranchDAO br = (BranchDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BRANCH);
    @Override
    public int SaveBranch(BranchDto dto) throws IOException {
      return br.Save(new Branch(dto.getBranchId(),dto.getAddress(),dto.getEmail(),dto.getAdmin()));
    }

    @Override
    public BranchDto GetBranch(int id) throws IOException {
        Branch branch = br.Get(id);
        return new BranchDto(branch.getBranchId(),branch.getAddress(),branch.getEmail(),branch.getAdmin(),branch.getBookList());
    }

    @Override
    public boolean UpdateBranch(BranchDto dto) throws IOException {
        return br.Update(new Branch(dto.getBranchId(),dto.getAddress(),dto.getEmail(),dto.getAdmin()));
    }

    @Override
    public boolean DeleteBranch(BranchDto dto) throws IOException {
        return br.Delete(new Branch(dto.getBranchId(),dto.getAddress(),dto.getEmail(),dto.getAdmin()));
    }

    @Override
    public List<BranchDto> GetAllBranches() throws IOException {
        List<BranchDto> list = new ArrayList<>();
        for(Branch branch:br.GetAll()){
            list.add(new BranchDto(branch.getBranchId(),branch.getAddress(), branch.getEmail(),branch.getAdmin(),branch.getBookList()));
        }
        return list;
    }
}
