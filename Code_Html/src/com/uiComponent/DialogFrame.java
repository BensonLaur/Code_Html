package com.uiComponent;

import java.awt.Frame;

import javax.swing.JDialog;

public class DialogFrame extends JDialog {
	public DialogFrame(Frame owner){
		super(owner, true);
	}
}