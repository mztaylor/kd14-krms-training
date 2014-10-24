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

INSERT INTO `KRMS_TERM_SPEC_T` VALUES('T1009','testScore','java.lang.Integer','Y',1,'application form test score ','KR-RULE-TEST');
INSERT INTO `KRMS_CNTXT_VLD_TERM_SPEC_T` VALUES ('T1008','CONTEXT1','T1009','N');
INSERT INTO `KRMS_TERM_T` VALUES ('T1008','T1009',1,'Test score term');