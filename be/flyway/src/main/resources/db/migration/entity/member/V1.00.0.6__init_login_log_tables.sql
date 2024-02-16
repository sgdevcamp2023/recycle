create table login_log
(
    id        bigint       not null auto_increment,
    create_at datetime(6)  not null,
    deleted   bit          not null,
    update_at datetime(6)  not null,
    member_fk bigint       not null,
    status    integer      not null,
    useragent varchar(255) not null,
    primary key (id)
);
