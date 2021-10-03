INSERT INTO users (username, password, user_role)
VALUES ('John', 'veryDifficultPassword', 'USER'),
       ('Mary', 'password', 'USER'),
       ('Admin', 'adminPassword', 'ADMIN');


INSERT INTO amenities (amenity_name, duration_minutes)
VALUES ('signUpForACarWash', 2),
       ('seeTheWaitingTime', 1);

INSERT INTO scheduled_amenities (user_id, amenity_id, start_time, end_time, active, live)
VALUES (2, 1, '2021-10-02 21:12:03.517000', '2021-10-02 21:14:03.517000', FALSE, TRUE),
       (1, 2, '2021-10-02 22:35:03.517000', '2021-10-02 22:36:03.517000', TRUE, FALSE),
       (3, 2, '2021-10-02 23:35:03.517000', '2021-10-02 23:36:03.517000', TRUE, TRUE),
       (1, 1, '2021-10-02 23:35:03.517000', '2021-10-02 23:37:03.517000', TRUE, FALSE),
       (2, 1, '2021-10-02 22:45:03.517000', '2021-10-02 22:47:03.517000', TRUE, TRUE),
       (3, 2, '2021-10-02 23:35:03.517000', '2021-10-02 23:36:03.517000', TRUE, FALSE);