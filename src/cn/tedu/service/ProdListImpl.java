package cn.tedu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.beans.Prod;
import cn.tedu.mapper.ProdMapper;
@Service
public class ProdListImpl implements ProdService {
	@Autowired
	private ProdMapper prodMapper;
	@Override
	public List<Prod> getProds() {
		return prodMapper.findProds();
	}

}
