#所有数据库数据
drop database if exists trand_platform_sys;
create database trand_platform_sys;
use trand_platform_sys;

create table category
(
    category_id   int auto_increment comment '分类标识'
        primary key,
    category_name varchar(255) not null comment '分类名称',
    create_time   datetime     not null comment '创建时间',
    constraint category_name
        unique (category_name)
)
    comment '分类表';

INSERT INTO trand_platform_sys.category (category_id, category_name, create_time) VALUES (1, '家用电器', '2023-05-22 17:52:49');
INSERT INTO trand_platform_sys.category (category_id, category_name, create_time) VALUES (2, '数码', '2023-05-22 17:52:49');
INSERT INTO trand_platform_sys.category (category_id, category_name, create_time) VALUES (3, '家具', '2023-05-22 17:52:49');
INSERT INTO trand_platform_sys.category (category_id, category_name, create_time) VALUES (4, '服装', '2023-05-22 17:52:49');
INSERT INTO trand_platform_sys.category (category_id, category_name, create_time) VALUES (5, '厨具', '2023-05-22 17:52:49');
INSERT INTO trand_platform_sys.category (category_id, category_name, create_time) VALUES (6, '鞋子', '2023-05-22 17:52:49');
INSERT INTO trand_platform_sys.category (category_id, category_name, create_time) VALUES (7, '化妆品', '2023-05-22 20:41:01');
INSERT INTO trand_platform_sys.category (category_id, category_name, create_time) VALUES (8, '钟表', '2023-05-22 20:41:22');
INSERT INTO trand_platform_sys.category (category_id, category_name, create_time) VALUES (9, '食品', '2023-05-22 20:41:34');
INSERT INTO trand_platform_sys.category (category_id, category_name, create_time) VALUES (10, '医疗药品', '2023-05-22 20:41:48');
INSERT INTO trand_platform_sys.category (category_id, category_name, create_time) VALUES (11, '书籍', '2023-05-22 20:42:34');
INSERT INTO trand_platform_sys.category (category_id, category_name, create_time) VALUES (12, '游戏账号', '2023-05-22 20:42:47');
INSERT INTO trand_platform_sys.category (category_id, category_name, create_time) VALUES (13, '其他', '2023-05-23 01:03:50');


create table goods
(
    good_id     int auto_increment comment '商品标识'
        primary key,
    good_name   varchar(255) not null comment '商品名称',
    price       double       not null comment '商品的价格',
    user_id     int          not null comment '商品发布者的标识',
    description varchar(255) not null comment '商品的描述',
    pay_type    int          not null comment '支付方式',
    good_img    varchar(255) not null comment '商品的图片',
    category_id int          not null comment '商品的类型标识',
    status      int          not null comment '状态',
    create_time datetime     not null comment '创建时间'
)
    comment '商品表';

INSERT INTO trand_platform_sys.goods (good_id, good_name, price, user_id, description, pay_type, good_img, category_id, status, create_time) VALUES (6, '索尼耳机', 100.11, 1, '耳机，听歌', 1, 'http://ruyj27zul.hn-bkt.clouddn.com/good/1684773740134.png', 2, 2, '2023-05-23 00:42:31');
INSERT INTO trand_platform_sys.goods (good_id, good_name, price, user_id, description, pay_type, good_img, category_id, status, create_time) VALUES (7, '高级手表', 999, 18, '商品描述1111111111111111商品描述1111111111111111商品描述1111111111111111商品描述1111111111111111商品描述1111111111111111商品描述1111111111111111商品描述1111111111111111商品描述1111111111111111', 1, 'http://ruyj27zul.hn-bkt.clouddn.com/good/1684775453053.png', 8, 2, '2023-05-23 01:11:19');
INSERT INTO trand_platform_sys.goods (good_id, good_name, price, user_id, description, pay_type, good_img, category_id, status, create_time) VALUES (8, '中兴手机', 1999, 4, '手机，可以刷刷视频什么的', 2, 'http://ruyj27zul.hn-bkt.clouddn.com/good/1684775499387.jpg', 2, 2, '2023-05-23 01:12:16');
INSERT INTO trand_platform_sys.goods (good_id, good_name, price, user_id, description, pay_type, good_img, category_id, status, create_time) VALUES (14, '商品1', 1999, 2, '商品描述1111111111111111商品描述1111111111111111商品描述1111111111111111商品描述1111111111111111商品描述1111111111111111商品描述1111111111111111商品描述1111111111111111商品描述1111111111111111', 1, 'http://ruyj27zul.hn-bkt.clouddn.com/good/1684930308418.png', 2, 2, '2023-05-24 20:14:07');
INSERT INTO trand_platform_sys.goods (good_id, good_name, price, user_id, description, pay_type, good_img, category_id, status, create_time) VALUES (15, '商品2', 1999, 2, '商品描述1111111111111111', 1, 'http://ruyj27zul.hn-bkt.clouddn.com/good/1684930308418.png', 2, 1, '2023-05-24 20:14:07');
INSERT INTO trand_platform_sys.goods (good_id, good_name, price, user_id, description, pay_type, good_img, category_id, status, create_time) VALUES (16, '商品3', 1999, 13, '商品描述1111111111111111', 1, 'http://ruyj27zul.hn-bkt.clouddn.com/good/1684930308418.png', 2, 2, '2023-05-24 20:14:07');
INSERT INTO trand_platform_sys.goods (good_id, good_name, price, user_id, description, pay_type, good_img, category_id, status, create_time) VALUES (17, '商品4', 1999, 2, '商品描述1111111111111111', 1, 'http://ruyj27zul.hn-bkt.clouddn.com/good/1684930308418.png', 2, 1, '2023-05-24 20:14:07');
INSERT INTO trand_platform_sys.goods (good_id, good_name, price, user_id, description, pay_type, good_img, category_id, status, create_time) VALUES (18, '商品5', 1999, 2, '商品描述1111111111111111', 1, 'http://ruyj27zul.hn-bkt.clouddn.com/good/1684930308418.png', 2, 2, '2023-05-24 20:14:07');
INSERT INTO trand_platform_sys.goods (good_id, good_name, price, user_id, description, pay_type, good_img, category_id, status, create_time) VALUES (20, '商品7', 1999, 2, '商品描述1111111111111111', 1, 'http://ruyj27zul.hn-bkt.clouddn.com/good/1684930308418.png', 2, 1, '2023-05-24 20:14:07');
INSERT INTO trand_platform_sys.goods (good_id, good_name, price, user_id, description, pay_type, good_img, category_id, status, create_time) VALUES (21, '商品8', 1999, 2, '商品描述1111111111111111', 1, 'http://ruyj27zul.hn-bkt.clouddn.com/good/1684930308418.png', 2, 2, '2023-05-24 20:14:07');
INSERT INTO trand_platform_sys.goods (good_id, good_name, price, user_id, description, pay_type, good_img, category_id, status, create_time) VALUES (22, '商品9', 1999, 2, '商品描述1111111111111111', 1, 'http://ruyj27zul.hn-bkt.clouddn.com/good/1684930308418.png', 2, 2, '2023-05-24 20:14:07');
INSERT INTO trand_platform_sys.goods (good_id, good_name, price, user_id, description, pay_type, good_img, category_id, status, create_time) VALUES (23, '商品10', 1999, 2, '商品描述1111111111111111', 1, 'http://ruyj27zul.hn-bkt.clouddn.com/good/1684930308418.png', 2, 1, '2023-05-24 20:14:07');
INSERT INTO trand_platform_sys.goods (good_id, good_name, price, user_id, description, pay_type, good_img, category_id, status, create_time) VALUES (24, '商品11', 1999, 2, '商品描述1111111111111111', 1, 'http://ruyj27zul.hn-bkt.clouddn.com/good/1684930308418.png', 2, 2, '2023-05-24 20:14:07');
INSERT INTO trand_platform_sys.goods (good_id, good_name, price, user_id, description, pay_type, good_img, category_id, status, create_time) VALUES (25, '商品12', 1999, 2, '商品描述1111111111111111', 1, 'http://ruyj27zul.hn-bkt.clouddn.com/good/1684930308418.png', 2, 2, '2023-05-24 20:14:07');
INSERT INTO trand_platform_sys.goods (good_id, good_name, price, user_id, description, pay_type, good_img, category_id, status, create_time) VALUES (26, '测试我的售出1', 1234, 6, '描述', 2, 'http://ruyj27zul.hn-bkt.clouddn.com/good/1684944703113.png', 7, 2, '2023-05-24 23:49:42');
INSERT INTO trand_platform_sys.goods (good_id, good_name, price, user_id, description, pay_type, good_img, category_id, status, create_time) VALUES (27, 'ad12', 123, 1, '123', 1, 'http://ruyj27zul.hn-bkt.clouddn.com/good/1684947509349.jpg', 3, 1, '2023-05-25 00:58:31');
INSERT INTO trand_platform_sys.goods (good_id, good_name, price, user_id, description, pay_type, good_img, category_id, status, create_time) VALUES (28, '修改名称', 90, 1, '1111dd', 2, 'http://ruyj27zul.hn-bkt.clouddn.com/good/1685092286636.png', 1, 1, '2023-05-26 17:11:27');
INSERT INTO trand_platform_sys.goods (good_id, good_name, price, user_id, description, pay_type, good_img, category_id, status, create_time) VALUES (29, '手机', 1999, 1, '厨具', 1, 'http://ruyj27zul.hn-bkt.clouddn.com/good/1685103611906.jpg', 5, 1, '2023-05-26 20:20:24');
INSERT INTO trand_platform_sys.goods (good_id, good_name, price, user_id, description, pay_type, good_img, category_id, status, create_time) VALUES (30, '测试发布商品', 600, 1, '用了好久的手表', 2, 'http://ruyj27zul.hn-bkt.clouddn.com/good/1685203752990.png', 8, 1, '2023-05-28 00:09:27');


create table module
(
    module_id   int auto_increment comment '资源标识'
        primary key,
    module_name varchar(255)      not null comment '资源名称',
    url         varchar(255)      not null,
    is_menu     tinyint default 0 null comment '是否为菜单',
    constraint url
        unique (url)
)
    comment '资源表';

INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (1, '获取所有用户功能（分页、关键字搜索）', '/user/all', 2);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (2, '用户添加功能（添加时无法分配角色）', '/user/add', 2);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (3, '用户信息修改功能', '/user/update', 2);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (4, '用户密码重置功能', '/user/reset/password', 2);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (5, '用户删除功能（admin无法被删除）', '/user/delete', 2);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (6, '用户批量删除功能（admin无法被删除）', '/user/delete/ids', 2);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (7, '用户登陆功能', '/user/login', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (8, '用户详细信息查看功能', '/user/info', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (9, '用户密码修改功能', '/user/update/password', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (10, '获取所有角色信息功能（分页、关键字搜索）', '/role/all', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (11, '角色添加功能', '/role/add', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (12, '角色删除功能（无法删除基础角色）', '/role/delete', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (13, '暂时', '/temp', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (14, '用户状态切换功能（admin无法被禁用）', '/user/switch/status', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (15, '统计用户数量功能', '/user/total', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (16, '角色批量删除功能（无法删除基础角色）', '/role/delete/ids', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (17, '角色信息修改功能', '/role/update', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (18, '统计角色数量功能', '/role/total', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (19, '获取所有资源信息功能（分页、关键字搜素）', '/module/all', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (20, '资源的添加', '/module/add', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (21, '资源的删除', '/module/delete', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (22, '资源的批量删除', '/module/delete/ids', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (23, '资源的修改功能', '/module/update', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (24, '资源数量统计功能', '/module/total', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (25, '获取所有商品的信息', '/good/all', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (26, '获取所有在售商品的信息', '/good/all/trading', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (27, '统计所有商品的信息', '/good/total', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (28, '统计所有在售商品的信息', '/good/total/trading', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (29, '商品发布功能', '/good/add', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (30, '商品删除功能', '/good/delete', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (31, '商品批量删除功能', '/good/delete/ids', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (32, '修改商品信息功能', '/good/update', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (33, '购买商品功能', '/good/buy', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (34, '获取商品详细信息功能', '/good/detail', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (35, '获取用户出售的所有商品信息', '/good/all/sale', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (36, '统计用户出售的所有商品的数量', '/good/total/sale', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (37, '用户下架商品的功能', '/good/take/down', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (38, '获取所有分类信息的功能', '/category/all', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (39, '统计所有分类信息的数量', '/category/total', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (40, '添加分类信息的功能', '/category/add', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (41, '删除分类信息的功能', '/category/delete', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (42, '批量删除分类信息的功能', '/category/delete/ids', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (43, '修改分类信息的功能', '/category/update', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (44, '获取所有订单信息的功能', '/order/all', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (45, '统计所有订单信息的数量的功能', '/order/total', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (46, '删除订单的功能', '/order/delete', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (47, '批量删除订单的功能', '/order/delete/ids', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (48, '用户获取自己的所有订单信息的功能', '/order/all/trading', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (49, '用户统计自己的所有订单信息的数量的功能', '/order/total/trading', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (50, '用户取消订单的功能', '/order/cancel', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (51, '用户选择完成订单的功能', '/order/complete', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (52, '获取所有权限的信息', '/permission/all', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (53, '统计所有权限的数量', '/permission/total', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (54, '添加权限的功能', '/permission/add', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (55, '删除权限的功能', '/permission/delete', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (56, '批量删除权限的功能', '/permission/delete/ids', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (57, '头像上传功能', '/qiniu/avatar', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (58, '商品图片上传功能', '/qiniu/good', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (59, '日志导出', '/log/export', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (60, '获取用户的平均分数', '/user/rate', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (61, '获取出售的商品种类信息的echarts图', '/user/sale/category/echarts', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (62, '获取统计评分的echarts图', '/user/rate/echarts', 0);
INSERT INTO trand_platform_sys.module (module_id, module_name, url, is_menu) VALUES (63, '获取一年销售额的echarts图', '/user/trading/echarts', 0);



create table orders
(
    order_id    int auto_increment comment '订单标识'
        primary key,
    good_id     int              not null comment '购买商品的标识',
    user_id     int              not null comment '购买商品的用户标识',
    status      tinyint          not null comment '订单状态,1表示完成交易，2表示未完成交易',
    rate        double default 0 not null comment '评分',
    create_time datetime         null comment '订单创建的时间',
    update_time datetime         null comment '订单修改的时间'
)
    comment '订单表';

INSERT INTO trand_platform_sys.orders (order_id, good_id, user_id, status, rate, create_time, update_time) VALUES (26, 7, 2, 1, 4, '2023-05-24 17:58:02', '2023-05-24 17:58:02');
INSERT INTO trand_platform_sys.orders (order_id, good_id, user_id, status, rate, create_time, update_time) VALUES (27, 8, 1, 1, 3, '2021-05-24 18:37:36', '2023-05-24 18:37:36');
INSERT INTO trand_platform_sys.orders (order_id, good_id, user_id, status, rate, create_time, update_time) VALUES (28, 10, 1, 2, 0, '2022-05-24 20:08:57', '2023-05-24 20:08:57');
INSERT INTO trand_platform_sys.orders (order_id, good_id, user_id, status, rate, create_time, update_time) VALUES (33, 6, 1, 1, 4, '2023-05-24 23:26:01', '2023-05-24 23:26:01');
INSERT INTO trand_platform_sys.orders (order_id, good_id, user_id, status, rate, create_time, update_time) VALUES (35, 21, 1, 1, 3, '2023-06-25 00:59:29', '2023-05-25 00:59:29');
INSERT INTO trand_platform_sys.orders (order_id, good_id, user_id, status, rate, create_time, update_time) VALUES (36, 18, 1, 1, 1, '2023-05-25 01:02:56', '2023-05-25 01:02:56');
INSERT INTO trand_platform_sys.orders (order_id, good_id, user_id, status, rate, create_time, update_time) VALUES (37, 14, 1, 1, 4, '2023-05-25 01:39:30', '2023-05-25 01:39:30');
INSERT INTO trand_platform_sys.orders (order_id, good_id, user_id, status, rate, create_time, update_time) VALUES (39, 26, 1, 2, 0, '2023-05-26 00:52:45', '2023-05-26 00:52:45');
INSERT INTO trand_platform_sys.orders (order_id, good_id, user_id, status, rate, create_time, update_time) VALUES (40, 24, 1, 2, 0, '2023-08-26 22:09:49', '2023-05-26 22:09:49');
INSERT INTO trand_platform_sys.orders (order_id, good_id, user_id, status, rate, create_time, update_time) VALUES (41, 22, 1, 2, 0, '2022-05-26 22:09:51', '2023-05-26 22:09:51');
INSERT INTO trand_platform_sys.orders (order_id, good_id, user_id, status, rate, create_time, update_time) VALUES (42, 25, 1, 2, 0, '2024-05-26 22:09:56', '2023-05-26 22:09:56');
INSERT INTO trand_platform_sys.orders (order_id, good_id, user_id, status, rate, create_time, update_time) VALUES (43, 16, 1, 1, 2, '2023-05-28 00:07:07', '2023-05-28 00:07:07');


create table permission
(
    permission_id int auto_increment comment '权限标识'
        primary key,
    role_id       int not null comment '角色标识',
    module_id     int not null comment '资源标识'
)
    comment '权限表';

INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (1, 9, 1);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (2, 9, 15);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (3, 9, 2);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (4, 9, 5);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (5, 9, 6);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (6, 9, 3);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (7, 9, 4);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (8, 9, 14);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (9, 9, 10);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (10, 9, 18);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (11, 9, 11);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (12, 9, 12);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (13, 9, 16);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (14, 9, 17);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (15, 9, 25);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (16, 9, 26);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (17, 9, 27);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (18, 9, 28);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (19, 9, 29);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (20, 9, 19);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (21, 9, 24);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (22, 10, 1);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (23, 10, 15);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (24, 10, 2);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (25, 10, 3);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (26, 10, 5);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (27, 10, 6);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (28, 10, 10);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (29, 10, 18);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (30, 11, 1);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (31, 11, 15);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (32, 1, 1);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (33, 1, 2);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (34, 1, 3);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (35, 1, 4);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (36, 1, 5);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (37, 1, 6);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (38, 1, 7);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (39, 1, 8);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (40, 1, 9);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (41, 1, 10);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (42, 1, 11);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (43, 1, 12);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (44, 1, 14);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (45, 1, 15);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (46, 1, 16);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (47, 1, 17);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (48, 1, 18);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (49, 1, 19);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (50, 1, 20);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (51, 1, 21);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (52, 1, 22);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (53, 1, 23);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (54, 1, 24);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (55, 1, 25);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (56, 1, 26);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (57, 1, 27);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (58, 1, 28);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (59, 1, 29);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (60, 1, 30);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (61, 1, 31);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (62, 1, 32);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (63, 1, 33);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (64, 1, 34);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (65, 1, 35);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (66, 1, 36);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (67, 1, 37);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (68, 1, 38);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (69, 1, 39);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (70, 1, 40);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (71, 1, 41);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (72, 1, 42);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (73, 1, 43);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (74, 1, 44);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (75, 1, 45);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (76, 1, 46);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (77, 1, 47);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (78, 1, 48);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (79, 1, 49);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (80, 1, 50);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (81, 1, 51);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (82, 2, 1);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (83, 2, 2);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (84, 2, 3);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (85, 2, 4);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (86, 2, 5);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (87, 2, 6);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (88, 2, 7);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (89, 2, 8);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (90, 2, 9);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (91, 2, 10);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (92, 2, 11);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (93, 2, 12);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (94, 2, 14);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (95, 2, 15);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (96, 2, 16);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (97, 2, 17);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (98, 2, 18);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (99, 2, 19);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (104, 2, 24);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (105, 2, 25);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (106, 2, 26);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (107, 2, 27);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (108, 2, 28);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (109, 2, 29);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (110, 2, 30);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (111, 2, 31);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (112, 2, 32);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (113, 2, 33);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (114, 2, 34);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (115, 2, 35);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (116, 2, 36);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (117, 2, 37);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (118, 2, 38);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (119, 2, 39);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (120, 2, 40);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (121, 2, 41);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (122, 2, 42);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (123, 2, 43);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (124, 2, 44);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (125, 2, 45);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (128, 2, 48);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (129, 2, 49);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (130, 2, 50);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (131, 2, 51);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (132, 1, 52);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (133, 1, 53);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (134, 1, 54);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (135, 1, 55);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (136, 1, 56);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (137, 1, 57);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (138, 1, 58);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (139, 2, 57);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (140, 2, 58);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (141, 3, 26);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (142, 3, 28);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (143, 3, 29);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (144, 3, 32);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (145, 3, 33);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (146, 3, 34);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (147, 3, 35);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (148, 3, 36);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (149, 3, 37);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (150, 3, 48);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (151, 3, 49);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (152, 3, 50);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (153, 3, 51);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (154, 3, 38);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (155, 3, 58);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (156, 1, 59);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (157, 2, 59);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (158, 1, 60);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (159, 2, 60);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (160, 3, 60);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (161, 1, 61);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (162, 2, 61);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (163, 3, 61);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (164, 1, 62);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (165, 2, 62);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (166, 3, 62);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (167, 1, 63);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (168, 2, 63);
INSERT INTO trand_platform_sys.permission (permission_id, role_id, module_id) VALUES (169, 3, 63);


create table role
(
    role_id     int auto_increment comment '角色标识'
        primary key,
    role_name   varchar(64) not null comment '角色名称',
    level       int         not null comment '权限等级',
    create_time datetime    not null comment '创建时间',
    constraint role_name
        unique (role_name)
)
    comment '角色表';

INSERT INTO trand_platform_sys.role (role_id, role_name, level, create_time) VALUES (1, '超级管理员', 1, '2023-05-13 01:36:31');
INSERT INTO trand_platform_sys.role (role_id, role_name, level, create_time) VALUES (2, '管理员', 2, '2023-05-13 01:36:40');
INSERT INTO trand_platform_sys.role (role_id, role_name, level, create_time) VALUES (3, '普通用户', 999, '2023-05-13 01:36:50');
INSERT INTO trand_platform_sys.role (role_id, role_name, level, create_time) VALUES (4, '超级角色', 3, '2023-05-18 14:30:49');
INSERT INTO trand_platform_sys.role (role_id, role_name, level, create_time) VALUES (5, '角色测试', 3, '2023-05-18 15:16:05');
INSERT INTO trand_platform_sys.role (role_id, role_name, level, create_time) VALUES (6, '测试人员', 3, '2023-05-18 15:16:12');
INSERT INTO trand_platform_sys.role (role_id, role_name, level, create_time) VALUES (7, '角色名字', 3, '2023-05-18 15:17:46');
INSERT INTO trand_platform_sys.role (role_id, role_name, level, create_time) VALUES (8, '小小商家', 3, '2023-05-18 15:44:20');
INSERT INTO trand_platform_sys.role (role_id, role_name, level, create_time) VALUES (9, '权限测试', 2, '2023-05-18 22:06:48');
INSERT INTO trand_platform_sys.role (role_id, role_name, level, create_time) VALUES (10, '权限测试-小组组长', 3, '2023-05-20 01:30:09');
INSERT INTO trand_platform_sys.role (role_id, role_name, level, create_time) VALUES (11, '权限测试-小组队员', 4, '2023-05-20 01:30:18');


create table user
(
    user_id     int auto_increment comment '用户标识'
        primary key,
    username    varchar(255) not null comment '用户名',
    phone       varchar(11)  not null,
    password    varchar(255) not null comment '用户密码',
    gender      tinyint      not null comment '性别',
    role_id     int          not null comment '角色标识',
    avatar      varchar(255) null comment '头像',
    create_time datetime     not null comment '创建时间',
    update_time datetime     null comment '最后更新时间',
    status      tinyint      not null comment '用户状态',
    constraint phone
        unique (phone)
)
    comment '用户表';

INSERT INTO trand_platform_sys.user (user_id, username, phone, password, gender, role_id, avatar, create_time, update_time, status) VALUES (1, 'admin', 'admin', '95155d692cba1bd4df6b3e9b8c7ef58a', 1, 1, 'http://ruyj27zul.hn-bkt.clouddn.com/avatar.png', '2023-05-15 22:49:20', '2023-05-15 22:49:24', 1);
INSERT INTO trand_platform_sys.user (user_id, username, phone, password, gender, role_id, avatar, create_time, update_time, status) VALUES (2, 'test', 'test', '95155d692cba1bd4df6b3e9b8c7ef58a', 1, 2, 'http://ruyj27zul.hn-bkt.clouddn.com/avatar/1684735582176.JPG', '2023-05-16 17:27:11', '2023-05-16 17:27:11', 1);
INSERT INTO trand_platform_sys.user (user_id, username, phone, password, gender, role_id, avatar, create_time, update_time, status) VALUES (3, 'eccentric', '17373011502', '95155d692cba1bd4df6b3e9b8c7ef58a', 1, 2, 'http://ruyj27zul.hn-bkt.clouddn.com/avatar/1684735290617.JPG', '2023-05-13 02:02:15', '2023-05-13 02:02:15', 1);
INSERT INTO trand_platform_sys.user (user_id, username, phone, password, gender, role_id, avatar, create_time, update_time, status) VALUES (4, '张三', '17323011502', '14a5d25145bc9a0afef8529d32926688', 1, 3, 'http://ruyj27zul.hn-bkt.clouddn.com/R.jpg', '2023-05-17 16:10:59', '2023-05-17 16:10:59', 1);
INSERT INTO trand_platform_sys.user (user_id, username, phone, password, gender, role_id, avatar, create_time, update_time, status) VALUES (5, '李四', '18973011502', '14a5d25145bc9a0afef8529d32926688', 1, 8, 'http://ruyj27zul.hn-bkt.clouddn.com/R.jpg', '2023-05-17 16:48:09', '2023-05-17 16:48:09', 1);
INSERT INTO trand_platform_sys.user (user_id, username, phone, password, gender, role_id, avatar, create_time, update_time, status) VALUES (6, 'flasdkfj', '17173011992', '95155d692cba1bd4df6b3e9b8c7ef58a', 1, 3, 'http://ruyj27zul.hn-bkt.clouddn.com/R.jpg', '2023-05-17 17:59:56', '2023-05-17 17:59:56', 1);
INSERT INTO trand_platform_sys.user (user_id, username, phone, password, gender, role_id, avatar, create_time, update_time, status) VALUES (7, '添加测试', '17373021501', '95155d692cba1bd4df6b3e9b8c7ef58a', 2, 4, 'http://ruyj27zul.hn-bkt.clouddn.com/R.jpg', '2023-05-17 18:00:27', '2023-05-17 18:00:27', 2);
INSERT INTO trand_platform_sys.user (user_id, username, phone, password, gender, role_id, avatar, create_time, update_time, status) VALUES (9, 'eccentric2', '17372011111', '95155d692cba1bd4df6b3e9b8c7ef58a', 1, 3, 'http://ruyj27zul.hn-bkt.clouddn.com/R.jpg', '2023-05-16 17:27:11', '2023-05-16 17:27:11', 2);
INSERT INTO trand_platform_sys.user (user_id, username, phone, password, gender, role_id, avatar, create_time, update_time, status) VALUES (10, '李四2', '18973011512', '14a5d25145bc9a0afef8529d32926688', 1, 8, 'http://ruyj27zul.hn-bkt.clouddn.com/R.jpg', '2023-05-18 13:42:08', '2023-05-18 13:42:08', 1);
INSERT INTO trand_platform_sys.user (user_id, username, phone, password, gender, role_id, avatar, create_time, update_time, status) VALUES (11, '你怎么也叫李四2', '19973011512', '14a5d25145bc9a0afef8529d32926688', 1, 3, 'http://ruyj27zul.hn-bkt.clouddn.com/R.jpg', '2023-05-18 13:42:23', '2023-05-18 13:42:23', 1);
INSERT INTO trand_platform_sys.user (user_id, username, phone, password, gender, role_id, avatar, create_time, update_time, status) VALUES (12, '李四2', '19973811512', '95155d692cba1bd4df6b3e9b8c7ef58a', 1, 3, 'http://ruyj27zul.hn-bkt.clouddn.com/R.jpg', '2023-05-18 13:43:04', '2023-05-18 13:43:04', 1);
INSERT INTO trand_platform_sys.user (user_id, username, phone, password, gender, role_id, avatar, create_time, update_time, status) VALUES (13, '想怎么取就怎么取，下次就叫admin', '17987950479', '95155d692cba1bd4df6b3e9b8c7ef58a', 1, 3, 'http://ruyj27zul.hn-bkt.clouddn.com/R.jpg', '2023-05-18 14:10:48', '2023-05-18 14:10:48', 1);
INSERT INTO trand_platform_sys.user (user_id, username, phone, password, gender, role_id, avatar, create_time, update_time, status) VALUES (14, '名字可以重复的', '19889798798', '95155d692cba1bd4df6b3e9b8c7ef58a', 1, 6, 'http://ruyj27zul.hn-bkt.clouddn.com/R.jpg', '2023-05-18 15:19:42', '2023-05-18 15:19:42', 1);
INSERT INTO trand_platform_sys.user (user_id, username, phone, password, gender, role_id, avatar, create_time, update_time, status) VALUES (15, '别报500啦', '15005005005', '95155d692cba1bd4df6b3e9b8c7ef58a', 1, 3, 'http://ruyj27zul.hn-bkt.clouddn.com/R.jpg', '2023-05-18 15:41:33', '2023-05-18 15:41:33', 1);
INSERT INTO trand_platform_sys.user (user_id, username, phone, password, gender, role_id, avatar, create_time, update_time, status) VALUES (16, '400也别报', '18975498607', '95155d692cba1bd4df6b3e9b8c7ef58a', 1, 3, 'http://ruyj27zul.hn-bkt.clouddn.com/R.jpg', '2023-05-18 15:43:57', '2023-05-18 15:43:57', 1);
INSERT INTO trand_platform_sys.user (user_id, username, phone, password, gender, role_id, avatar, create_time, update_time, status) VALUES (17, '名字变短', '17897987978', '95155d692cba1bd4df6b3e9b8c7ef58a', 2, 5, 'http://ruyj27zul.hn-bkt.clouddn.com/avatar/1685196218650.JPG', '2023-05-18 16:14:32', '2023-05-18 16:14:32', 1);
INSERT INTO trand_platform_sys.user (user_id, username, phone, password, gender, role_id, avatar, create_time, update_time, status) VALUES (18, 'mingzi', '16789079847', '95155d692cba1bd4df6b3e9b8c7ef58a', 1, 2, 'http://ruyj27zul.hn-bkt.clouddn.com/R.jpg', '2023-05-18 17:00:06', '2023-05-18 17:00:06', 1);
INSERT INTO trand_platform_sys.user (user_id, username, phone, password, gender, role_id, avatar, create_time, update_time, status) VALUES (19, '权限测试员', '19879870879', '95155d692cba1bd4df6b3e9b8c7ef58a', 1, 9, 'http://ruyj27zul.hn-bkt.clouddn.com/R.jpg', '2023-05-19 20:57:35', '2023-05-19 20:57:35', 1);
INSERT INTO trand_platform_sys.user (user_id, username, phone, password, gender, role_id, avatar, create_time, update_time, status) VALUES (20, 'admin-test', '19999999999', '95155d692cba1bd4df6b3e9b8c7ef58a', 2, 1, 'http://ruyj27zul.hn-bkt.clouddn.com/R.jpg', '2023-05-19 21:21:53', '2023-05-19 21:21:53', 1);
INSERT INTO trand_platform_sys.user (user_id, username, phone, password, gender, role_id, avatar, create_time, update_time, status) VALUES (21, 'admin-test', '18888888888', '95155d692cba1bd4df6b3e9b8c7ef58a', 1, 1, 'http://ruyj27zul.hn-bkt.clouddn.com/R.jpg', '2023-05-19 21:23:42', '2023-05-19 21:23:42', 1);
INSERT INTO trand_platform_sys.user (user_id, username, phone, password, gender, role_id, avatar, create_time, update_time, status) VALUES (22, '权限测试人员进行添加操作', '17897987897', '95155d692cba1bd4df6b3e9b8c7ef58a', 2, 3, 'http://ruyj27zul.hn-bkt.clouddn.com/R.jpg', '2023-05-19 22:09:30', '2023-05-19 22:09:30', 1);
INSERT INTO trand_platform_sys.user (user_id, username, phone, password, gender, role_id, avatar, create_time, update_time, status) VALUES (23, '我是组长', '13333333333', '95155d692cba1bd4df6b3e9b8c7ef58a', 1, 10, 'http://ruyj27zul.hn-bkt.clouddn.com/R.jpg', '2023-05-20 01:30:42', '2023-05-20 01:30:42', 1);
INSERT INTO trand_platform_sys.user (user_id, username, phone, password, gender, role_id, avatar, create_time, update_time, status) VALUES (24, '我是组员', '15555555555', '95155d692cba1bd4df6b3e9b8c7ef58a', 2, 11, 'http://ruyj27zul.hn-bkt.clouddn.com/R.jpg', '2023-05-20 01:30:49', '2023-05-20 01:30:49', 1);
INSERT INTO trand_platform_sys.user (user_id, username, phone, password, gender, role_id, avatar, create_time, update_time, status) VALUES (25, '头像默认设置', '19089080980', '95155d692cba1bd4df6b3e9b8c7ef58a', 1, 3, 'http://ruyj27zul.hn-bkt.clouddn.com/R.jpg', '2023-05-22 14:05:04', '2023-05-22 14:05:04', 1);
