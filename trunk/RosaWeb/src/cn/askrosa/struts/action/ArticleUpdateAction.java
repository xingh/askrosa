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

import cn.askrosa.struts.form.ArticleUpdateForm;

import database.Article;
import database.ArticlePeer;
import database.Criteria;
import database.Scroller;

/**
 * MyEclipse Struts Creation date: 09-22-2008 XDoclet definition:
 * 
 * @struts.action path="/modifyMessage" name="modifyMessageForm"
 *                input="/modifyMessage.jsp" scope="request" validate="true"
 */
public class ArticleUpdateAction extends Action
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
	    HttpServletRequest request, HttpServletResponse response)
    {
	ArticleUpdateForm modifyMessageForm = (ArticleUpdateForm) form;
	String id = request.getParameter("id");
	if (id != null)
	{
	    try
	    {
		// get Contact by Id
		Criteria crit = new Criteria();
		crit.add(Article.ID, id.trim());
		Scroller<Article> results = ArticlePeer.doSelect(crit);
		if (!results.hasNext())
		    return mapping.getInputForward();

		// save Contact to form
		Article article = (Article) results.next();
		article.setVerify("");
		modifyMessageForm.setArticle(article);
		ActionForward af = mapping.findForward("success");
		return af;
	    }
	    catch (Exception ignore)
	    {
		ignore.printStackTrace();
	    }
	}

	return mapping.getInputForward();
    }
}