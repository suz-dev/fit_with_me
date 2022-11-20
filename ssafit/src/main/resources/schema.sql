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
    part         VARCHAR(50) NOT NULL,
    channel_name VARCHAR(20) NOT NULL,
    view_cnt     INT         NOT NULL DEFAULT '0',
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
    profile VARCHAR(20) NOT NULL DEFAULT('basic'),
    PRIMARY KEY (user_id)
);

CREATE TABLE review
(
    review_id INT AUTO_INCREMENT,
    profile VARCHAR(20) NOT NULL,
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
###수정
테스트..................
############ 수정 완
desc user;
desc video;

SELECT *
FROM video;

SELECT *
FROM review;

SELECT r.title, r.content, r.view_cnt
FROM review r,
     user u
WHERE r.video_id = "swRNeYw1JkY"
  and r.user_seq = u.user_seq;

desc review;

INSERT INTO video(title, part, id, channel_name)
VALUES ("전신 다이어트 최고의 운동 [칼소폭 찐 핵핵매운맛]", "전신", "gMaB-fG4u4g", "ThankyouBUBU");

INSERT INTO video(title, part, id, channel_name)
VALUES ("하루 15분! 전신 칼로리 불태우는 다이어트 운동", "전신", "swRNeYw1JkY", "ThankyouBUBU");

INSERT INTO video(title, part, id, channel_name)
VALUES ("상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]", "상체", "54tTYO-vU2E", "ThankyouBUBU");


INSERT INTO video(title, part, id, channel_name)
VALUES ("상체비만 다이어트 최고의 운동 [상체 핵매운맛]", "상체", "QqqZH3j_vH0", "ThankyouBUBU");

INSERT INTO video(title, part, id, channel_name)
VALUES ("하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]", "하체", "tzN6ypk6Sps", "김강민");

INSERT INTO video(title, part, id, channel_name)
VALUES ("저는 하체 식주의자 입니다", "하체", "u5OgcZdNbMo", "GYM종국");

INSERT INTO video(title, part, id, channel_name)
VALUES ("11자복근 복부 최고의 운동 [복근 핵매운맛]", "복부", "PjGcOP-TQPE", "ThankyouBUBU");

INSERT INTO video(title, part, id, channel_name)
VALUES ("(Sub)누워서하는 5분 복부운동!! 효과보장! (매일 2주만 해보세요!)", "복부", "7TLk7pscICk", "SomiFit");

INSERT INTO user
VALUES (0, "userId", "password", "name");

INSERT INTO review(title, content, user_seq, user_id, video_id, star)
VALUES ("리뷰 테스트", "리뷰 내용", 1, "userId", "gMaB-fG4u4g", 0);

SELECT *
FROM user;

INSERT INTO wish (video_id, user_seq)
VALUES ("PjGcOP-TQPE", 1);

SELECT *
FROM wish;

SELECT video_id
FROM wish
WHERE user_seq = 1;

SELECT *
FROM video
WHERE id IN (SELECT video_id
             FROM wish
             WHERE user_seq = 1);

SELECT *
FROM follow;

INSERT INTO follow
VALUES (1, 2);

SELECT *
FROM user
WHERE user_seq IN (SELECT to_user FROM follow WHERE from_user = 1);

SELECT u.user_name, v.title
FROM wish w,
     video v,
     user u
WHERE w.user_seq IN (SELECT to_user FROM follow WHERE from_user = 1)
  and w.video_id = v.id
  and w.user_seq = u.user_seq;
