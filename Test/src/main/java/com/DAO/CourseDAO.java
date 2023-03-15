package com.DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.DTO.CourseRequestDTO;
import com.DTO.CourseResponseDTO;



@Service("CourseDAO")
public class CourseDAO {
	public static Connection con = null;
	static {
		con = ConnectionClass.myConnection();
	}
	 public String generateCId()
	  {
	    String CId = null ;
	    int count = 0;
	    Connection con=ConnectionClass.myConnection();
	    try 
	    {
	    PreparedStatement ps=con.prepareStatement( "select count(*)+1 from course");
	    ResultSet rs=ps.executeQuery();
	    rs.next();
	    
	    count = rs.getInt(1);
	     
	    if (count < 10) {
	     CId = "COR00"+ count;
	    } else if (count >= 10 && count < 100) {
	      CId = "COR0"+ count;
	    } else if (count == 100) {
	      CId= "COR"+ count;
	    }
	    
	  
	    } catch (SQLException e) {
	      System.out.println(e.getMessage());
	    }
	    return CId;
	  }
	public int insertCourse(CourseRequestDTO dto)
	{
		int result = 0;
		Connection con = ConnectionClass.myConnection();
		try
		{
			PreparedStatement ps = con.prepareStatement("insert into course(course_id,course_name) values(?,?)");
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getName());
			result = ps.executeUpdate();
		}catch (SQLException e) 
		{
				System.out.println(e.getMessage());
		}
		{
				System.out.println("Rest of code");
		}
		return result;
	}
	public ArrayList<CourseResponseDTO> ShowCourse()
	{
		ArrayList<CourseResponseDTO> list = new ArrayList();
		Connection con=ConnectionClass.myConnection();
		try 
		{
		PreparedStatement ps=con.prepareStatement("select * from course");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) 
		{
			CourseResponseDTO res = new CourseResponseDTO();
			res.setId(rs.getString("course_id"));
			res.setName(rs.getString("course_name"));	
			list.add(res);
		}
		} catch (SQLException e) {
		System.out.println(e.getMessage());
		}
		return list;
	}
}
