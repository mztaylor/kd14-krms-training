--
-- Copyright 2005-2013 The Kuali Foundation
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



-- Builds permissions related to training application
INSERT INTO `KRIM_PERM_T` (`PERM_ID`,`OBJ_ID`,`VER_NBR`,`PERM_TMPL_ID`,`NMSPC_CD`,`NM`,`DESC_TXT`) 
VALUES ('KRTRN-KIM-PERM-01', uuid(), 1, '52', 'KRA-TRNG', 'Maintain KRMS Agenda', 'Allows creation and modification of agendas via the agenda editor');

INSERT INTO `KRIM_ROLE_PERM_T` (`ROLE_PERM_ID`,`OBJ_ID`,`VER_NBR`,`ROLE_ID`,`PERM_ID`,`ACTV_IND`)
VALUES ('KRTRN-KIM-ROLE-PERM-01', uuid(), 1, '98', 'KRTRN-KIM-PERM-01', 'Y');