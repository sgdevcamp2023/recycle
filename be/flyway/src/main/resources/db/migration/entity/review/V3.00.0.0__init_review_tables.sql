create table comment_entity (
                                comment_id bigint not null auto_increment,
                                author varchar(255),
                                author_id bigint,
                                content varchar(255),
                                created_at datetime(6),
                                is_deleted bit not null,
                                parent_id bigint,
                                question_id bigint,
                                updated_at datetime(6),
                                primary key (comment_id)
);

create table question_entity (
                                 question_id bigint not null auto_increment,
                                 author varchar(255),
                                 author_id bigint,
                                 content varchar(10000),
                                 created_at datetime(6),
                                 deleted_at datetime(6),
                                 is_deleted bit default false not null,
                                 review_cnt integer,
                                 primary key (question_id)
);

create table question_request_entity (
                                         question_req_id bigint not null auto_increment,
                                         question_id bigint,
                                         receiver varchar(255),
                                         receiver_id bigint,
                                         requester varchar(255),
                                         requester_id bigint,
                                         primary key (question_req_id)
);

create table reviewer_list_entity (
                                      reviewer_list_id bigint not null auto_increment,
                                      question_id bigint,
                                      reviewer_id bigint,
                                      reviewer_name varchar(255),
                                      primary key (reviewer_list_id)
);

create table review_entity (
                               review_id bigint not null auto_increment,
                               author varchar(255),
                               author_id bigint,
                               content varchar(10000),
                               created_at datetime(6),
                               deleted_at datetime(6),
                               end_point_index integer,
                               end_point_point integer,
                               is_deleted bit default false not null,
                               question_id bigint,
                               start_point_index integer,
                               start_point_point integer,
                               tag integer,
                               updated_at datetime(6),
                               primary key (review_id)
);