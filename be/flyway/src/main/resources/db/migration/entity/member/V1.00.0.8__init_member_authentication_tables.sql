create table member_authentication
(
    id            bigint       not null auto_increment,
    create_at     datetime(6)  not null,
    deleted       bit          not null,
    update_at     datetime(6)  not null,
    member_fk     bigint       not null,
    certification varchar(255) not null,
    password      varchar(255) not null,
    primary key (id)
);
