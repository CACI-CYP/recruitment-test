create sequence student_seq;
create sequence exam_result_seq;

create table student (
    id bigint not null,
    full_name varchar(255) not null
);

create table exam_result (
    id bigint not null,
    name varchar(255) not null,
    result int,
    student_id bigint not null
);