/*
 * Generated by MyEclipse Struts Template path: templates/java/JavaClass.vtl
 */
package cn.askrosa.struts.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import cn.askrosa.struts.form.FtpSiteListForm;
import cn.askrosa.others.PageIndexResult;
import database.FtpFileCount;
import database.FtpSiteInfo;
import database.FtpSitesManager;
import database.Scroller;

/**
 * MyEclipse Struts Creation date: 03-08-2008 XDoclet definition:
 * 
 * @struts.action parameter="listing.jsp" validate="true"
 */
public class FtpSiteListAction extends Action
{
    /*
     * Generated Methods
     */

    /**
     * Method execute
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	// TODO Auto-generated method stub

	String pageString = request.getParameter("page");
	FtpSiteListForm ftpSiteListForm = (FtpSiteListForm) form;
	String ftpAddress = ftpSiteListForm.getFtpAddress();
	String author = ftpSiteListForm.getAuthor();
	String dateFrom = ftpSiteListForm.getDateFrom();
	String dateTo = ftpSiteListForm.getDateTo();
	short location = ftpSiteListForm.getLocation();
	String sort = ftpSiteListForm.getSort();

	int pageSize = 30;//
	int pageDisplayNumber = 11;// 可自己定义,奇数！！
	if (pageString == null)
	    pageString = "1";
	int page = Integer.parseInt(pageString);
	int from = (page - 1) * pageSize;

	// String path = this.getServlet().getServletContext().getRealPath("/")
	// + "/XMLForFlashFxp.ftp";
	// FtpSitesManager.storeFtpSitesToXML(new File(path));
	
	ArrayList<FtpFileCount> ftpFileCountList =ftpSiteListForm.getFtpFileCountList();
	if(ftpFileCountList==null)
	{
	    ftpFileCountList=FtpSitesManager.TotalFileCount();
	int totalFtpCount = 0;
	long totalFileCount = 0;
	for (FtpFileCount ele : ftpFileCountList)
	{
	    if (ele.getLocation() == 0)
		ele.setLocationString("校园网");
	    if (ele.getLocation() == 1)
		ele.setLocationString("教育网");
	    if (ele.getLocation() == 2)
		ele.setLocationString("公共网");
	    totalFtpCount += ele.getFtpCount();
	    totalFileCount += ele.getFileCount();
	}
	ftpSiteListForm.setTotalFileCount(totalFileCount);
	ftpSiteListForm.setTotalFtpCount(totalFtpCount);
	}
	Scroller<FtpSiteInfo> srcSize = FtpSitesManager.TotalFileCount(ftpAddress, author,
		dateFrom, dateTo, location);
	int resultNumber = srcSize.size();
	int resultPages = (int) Math.ceil(resultNumber / (double) pageSize);// 结果页面数目

	Scroller<FtpSiteInfo> src = FtpSitesManager.listPagedFtpSites(sort, from, pageSize,
		ftpAddress, author, dateFrom, dateTo, location);

	// 设置页码
	request.setAttribute("pageIndex", PageIndexResult.getPageIndexResult(page,
		pageDisplayNumber, resultPages, "ftpSiteList.do?page="));
	request.setAttribute("page", (page - 1) * pageSize);
	request.setAttribute("listing", src);
	request.setAttribute("title", "站点列表");
	request.setAttribute("totalFtpCount", ftpSiteListForm.getTotalFtpCount());
	request.setAttribute("totalFileCount", ftpSiteListForm.getTotalFileCount());
	request.setAttribute("ftpFileCountList", ftpFileCountList);
	return new ActionForward(mapping.findForward("success"));
    }
}