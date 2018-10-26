package cn.tedu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.beans.User;
import cn.tedu.exception.MsgException;
import cn.tedu.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	@Override
	public boolean hasUsername(String username) {
		User user = userMapper.findUserByUsername(username);
		if(user != null){
			return true;
		}
		return false;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public boolean registUser(User user) {
		//调用接口类中的添加用户的方法返回值为影响的行数
		int num = userMapper.addUser(user);
		if(num > 0){
			return true;
		}
		return false;
	}

	@Override
	public User login(String username, String password) throws MsgException {
		return userMapper.findUserByUAP(username, password);
	}

}
