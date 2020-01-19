package ej.com.homedream.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import ej.com.homedream.dto.MemberDTO;

public class MemberDAO {
	
	private static MemberDAO dao=new MemberDAO();
	public static MemberDAO getDAO() {
		
		return dao;
	}//싱글톤
	private MemberDAO() {}
///////////////////////////////////////////////////////////////////////////////////////////////////

	public int memberCount(Connection conn, String search, String txtsearch
			, int stxtsearch1, int stxtsearch2) throws SQLException {//자료수 리턴
		StringBuilder sql=new StringBuilder();
		int count=0;
		ResultSet rs=null;
		
		sql.append(" select count(*) ");
		sql.append(" from member     ");
		sql.append(" where mem_no!=1 ");
		//검색
		if(!search.equals("")&&!txtsearch.equals(""))
		{
			if(search.equals("id"))
				sql.append(" and id like ? ");
			else if(search.equals("name"))
				sql.append(" and name like ? ");
		}
		//금액검색 
		if(stxtsearch1!=0&&stxtsearch2!=0)
		{
			sql.append(" and total between ? and ? ");
		}
		
	
		try(PreparedStatement pstmt=conn.prepareStatement(sql.toString());){
			if(!search.equals("")&&!txtsearch.equals(""))
			{
			pstmt.setString(1,"%"+txtsearch+"%");//txtsearch를 포함하는
				
			}else//검색안할때
			{
				if(stxtsearch1!=0&&stxtsearch2!=0)//금액검색하면
				{
					pstmt.setInt(1, stxtsearch1);
					pstmt.setInt(2, stxtsearch2);
				}
			}
			
			
			 rs=pstmt.executeQuery();
			 if(rs.next()) {
					count=rs.getInt(1);//첫번째꺼가져와
			 }
		
		}
		finally {
			if(rs!=null)try {rs.close();}catch(SQLException e) {}
		}
		return count;
	}
//////////////////////////////////////////////////////////////////////////////
	public List<MemberDTO> getlist(Connection conn, int startrow, int endrow, String search, String txtsearch
			, int stxtsearch1, int stxtsearch2) throws SQLException {//리스트출력
		
		List<MemberDTO> list=new ArrayList<>();
		StringBuilder sql=new StringBuilder();
		ResultSet rs=null;
		
		sql.append(" select *  from member ");
		
		
		
		
		if(!search.equals("")&&!txtsearch.equals(""))//검색을 하면
		{	
			if(search.equals("id"))
				sql.append(" where id like ? ");
			else if(search.equals("name"))
				sql.append(" where name like ? ");
		}
		if(stxtsearch1!=0&&stxtsearch2!=0)//금액검색하면
		{
			sql.append(" and total between ? and ? ");
		}
		
	
			sql.append(" limit ?,15 ");
		try(PreparedStatement pstmt=conn.prepareStatement(sql.toString());){
		
		if(!search.equals("")&&!txtsearch.equals(""))//검색하면
		{
			pstmt.setString(1, "%"+txtsearch+"%");
			pstmt.setInt(2, startrow);
			
		}
		else if(stxtsearch1!=0&&stxtsearch2!=0)//금액검색하면
		{
			pstmt.setInt(1, stxtsearch1);
			pstmt.setInt(2, stxtsearch2);
			pstmt.setInt(3, startrow);
		}
		else{//존재하지않으면 또는 검색안하면
			pstmt.setInt(1, startrow);
		}
		
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				MemberDTO dto=new MemberDTO();
				dto.setMemNo(rs.getInt("mem_no"));
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
				dto.setBirth(rs.getString("birth"));
				dto.setPhone(rs.getString("phone"));
				dto.setAddr(rs.getString("addr"));
				dto.setZipcode(rs.getInt("zipcode"));
				dto.setTotal(rs.getInt("total"));
				
				list.add(dto);
			}
	}finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
		}
		return list;
	}

}
