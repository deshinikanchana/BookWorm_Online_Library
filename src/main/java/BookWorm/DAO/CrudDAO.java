package BookWorm.DAO;

import java.io.IOException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    int  Save(T dto) throws IOException;
    T Get(int id) throws IOException;
    boolean Update(T dto) throws IOException;
    boolean Delete(T dto) throws IOException;
    List<T> GetAll() throws IOException;
}
