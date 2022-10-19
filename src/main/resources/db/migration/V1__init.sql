create table advisors(
    id          serial primary key,
    cognito_id  text not null,
    email       text not null,
    first_name  varchar(50) not null,
    last_name   varchar(150) not null,
    role        text not null
);

create table applicants(
    id          serial primary key,
    cognito_id  text not null,
    email       text not null,
    first_name  varchar(50) not null,
    last_name   varchar(150) not null,
    ssn         varchar(10) not null,
    city        varchar(50),
    street      varchar(50),
    str_number  varchar(50),
    zip         varchar(10),
    apt         varchar(3)
);

create table phones(
    applicant_id    bigint references applicants(id) not null,
    phone_type      text not null,
    phone_number    varchar(10) not null
);

create table applications(
    id          serial primary key,
    amount      decimal(10, 2) not null,
    status      text not null,
    created_at  timestamp not null default now(),
    assigned_at timestamp,
    resolved_at timestamp,
    applicant_id bigint references applicants(id) not null,
    advisor_id      bigint references advisors(id)
);

insert into advisors(cognito_id, email, first_name, last_name, role)
values ('UUID_1000', 'blackJack@gmail.com', 'jack', 'black', 'ASSOCIATE'),
       ('UUID_1001', 'whiteJack@gmail.com', 'jack', 'white', 'PARTNER'),
       ('UUID_1002', 'yellowJack@gmail.com', 'jack', 'yellow', 'SENIOR');

insert into applicants(cognito_id, email, first_name, last_name, ssn)
values ('UUID_1003', 'greenJack', 'jack', 'green', '1234567890'),
       ('UUID_1004', 'blueJack', 'jack', 'blue', '1234567891');

insert into applications(amount, status, applicant_id)
values (10000, 'NEW', 1),
       (55000, 'NEW', 2);