package cn.tedu.service;

import java.util.List;

import cn.tedu.beans.Prod;

public interface ProdService {
	/**
	 * ��ȡ��Ʒ�б�ķ���
	 * @return
	 */
	public List<Prod> getProds();
	
	/**
	 * �����Ʒ�ķ���
	 * @param prod ��װ����Ʒ��Ϣ����Ʒ����
	 * @return true ��ӳɹ� false ���ʧ��
	 */
	public boolean addProd(Prod prod);
}
