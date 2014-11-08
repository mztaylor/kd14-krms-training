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



-- Builds namespace related to training application

REPLACE INTO KRCR_NMSPC_T (NMSPC_CD,OBJ_ID,VER_NBR,NM,ACTV_IND,APPL_ID) VALUES ('KRA-TRNG',uuid(),1,'Training App','Y','RICE');
