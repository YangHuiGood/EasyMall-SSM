package cn.tedu.mapper;

import cn.tedu.beans.ProdCategory;

public interface ProdCategoryMapper {
	/**
	 * 添加商品种类的方法
	 * @param pc 封装了商品种类信息的对象
	 * @return 返回影响的行数
	 */
	public int addPC(ProdCategory pc);
	
	/**
	 * 通过商品种类名称查询商品种类id
	 * @param cname 商品种类名称
	 * @return 返回商品种类id
	 */
	public ProdCategory findCidByCname(String cname);
}
