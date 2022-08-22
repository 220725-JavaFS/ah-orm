package rev.orm.daos;

import java.util.List;

public interface ObjectDAO {

	public abstract void storeObject(Object obj);
	
	public abstract Object getByContentByColumnNum(String content, int columnNum, Object obj);
	
	public abstract List<Object> getAll(Object obj);
	
	
}
