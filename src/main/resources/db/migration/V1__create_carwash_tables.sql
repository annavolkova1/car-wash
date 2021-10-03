CREATE TABLE IF NOT EXISTS Users
(
    id        SERIAl      NOT NULL,
    username  VARCHAR(45) NOT NULL,
    password  VARCHAR(45) NOT NULL,
    user_role VARCHAR(45) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Amenities
(
    id               SERIAL       NOT NULL,
    amenity_name     VARCHAR(100) NOT NULL,
    duration_minutes INT          NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Scheduled_Amenities
(
    id         SERIAL  NOT NULL,
    user_id    INT     NOT NULL,
    amenity_id INT     NOT NULL,
    start_time TIMESTAMP,
    end_time   TIMESTAMP,
    active     BOOLEAN NOT NULL,
    live       BOOLEAN NOT NULL,


    PRIMARY KEY (id),
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
            REFERENCES users (id),
    CONSTRAINT fk_service
        FOREIGN KEY (amenity_id)
            REFERENCES amenities (id)
);
