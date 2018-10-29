package cn.tedu.mapper;

import java.util.List;


import cn.tedu.beans.Prod;
public interface ProdMapper {
	/**
	 * 查询所有商品信息的方法
	 * @return 商品信息的列表
	 */
	public List<Prod> findProds();
	
	/**
	 * 添加商品的方法
	 * @param prod 封装了商品信息的对象
	 * @return 返回影响的行数
	 */
	public int addProdOne(Prod prod);
}
