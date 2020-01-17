package hm.com.homedream.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.homedream.comm.DBConnection;

import hm.com.homedream.dao.Hm_ExpertDAO;
import hm.com.homedream.dto.Hm_ExpertDTO;

public class Hm_ExpertService {

	private static Hm_ExpertService service = new Hm_ExpertService();
	
	public static Hm_ExpertService getService()
	{
		return service;
	}
	private Hm_ExpertService() {}
	public void dataInsert(Hm_ExpertDTO dto) {
		// TODO Auto-generated method stub
		
		DBConnection db = DBConnection.getInstance();
		Connection conn = null;
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Hm_ExpertDAO dao = Hm_ExpertDAO.getDAO();
			dao.dataInsert(conn,dto);
			conn.commit();
		}catch(NamingException | SQLException e)
		{
			System.out.println(e);
			try {conn.rollback();}catch(SQLException e2) {}
		}finally {
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
	}
	
	
}