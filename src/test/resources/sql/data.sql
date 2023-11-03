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

-- table account_group
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

-- table lesson
insert into lesson(group_id,lesson_title,lesson_description,lesson_type,teacher_id,lesson_date,start_time,end_time,module_id,link_lms,link_zoom,archived)
values (1, 'basic_programming/lesson_52','Smalltalk about homework Questions Binary seek in arrays','consultation',1,'2023-11-27','10:15:30','12:15:30',1,'https://lms.ait-tr.de/#/group/cohort26/module/basic_programming/lesson/lesson_52','https://us05web.zoom.us/j/82073564366?pwd=LqOp9ojsiqseacSVwFPyt2jZA2tqUI.1',false);
insert into lesson(group_id,lesson_title,lesson_description,lesson_type,teacher_id,lesson_date,start_time,end_time,module_id,link_lms,link_zoom,archived)
values (2, 'basic_programming/lesson_53','Smalltalk about homework Questions Binary seek in arrays','consultation',2,'2023-11-28','10:15:30','12:15:30',1,'https://lms.ait-tr.de/#/group/cohort26/module/basic_programming/lesson/lesson_53','https://us05web.zoom.us/j/82073564366?pwd=LqOp9ojsiqseacSVwFPyt2jZA2tqUI.1',false);
insert into lesson(group_id,lesson_title,lesson_description,lesson_type,teacher_id,lesson_date,start_time,end_time,module_id,link_lms,link_zoom,archived)
values (1, 'basic_programming/lesson_54','Smalltalk about homework Questions Binary seek in arrays','consultation',1,'2023-11-29','10:15:30','12:15:30',1,'https://lms.ait-tr.de/#/group/cohort26/module/basic_programming/lesson/lesson_54','https://us05web.zoom.us/j/82073564366?pwd=LqOp9ojsiqseacSVwFPyt2jZA2tqUI.1',false);
insert into lesson(group_id,lesson_title,lesson_description,lesson_type,teacher_id,lesson_date,start_time,end_time,module_id,link_lms,link_zoom,archived)
values (2, 'basic_programming/lesson_55','Smalltalk about homework Questions Binary seek in arrays','consultation',2,'2023-11-30','10:15:30','12:15:30',1,'https://lms.ait-tr.de/#/group/cohort26/module/basic_programming/lesson/lesson_55','https://us05web.zoom.us/j/82073564366?pwd=LqOp9ojsiqseacSVwFPyt2jZA2tqUI.1',false);
