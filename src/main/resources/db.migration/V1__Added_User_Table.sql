-- create sequence user_id_seq start with 1 increment by 1;

-- truncate table user_entity cascade ;
-- truncate table book_entity;


create table user_entity
(
    id         integer GENERATED ALWAYS AS IDENTITY,
    email      varchar(255)                not null,
    name       varchar(255)                not null,
    password   varchar(255)                not null,
    role       varchar(255)                not null,
    primary key (id),
    constraint user_email_unique unique (email)
);

INSERT INTO user_entity (name, email, password, role, year)
VALUES
    ('John Doe', 'john.doe@example.com', '$2a$12$aV3oEQGEC71wtV3R5R2Ss.I3WIJmgRQHsPN0mZvbGk0XhZ1f18YNu', 'ROLE_USER', 1990),
    ('Jane Smith', 'jane.smith@example.com', '$2a$12$aV3oEQGEC71wtV3R5R2Ss', 'ROLE_USER', 1985),
    ('Michael Johnson', 'michael.johnson@example.com', '$2a$12$aV3oEQGEC71wtV3R5R2Ss', 'ROLE_USER', 1995),
    ('Emily Brown', 'emily.brown@example.com', '$2a$12$aV3oEQGEC71wtV3R5R2Ss', 'ROLE_USER', 1982),
    ('William Davis', 'william.davis@example.com', '$2a$12$aV3oEQGEC71wtV3R5R2Ss', 'ROLE_USER', 1988),
    ('Olivia Wilson', 'olivia.wilson@example.com', '$2a$12$aV3oEQGEC71wtV3R5R2Ss', 'ROLE_USER', 1992),
    ('James Taylor', 'james.taylor@example.com', '$2a$12$aV3oEQGEC71wtV3R5R2Ss', 'ROLE_USER', 1986),
    ('Sophia Martinez', 'sophia.martinez@example.com', '$2a$12$aV3oEQGEC71wtV3R5R2Ss', 'ROLE_USER', 1997),
    ('Benjamin Anderson', 'benjamin.anderson@example.com', '$2a$12$aV3oEQGEC71wtV3R5R2Ss', 'ROLE_USER', 1994),
    ('Isabella Thomas', 'isabella.thomas@example.com', '$2a$12$aV3oEQGEC71wtV3R5R2Ss', 'ROLE_USER', 1980);

-- Add more rows as needed
