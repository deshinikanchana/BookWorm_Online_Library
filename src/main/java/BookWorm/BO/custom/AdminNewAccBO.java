package BookWorm.BO.custom;

import BookWorm.BO.SuperBO;
import BookWorm.DTO.AdminDto;

import java.io.IOException;

public interface AdminNewAccBO extends SuperBO {
    int SaveAdmin(AdminDto admin) throws IOException;

}
