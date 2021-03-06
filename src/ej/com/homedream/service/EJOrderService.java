package ej.com.homedream.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.homedream.comm.DBConnection;


import ej.com.homedream.dao.EJOrderDAO;
import ej.com.homedream.dto.EJOrderDTO;




public class EJOrderService {

	private static EJOrderService service=new EJOrderService();
	public static EJOrderService getService() {
		
		return service;
	}//싱글톤
	
	private EJOrderService() {}
	//////////////////////////////////////////////////////////////
	public int getCount(String search, String txtsearch, int txtsearch1, int txtsearch2)
	{//connection만들어서 dao로 넘겨
		
		DBConnection db=DBConnection.getInstance();//dbconnection꺼 받아
		Connection conn=null;
		int count=0;
		try{
			conn=db.getConnection();
			conn.setAutoCommit(false);
			EJOrderDAO dao=EJOrderDAO.getDAO();//싱글톤햇으니까 이렇게
			count=dao.orderCount(conn, search, txtsearch, txtsearch1, txtsearch2);
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
	}//전체 자료수
	
	public List<EJOrderDTO> getList(int startrow, int endrow, String search, String txtsearch
			, int stxtsearch1, int stxtsearch2) {//출력
		Connection conn=null;
		List<EJOrderDTO> list=null;
				
		try {
			DBConnection db=DBConnection.getInstance();
			
			conn=db.getConnection();
			conn.setAutoCommit(false);
			
			EJOrderDAO dao=EJOrderDAO.getDAO();
			list=dao.getlist(conn,startrow, endrow, search, txtsearch, stxtsearch1, stxtsearch2);
			//list를 리턴을 받아줫을때
			
			conn.commit();
			
		}catch(NamingException|SQLException e)
		{
			System.out.println(e);
			try {conn.rollback();}catch(Exception e2) {}
		}finally {
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}return list;
		
	}
	

	public void delete(int orderno) {
		Connection conn=null;
	
				
		try {
			DBConnection db=DBConnection.getInstance();
			
			conn=db.getConnection();
			conn.setAutoCommit(false);
			
			EJOrderDAO dao=EJOrderDAO.getDAO();
			
			dao.delete(conn,orderno);
			//list를 리턴을 받아줫을때
			
			conn.commit();
			
		}catch(NamingException|SQLException e)
		{
			System.out.println(e);
			try {conn.rollback();}catch(Exception e2) {}
		}finally {
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		
	}

		public void update(int orderno, int status) {
		Connection conn=null;
		
		
		try {
			DBConnection db=DBConnection.getInstance();
			
			conn=db.getConnection();
			conn.setAutoCommit(false);
			
			EJOrderDAO dao=EJOrderDAO.getDAO();
			dao.update(conn,orderno, status);
			//list를 리턴을 받아줫을때
			
			conn.commit();
			
		}catch(NamingException|SQLException e)
		{
			System.out.println(e);
			try {conn.rollback();}catch(Exception e2) {}
		}finally {
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		
		
	}
	
		
	}
	


