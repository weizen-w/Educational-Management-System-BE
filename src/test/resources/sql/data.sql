-- table account
insert into account(hash_password,first_name,last_name,email,role,account_state)
values ('Qwerty007!', 'Student1', 'Name1', 'Student1@gmail.com', 'STUDENT', 'CONFIRMED');
insert into account(hash_password,first_name,last_name,email,role,account_state)
values ('Qwerty007!', 'Student2', 'Name2', 'Student2@gmail.com', 'STUDENT', 'CONFIRMED');
insert into account(hash_password,first_name,last_name,email,role,account_state)
values ('Qwerty007!', 'Student3', 'Name3', 'Student3@gmail.com', 'STUDENT', 'CONFIRMED');
insert into account(hash_password,first_name,last_name,email,role,account_state)
values ('Qwerty007!', 'Admin', 'Admin', 'admin@gmail.com', 'ADMIN', 'CONFIRMED');

-- table course
insert into course(course_name,archived)
values ('Course 1', false);
insert into course(course_name,archived)
values ('Course 2', false);
insert into course(course_name,archived)
values ('Course 3', false);
insert into course(course_name,archived)
values ('Course 4', false);

-- table student_group
insert into student_group(group_name,course_id,archived)
values ('Group 1', 1, false);
insert into student_group(group_name,course_id,archived)
values ('Group 2', 2, false);
insert into student_group(group_name,course_id,archived)
values ('Group 3', 3, false);
insert into student_group(group_name,course_id,archived)
values ('Group 4', 4, false);

-- table student_group
insert into account_group(account_id,group_id,main_group)
values (1, 1, true);
insert into account_group(account_id,group_id,main_group)
values (2, 2, true);
insert into account_group(account_id,group_id,main_group)
values (3, 3, true);
insert into account_group(account_id,group_id,main_group)
values (4, 4, true);

-- table module
insert into module(module_name,archived)
values ('Module 1', false);
insert into module(module_name,archived)
values ('Module 2', false);
insert into module(module_name,archived)
values ('Module 3', false);
insert into module(module_name,archived)
values ('Module 4', false);
