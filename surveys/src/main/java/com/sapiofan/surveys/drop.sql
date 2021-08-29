create table users
(
    id         bigserial primary key,
    nickname   text,
    password   text,
    created_at timestamp default now()
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

create table surveys
(
    id bigserial primary key,
    name text,
    size int,
    user_id bigint references users(id) on delete cascade
);
create table questions
(
    id bigserial primary key,
    number int,
    description text,
    survey_id bigint references surveys(id) on delete cascade
);

create table answers
(
    id bigserial primary key,
    number int,
    answer text,
    correctness boolean,
    question_id bigint references questions(id) on delete cascade
);



DROP SCHEMA public CASCADE;
CREATE SCHEMA public;