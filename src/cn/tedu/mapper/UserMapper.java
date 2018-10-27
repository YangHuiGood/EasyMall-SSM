package cn.tedu.mapper;

import org.apache.ibatis.annotations.Param;

import cn.tedu.beans.User;
import cn.tedu.exception.MsgException;

public interface UserMapper {
	/**
	 * �����û�����ѯ�û��Ƿ���ڵķ���
	 * @param username ��ѯ���û���
	 * @return true-�����û�    false-�������û�
	 */
	User findUserByUsername(String username);
	
	
	/**
	 * 
	 * @param user ��װ���û���Ϣ�Ķ���
	 * @return ����ֵΪӰ�������
	 */
	int addUser(User user);
	
	/**
	 * �����û�����������е�¼��������
	 * @param username �û����û���
	 * @param password �û�������
	 * @return  �ɹ�--���� User����  ʧ��--����null 
	 * @throws ��װ����ʾ��Ϣ���Զ����쳣����
	 */
	User findUserByUAP(@Param("username")String username, @Param("password")String password) throws MsgException;
}
