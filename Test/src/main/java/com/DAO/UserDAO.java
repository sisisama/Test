package com.DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.DTO.UserRequestDTO;
import com.DTO.UserResponseDTO;

@Service("UserDAO")
public class UserDAO {
	public static Connection con = null;
	static {
		con = ConnectionClass.myConnection();
	}
	
	 public String generateUsrId()
	  {
	    String usrId = null ;
	    int count = 0;
	    Connection con=ConnectionClass.myConnection();
	    try 
	    {
	    PreparedStatement ps=con.prepareStatement( "select count(*)+1 from user");
	    ResultSet rs=ps.executeQuery();
	    rs.next();
	    
	    count = rs.getInt(1);
	     
	    if (count < 10) {
	      usrId = "USR00"+ count;
	    } else if (count >= 10 && count < 100) {
	      usrId = "USR0"+ count;
	    } else if (count == 100) {
	      usrId= "USR"+ count;
	    }
	    
	  
	    } catch (SQLException e) {
	      System.out.println(e.getMessage());
	    }
	    return usrId;
	  }
		public int insertUser(UserRequestDTO dto) {
			int result = 0;
			String sql = "insert into user(user_id,user_name,user_email,user_password,user_role) values (?,?,?,?,?)";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				
				ps.setString(1, dto.getId());
				ps.setString(2, dto.getName());
				ps.setString(3, dto.getEmail());
				ps.setString(4, dto.getPas());
				ps.setString(5, dto.getRole());
				result = ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Insert Failed!");
			}
			return result;
		}
		public UserResponseDTO CheckUser(UserRequestDTO dto)
		{
			UserResponseDTO res = null;
			
			try 
			{
			Connection con= ConnectionClass.myConnection();
			PreparedStatement ps=con.prepareStatement("select * from user where user_name=? and user_password=?");
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getPas());
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) 
			{
				res = new UserResponseDTO();
				res.setName(rs.getString("user_name"));
				res.setEmail(rs.getString("user_email"));
				res.setRole(rs.getString("user_role"));
				res.setPas(rs.getString("user_password"));
			}
			} catch (SQLException e) 
			{
			System.out.println(e.getMessage());
			}
			return res;
		}
		public ArrayList<UserResponseDTO> ShowAllResult()
		{
			ArrayList<UserResponseDTO> list = new ArrayList();
			Connection con=ConnectionClass.myConnection();
			try 
			{
			PreparedStatement ps=con.prepareStatement("select * from user");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) 
			{
				UserResponseDTO res = new UserResponseDTO();
				res.setId(rs.getString("user_id"));
				res.setName(rs.getString("user_name"));
				res.setPas(rs.getString("user_password"));
				res.setEmail(rs.getString("user_email"));
				res.setRole(rs.getString("user_role"));	
				list.add(res);
			}
			} catch (SQLException e) {
			System.out.println(e.getMessage());
			}
			return list;
		}
		public int updateUser(UserRequestDTO dto) {
			int result = 0;
			String sql = "update user set user_name=?,user_email=?,user_password=?,user_role=? where user_id=?";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				
				ps.setString(1, dto.getName());
				ps.setString(2, dto.getEmail());
				ps.setString(3, dto.getPas());
				ps.setString(4, dto.getRole());
				ps.setString(5, dto.getId());
				
				result = ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Update Failed!");
			}
			return result;
		}
		public int deleteUser(UserRequestDTO dto) {
			int result = 0;
			String sql = "delete from user where user_id=?";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, dto.getId());
				result = ps.executeUpdate();
				if(result == 0) {
					
				}else {
					
				}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Delete Failed!");
			}
			return result;
		}
		public UserResponseDTO selectOne(UserRequestDTO dto) {
			UserResponseDTO res = new UserResponseDTO();
			String sql = "select * from user where user_id=?";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1,dto.getId());
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					res.setId(rs.getString("user_id"));
					res.setName(rs.getString("user_name"));
					res.setEmail(rs.getString("user_email"));
					res.setPas(rs.getString("user_password"));
					res.setRole(rs.getString("user_role"));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return res;
		}
		public ArrayList<UserResponseDTO> searchData(UserRequestDTO dto) {
			UserResponseDTO res = new UserResponseDTO();
			ArrayList<UserResponseDTO> list = new ArrayList();
			String sql = "select * from user where user_id=? or user_name=?";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(2, dto.getName());
				ps.setString(1, dto.getId());
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					res.setId(rs.getString("user_id"));
					res.setName(rs.getString("user_name"));
					list.add(res);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return list;
		}
}
