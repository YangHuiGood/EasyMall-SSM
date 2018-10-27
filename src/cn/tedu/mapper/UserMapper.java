package cn.tedu.mapper;

import org.apache.ibatis.annotations.Param;

import cn.tedu.beans.User;
import cn.tedu.exception.MsgException;

public interface UserMapper {
	/**
	 * 根据用户名查询用户是否存在的方法
	 * @param username 查询的用户名
	 * @return true-存在用户    false-不存在用户
	 */
	User findUserByUsername(String username);
	
	
	/**
	 * 
	 * @param user 封装了用户信息的对象
	 * @return 返回值为影响的行数
	 */
	int addUser(User user);
	
	/**
	 * 根据用户名和密码进行登录操作方法
	 * @param username 用户的用户名
	 * @param password 用户的密码
	 * @return  成功--返回 User对象  失败--返回null 
	 * @throws 封装了提示信息的自定义异常对象
	 */
	User findUserByUAP(@Param("username")String username, @Param("password")String password) throws MsgException;
}
