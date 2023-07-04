CREATE TABLE IF NOT EXISTS rss_channel (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    link VARCHAR(255) ,
    description TEXT,
    language VARCHAR(255) ,
    pubDate VARCHAR(255) ,
    lastBuildDate VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS rss_image (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) ,
    url VARCHAR(255) ,
    link VARCHAR(255),
    description TEXT
);

CREATE TABLE IF NOT EXISTS rss_item (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) ,
    link VARCHAR(255) ,
    description TEXT ,
    author VARCHAR(255),
    pub_date VARCHAR(255),
    guid BIGINT ,
    comments VARCHAR(255),
    pubDate VARCHAR(255)
);