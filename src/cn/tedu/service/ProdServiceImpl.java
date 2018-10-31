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
		// ���û�������Ʒ����
		if (pc1 == null) {
			// ����ProdCategory���󣬷�װҪ��ӵ�����
			ProdCategory pc = new ProdCategory();
			pc.setCname(prod.getCname());
			// 1) ����������Ʒ����
			int n = prodCategoryMapper.addPC(pc);
			if (n == -1) {
				return false;
			}
			// 2) �ٴβ�ѯcid����ȡ���ݿ����ɵ����µ�cid
			try {
				pc1 = prodCategoryMapper.findCidByCname(prod.getCname());
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		// ����ѯ����cid��ӵ�prod������
		prod.setCid(pc1.getId());
		// �����Ʒ
		 int n1 = prodMapper.addProdOne(prod);
		 if(n1 > 0){
			 return true;
		 }
		 return false;
	}
}
