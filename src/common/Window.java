package common;

import org.eclipse.swt.widgets.*;

public class Window
{
	private Shell shell;
	private Shell parent;
	
	public Window()
	{
		this(null);
	}
	
	public Window(Shell parent)
	{
		shell = new Shell(parent);
		this.parent = parent;
	}
	
	public Shell getParent()
	{
		return this.parent;
	}
}