insert into user(U_username,U_password) values ('testname','testname');
insert into user(U_username,U_password) values ('hello','hello');
insert into user(U_username,U_password) values ('world','world');


insert into project(P_name,P_context,P_preview,P_Uid) values ('星火','星火是一个制作星星与火焰特效模型的项目','/resources/images/1.jpeg',1);
insert into project(P_name,P_context,P_preview,P_Uid) values ('流体','流体是一个制作流体模型的项目','/resources/images/2.jpeg',1);
insert into project(P_name,P_context,P_preview,P_Uid) values ('火流体','火流体是一个制作流体模型的项目','/resources/images/2.jpeg',2);

insert into class(C_name,C_context) values ('角色','角色模型，多用于cg游戏等领域');


insert into model(M_name,M_preview,M_cid,M_pid) values ('小女孩','/resources/images/3.jpeg',1,2);
insert into model(M_name,M_preview,M_cid,M_pid) values ('男孩','/resources/images/4.jpeg',1,1);

insert into subscribe(Sub_uid,Sub_pid) values (3,2);
insert into subscribe(Sub_uid,Sub_pid) values (3,1);
insert into subscribe(Sub_uid,Sub_pid) values (3,3);
insert into subscribe(Sub_uid,Sub_pid) values (1,3);
insert into subscribe(Sub_uid,Sub_pid) values (2,1);
insert into subscribe(Sub_uid,Sub_pid) values (2,2);



insert into mark(Mark_mid,Mark_uid) values (1,1);
insert into mark(Mark_mid,Mark_uid) values (1,3);
insert into mark(Mark_mid,Mark_uid) values (2,2);
insert into mark(Mark_mid,Mark_uid) values (1,2);