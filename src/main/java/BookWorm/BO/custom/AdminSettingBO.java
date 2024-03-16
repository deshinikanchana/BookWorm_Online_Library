package BookWorm.BO.custom;

import BookWorm.BO.SuperBO;
import BookWorm.projection.AdminProjection;

import java.io.IOException;
import java.util.List;

public interface AdminSettingBO extends SuperBO {
    List<AdminProjection> getAdminProjection() throws IOException;

}
