package com.DAO;

import java.sql.Connection;
import com.DTO.StudentResponseDTO;
import com.DTO.StudentRequestDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service("StudentDAO")
public class StudentDAO {
  public static Connection con = null;
  static {
    con = ConnectionClass.myConnection();
  }
  public String generateCusId()
  {
    String stuId = null ;
    int count = 0;
    Connection con=ConnectionClass.myConnection();
    try 
    {
    PreparedStatement ps=con.prepareStatement( "select count(*)+1 from student");
    ResultSet rs=ps.executeQuery();
    rs.next();
    
    count = rs.getInt(1);
     
    if (count < 10) {
      stuId = "STU00"+ count;
    } else if (count >= 10 && count < 100) {
      stuId = "STU0"+ count;
    } else if (count == 100) {
      stuId= "STU"+ count;
    }
    
  
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return stuId;
  }
  public int insertStU(StudentRequestDTO dto)
  {
    int result = 0;
    Connection con = ConnectionClass.myConnection();
    try
    {
      PreparedStatement ps = con.prepareStatement("insert into student(student_id,student_name,student_dob,student_gender,student_phone,student_education,student_attend,student_photo) values(?,?,?,?,?,?,?,?)");
      ps.setString(1, dto.getId());
      ps.setString(2, dto.getName());
      ps.setString(3, dto.getDob());
      ps.setString(4, dto.getGender());
      ps.setString(5, dto.getPhone());
      ps.setString(6, dto.getEducation());
      ps.setString(7, dto.getAttend());
      ps.setString(8, dto.getPhoto());
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
  public StudentResponseDTO SelectOne(StudentRequestDTO dto)
	{
	  StudentResponseDTO res = new StudentResponseDTO();
		Connection con=ConnectionClass.myConnection();
		try 
		{
		PreparedStatement ps=con.prepareStatement("select * from student where student_id=?");
		ps.setString(1, dto.getId());
		ResultSet rs=ps.executeQuery();
		while(rs.next()) 
		{
			
			res.setId(rs.getString("student_id"));
			res.setName(rs.getString("student_name"));
			res.setDob(rs.getString("student_dob"));
			res.setGender(rs.getString("student_gender"));
			res.setPhone(rs.getString("student_phone"));
			res.setEducation(rs.getString("student_education"));
			res.setAttend(rs.getString("student_attend"));
			res.setPhoto(rs.getString("student_photo"));
			
		}
		} catch (SQLException e) {
		System.out.println(e.getMessage());
		}
		return res;
	}	
	
	public int UpdateStu(StudentRequestDTO dto)
	{
		int result = 0;
		Connection con = ConnectionClass.myConnection();
		try
		{
			PreparedStatement ps = con.prepareStatement("update student set student_name=?,student_dob=?,student_gender=?,student_phone=?,student_education=?,student_attend=?,student_photo=? where student_id=?");
			
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getDob());
			ps.setString(3, dto.getGender());
			ps.setString(4, dto.getPhone());
			ps.setString(5, dto.getEducation());
			ps.setString(6, dto.getAttend());
			ps.setString(7, dto.getPhoto());
			ps.setString(8, dto.getId());
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
	
	public int DeleteStu(StudentRequestDTO dto)
	{
		int result = 0;
		Connection con = ConnectionClass.myConnection();
		try
		{
			PreparedStatement ps = con.prepareStatement("delete from student where student_id=?");
			ps.setString(1, dto.getId());
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
	public ArrayList<StudentResponseDTO> ShowStu()
	{
		ArrayList<StudentResponseDTO> list = new ArrayList();
		Connection con=ConnectionClass.myConnection();
		try 
		{
		PreparedStatement ps=con.prepareStatement("select * from student");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) 
		{
			StudentResponseDTO res = new StudentResponseDTO();
			res.setId(rs.getString("student_id"));
			res.setName(rs.getString("student_name"));
			res.setDob(rs.getString("student_dob"));
			res.setGender(rs.getString("student_gender"));
			res.setPhone(rs.getString("student_phone"));
			res.setEducation(rs.getString("student_education"));
			res.setAttend(rs.getString("student_attend"));
			res.setPhoto(rs.getString("student_photo"));
			list.add(res);
		}
		} catch (SQLException e) {
		System.out.println(e.getMessage());
		}
		
		return list;
	}	
	
	public ArrayList<StudentResponseDTO> searchData(StudentRequestDTO dto) {
		
		ArrayList<StudentResponseDTO> list = new ArrayList();
		
		String sql = "select * from student where student_id=? or student_name=? or student_attend=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, dto.getId());
			ps.setString(2,dto.getName());
			ps.setString(3, dto.getAttend());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				StudentResponseDTO res = new StudentResponseDTO();
				res.setId(rs.getString("student_id"));
				res.setName(rs.getString("student_name"));
				res.setAttend(rs.getString("student_attend"));
				list.add(res);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	public ArrayList<StudentResponseDTO> SearchwithCourse(String attend)
	{
		ArrayList<StudentResponseDTO> list = new ArrayList();
		Connection con=ConnectionClass.myConnection();
		String sql = "select * from student where student_attend like '%"+attend+"%'";
		try 
		{
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) 
		{
			StudentResponseDTO res = new StudentResponseDTO();
			res.setId(rs.getString("student_id"));
			res.setName(rs.getString("student_name"));
			res.setDob(rs.getString("student_dob"));
			res.setGender(rs.getString("student_gender"));
			res.setPhone(rs.getString("student_phone"));
			res.setEducation(rs.getString("student_education"));
			res.setAttend(rs.getString("student_attend"));
			res.setPhoto(rs.getString("student_photo"));
			list.add(res);
		}
		} catch (SQLException e) {
		System.out.println(e.getMessage());
		}
		return list;
	}
	
	
	
  
}