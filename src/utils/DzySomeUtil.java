package utils;

import javax.servlet.http.HttpSession;

public class DzySomeUtil {

	/**
	 * session�ĺ�ʵ�ж��Ƿ��Ѿ���¼
	 * @param session
	 * @throws Exception
	 */
	public static boolean sessionCheck(HttpSession session) throws Exception {
		
		//�Ѿ���¼���鿴session session��ֵ��˵���Ѿ���¼�������ж�ҳ�漴��
		String roles=(String)session.getAttribute("roles");
		String names =(String)session.getAttribute("names");

		//���е�¼��session��û��rolesֵ��˵����¼�Ѿ�ʧЧ�����ߵ�һ�ε�¼�����е�¼����rolesֵ�󶨵�session��
		if(roles==null){
			return false ;
		}else {
			return true ;
		}
	}
	/**
	 * ���ַ�����˫����
	 */
	public String addMark(String old) {
		return "\"" + old + "\"";
	}
}
