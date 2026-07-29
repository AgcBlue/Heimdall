create table if not exists log_events 
(
    id int unsigned auto_increment primary key,
    time_stamp timestamp,
    ip varchar(50),
    username varchar(100),
    action varchar(100),
    status varchar(20)
);