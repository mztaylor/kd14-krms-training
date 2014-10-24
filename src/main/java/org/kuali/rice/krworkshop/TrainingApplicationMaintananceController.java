/**
 * Copyright 2005-2012 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.rice.krworkshop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.rice.krad.maintenance.MaintenanceDocumentController;
import org.kuali.rice.krad.uif.service.ViewHelperService;
import org.kuali.rice.krad.web.controller.UifControllerBase;
import org.kuali.rice.krad.web.form.MaintenanceDocumentForm;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: Brian
 * Date: 10/3/12
 * Time: 10:58 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/trainingApplication")
public class TrainingApplicationMaintananceController extends  MaintenanceDocumentController {


    
    @RequestMapping(params = "methodToCall=getApplicationStatus")
	public ModelAndView getApplicationStatus(
			@ModelAttribute("KualiForm") UifFormBase form,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
    	
    	MaintenanceDocumentForm document = this.getMaintenanceDocumentForm( form);
    	TrainingApplication trainingApplication = (TrainingApplication) document.getDocument().getDocumentDataObject();

    	TrainingApplicationMaintainableImpl maintainable = new TrainingApplicationMaintainableImpl();
    	 String status =  maintainable.evaluateApplicationStatus(trainingApplication);
    	 trainingApplication.setTestScoreStatusMessage(status);
		return showDialog("applicationStatus", true,form);
	}
    
    
    
    protected MaintenanceDocumentForm getMaintenanceDocumentForm(UifFormBase form) {
        if (form instanceof MaintenanceDocumentForm) {
            return (MaintenanceDocumentForm) form;
        }
        throw new RuntimeException("Error retrieving Maintenance document form from UifFormBase");
    }

}
