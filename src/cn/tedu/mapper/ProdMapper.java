package cn.tedu.mapper;

import java.util.List;


import cn.tedu.beans.Prod;
public interface ProdMapper {
	/**
	 * ��ѯ������Ʒ��Ϣ�ķ���
	 * @return ��Ʒ��Ϣ���б�
	 */
	public List<Prod> findProds();
	
	/**
	 * �����Ʒ�ķ���
	 * @param prod ��װ����Ʒ��Ϣ�Ķ���
	 * @return ����Ӱ�������
	 */
	public int addProdOne(Prod prod);
}
