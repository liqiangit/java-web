DROP TABLE IF EXISTS student;
CREATE TABLE student (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  name varchar(1024) NOT NULL,
  sex tinyint(1) NOT NULL,
  addr varchar(1024) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS person;
CREATE TABLE person (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  name varchar(1024) NOT NULL,
  cities varchar(1024) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE GROUP_DETAIL (
	ID BIGINT(20) NOT NULL,
	CHN VARCHAR(10) NOT NULL COMMENT '渠道',
	LIBRARY_NAME1 VARCHAR(50) NOT NULL COMMENT '一级分库',
	LIBRARY_NAME2 VARCHAR(50) NOT NULL COMMENT '二级分库',
	FEATURE_ID VARCHAR(50) NOT NULL COMMENT '特征ID',
	PEOPLE_ID VARCHAR(50) NOT NULL COMMENT '人员ID',
	CATEGORY VARCHAR(50) NOT NULL COMMENT '人员属性分类',
	X INT(11) NOT NULL COMMENT '特征坐标x',
	Y INT(11) NOT NULL COMMENT '特征坐标y',
	UPDATED_ON BIGINT(20) NOT NULL COMMENT '最后更新时间',
	BIO_SUBTYPE INT(11) NOT NULL COMMENT '预留，目前不使用',
	PRIMARY KEY (ID),
	INDEX LIBRARY_NAME (CHN, LIBRARY_NAME1, LIBRARY_NAME2),
	INDEX PEOPLE (PEOPLE_ID,CATEGORY),
	UNIQUE INDEX FEATURE (CHN, LIBRARY_NAME1, LIBRARY_NAME2, FEATURE_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
