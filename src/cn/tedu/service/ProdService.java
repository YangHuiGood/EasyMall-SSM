package cn.tedu.service;

import java.util.List;

import cn.tedu.beans.Prod;

public interface ProdService {
	/**
	 * 获取商品列表的方法
	 * @return
	 */
	public List<Prod> getProds();
	
	/**
	 * 添加商品的方法
	 * @param prod 封装了商品信息的商品对象
	 * @return true 添加成功 false 添加失败
	 */
	public boolean addProd(Prod prod);
}
