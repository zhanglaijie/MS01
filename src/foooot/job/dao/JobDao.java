package foooot.job.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import foooot.connectDB.mysql.ConnectToMysql;
import foooot.job.bean.JobBean;
public class JobDao {
	//��ʼ��
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	Statement stmt=null;
	
	public boolean addJob(JobBean job) //����ְ��
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="INSERT INTO JOB (JOBID,JOBTITLE,MINSALARY,MAXSALARY) VALUES(?,?,?,?)";
		try{
			con=new ConnectToMysql().ConnectToMysqlDB();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,job.getJobId());
			pstmt.setString(2, job.getJobTitle());
			pstmt.setDouble(3, job.getMinSalary());
			pstmt.setDouble(4, job.getMaxSalary());
			pstmt.executeUpdate();
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			//Connectionutil.close(con.pstmt) �ر���ݿ�
		}
		return false;
			
	}	
	
	public boolean updateJob(JobBean job) //����Ա����Ϣ
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="UPDATE JOB SET JOBTITLE=?,MINSALARY=?,MAXSALARY=? WHERE JOBID=?";
		try{
			con=new ConnectToMysql().ConnectToMysqlDB();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, job.getJobTitle());
			pstmt.setDouble(2, job.getMinSalary());
			pstmt.setDouble(3, job.getMaxSalary());
			pstmt.setInt(4,job.getJobId());
			pstmt.executeUpdate();
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			//Connectionutil.close(con.pstmt) �ر���ݿ�
		}
		return false;
		
	}
	
	public boolean deleteJob(JobBean job) //ɾ��ְ��
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="DELETE FROM JOB WHERE JOBID=?";
		try{
			con=new ConnectToMysql().ConnectToMysqlDB();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,job.getJobId());
			pstmt.executeUpdate();
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			//Connectionutil.close(con.pstmt) �ر���ݿ�
		}
		return false;	
	}
	   //jobNo��ΪjobId
	public JobBean queryByIdJob(int jobId) //���Ա����Ų�ѯԱ����Ϣ
	{
		JobBean b=new JobBean();
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="SELECT * FROM JOB WHERE JOBID=?";
		try{
			con=new ConnectToMysql().ConnectToMysqlDB();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,jobId);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				b.setJobId(rs.getInt(1));
				b.setJobTitle(rs.getString(2));
				b.setMinSalary(rs.getDouble(3));
				b.setMaxSalary(rs.getDouble(4));
			}
			//return b;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			//Connectionutil.close(con.pstmt) �ر���ݿ�
		}
		return b;	
	}
	
	
	public ArrayList<JobBean> queryAllJob() //��ݲ�ѯԱ��ȫ����Ϣ
	{
		ArrayList<JobBean> al = new ArrayList<JobBean>();
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="SELECT * FROM JOB ORDER BY JOBID";
		try{
			con=new ConnectToMysql().ConnectToMysqlDB();
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next())
			{  
				JobBean bean = new JobBean();
				bean.setJobId(rs.getInt(1));
				bean.setJobTitle(rs.getString(2));
				bean.setMaxSalary(rs.getDouble(3));
				bean.setMinSalary(rs.getDouble(4));
				al.add(bean);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			//Connectionutil.close(con.pstmt) �ر���ݿ�
		}
		return al;
	}
	
	public String getJobtitle(int jobid)
	{
		Connection con = new ConnectToMysql().ConnectToMysqlDB();
		String sql="SELECT JOBTITLE FROM JOB WHERE JOBID=?";
		try {
			PreparedStatement pst =con.prepareStatement(sql);
			pst.setInt(1, jobid);
			ResultSet rs =pst.executeQuery();
			if(rs.next())
			{
				return rs.getString(1);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		JobDao dao = new JobDao();
		ArrayList<JobBean> lg = new ArrayList<JobBean>();
		lg = dao.queryAllJob();
		Iterator<JobBean> lt =lg.iterator();
		while(lt.hasNext()){
			System.out.print(lt.next().getJobId());
		}
		
		
		System.out.println(dao.getJobtitle(5));
	}
	

}
