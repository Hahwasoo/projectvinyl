package ej.com.homedream.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.homedream.comm.DBConnection;

import ej.com.homedream.dao.MemberDAO;
import ej.com.homedream.dto.MemberDTO;



public class MemberService {

	private static MemberService service=new MemberService();
	public static MemberService getService() {
		
		return service;
	}//�̱���
	
	private MemberService() {}
	//////////////////////////////////////////////////////////////
	public int getCount(String search, String txtsearch)
	{//connection���� dao�� �Ѱ�
		
		DBConnection db=DBConnection.getInstance();//dbconnection�� �޾�
		Connection conn=null;
		int count=0;
		try{
			conn=db.getConnection();
			conn.setAutoCommit(false);
			MemberDAO dao=MemberDAO.getDAO();//�̱��������ϱ� �̷���
			count=dao.memberCount(conn, search, txtsearch);
			//System.out.println("count: "+count);
			conn.commit();
		}catch(NamingException|SQLException e)
		{
			System.out.println(e);
			try {conn.rollback();}catch(Exception e2) {}
		}finally {
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		return count;
	}//��ü �ڷ��
	
	public List<MemberDTO> getList(int startrow, int endrow, String search, String txtsearch) {//���
		Connection conn=null;
		List<MemberDTO> list=null;
				
		try {
			DBConnection db=DBConnection.getInstance();
			
			conn=db.getConnection();
			conn.setAutoCommit(false);
			
			MemberDAO dao=MemberDAO.getDAO();
			list=dao.getlist(conn,startrow, endrow, search, txtsearch);
			//list�� ������ �޾ƢZ����
			
			conn.commit();
			
		}catch(NamingException|SQLException e)
		{
			System.out.println(e);
			try {conn.rollback();}catch(Exception e2) {}
		}finally {
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}return list;
		
	}
	/*public List<MemberDTO> getselectedList() {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	

}
