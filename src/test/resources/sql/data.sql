-- table courses
insert into courses(course_name, is_archived)
values ('Course 1', false);

insert into courses(course_name, is_archived)
values ('Course 2', false);

insert into courses(course_name, is_archived)
values ('Course 3', false);

insert into courses(course_name, is_archived)
values ('Course 4', false);
-- table student_groups
insert into student_groups(group_name,course_id,is_archived)
values ('Group 1', 1, false);

insert into student_groups(group_name,course_id,is_archived)
values ('Group 2', 2, false);

insert into student_groups(group_name,course_id,is_archived)
values ('Group 3', 3, false);

insert into student_groups(group_name,course_id,is_archived)
values ('Group 4', 4, false);