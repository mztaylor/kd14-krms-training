--
-- Copyright 2005-2014 The Kuali Foundation
--
-- Licensed under the Educational Community License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
-- http://www.opensource.org/licenses/ecl2.php
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--
DROP TABLE IF EXISTS `TRNG_APP_QST_T`;

CREATE TABLE `TRNG_APP_QST_T` (
  `TRNG_APP_QST_ID` varchar(40) NOT NULL DEFAULT '0',
  `TRNG_APP_DOC_ID` decimal(14,0) NOT NULL DEFAULT '0',
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL DEFAULT '1',
  `QUESTION` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `ANSWER` varchar(40) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`TRNG_APP_QST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `TRNG_APP_QST_S`;

CREATE TABLE `TRNG_APP_QST_ID_S` (
  `id` bigint(19) COLLATE utf8_bin NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
