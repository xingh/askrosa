/*
 * Generated by MyEclipse Struts Template path: templates/java/JavaClass.vtl
 */
package cn.askrosa.struts.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.ialvin.util.Lunar;
import database.Criteria;
import database.HistoryUsers;
import database.HistoryUsersPeer;
import database.Scroller;

/**
 * MyEclipse Struts Creation date: 06-01-2008 XDoclet definition:
 * 
 * @struts.action validate="true"
 * @struts.action-forward name="success" path="/index.jsp"
 */
public class WelcomebottomAction extends Action
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

    private static Lunar lunar = new Lunar(new Date());

    private static String solarDate = lunar.getSolarDateString();

    private static String weekName = lunar.getWeekName();

    private static String sFestivalName = lunar.getSFestivalName();

    private static String lunarDate = lunar.getLunarDateString();

    private static String lFestival = lunar.getLFestivalName();

    private static String term = lunar.getTermString();

    @SuppressWarnings("deprecation")
    public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
    {
	Criteria c = new Criteria();
	c.add(HistoryUsers.ID, "1");
	Scroller<HistoryUsers> scr;
	try
	{
	    // 在线用户
	    scr = HistoryUsersPeer.doSelect(c);
	    if (scr.hasNext())
	    {
		HistoryUsers h = (HistoryUsers) scr.next();
		request.setAttribute("users", h);
	    }
	    // 农历计算
	    WelcomebottomAction.lunar.isFestival();
	    Date date = new Date();
	    if (date.getDate() != WelcomebottomAction.lunar.getSolarDay())
	    {
		WelcomebottomAction.lunar = new Lunar(date);
		WelcomebottomAction.lunar.isFestival();
		WelcomebottomAction.solarDate = WelcomebottomAction.lunar.getSolarDateString();
		WelcomebottomAction.weekName = WelcomebottomAction.lunar.getWeekName();
		WelcomebottomAction.sFestivalName = WelcomebottomAction.lunar.getSFestivalName();
		WelcomebottomAction.lunarDate = WelcomebottomAction.lunar.getLunarDateString();
		WelcomebottomAction.lFestival = WelcomebottomAction.lunar.getLFestivalName();
		WelcomebottomAction.term = WelcomebottomAction.lunar.getTermString();
	    }

	    request.setAttribute("solarDate", WelcomebottomAction.solarDate);
	    request.setAttribute("weekName", WelcomebottomAction.weekName);
	    request.setAttribute("sFestivalName", WelcomebottomAction.sFestivalName);

	    request.setAttribute("lunarDate", WelcomebottomAction.lunarDate);
	    request.setAttribute("lFestival", WelcomebottomAction.lFestival);
	    request.setAttribute("term", WelcomebottomAction.term);

	    String remoteIp = request.getRemoteAddr();
	    request.setAttribute("remoteIp", remoteIp);
	    request.setAttribute("serverIP", request.getLocalAddr());

	    return mapping.findForward("success");
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	return mapping.getInputForward();
    }
}