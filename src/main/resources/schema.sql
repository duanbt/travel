DROP TABLE IF EXISTS TAB_FAVORITE;
DROP TABLE IF EXISTS TAB_ROUTE_IMG;
DROP TABLE IF EXISTS TAB_ROUTE;
DROP TABLE IF EXISTS TAB_CATEGORY;
DROP TABLE IF EXISTS TAB_SELLER;
DROP TABLE IF EXISTS TAB_USER;

/*==============================================================*/
/* TABLE: TAB_CATEGORY                                          */
/*==============================================================*/
CREATE TABLE TAB_CATEGORY
(
    CID   INT          NOT NULL AUTO_INCREMENT,
    CNAME VARCHAR(100) NOT NULL,
    PRIMARY KEY (CID),
    UNIQUE (CNAME)
);

/*==============================================================*/
/* TABLE: TAB_FAVORITE                                          */
/*==============================================================*/
CREATE TABLE TAB_FAVORITE
(
    RID  INT  NOT NULL,
    DATE DATE NOT NULL,
    UID  INT  NOT NULL,
    PRIMARY KEY (RID, UID)
);

/*==============================================================*/
/* TABLE: TAB_ROUTE                                             */
/*==============================================================*/
CREATE TABLE TAB_ROUTE
(
    RID            INT          NOT NULL AUTO_INCREMENT,
    RNAME          VARCHAR(500) NOT NULL,
    PRICE          DOUBLE       NOT NULL,
    ROUTE_INTRODUCE VARCHAR(1000),
    RFLAG          CHAR(1)      NOT NULL,
    RDATE          VARCHAR(19),
    IS_THEME_TOUR    CHAR(1)      NOT NULL,
    COUNT          INT DEFAULT 0,
    CID            INT          NOT NULL,
    RIMAGE         VARCHAR(200),
    SID            INT,
    SOURCE_ID       VARCHAR(50),
    PRIMARY KEY (RID),
    UNIQUE (SOURCE_ID)
);

/*==============================================================*/
/* TABLE: TAB_ROUTE_IMG                                         */
/*==============================================================*/
CREATE TABLE TAB_ROUTE_IMG
(
    RGID     INT          NOT NULL AUTO_INCREMENT,
    RID      INT          NOT NULL,
    BIGPIC   VARCHAR(200) NOT NULL,
    SMALLPIC VARCHAR(200),
    PRIMARY KEY (RGID)
);

/*==============================================================*/
/* TABLE: TAB_SELLER                                            */
/*==============================================================*/
CREATE TABLE TAB_SELLER
(
    SID       INT          NOT NULL AUTO_INCREMENT,
    SNAME     VARCHAR(200) NOT NULL,
    CONSPHONE VARCHAR(20)  NOT NULL,
    ADDRESS   VARCHAR(200),
    PRIMARY KEY (SID),
    UNIQUE (SNAME)
);

/*==============================================================*/
/* TABLE: TAB_USER                                              */
/*==============================================================*/
CREATE TABLE TAB_USER
(
    UID       INT          NOT NULL AUTO_INCREMENT,
    USERNAME  VARCHAR(100) NOT NULL,
    PASSWORD  VARCHAR(32)  NOT NULL,
    NAME      VARCHAR(100),
    BIRTHDAY  DATE,
    SEX       CHAR(1),
    TELEPHONE VARCHAR(11),
    EMAIL     VARCHAR(100),
    STATUS    CHAR(1),
    CODE      VARCHAR(50),

    PRIMARY KEY (UID),
    UNIQUE (USERNAME),
    UNIQUE (CODE)
);

ALTER TABLE TAB_FAVORITE
    ADD CONSTRAINT FK_ROUTE_FAVORITE FOREIGN KEY (RID)
        REFERENCES TAB_ROUTE (RID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE TAB_FAVORITE
    ADD CONSTRAINT FK_USER_FAVORITE FOREIGN KEY (UID)
        REFERENCES TAB_USER (UID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE TAB_ROUTE
    ADD CONSTRAINT FK_CATEGORY_ROUTE FOREIGN KEY (CID)
        REFERENCES TAB_CATEGORY (CID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE TAB_ROUTE
    ADD CONSTRAINT FK_SELLER_ROUTE FOREIGN KEY (SID)
        REFERENCES TAB_SELLER (SID) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE TAB_ROUTE_IMG
    ADD CONSTRAINT FK_ROUTE_ROUTEIMG FOREIGN KEY (RID)
        REFERENCES TAB_ROUTE (RID) ON DELETE RESTRICT ON UPDATE RESTRICT;