-- 导出 icbc_search_engine 的数据库结构
CREATE DATABASE IF NOT EXISTS `icbc_search_engine` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `icbc_search_engine`;

-- 导出  表 icbc_search_engine.FEATURE 结构
CREATE TABLE IF NOT EXISTS `FEATURE` (
  `ID` varchar(50) DEFAULT NULL,
  `FEATURE` blob,
  `PEOPLE_ID` varchar(50) DEFAULT NULL,
  `CATEGORY` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='特征';

-- 数据导出被取消选择。
-- 导出  表 icbc_search_engine.GROUP 结构
CREATE TABLE IF NOT EXISTS `GROUP` (
  `CHN` varchar(10) NOT NULL COMMENT '渠道',
  `LIBRARY_NAME1` varchar(10) NOT NULL COMMENT '一级分库',
  `LIBRARY_NAME2` varchar(10) NOT NULL COMMENT '二级分库',
  PRIMARY KEY (`CHN`,`LIBRARY_NAME1`,`LIBRARY_NAME2`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人脸库';

-- 数据导出被取消选择。
-- 导出  表 icbc_search_engine.GROUP_DETAIL 结构
CREATE TABLE IF NOT EXISTS `GROUP_DETAIL` (
  `ID` varchar(50) NOT NULL,
  `CHN` varchar(10) NOT NULL COMMENT '渠道',
  `LIBRARY_NAME1` varchar(10) NOT NULL COMMENT '一级分库',
  `LIBRARY_NAME2` varchar(10) NOT NULL COMMENT '二级分库',
  `FEATURE_ID` varchar(50) NOT NULL COMMENT '特征ID',
  `PEOPLE_ID` varchar(50) NOT NULL COMMENT '人员ID',
  `CATEGORY` varchar(50) NOT NULL COMMENT '人员属性分类',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `FEATURE` (`CHN`,`LIBRARY_NAME1`,`LIBRARY_NAME2`,`FEATURE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='特征库详情';

-- 数据导出被取消选择。
-- 导出  表 icbc_search_engine.PEOPLE 结构
CREATE TABLE IF NOT EXISTS `PEOPLE` (
  `ID` varchar(50) NOT NULL COMMENT 'ID',
  `CATEGORY` varchar(50) NOT NULL COMMENT '归属分类',
  PRIMARY KEY (`ID`,`CATEGORY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员';
