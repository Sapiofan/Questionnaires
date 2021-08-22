
create table users
(
    id         bigserial primary key,
    nickname   text        not null,
    password   text        not null,
    created_at timestamp not null
)

create table surveys
(
    id bigserial not null primary key,
    survey_name text not null,
    size int,
    user_id bigint references users(id) on delete cascade
)
create table questions
(
    id bigserial not null primary key,
    description text not null,
    survey_id bigint references surveys(id) on delete cascade
)

create table answers
(
    id bigserial not null primary key,
    answer text not null,
    correctness boolean not null,
    question_id bigint references questions(id) on delete cascade
);




DROP TABLE answers, questions, surveys, users;

DROP SCHEMA public CASCADE;
CREATE SCHEMA public;