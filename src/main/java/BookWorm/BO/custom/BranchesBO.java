package BookWorm.BO.custom;

import BookWorm.BO.SuperBO;
import BookWorm.DTO.BranchDto;

import java.io.IOException;
import java.util.List;

public interface BranchesBO extends SuperBO {
     int SaveBranch(BranchDto dto) throws IOException;
    BranchDto GetBranch(int id) throws IOException;
    boolean UpdateBranch(BranchDto dto) throws IOException;
    boolean DeleteBranch(BranchDto dto) throws IOException;
    List<BranchDto> GetAllBranches() throws IOException;
}
