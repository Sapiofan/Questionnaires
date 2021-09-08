create table users
(
    id         bigserial primary key,
    nickname   text unique not null,
    password   text not null,
    created_at timestamp default now()
);

create table surveys
(
    id bigserial primary key,
    number int not null,
    name text not null,
    description text,
    size int,
    user_id bigint references users(id) on delete cascade
);
create table questions
(
    id bigserial primary key,
    number int not null,
    description text,
    survey_id bigint references surveys(id) on delete cascade
);

create table answers
(
    id bigserial primary key,
    number int not null,
    answer text not null,
    correctness boolean not null,
    question_id bigint references questions(id) on delete cascade
);

create table results
(
    id uuid primary key,
    start timestamp default now(),
    end_time timestamp default now(),
    user_id bigint references users(id) on delete cascade,
    survey_id bigint references surveys(id) on delete cascade
);

create table right_answers
(
    id bigserial primary key,
    result_id uuid references results(id) on delete cascade,
    question_id bigint references questions(id) on delete cascade,
    a_boolean boolean not null
);

create table questionnaires
(
    id bigserial primary key,
    number int not null,
    name text not null,
    size int,
    general_description text,
    user_id bigint references users(id) on delete cascade
);

create table questionnaire_questions
(
    id bigserial primary key,
    number int not null,
    name text not null,
    questionnaire_id bigint references questionnaires(id) on delete cascade
);

create table descriptions
(
    id bigserial primary key,
    number int not null,
    questionnaire_id bigint references questionnaires(id) on delete cascade,
    description text,
    start_scale int not null,
    end_scale int not null
);

create table questionnaire_results
(
    id uuid primary key,
    start timestamp default now(),
    end_time timestamp default now(),
    user_id bigint references users(id) on delete cascade,
    questionnaire_id bigint references questionnaires(id) on delete cascade
);

create table evaluated_questions
(
    id bigserial primary key,
    result_id uuid references questionnaire_results(id) on delete cascade,
    question_id bigint references questionnaire_questions(id) on delete cascade,
    grade int not null
);

create table scales
(
    id          int  not null primary key,
    description text not null
);

insert into scales (id, description)
values (1, 'FIVE');
insert into scales (id, description)
values (2, 'TEN');

DROP SCHEMA public CASCADE;
CREATE SCHEMA public;