/*
 * Generated by MyEclipse Struts Template path: templates/java/JavaClass.vtl
 */
package cn.askrosa.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import cn.askrosa.others.PageIndexResult;
import cn.askrosa.struts.form.ArticleListForm;
import database.ArticlePeer;
import database.Scroller;

/**
 * MyEclipse Struts Creation date: 09-22-2008 XDoclet definition:
 * 
 * @struts.action
 * @struts.action-forward name="success" path="listArticles.jsp"
 */
public class ArticleListAction extends Action
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
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	ArticleListForm articleListForm = (ArticleListForm) form;
	String keyword = articleListForm.getKeyword();
	String dateFrom = articleListForm.getDateFrom();
	String dateTo = articleListForm.getDateTo();
	String author =articleListForm.getAuthor();
	String pageString = request.getParameter("pageLink");
	if(pageString==null)pageString="1";
	
	int pageDisplayNumber = 11;
	final int pageSize = 30;
	String sort = request.getParameter("sort");
	int page = Integer.parseInt(pageString);
	int from = (page - 1) * pageSize;
	
	Scroller<database.Article> srcSize = ArticlePeer.listPagedArticle(keyword,author,dateFrom,dateTo);
	int resultNumber=srcSize.size();	
	
	int resultPages = (int) Math.ceil( resultNumber / (double) pageSize);
	request.setAttribute("listing", ArticlePeer.listPagedArticle(keyword,author,from,pageSize,dateFrom,dateTo,sort));	
	request.setAttribute("pageNumber", page);
	request.setAttribute("page", (page-1)*pageSize);
	request.setAttribute("title", "留言列表");
	request.setAttribute("pageIndex", PageIndexResult.getPageIndexResult(page,
		pageDisplayNumber, resultPages,"articleList.do?pageLink="));
	return new ActionForward(mapping.findForward("success"));
    }
}