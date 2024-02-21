create table if not exists photoz (
    id bigint auto_increment primary key,
    file_name varchar(255),
    content_type varchar(255),
--    data binary(50000)
    data BLOB
);