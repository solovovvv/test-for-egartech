CREATE TABLE IF NOT EXISTS INSTRUMENTS
(
    ID             INT         NOT NULL AUTO_INCREMENT,
    PLACEMENT_DATE DATE        NOT NULL,
    NAME           VARCHAR(40) NOT NULL,
    COST           INT,
    VERSION        INT         NOT NULL DEFAULT 0,
    PRIMARY KEY (ID)
);
