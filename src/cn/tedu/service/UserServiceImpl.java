package cn.tedu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.beans.User;
import cn.tedu.exception.MsgException;
import cn.tedu.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	@Override
	public boolean hasUsername(String username) {
		User user = userMapper.getUserByUsername(username);
		if(user != null){
			return true;
		}
		return false;
	}

	@Override
	public boolean registUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User login(String username, String password) throws MsgException {
		// TODO Auto-generated method stub
		return null;
	}

}
