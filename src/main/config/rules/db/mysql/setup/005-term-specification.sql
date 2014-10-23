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
insert into `KRMS_TERM_SPEC_T` (`TERM_SPEC_ID`,`NMSPC_CD`,`NM`,`TYP`,`ACTV`,`VER_NBR`,`DESC_TXT`) values ('KRTRN-TS-COLLEGE','KRA-TRNG','college','java.lang.String','Y',1,'Selected College');
insert into `KRMS_TERM_T` (`TERM_ID`,`TERM_SPEC_ID`,`VER_NBR`,`DESC_TXT`) values ('KRTRN-T-COLLEGE','KRTRN-TS-COLLEGE',1,'College');
insert into `KRMS_CNTXT_VLD_TERM_SPEC_T` (`CNTXT_TERM_SPEC_PREREQ_ID`,`CNTXT_ID`,`TERM_SPEC_ID`,`PREREQ`) values ('KRTRN-VLD-T-001','KRTRN-CONTEXT','KRTRN-TS-COLLEGE','N');

insert into `KRMS_TERM_SPEC_T` (`TERM_SPEC_ID`,`NMSPC_CD`,`NM`,`TYP`,`ACTV`,`VER_NBR`,`DESC_TXT`) values ('KRTRN-TS-CAMPUS','KRA-TRNG','campus','java.lang.String','Y',1,'Selected Campus');
insert into `KRMS_TERM_T` (`TERM_ID`,`TERM_SPEC_ID`,`VER_NBR`,`DESC_TXT`) values ('KRTRN-T-CAMPUS','KRTRN-TS-CAMPUS',1,'Campus');
insert into `KRMS_CNTXT_VLD_TERM_SPEC_T` (`CNTXT_TERM_SPEC_PREREQ_ID`,`CNTXT_ID`,`TERM_SPEC_ID`,`PREREQ`) values ('KRTRN-VLD-T-002','KRTRN-CONTEXT','KRTRN-TS-CAMPUS','N');