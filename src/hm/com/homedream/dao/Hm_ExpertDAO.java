package hm.com.homedream.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import hm.com.homedream.dto.Hm_ExpertDTO;

public class Hm_ExpertDAO {

	private static Hm_ExpertDAO dao = new Hm_ExpertDAO();
	
	public static Hm_ExpertDAO getDAO()
	{
		return dao;
	}
	private Hm_ExpertDAO() {}
	public void dataInsert(Connection conn, Hm_ExpertDTO dto) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		sql.append("  insert into expert(                            ");
		sql.append("          name                                  ");
		sql.append("          ,text                                  ");
		sql.append("          ,place                                 ");
		sql.append("          ,lat                                   ");
		sql.append("          ,lon                                   ");
		sql.append("          ,file_name                             ");
		sql.append("          ,loc      )                            ");
		sql.append("         values(?,?,?,?,?,?,?)                   ");
		try {
			pstmt = conn.prepareStatement(sql.toString());	
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getText());
			pstmt.setString(3, dto.getPlace());
			pstmt.setString(4, dto.getLat());
			pstmt.setString(5, dto.getLon());
			pstmt.setString(6, dto.getFile_name());
			pstmt.setString(7, dto.getLoc());
			pstmt.executeUpdate();
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
		}
	}
	public List<Hm_ExpertDTO> getList(Connection conn,int currpage , int pagepercount ,String search) throws SQLException {
	
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		List<Hm_ExpertDTO> list = new ArrayList<Hm_ExpertDTO>();
		sql.append("   select                                               ");
		sql.append("                             no                         ");
		sql.append("                             ,name                      ");
		sql.append("                             ,text                      ");
		sql.append("                             ,place                     ");
		sql.append("                             ,lat                       ");
		sql.append("                             ,lon                       ");
		sql.append("                             ,file_name                 ");
		sql.append("                             ,loc                       ");
		sql.append("   from  expert                                         ");
		if(!search.equals(""))
		{
			if(search.equals("����"))
	    sql.append("    where place like?                                     ");
			else if(search.equals("����"))
		sql.append("     where place like?                                    ");
			else if(search.equals("����"))
		sql.append("     where place like?                                    ");
			//�׽�Ʈ�� -> �� �κ� ���߿� ��ĥ �� 
		}
		sql.append("     limit ?, ?                                           ");
		
		
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql.toString());
			
			if(!search.equals(""))
			{
				pstmt.setString(1, search);
				pstmt.setInt(2, (currpage-1)*pagepercount+1);
				pstmt.setInt(3, pagepercount);
			}else {
				pstmt.setInt(1, (currpage-1)*pagepercount+1);
				pstmt.setInt(2, pagepercount);
			}
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Hm_ExpertDTO dto = new Hm_ExpertDTO();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setText(rs.getString("text"));
				dto.setPlace(rs.getString("place"));
				dto.setLat(rs.getString("lat"));
				dto.setLon(rs.getString("lon"));
				dto.setFile_name(rs.getString("file_name"));
				dto.setLoc(rs.getString("loc"));
				list.add(dto);
			}
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(rs!=null)try {rs.close();}catch(SQLException e) {}
		}
		return list;
	}
	public int getCount(Connection conn, String search) throws SQLException{
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		sql.append("  select    count(*)       ");
		sql.append("  from     expert          ");
		if(!search.equals(""))
		{
			if(search.equals("����"))
				sql.append("   where place like?    ");
			else if(search.equals("����"))
				sql.append("   where place like?    ");
			else if(search.equals("����"))
				sql.append("   where place like?    ");
		}
		
		int count = 0;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql.toString());
			
			if(!search.equals(""))
			{
				pstmt.setString(1, search);
			}
			
			rs=pstmt.executeQuery();
			
			if(rs.next())
			{
				count = rs.getInt(1);
			}
		}finally {
		if(rs!=null)try {rs.close();}catch(SQLException e) {}
		if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
		}
		return count;
	}
	
	
	
}
