package ui;
import java.io.File;


import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;



public class WordFrame extends ApplicationWindow {

	private Action action;

	public static void main(String[] args) {


		try {
			WordFrame window = new WordFrame();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 构造函数
	public WordFrame() {

		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();

	}

	
	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		


		return container;
	}
	


	
	
	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
		{
			action = new Action("\u6253\u5F00Word\u6587\u4EF6") {
				public void run() {

					FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
					dialog.setFilterExtensions(new String[] { "*.doc" });
					String path = dialog.open();
					if (path != null) {
						File file = new File(path);
					}

				};

			};
		}

	}
}
