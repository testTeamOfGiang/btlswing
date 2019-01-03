package ui;

/**
 * @author huylam98it
 *
 */
public interface UIComponent {

	/**
	 * dung de khoi tao component
	 * 
	 */
	public void init();

	/**
	 * dung de add cac component vao
	 */
	public void addComponent();
	
	public void setFrame(MainFrame frame);
}
