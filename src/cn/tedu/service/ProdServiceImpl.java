package cn.tedu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.beans.Prod;
import cn.tedu.beans.ProdCategory;
import cn.tedu.exception.MsgException;
import cn.tedu.mapper.ProdCategoryMapper;
import cn.tedu.mapper.ProdMapper;
@Service
public class ProdServiceImpl implements ProdService {
	@Autowired
	private ProdMapper prodMapper;
	@Autowired
	private ProdCategoryMapper prodCategoryMapper;
	@Override
	public List<Prod> getProds() {
		return prodMapper.findProds();
	}
	@Override
	@Transactional
	public boolean addProd(Prod prod) {
		ProdCategory pc1 = null;
		pc1  = prodCategoryMapper.findCidByCname(prod.getCname());
		// 如果没有这个商品种类
		if (pc1 == null) {
			// 创建ProdCategory对象，封装要添加的数据
			ProdCategory pc = new ProdCategory();
			pc.setCname(prod.getCname());
			// 1) 先添加这个商品种类
			int n = prodCategoryMapper.addPC(pc);
			if (n == -1) {
				return false;
			}
			// 2) 再次查询cid，获取数据库生成的最新的cid
			try {
				pc1 = prodCategoryMapper.findCidByCname(prod.getCname());
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		// 将查询到的cid添加到prod对象中
		prod.setCid(pc1.getId());
		// 添加商品
		 int n1 = prodMapper.addProdOne(prod);
		 if(n1 > 0){
			 return true;
		 }
		 return false;
	}
}
