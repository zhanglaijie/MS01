package foooot.login.dao;

import java.util.ArrayList;

import foooot.login.bean.Login;

public interface LoginDao {
	public ArrayList<Login> queryAll();  //���������û�
	public Login queryById(String loginid);	//�û�������
	public boolean insert(Login use);   //�����û�
	public boolean update(Login use);	//�޸��û�
	public boolean delete(String loginid);  //ɾ���û�
	public boolean checkById(String loginid);  //����û���
	public boolean checkLogin(String loginid, String passwd);  //��½���
	public boolean checkLevel(String loginid); //Ȩ�޼��
	public boolean giveSalary(String loginid,double salary);  //�ַ�����
	public boolean assign(String loginid,int jobid);  //ְ��ַ�
}
