/*
 * Generated by MyEclipse Struts Template path: templates/java/JavaClass.vtl
 */
package cn.askrosa.struts.form;

import org.apache.struts.validator.ValidatorForm;

import database.FtpSiteInfo;

/**
 * MyEclipse Struts Creation date: 03-10-2008 XDoclet definition:
 * 
 * @struts.form name="updateFtpSiteForm"
 */
public class FtpSiteUpdateForm extends ValidatorForm
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 2210611327412004570L;

    private FtpSiteInfo ftpSiteInfo = new FtpSiteInfo();

    private String type;
    
    private String title;

    public void setType(String type)
    {
	this.type = type;
    }

    public String getType()
    {
	return this.type;

    }

    public FtpSiteInfo getFtpSiteInfo()
    {
	return this.ftpSiteInfo;
    }

    public void setFtpSiteInfo(FtpSiteInfo ftpSiteInfo)
    {
	this.ftpSiteInfo = ftpSiteInfo;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
}