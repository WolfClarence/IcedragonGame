drop table if exists `post`;
drop table if exists `game`;
drop table if exists `category`;
drop table if exists `reply`;
drop table if exists `user`;
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
                     (2,'元神奔跑把是世界上最好玩的游戏',123,3,'2023-2-2 1:1:1','http://baidu.com',10,'未审核','gengxuelong','https://picsum.photos/id/1/200/300','元神奔跑吧','控制一个元神,来会奔跑,豪华零氪',1,'文章初始化,尚未书写'),
                     (3,'元神奔跑把是世界上最好玩的游戏',123,3,'2023-2-2 1:1:1','http://baidu.com',10,'未审核','gengxuelong','https://picsum.photos/id/1/200/300','元神奔跑吧','控制一个元神,来会奔跑,豪华零氪',1,'文章初始化,尚未书写'),
                     (4,'元神奔跑把是世界上最好玩的游戏',123,3,'2023-2-2 1:1:1','http://baidu.com',10,'未审核','gengxuelong','https://picsum.photos/id/1/200/300','元神奔跑吧','控制一个元神,来会奔跑,豪华零氪',1,'文章初始化,尚未书写'),
                     (5,'元神奔跑把是世界上最好玩的游戏',123,3,'2023-2-2 1:1:1','http://baidu.com',10,'未审核','gengxuelong','https://picsum.photos/id/1/200/300','元神奔跑吧','控制一个元神,来会奔跑,豪华零氪',1,'文章初始化,尚未书写'),
                     (6,'元神奔跑把是世界上最好玩的游戏',123,3,'2023-2-2 1:1:1','http://baidu.com',10,'未审核','gengxuelong','https://picsum.photos/id/1/200/300','元神奔跑吧','控制一个元神,来会奔跑,豪华零氪',1,'文章初始化,尚未书写');

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

create table likes
(
    id int primary key auto_increment,
    post_id int not null ,
    username varchar(80) not null
);
insert into likes values
                      (1,1,'gengxuelong'),
                      (2,2,'gengxuelong'),
                      (3,3,'gengxuelong'),
                      (4,4,'gengxuelong'),
                      (4,5,'gengxuelong');

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
    user_status   varchar(20)  not null comment '用户的状态,当为 已封禁 时,用户不再可用,当再解封后为 已解封'
);
insert into user values
                     ('root','$2a$10$6c1iHYH2JeXQLpY9qMEvmuprGARa6DDVTfix/qEvswS7TQn/9sGvS','超级管理员',1,10,'超级管理员状态'),# 密码: root
                     ('gengxuelong','$2a$10$Pw0DUtXSc61k90b9qq.iwesEhWYaQ37hkJzoVoMfz0kMa7PmWflM2','耿雪龙',0,10,'一般用户');# 密码:123456


