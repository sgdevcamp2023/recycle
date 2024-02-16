create table request_review_history
(
    id                bigint      not null auto_increment,
    create_at         datetime(6) not null,
    deleted           bit         not null,
    update_at         datetime(6) not null,
    member_id         bigint      not null,
    question_id       bigint      not null,
    request_member_id bigint      not null,
    primary key (id)
);
