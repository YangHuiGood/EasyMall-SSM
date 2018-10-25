package cn.tedu.beans;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class User {
	private int id;
	@NotNull(message="���䲻��Ϊ��!")
	private String username;
	@NotNull(message="���벻��Ϊ��!")
	private String password;
	@NotNull(message="�ǳƲ���Ϊ��!")
	private String nickname;
	@NotNull(message="���䲻��Ϊ��!")
	@Pattern(regexp="^\\w+@\\w+(\\.\\w+)+$",message="�����ʽ����")
	private String email;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String username, String password, String nickname,
			String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", nickname=" + nickname + ", email=" + email
				+ "]";
	}
	
}
