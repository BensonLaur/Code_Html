package com.dataStructure;

public class Attribute {

	private String baseColor = "black";// ����Ļ�ɫ
	// black,purple,green,yellow,blue,red,cyan,gray,
	private String keywordColor = "purple";// �ؼ��ֵ���ɫ
	// black,purple,green,yellow,blue,red,cyan,gray,
	private String commentColor = "gray";// ע�ʹ������ɫ
	// black,purple,green,yellow,blue,red,cyan,gray,
	private String stringColor = "blue";// �ַ�����������ɫ

	private int FontSize = 3;// �����С
	// int 1~7 ��С����
	private String FontStyle = "����";// �ַ�����������ɫ

	public String getStringColor() {
		return stringColor;
	}

	public void setStringColor(String stringColor) {
		this.stringColor = stringColor;
	}

	public int getFontSize() {

		return FontSize;
	}

	public void setFontSize(int fontSize) {
		FontSize = fontSize;
	}

	public String getFontStyle() {
		return FontStyle;
	}

	public void setFontStyle(String fontStyle) {
		FontStyle = fontStyle;
	}

	public String getBaseColor() {
		return this.baseColor;
	}

	public void setBaseColor(String bcolor) {
		this.baseColor = bcolor;
	}

	public String getKeywordColor() {
		return this.keywordColor;
	}

	public void setKeywordColor(String kcolor) {
		this.keywordColor = kcolor;
	}

	public String getCommentColor() {
		return this.commentColor;
	}

	public void setCommentColor(String ccolor) {
		this.commentColor = ccolor;
	}
}
