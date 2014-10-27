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
insert into KRMS_TYP_T (TYP_ID, NM, NMSPC_CD, ACTV, VER_NBR) values ('KRTRN-TYPE-300', 'CONTEXT', 'KRA-TRNG', 'Y', 1);
insert into KRMS_TYP_T (TYP_ID, NM, NMSPC_CD, ACTV, VER_NBR) values ('KRTRN-TYPE-301', 'AGENDA', 'KRA-TRNG', 'Y', 1);
insert into KRMS_CNTXT_T (CNTXT_ID, NMSPC_CD, NM, TYP_ID, ACTV, VER_NBR, DESC_TXT) values ('KRTRN-CONTEXT', 'KRA-TRNG', 'Apply Now', 'KRTRN-TYPE-300', 'Y', 1, 'KRMS Training Context');
