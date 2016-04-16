package com.dataStructure;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
/**
 *  一个没有添加监听器的右键菜单
 *  @member 1、 JMenuItem("展开")
 *  @member 2、JMenuItem("创建子文件夹")
 *  @member 3、JMenuItem("删除")
 *  @member 4、JMenuItem("复制")
 *  @member 5、JMenuItem("粘贴")
 */
public class RightClickMenu extends JPopupMenu {
	/**
	 *@member  JMenuItem("展开")
	 */
	public JMenuItem menuItem1 = new JMenuItem("展开");
	/**
	 *  @member JMenuItem("创建子文件夹")
	 */
	public JMenuItem menuItem2 = new JMenuItem("创建子文件夹");
	/**
	 *  @member JMenuItem("复制")
	 */
	public JMenuItem menuItem3 = new JMenuItem("复制 已选项");
	/**
	 *  @member JMenuItem("粘贴")
	 */
	public JMenuItem menuItem4 = new JMenuItem("粘贴");
	/**
	 *  @member JMenuItem("删除")
	 */
	public JMenuItem menuItem5 = new JMenuItem("删除");
	
/**
 * <code>TYPE_RESOURCE</code> 选择的右键类型用于资源管理器，包含：
 * ("展开")("创建子文件夹")("复制")
 */
	public static final int TYPE_RESOURCE=1;

/**
 * <code>TYPE_RESOURCE</code> 选择的右键类型用于文件预览与选择panel，包含：
 *   ("复制")("粘贴")("删除")
 */
	public static final int TYPE_FILES_PANEL=2;
	/**
	 * 一个没有添加监听器的右键菜单 的构造函数
 *  @param TYPE
 * <dl compact>
 * <dt>TYPE_RESOURCE <dd>
 * <code>TYPE_RESOURCE</code> 选择的右键类型用于资源管理器，包含：
 * ("展开")("创建子文件夹")
 *  <dd><dt>TYPE_FILES_PANEL<dd>
 * <code>TYPE_RESOURCE</code> 选择的右键类型用于文件预览与选择panel，包含：
 *   ("复制")("粘贴")("删除")
 * </dl compact>
 * 
 *  <p>Type可为  <code>TYPE_RESOURCE</code>  或者 <code>TYPE_FILES_PANEL</code>
 *  </P>
 *  <p>使用时用如下语句：RightClickMenu.<code>TYPE_RESOURCE</code>
 *  </P>
	 * @throws Throwable 
 *  @member 
 *   1、 JMenuItem("展开")
 *   2、JMenuItem("创建子文件夹")
 *   3、JMenuItem("复制")
 *   4、JMenuItem("粘贴")
 *   5、JMenuItem("删除")
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
