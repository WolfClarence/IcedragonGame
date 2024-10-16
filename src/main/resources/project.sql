drop table if exists `post`;
drop table if exists `category`;
drop table if exists `reply`;
drop table if exists `user`;
drop table if exists `attentions`;
create table post
(
    id  int  auto_increment  primary key,
    title        varchar(200)    not null,
    scan_num     int  not null default 0,
    reply_num    int  not null default 0,
    build_time   datetime        not null,
    download_url varchar(200)    not null,
    points       int  not null default 0,
    audit_status varchar(100)    not null default '未审核' comment '未审核 审核通过 审核未通过',
    username     varchar(80)     not null,
    image_url    varchar(100)    null,
    game_name      varchar(100)             not null,
    game_description varchar(500)  null ,
    category_id int not null ,
    content   longtext null
) comment='文章表';
insert into post values
                     (1,'元神奔跑把是世界上最好玩的游戏',123,3,'2023-2-2 1:1:1','http://baidu.com',10,'未审核','gengxuelong','https://picsum.photos/id/1/200/300','元神奔跑吧','控制一个元神,来会奔跑,豪华零氪',1,'文章初始化,尚未书写'),
                     (2,'111',123,3,'2013-2-2 1:1:1','http://baidu.com',10,'未审核','gengxuelong','https://picsum.photos/id/1/200/300','元神奔跑吧','控制一个元神,来会奔跑,豪华零氪',1,'文章初始化,尚未书写'),
                     (3,'222',221,3,'2031-2-2 1:1:1','http://baidu.com',10,'未审核','gengxuelong','https://picsum.photos/id/1/200/300','元神奔跑吧','控制一个元神,来会奔跑,豪华零氪',1,'文章初始化,尚未书写'),
                     (4,'333',123,3,'2053-2-2 1:1:1','http://baidu.com',10,'未审核','gengxuelong','https://picsum.photos/id/1/200/300','元神奔跑吧','控制一个元神,来会奔跑,豪华零氪',1,'文章初始化,尚未书写'),
                     (5,'111bbb',123,3,'1023-2-2 1:1:1','http://baidu.com',10,'未审核','gengxuelong','https://picsum.photos/id/1/200/300','元神奔跑吧','控制一个元神,来会奔跑,豪华零氪',1,'文章初始化,尚未书写'),
                     (7,'111aaa',123,3,'2223-2-2 1:1:1','http://baidu.com',10,'未审核','gengxuelong','https://picsum.photos/id/1/200/300','元神奔跑吧','控制一个元神,来会奔跑,豪华零氪',1,'文章初始化,尚未书写'),
                     (8,'111aaa',123,3,'2123-2-2 1:1:1','http://baidu.com',10,'未审核','gengxuelong','https://picsum.photos/id/1/200/300','元神奔跑吧','控制一个元神,来会奔跑,豪华零氪',1,'文章初始化,尚未书写'),
                     (9,'111aaa',123,3,'2023-2-2 1:1:1','http://baidu.com',10,'未审核','gengxuelong','https://picsum.photos/id/1/200/300','元神奔跑吧','控制一个元神,来会奔跑,豪华零氪',1,'文章初始化,尚未书写'),
                     (10,'111aaa',123,3,'2023-2-2 1:1:1','http://baidu.com',10,'未审核','gengxuelong','https://picsum.photos/id/1/200/300','元神奔跑吧','控制一个元神,来会奔跑,豪华零氪',1,'文章初始化,尚未书写'),
                     (11,'111aaa',123,3,'2023-2-2 1:1:1','http://baidu.com',10,'未审核','gengxuelong','https://picsum.photos/id/1/200/300','元神奔跑吧','控制一个元神,来会奔跑,豪华零氪',1,'文章初始化,尚未书写'),
                     (12,'111aaa',123,3,'2023-2-2 1:1:1','http://baidu.com',10,'未审核','gengxuelong','https://picsum.photos/id/1/200/300','元神奔跑吧','控制一个元神,来会奔跑,豪华零氪',1,'文章初始化,尚未书写'),
                     (13,'111aaa',12121,3,'2023-2-2 1:1:1','http://baidu.com',10,'未审核','gengxuelong','https://picsum.photos/id/1/200/300','元神奔跑吧','控制一个元神,来会奔跑,豪华零氪',1,'文章初始化,尚未书写'),
                     (14,'111aaa',1,3,'2023-2-2 1:1:1','http://baidu.com',10,'未审核','gengxuelong','https://picsum.photos/id/1/200/300','元神奔跑吧','控制一个元神,来会奔跑,豪华零氪',1,'文章初始化,尚未书写'),
                     (15,'111aaa',123,3,'3023-2-2 1:1:1','http://baidu.com',10,'未审核','gengxuelong','https://picsum.photos/id/1/200/300','元神奔跑吧','控制一个元神,来会奔跑,豪华零氪',1,'文章初始化,尚未书写'),
                     (16,'111aaa',123,3,'2123-2-2 1:1:1','http://baidu.com',10,'未审核','gengxuelong','https://picsum.photos/id/1/200/300','元神奔跑吧','控制一个元神,来会奔跑,豪华零氪',1,'文章初始化,尚未书写'),
                     (17,'111aaa',123,3,'2023-2-2 1:1:1','http://baidu.com',10,'未审核','gengxuelong','https://picsum.photos/id/1/200/300','王者农药','控制一个元神,来会奔跑,豪华零氪',1,'文章初始化,尚未书写'),
                     (18,'111aaa',123,3,'2022-2-2 1:1:1','http://baidu.com',10,'未审核','gengxuelong','https://picsum.photos/id/1/200/300','王者农药','控制一个元神,来会奔跑,豪华零氪',1,'文章初始化,尚未书写'),
                     (19,'111aaa',123,3,'2023-2-2 1:1:1','http://baidu.com',10,'未审核','gengxuelong','https://picsum.photos/id/1/200/300','王者农药','控制一个元神,来会奔跑,豪华零氪',1,'文章初始化,尚未书写'),
                     (20,'111aaa',3213,3,'2023-2-2 1:1:1','http://baidu.com',10,'未审核','gengxuelong','https://picsum.photos/id/1/200/300','王者农药','控制一个元神,来会奔跑,豪华零氪',1,'文章初始化,尚未书写'),
                     (21,'111aaa',1,3,'2023-2-2 1:1:1','http://baidu.com',10,'未审核','gengxuelong','https://picsum.photos/id/1/200/300','王者农药','控制一个元神,来会奔跑,豪华零氪',1,'文章初始化,尚未书写'),
                     (22,'111aaa',123,3,'6023-2-2 1:1:1','http://baidu.com',10,'未审核','gengxuelong','https://picsum.photos/id/1/200/300','王者农药','控制一个元神,来会奔跑,豪华零氪',1,'文章初始化,尚未书写');

create table category(
    id int primary key  auto_increment,
    category_name varchar(80) not null
);
insert into category values
                         (1,'竞技类'),
                         (2,'休闲类'),
                         (3,'养成类'),
                         (4,'策略类'),
                         (5,'即时战略类');

create table attentions
(
    id int primary key auto_increment,
    post_id int not null ,
    username varchar(80) not null
);
insert into attentions values
                      (1,1,'gengxuelong'),
                      (2,2,'gengxuelong'),
                      (3,3,'gengxuelong'),
                      (4,4,'gengxuelong'),
                      (5,5,'gengxuelong');

create table reply
(
    id      int   auto_increment primary key ,
    reply_context varchar(45)  not null,
    build_time    datetime     not null,
    post_id       int  not null,
    username varchar(80)
);
insert into reply values
                      (1,'元神确实很好玩','2022-2-2 2:2:2',1,'gengxuelong'),
                      (2,'使命召唤确实很好玩','2022-2-2 2:2:2',2,'gengxuelong'),
                      (3,'王者荣耀很好玩','2022-2-2 2:2:2',3,'gengxuelong'),
                      (4,'英雄联盟确实很好玩','2022-2-2 2:2:2',4,'gengxuelong'),
                      (5,'奔跑吧少年确实很好玩','2022-2-2 2:2:2',5,'gengxuelong');


create table user
(
    username      varchar(80)  primary key,
    password      varchar(150) not null,
    user_nickname varchar(80)  not null unique ,
    user_identity int          not null comment '1:管理员,0:普通用户',
    user_points   int          not null comment '用户的积分',
    user_status   varchar(20)  not null comment '用户的状态,当为 已封禁 时,用户不再可用,当再解封后为 已解封',
    image_url varchar(150) null

);
insert into user values
                     ('root','$2a$10$B982SlXnTicDF0aB5fgFE.RRvDtEgV96kRJm12X8tPxnWIqkJma2G','超级管理员',1,10,'超级管理员状态','https://picsum.photos/id/1/200/300'),# 密码: root
                     ('gengxuelong','$2a$10$u2SJSxQ.E5V.DeJasBTDcOySjoDNd.D09o9cFKZ2CRtvZaX9vt9dK','耿雪龙',0,10,'一般用户','https://picsum.photos/id/1/200/300'),# 密码:123456
                     ('wenrui','$2a$10$u2SJSxQ.E5V.DeJasBTDcOySjoDNd.D09o9cFKZ2CRtvZaX9vt9dK','文睿',0,10,'一般用户','https://picsum.photos/id/1/200/300'),# 密码:123456
                     ('haha','$2a$10$u2SJSxQ.E5V.DeJasBTDcOySjoDNd.D09o9cFKZ2CRtvZaX9vt9dK','fafs',0,10,'一般用户','https://picsum.photos/id/1/200/300'),# 密码:123456
                     ('xixi','$2a$10$u2SJSxQ.E5V.DeJasBTDcOySjoDNd.D09o9cFKZ2CRtvZaX9vt9dK','dsfsfs',0,10,'一般用户','https://picsum.photos/id/1/200/300'),# 密码:123456
                     ('didi','$2a$10$u2SJSxQ.E5V.DeJasBTDcOySjoDNd.D09o9cFKZ2CRtvZaX9vt9dK','ewsf',0,10,'一般用户','https://picsum.photos/id/1/200/300'),# 密码:123456
                     ('sisi','$2a$10$u2SJSxQ.E5V.DeJasBTDcOySjoDNd.D09o9cFKZ2CRtvZaX9vt9dK','fsfasw',0,10,'一般用户','https://picsum.photos/id/1/200/300');# 密码:123456


