package cn.tedu.service;

import cn.tedu.beans.User;
import cn.tedu.exception.MsgException;

public interface UserService {
	//1.��Ҫһ������һ���þͻ᷵�����Ե��û�������
		/**
		 * �����ж��û����Ƿ���ڵķ���
		 * @param username �жϵ��û���
		 * @return true- �û�������  false- �û���������
		 */
		boolean hasUsername(String username);
		
		/**
		 * ע���û��ķ���
		 * @param user �������û���Ϣ��javaBean
		 * @return true-ע��ɹ� false- ע��ʧ��
		 */
		boolean registUser(User user);
		
		/**
		 * �û���¼����
		 * @param username �û���
		 * @param password �û�����
		 * @return  user�����null
		 */
		User login(String username, String password) throws MsgException;
}
