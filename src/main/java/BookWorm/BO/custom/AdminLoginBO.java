package BookWorm.BO.custom;

import BookWorm.BO.SuperBO;
import BookWorm.DTO.AdminDto;

import java.io.IOException;
import java.util.List;

public interface AdminLoginBO extends SuperBO {
    List<AdminDto> GetAllAdmins() throws IOException;
}
