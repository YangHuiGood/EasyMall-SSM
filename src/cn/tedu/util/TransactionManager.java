package cn.tedu.util;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * ���������
 * ����������صĲ���ȫ����װ�ڸ�������
 * ʵ��service��dao��Ľ���
 * @author tarena
 *
 */
public class TransactionManager {
	//���ڲ���ÿ���̣߳��û�����map���Ϲ��������
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	/**
	 * �������񷽷�
	 */
	public static void startTran(){
		//Ϊ��ǰ�̼߳��û���ȡ���Ӷ���
		Connection conn = JDBCUtils.getConnection();
		//�����Ӷ��ֱ����ڵ�ǰ�̵߳�map������
		tl.set(conn);
		try {
			//���ڵ�ǰ�Լ����߳̿�������
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * �ύ����ķ���
	 */
	public static void commit(){
		// ���ڵ�ǰ�߳��Լ������Ӷ������ύ����
		Connection conn = tl.get();
		try {
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * �ع�����ķ���
	 */
	public static void rollback(){
		try {
			//���ڵ�ǰ���Լ��߳����Ӷ������ع�����
			tl.get().rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * ��ȡ���Ӷ���ķ���--��ȡ����TM�й�������Ӷ���
	 */
	public static Connection getConn(){
		return tl.get();
	}
	
	/**
	 * �ر����ӵķ���
	 */
	public static void colseConn(){
		Connection conn = tl.get();
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				conn = null;
			}
		}
		//�ӵ�ǰ�߳��е�map������ɾ�����Ӷ���
		tl.remove();
	}
}
