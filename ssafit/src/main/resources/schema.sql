drop
    database ssafit;
CREATE
    DATABASE ssafit DEFAULT CHARACTER SET utf8mb4;

USE
    ssafit;

CREATE TABLE video
(
    id           VARCHAR(200),
    title        VARCHAR(500) NOT NULL,
    part         VARCHAR(50)  NOT NULL,
    channel_name VARCHAR(200) NOT NULL,
    view_cnt     INT          NOT NULL DEFAULT '0',
    PRIMARY KEY (id)
);

CREATE TABLE user
(
    user_id    VARCHAR(255) NOT NULL,
    password   VARCHAR(20)  NOT NULL,
    user_name  VARCHAR(20)  NOT NULL,
    email      VARCHAR(255) NOT NULL,
    birth_date DATE,
    sex        VARCHAR(10),
    profile    VARCHAR(20)  NOT NULL DEFAULT ('basic'),
    PRIMARY KEY (user_id)
);

CREATE TABLE review
(
    review_id INT AUTO_INCREMENT,
    profile   VARCHAR(20)  NOT NULL,
    content   TEXT         NOT NULL,
    user_id   VARCHAR(255) NOT NULL,
    user_name VARCHAR(20)  NOT NULL,
    video_id  VARCHAR(200) NOT NULL,
    reg_date  DATETIME     NOT NULL DEFAULT now(),
    star      INT          NOT NULL DEFAULT '0',
    PRIMARY KEY (review_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id) on delete cascade,
    FOREIGN KEY (video_id) REFERENCES video (id) on delete cascade
);

CREATE TABLE `like`
(
    video_id VARCHAR(200) NOT NULL,
    user_id  VARCHAR(255) NOT NULL,
    PRIMARY KEY (video_id, user_id),
    FOREIGN KEY (video_id) REFERENCES video (id) on delete cascade,
    FOREIGN KEY (user_id) REFERENCES user (user_id) on delete cascade
);
CREATE TABLE follow
(
    from_user VARCHAR(255) NOT NULL,
    to_user   VARCHAR(255) NOT NULL,
    PRIMARY KEY (from_user, to_user),
    FOREIGN KEY (from_user) REFERENCES user (user_id) on delete cascade,
    FOREIGN KEY (to_user) REFERENCES user (user_id) on delete cascade
);

CREATE TABLE calendar
(
    calendar_id INT AUTO_INCREMENT,
    user_id     VARCHAR(255) NOT NULL,
    date        DATE         NOT NULL,
    part        VARCHAR(50)  NOT NULL,
    start_time  TIME         NOT NULL,
    end_time    TIME         NOT NULL,
    video_url   VARCHAR(255),
    memo        TEXT,
    PRIMARY KEY (calendar_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id) on delete cascade
);

desc user;
desc video;

SELECT *
FROM video;

SELECT *
FROM review;
