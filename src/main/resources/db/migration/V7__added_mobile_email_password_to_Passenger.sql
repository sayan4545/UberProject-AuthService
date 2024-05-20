ALTER TABLE passenger
    ADD mobile_number varchar(100) not null ,
    ADD email varchar(100) not null,
    ADD password varchar(100) not null,
    MODIFY passenger_name varchar(100) not null;

