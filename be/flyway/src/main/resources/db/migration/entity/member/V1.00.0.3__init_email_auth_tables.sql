create table email_auth
(
    id        bigint       not null auto_increment,
    create_at datetime(6)  not null,
    deleted   bit          not null,
    update_at datetime(6)  not null,
    member_fk bigint       not null,
    code      varchar(255) not null,
    email     varchar(255) not null,
    nonce     varchar(255) not null,
    primary key (id)
);
