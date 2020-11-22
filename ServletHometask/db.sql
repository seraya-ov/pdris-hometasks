CREATE DATABASE UserDB;
USE UserDB;

CREATE TABLE `Users` (
                         `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
                         `username` varchar(20) NOT NULL DEFAULT '',
                         `password` varchar(20) NOT NULL DEFAULT '',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;