create table member
(
    id        bigint       not null auto_increment,
    create_at datetime(6)  not null,
    deleted   bit          not null,
    update_at datetime(6)  not null,
    resource  json,
    member_st varchar(255) not null,
    primary key (id)
);
