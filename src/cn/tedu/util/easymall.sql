-- �������ݿ� easymall
create database easymall;

-- ʹ��easymall��
use easymall;

-- ����user�� id���û��������룬�ǳƣ�����
 create table user(
 id int primary key auto_increment,-- �û���
 username varchar(50), -- �û���
 password varchar(50), -- ����
 nickname varchar(50), -- �ǳ�
 email varchar(50) -- ����
 );
 -- ��������
 insert into user values(null,'admin','123','��������Ա','123@qq.com');
 insert into user values(null,'�ŷ�','123','����Ա','123@qq.com');
 
 --��Ʒ��
 create table prod(
	id int primary key auto_increment, 	-- ��ƷID
	name varchar(255),					-- ��Ʒ����
	price double,						-- ��Ʒ�۸�
	cid int,							-- ��Ʒ����
	pnum int,							-- ��Ʒ����
	imgurl varchar(255),				-- ��ƷͼƬurl��ַ
	description varchar(255)		    -- ��Ʒ����
);	
			
--��Ʒ�����
create table prod_category(
	id int primary key auto_increment,	-- ��Ʒ����ID
	cname varchar(255)					-- ��Ʒ��������
);