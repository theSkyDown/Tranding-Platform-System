truncate trand_platform_sys.module;
INSERT INTO trand_platform_sys.module (module_name, url, is_menu) VALUES ('获取所有用户功能（分页、关键字搜索）', '/user/all', 2);
INSERT INTO trand_platform_sys.module (module_name, url, is_menu) VALUES ('用户添加功能（添加时无法分配角色）', '/user/add', 2);
INSERT INTO trand_platform_sys.module (module_name, url, is_menu) VALUES ('用户信息修改功能', '/user/update', 2);
INSERT INTO trand_platform_sys.module (module_name, url, is_menu) VALUES ('用户密码重置功能', '/user/reset/password', 2);
INSERT INTO trand_platform_sys.module (module_name, url, is_menu) VALUES ('用户删除功能（admin无法被删除）', '/user/delete', 2);
INSERT INTO trand_platform_sys.module (module_name, url, is_menu) VALUES ('用户批量删除功能（admin无法被删除）', '/user/delete/ids', 2);
INSERT INTO trand_platform_sys.module (module_name, url, is_menu) VALUES ('用户登陆功能', '/user/login', 0);
INSERT INTO trand_platform_sys.module (module_name, url, is_menu) VALUES ('用户详细信息查看功能', '/user/info', 0);
INSERT INTO trand_platform_sys.module (module_name, url, is_menu) VALUES ('用户密码修改功能', '/user/update/password', 0);
INSERT INTO trand_platform_sys.module (module_name, url, is_menu) VALUES ('获取所有角色信息功能（分页、关键字搜索）', '/role/all', 0);
INSERT INTO trand_platform_sys.module (module_name, url, is_menu) VALUES ('角色添加功能', '/role/add', 0);
INSERT INTO trand_platform_sys.module (module_name, url, is_menu) VALUES ('角色删除功能（无法删除基础角色）', '/role/delete', 0);
INSERT INTO trand_platform_sys.module (module_name, url, is_menu) VALUES ('暂时', '/temp', 0);
INSERT INTO trand_platform_sys.module (module_name, url, is_menu) VALUES ('用户状态切换功能（admin无法被禁用）', '/user/switch/status', 0);
INSERT INTO trand_platform_sys.module (module_name, url, is_menu) VALUES ('统计用户数量功能', '/user/total', 0);
INSERT INTO trand_platform_sys.module (module_name, url, is_menu) VALUES ('角色批量删除功能（无法删除基础角色）', '/role/delete/ids', 0);
INSERT INTO trand_platform_sys.module (module_name, url, is_menu) VALUES ('角色信息修改功能', '/role/update', 0);
INSERT INTO trand_platform_sys.module (module_name, url, is_menu) VALUES ('统计角色数量功能', '/role/total', 0);
INSERT INTO trand_platform_sys.module (module_name, url, is_menu) VALUES ('获取所有资源信息功能（分页、关键字搜素）', '/module/all', 0);
INSERT INTO trand_platform_sys.module (module_name, url, is_menu) VALUES ('资源的添加', '/module/add', 0);
INSERT INTO trand_platform_sys.module (module_name, url, is_menu) VALUES ('资源的删除', '/module/delete', 0);
INSERT INTO trand_platform_sys.module (module_name, url, is_menu) VALUES ('资源的批量删除', '/module/delete/ids', 0);
INSERT INTO trand_platform_sys.module (module_name, url, is_menu) VALUES ('资源的修改功能', '/module/update', 0);
INSERT INTO trand_platform_sys.module (module_name, url, is_menu) VALUES ('资源数量统计功能', '/module/total', 0);