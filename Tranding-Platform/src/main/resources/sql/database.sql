create database trand_platform_sys;
use trand_platform_sys;
create table user(
                     user_id int primary key auto_increment comment '用户标识',
                     username varchar(255) not null comment '用户名',
                     phone varchar(11) not null comment '用户手机号',
                     password varchar(255) not null comment '用户密码',
                     gender tinyint comment '用户性别',
                     role_id int not null comment '角色标识',
                     avatar varchar(255) comment '用户头像',
                     create_time datetime not null comment '创建时间',
                     status int comment '用户的状态'
)comment '用户表';

create table role(
                     role_id int primary key auto_increment comment '角色标识',
                     name varchar(255) not null comment '角色称呼'
)comment '角色表';

create table permission(
                           permission_id int primary key auto_increment comment '权限标识',
                           name varchar(255) not null comment '功能名称',
                           url varchar(255)  comment '接口路径',
                           is_menu tinyint default 0 comment '是否为菜单'
)comment '权限表';

create table role_permission(
                                id int primary key auto_increment comment '角色-权限标识',
                                role_id int not null comment '角色id',
                                permission_id int not null comment '权限id'
)comment '角色-权限表';

create table log(
                    log_id int primary key auto_increment comment '日志标识',
                    title varchar(255) not null comment '日志标题',
                    url varchar(255) not null comment '访问的接口路由',
                    user_id int not null comment '执行操作的用户',
                    execution_time datetime comment '执行的时间'
)comment '日志表';

create table goods(
                      good_id int primary key auto_increment comment '商品标识',
                      name varchar(255) not null comment '商品名称',
                      price double not null comment '商品价格',
                      user_id int not null comment '商品的发布者标识',
                      description varchar(255) comment '商品描述',
                      classify varchar(255) not null comment '商品的分类',
                      good_img varchar(255) not null comment '商品图片'
)comment '商品表';

create table orders(
                       order_id int primary key auto_increment comment '订单标识',
                       good_id int not null comment '购买商品的标识',
                       user_id int not null comment '购买商品的用户的标识',
                       price double not null comment '订单的价格',
                       pay_type tinyint not null comment '支付的类型，钱/以物易物',
                       status tinyint not null comment '订单的状态',
                       create_time datetime not null comment '订单创建的时间',
                       update_time datetime comment '订单修改的时间'
)comment '订单表'