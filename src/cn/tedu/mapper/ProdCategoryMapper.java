package cn.tedu.mapper;

import cn.tedu.beans.ProdCategory;

public interface ProdCategoryMapper {
	/**
	 * �����Ʒ����ķ���
	 * @param pc ��װ����Ʒ������Ϣ�Ķ���
	 * @return ����Ӱ�������
	 */
	public int addPC(ProdCategory pc);
	
	/**
	 * ͨ����Ʒ�������Ʋ�ѯ��Ʒ����id
	 * @param cname ��Ʒ��������
	 * @return ������Ʒ����id
	 */
	public ProdCategory findCidByCname(String cname);
}
