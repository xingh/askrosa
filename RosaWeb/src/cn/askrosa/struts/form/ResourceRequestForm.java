/*
 * Generated by MyEclipse Struts Template path: templates/java/JavaClass.vtl
 */
package cn.askrosa.struts.form;

import org.apache.struts.validator.ValidatorForm;

import database.ResourceRequest;

/**
 * MyEclipse Struts Creation date: 07-20-2008 XDoclet definition:
 * 
 * @struts.form name="resourceRequestForm"
 */
public class ResourceRequestForm extends ValidatorForm
{
    /*
     * Generated fields
     */

    /**
	 * 
	 */
    private static final long serialVersionUID = -3044551297507351155L;

    private String deadline;
    /** request property */
    private ResourceRequest request = new ResourceRequest();
    // /*
    // * Generated Methods
    // */
    //
    // /**
    // * Method validate
    // * @param mapping
    // * @param request
    // * @return ActionErrors
    // */
    // public ActionErrors validate(ActionMapping mapping,
    // HttpServletRequest request) {
    // //
    // return null;
    // }
    //
    // /**
    // * Method reset
    // * @param mapping
    // * @param request
    // */
    // public void reset(ActionMapping mapping, HttpServletRequest request) {
    // //
    // }

    /**
     * Returns the request.
     * 
     * @return ResourceRequest
     */
    public ResourceRequest getRequest()
    {
	return request;
    }

    /**
     * Set the request.
     * 
     * @param request
     *            The request to set
     */
    public void setRequest(ResourceRequest request)
    {
	this.request = request;
    }

    public String getDeadline()
    {
        return deadline;
    }

    public void setDeadline(String deadline)
    {
        this.deadline = deadline;
    }
}