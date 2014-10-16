<%--
 Copyright 2007-2009 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl2.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/rice-portal/jsp/sys/riceTldHeader.jsp"%>
<%
/*
* User Preferences
* Quicklinks
* Routing Rules
* Routing Rule Delegations
* Routing and Identity Management Document Type Hierarchy
* Document Type
* eDoc Lite
* People Flow
*/
%>
<channel:portalChannelTop channelTitle="KRMS Training" />
<div class="body">
	
    <ul class="chan">
        <li><portal:portalLink displayTitle="true" title="User Preferences" url="${ConfigProperties.kew.url}/Preferences.do?returnLocation=${ConfigProperties.application.url}/portal.do" /></li>
    </ul>
    
</div>
<channel:portalChannelBottom />