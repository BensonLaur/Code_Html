package com.dataStructure;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
/**
 *  һ��û����Ӽ��������Ҽ��˵�
 *  @member 1�� JMenuItem("չ��")
 *  @member 2��JMenuItem("�������ļ���")
 *  @member 3��JMenuItem("ɾ��")
 *  @member 4��JMenuItem("����")
 *  @member 5��JMenuItem("ճ��")
 */
public class RightClickMenu extends JPopupMenu {
	/**
	 *@member  JMenuItem("չ��")
	 */
	public JMenuItem menuItem1 = new JMenuItem("չ��");
	/**
	 *  @member JMenuItem("�������ļ���")
	 */
	public JMenuItem menuItem2 = new JMenuItem("�������ļ���");
	/**
	 *  @member JMenuItem("����")
	 */
	public JMenuItem menuItem3 = new JMenuItem("���� ��ѡ��");
	/**
	 *  @member JMenuItem("ճ��")
	 */
	public JMenuItem menuItem4 = new JMenuItem("ճ��");
	/**
	 *  @member JMenuItem("ɾ��")
	 */
	public JMenuItem menuItem5 = new JMenuItem("ɾ��");
	
/**
 * <code>TYPE_RESOURCE</code> ѡ����Ҽ�����������Դ��������������
 * ("չ��")("�������ļ���")("����")
 */
	public static final int TYPE_RESOURCE=1;

/**
 * <code>TYPE_RESOURCE</code> ѡ����Ҽ����������ļ�Ԥ����ѡ��panel��������
 *   ("����")("ճ��")("ɾ��")
 */
	public static final int TYPE_FILES_PANEL=2;
	/**
	 * һ��û����Ӽ��������Ҽ��˵� �Ĺ��캯��
 *  @param TYPE
 * <dl compact>
 * <dt>TYPE_RESOURCE <dd>
 * <code>TYPE_RESOURCE</code> ѡ����Ҽ�����������Դ��������������
 * ("չ��")("�������ļ���")
 *  <dd><dt>TYPE_FILES_PANEL<dd>
 * <code>TYPE_RESOURCE</code> ѡ����Ҽ����������ļ�Ԥ����ѡ��panel��������
 *   ("����")("ճ��")("ɾ��")
 * </dl compact>
 * 
 *  <p>Type��Ϊ  <code>TYPE_RESOURCE</code>  ���� <code>TYPE_FILES_PANEL</code>
 *  </P>
 *  <p>ʹ��ʱ��������䣺RightClickMenu.<code>TYPE_RESOURCE</code>
 *  </P>
	 * @throws Throwable 
 *  @member 
 *   1�� JMenuItem("չ��")
 *   2��JMenuItem("�������ļ���")
 *   3��JMenuItem("����")
 *   4��JMenuItem("ճ��")
 *   5��JMenuItem("ɾ��")
	 */
	public RightClickMenu(int Type) {
		if(Type==TYPE_RESOURCE){
			add(menuItem1);
			addSeparator();
			add(menuItem2);
			
		}else if(Type==TYPE_FILES_PANEL){
			add(menuItem3);
			addSeparator();
			add(menuItem4);
			addSeparator();
			add(menuItem5);
		} else
			try {
				throw new Exception("Type unSupported! ");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
