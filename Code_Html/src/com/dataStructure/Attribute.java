package com.dataStructure;

public class Attribute {

	private String baseColor = "black";// 字体的基色
	// black,purple,green,yellow,blue,red,cyan,gray,
	private String keywordColor = "purple";// 关键字的颜色
	// black,purple,green,yellow,blue,red,cyan,gray,
	private String commentColor = "gray";// 注释代码的颜色
	// black,purple,green,yellow,blue,red,cyan,gray,
	private String stringColor = "blue";// 字符串常量的颜色

	private int FontSize = 3;// 字体大小
	// int 1~7 由小到大
	private String FontStyle = "宋体";// 字符串常量的颜色

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
