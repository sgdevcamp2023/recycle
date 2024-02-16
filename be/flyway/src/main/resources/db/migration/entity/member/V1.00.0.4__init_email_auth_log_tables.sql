create table email_auth_log
(
    id            bigint       not null auto_increment,
    create_at     datetime(6)  not null,
    deleted       bit          not null,
    update_at     datetime(6)  not null,
    email_auth_fk bigint       not null,
    member_fk     bigint       not null,
    reason        varchar(255) not null,
    try_cnt       bigint       not null,
    primary key (id)
);