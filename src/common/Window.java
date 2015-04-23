package common;

import org.eclipse.swt.widgets.*;

/**
 * This class is inherited by all other classes that intantiates a Shell.
 * @author khaister
 */
@SuppressWarnings("unused")
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