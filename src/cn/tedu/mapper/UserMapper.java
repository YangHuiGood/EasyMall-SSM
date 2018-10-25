package cn.tedu.mapper;

import cn.tedu.beans.User;
import cn.tedu.exception.MsgException;

public interface UserMapper {
	/**
	 * 根据用户名查询用户是否存在的方法
	 * @param username 查询的用户名
	 * @return true-存在用户    false-不存在用户
	 */
	User getUserByUsername(String username);
	
	
	/**
	 * 保存用户信息
	 * @param user 封装用户信息的Javabean
	 * @return true- 插入用户信息成功 false- 插入用户信息失败
	 */
	boolean saveUser(User user);
	
	/**
	 * 根据用户名和密码进行登录操作方法
	 * @param username 用户的用户名
	 * @param password 用户的密码
	 * @return  成功--返回 User对象  失败--返回null 
	 * @throws 封装了提示信息的自定义异常对象
	 */
	User getUserByUAP(String username, String password) throws MsgException;
}
