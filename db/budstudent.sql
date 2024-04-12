DROP DATABASE IF EXISTS turing;
CREATE DATABASE turing;
USE turing;

DROP TABLE IF EXISTS tb_stu_user;
CREATE TABLE tb_stu_user
(
    id                BIGINT       NOT NULL PRIMARY KEY,
    wx_id             VARCHAR(255)          DEFAULT NULL,
    hw_id             VARCHAR(255)          DEFAULT NULL,
    id_card           VARCHAR(255) NOT NULL,
    phone             VARCHAR(255)          DEFAULT NULL,
    password          VARCHAR(255) NOT NULL,
    active_status     BOOLEAN      NOT NULL DEFAULT FALSE,
    ban_expire_status BOOLEAN      NOT NULL DEFAULT FALSE,
    create_at         TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    update_at         TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY id_card_unique (id_card)
);

DROP TABLE IF EXISTS tb_stu_info;
CREATE TABLE tb_stu_info
(
    id             BIGINT       NOT NULL PRIMARY KEY,
    id_card        VARCHAR(255) NOT NULL,
    name           VARCHAR(50)  NOT NULL,
    gender         VARCHAR(10)  NOT NULL,
    notice_number  VARCHAR(255) NOT NULL COMMENT '录取通知书编号',
    exam_number    VARCHAR(255) NOT NULL COMMENT '高考考生号',
    school         VARCHAR(10)  NOT NULL,
    college        VARCHAR(10)  NOT NULL,
    major          VARCHAR(10)  NOT NULL,
    user_class     VARCHAR(255) NOT NULL,
    signature      VARCHAR(255) DEFAULT NULL,
    postal_code    VARCHAR(6)   NOT NULL,
    home_address   VARCHAR(255) NOT NULL,
    avatar_url     VARCHAR(255),
    real_photo_url VARCHAR(255),
    create_at      TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    update_at      TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY id_card_unique (id_card)
);

DROP TABLE IF EXISTS tb_stu_persona;
CREATE TABLE tb_stu_persona
(
    id            INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(50)  NOT NULL,
    gender        VARCHAR(10)  NOT NULL,
    url           VARCHAR(255) NOT NULL,
    require_point INT          NOT NULL,
    create_time   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS tb_stu2persona;
CREATE TABLE tb_stu2persona
(
    student_id BIGINT NOT NULL,
    persona_id INT    NOT NULL,
    PRIMARY KEY (student_id, persona_id)
);

DROP TABLE IF EXISTS tb_stu_persona_now;
CREATE TABLE tb_stu_persona_now
(
    student_id BIGINT NOT NULL,
    persona_id INT    NOT NULL,
    PRIMARY KEY (student_id, persona_id)
);

DROP TABLE IF EXISTS tb_stu_login_record;
CREATE TABLE `tb_stu_login_record`
(
    `id`         INT          NOT NULL AUTO_INCREMENT,
    `student_id` BIGINT       NOT NULL,
    `ip`         varchar(50)  NOT NULL,
    `city`       varchar(255) NOT NULL,
    `status`     tinyint(1)   NOT NULL,
    `time`       timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS tb_stu_point;
CREATE TABLE tb_stu_point
(
    id                       BIGINT       NOT NULL PRIMARY KEY,
    id_card                  VARCHAR(255) NOT NULL,
    school_code              VARCHAR(10)  NOT NULL,
    task_completed_count     INT       DEFAULT 0,
    last_completed_task_time TIMESTAMP DEFAULT NULL,
    points_total             INT       DEFAULT 0 COMMENT '总获取积分',
    points_now               INT       DEFAULT 0 COMMENT '当前剩余积分',
    UNIQUE KEY id_card_unique (id_card)
);

DROP TABLE IF EXISTS tb_stu_point_record;
CREATE TABLE tb_stu_point_record
(
    id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    student_id  BIGINT       NOT NULL,
    reason      VARCHAR(255) NOT NULL,
    points      INT          NOT NULL,
    create_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS tb_admin_user;
CREATE TABLE tb_admin_user
(
    id          BIGINT       NOT NULL PRIMARY KEY,
    nickname    VARCHAR(255) NOT NULL DEFAULT 'Admin',
    username    VARCHAR(255) NOT NULL,
    phone       VARCHAR(20)  NOT NULL,
    password    VARCHAR(255) NOT NULL,
    school      VARCHAR(10)  NOT NULL,
    delete_flag BOOLEAN      NOT NULL DEFAULT TRUE,
    role        BOOLEAN      NOT NULL DEFAULT FALSE COMMENT '默认false为普通学校管理员',
    create_at   TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    update_at   TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS tb_admin_login_record;
CREATE TABLE `tb_admin_login_record`
(
    `id`       int          NOT NULL AUTO_INCREMENT,
    `admin_id` BIGINT       NOT NULL,
    `ip`       varchar(50)  NOT NULL,
    `city`     varchar(255) NOT NULL,
    `status`   tinyint(1)   NOT NULL,
    `time`     timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS tb_school;
CREATE TABLE tb_school
(
    school_code  VARCHAR(10)  NOT NULL PRIMARY KEY,
    name         VARCHAR(50)  NOT NULL,
    address      VARCHAR(255) NOT NULL,
    postal_code  CHAR(6)      NOT NULL,
    call_number  VARCHAR(50)  NOT NULL,
    introduction TEXT,
    website_url  VARCHAR(255)
);

DROP TABLE IF EXISTS tb_college;
CREATE TABLE tb_college
(
    school_code  VARCHAR(10) NOT NULL,
    collage_code VARCHAR(10) NOT NULL,
    name         VARCHAR(50) NOT NULL,
    introduction TEXT,
    website_url  VARCHAR(255)
);

DROP TABLE IF EXISTS tb_major;
CREATE TABLE tb_major
(
    major_code  VARCHAR(10) NOT NULL PRIMARY KEY,
    name        VARCHAR(50) NOT NULL,
    degree_type VARCHAR(50) NOT NULL,
    duration    INT         NOT NULL
);

DROP TABLE IF EXISTS tb_college2major;
CREATE TABLE tb_college2major
(
    school_code  VARCHAR(10) NOT NULL,
    college_code VARCHAR(10) NOT NULL,
    major_code   VARCHAR(10) NOT NULL,
    PRIMARY KEY (school_code, college_code, major_code)
);

DROP TABLE IF EXISTS tb_task_online;
CREATE TABLE tb_task_online
(
    id          INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    school_code VARCHAR(10) NOT NULL,
    creator_id  BIGINT      NOT NULL,
    topic       VARCHAR(50) NOT NULL,
    description TEXT        NOT NULL,
    obj_id      INT,
    point_award INT         NOT NULL,
    date        DATE        NOT NULL,
    create_time TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS tb_task;
CREATE TABLE tb_task
(
    id             INT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    creator_id     BIGINT    NOT NULL,
    school_code    VARCHAR(10),
    task_sub_id    INT,
    type           INT       NOT NULL,
    topic          VARCHAR(50),
    description    JSON      NOT NULL,
    obj_id         INT,
    answer         CHAR(1),
    location       POINT,
    radius         INT,
    award          INT       NOT NULL,
    parent_task_id INT,
    pre_task_id    INT,
    start_time     DATE      NOT NULL,
    end_time       DATE      NOT NULL,
    create_time    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS tb_task_object;
CREATE TABLE tb_task_object
(
    id             INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    object_name    VARCHAR(50) NOT NULL,
    object_name_zh VARCHAR(50) NOT NULL
);

INSERT INTO tb_task_object (id, object_name, object_name_zh)
VALUES (1, 'charger', '充电器'),
       (2, 'cup', '杯子'),
       (3, 'suitcase', '手提箱'),
       (4, 'ID', '身份证'),
       (5, 'mouse', '鼠标');

DROP TABLE IF EXISTS tb_task_complete;
CREATE TABLE tb_task_complete
(
    id             INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    school_code    VARCHAR(10) NOT NULL,
    task_online_id INT,
    task_id        INT,
    stu_id         LONG        NOT NULL,
    img_url        VARCHAR(255),
    location       POINT,
    complete_time  TIMESTAMP,
    status         INT         NOT NULL
);

DROP TABLE IF EXISTS tb_advertisement;
CREATE TABLE tb_advertisement
(
    id              INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type            VARCHAR(20)  NOT NULL,
    topic           VARCHAR(50)  NOT NULL,
    content         TEXT         NOT NULL,
    cover_url       VARCHAR(255) NOT NULL,
    content_url     VARCHAR(255) NOT NULL,
    audit_status    VARCHAR(20)  NOT NULL CHECK ( audit_status IN ('待审核', '审核未通过', '待发布', '发布中', '已过期') ),
    publish_days    INT          NOT NULL COMMENT '发布天数',
    publish_user_id BIGINT       NOT NULL,
    audit_id        BIGINT,
    for_region      VARCHAR(50),
    for_school      VARCHAR(10),
    for_college     VARCHAR(20),
    for_gender      VARCHAR(20),
    submit_time     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    publish_time    TIMESTAMP COMMENT '发布时间',
    expire_time     TIMESTAMP COMMENT '过期时间'
);

DROP TABLE IF EXISTS tb_tag;
CREATE TABLE tb_tag
(
    id           INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    topic        VARCHAR(50) NOT NULL,
    level        INT,
    search_count INT DEFAULT 0
);

INSERT INTO tb_tag (id, topic, level)
VALUES (1, '考研', 1);

DROP TABLE IF EXISTS tb_tag_level;
CREATE TABLE tb_tag_level
(
    level INT NOT NULL PRIMARY KEY,
    price INT NOT NULL
);

INSERT INTO tb_tag_level (level, price)
VALUES (1, 100),
       (2, 200),
       (3, 300),
       (4, 400),
       (5, 500);

DROP TABLE IF EXISTS tb_ad2tag;
CREATE TABLE tb_ad2tag
(
    ad_id  INT NOT NULL,
    tag_id INT NOT NULL
);

DROP TABLE IF EXISTS tb_order;
CREATE TABLE tb_order
(
    order_id      VARCHAR(255) NOT NULL PRIMARY KEY,
    user_id       BIGINT       NOT NULL,
    ad_id         INT          NOT NULL,
    amount        DOUBLE       NOT NULL,
    pay_status    BOOLEAN      NOT NULL DEFAULT FALSE COMMENT '默认false为未支付',
    create_time   TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    complete_time TIMESTAMP
);

DROP TABLE IF EXISTS tb_merchant_user;
CREATE TABLE tb_merchant_user
(
    id                BIGINT       NOT NULL PRIMARY KEY,
    phone             VARCHAR(11)  NOT NULL,
    password          VARCHAR(255) NOT NULL,
    ban_expire_status BOOLEAN      NOT NULL DEFAULT FALSE,
    create_at         TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    update_at         TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS tb_merchant_login_record;
CREATE TABLE `tb_merchant_login_record`
(
    `id`          int          NOT NULL AUTO_INCREMENT,
    `merchant_id` BIGINT       NOT NULL,
    `ip`          varchar(50)  NOT NULL,
    `city`        varchar(255) NOT NULL,
    `status`      tinyint(1)   NOT NULL,
    `time`        timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS tb_ad_day_amount;
CREATE TABLE `tb_ad_day_amount`
(
    `amount` int NOT NULL
);

DROP TABLE IF EXISTS tb_gift;
CREATE TABLE `tb_gift`
(
    `id`           int          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`         varchar(50)  NOT NULL,
    `description`  varchar(255) NOT NULL,
    `max_exchange` int          NOT NULL DEFAULT -1 COMMENT '礼品的最大兑换次数(库存)，-1表示无限制',
    `photo_url`    varchar(255),
    `point`        int          NOT NULL,
    `school_code`  VARCHAR(10)  NOT NULL,
    `create_id`    BIGINT       NOT NULL,
    `create_time`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS `tb_user_gift`;
CREATE TABLE `tb_user_gift`
(
    `id`            int       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id`       BIGINT    NOT NULL,
    `gift_id`       int       NOT NULL,
    `status`        BOOLEAN   NOT NULL DEFAULT FALSE COMMENT '领取状态(默认未领取)',
    `exchange_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '兑换时间',
    `get_time`      TIMESTAMP COMMENT '领取时间'
);

DROP TABLE IF EXISTS tb_sys_log;
CREATE TABLE tb_sys_log
(
    id            INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id       BIGINT       NOT NULL,
    ip            VARCHAR(50)  NOT NULL,
    method_name   VARCHAR(255) NOT NULL,
    method_mark   VARCHAR(255) NOT NULL,
    req_url       VARCHAR(255) NOT NULL,
    opt_req_param TEXT,
    opt_resp_info TEXT,
    create_at     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_at     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);