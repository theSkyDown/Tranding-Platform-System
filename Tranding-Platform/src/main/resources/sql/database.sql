# create database trand_platform_sys;
use trand_platform_sys;
drop table user;
create table user(
    user_id int primary key auto_increment comment '用户标识',
    username varchar(255) not null comment '用户名',
    phone varchar(11) not null unique comment '用户手机号',
    password varchar(255) not null comment '用户密码',
    gender tinyint not null comment '性别',
    role_id int not null comment '角色标识',
    avatar varchar(255) comment '头像',
    create_time datetime not null comment '创建时间',
    update_time datetime comment '最后更新时间',
    status tinyint not null comment '用户状态'
)comment '用户表';
drop table role;
create table role(
    role_id int primary key auto_increment comment '角色标识',
    role_name varchar(64) not null unique comment '角色名称',
    level int not null comment '权限等级',
    create_time datetime not null comment '创建时间'
)comment '角色表';
drop table module;
create table module(
    module_id int primary key auto_increment comment '资源标识',
    module_name varchar(255) not null comment '资源名称',
    url varchar(255) comment '对应的访问路径',
    is_menu tinyint default 0 comment '是否为菜单'
)comment '资源表';
drop table permission;
create table permission(
    permission_id int primary key auto_increment comment '权限标识',
    role_id int not null comment '角色标识',
    module_id int not null comment '资源标识'
)comment '权限表';
drop table goods;
create table goods(
    good_id int primary key auto_increment comment '商品标识',
    good_name varchar(255) not null comment '商品名称',
    price double not null comment '商品的价格',
    user_id int not null comment '商品发布者的标识',
    description varchar(255) not null comment '商品的描述',
    good_img varchar(255) not null comment '商品的图片',
    category_id int not null comment '商品的类型标识',
    create_time datetime not null comment '创建时间'
)comment '商品表';
drop table category;
create table category(
    category_id int primary key auto_increment comment '分类标识',
    category_name varchar(255) not null comment '分类名称',
    create_time datetime not null comment '创建时间'
)comment '分类表';
drop table orders;
create table orders(
    order_id int primary key auto_increment comment '订单标识',
    good_id int not null comment '购买商品的标识',
    user_id int not null comment '购买商品的用户标识',
    price double not null comment '订单的价格',
    pay_type tinyint not null comment '支付类型',
    status tinyint not null comment '订单状态',
    create_time datetime comment '订单创建的时间',
    update_time datetime comment '订单修改的时间'
)comment '订单表';
