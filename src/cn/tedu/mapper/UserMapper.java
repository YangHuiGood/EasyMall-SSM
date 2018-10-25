package cn.tedu.mapper;

import cn.tedu.beans.User;
import cn.tedu.exception.MsgException;

public interface UserMapper {
	/**
	 * �����û�����ѯ�û��Ƿ���ڵķ���
	 * @param username ��ѯ���û���
	 * @return true-�����û�    false-�������û�
	 */
	User getUserByUsername(String username);
	
	
	/**
	 * �����û���Ϣ
	 * @param user ��װ�û���Ϣ��Javabean
	 * @return true- �����û���Ϣ�ɹ� false- �����û���Ϣʧ��
	 */
	boolean saveUser(User user);
	
	/**
	 * �����û�����������е�¼��������
	 * @param username �û����û���
	 * @param password �û�������
	 * @return  �ɹ�--���� User����  ʧ��--����null 
	 * @throws ��װ����ʾ��Ϣ���Զ����쳣����
	 */
	User getUserByUAP(String username, String password) throws MsgException;
}
