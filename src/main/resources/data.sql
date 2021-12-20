insert into student (id, full_name)
values (next value for student_seq, 'Jane Doe');

insert into student (id, full_name)
values (next value for student_seq, 'John Doe');

insert into exam_result (id, name, result, student_id)
values (next value for exam_result_seq, 'Chemistry 100', 60, 1);

insert into exam_result (id, name, result, student_id)
values (next value for exam_result_seq, 'Maths 200', 55, 1);

insert into exam_result (id, name, result, student_id)
values (next value for exam_result_seq, 'English 120', 98, 1);

insert into exam_result (id, name, result, student_id)
values (next value for exam_result_seq, 'English 120', null, 1);

insert into exam_result (id, name, result, student_id)
values (next value for exam_result_seq, 'Chemistry 100', 70, 2);