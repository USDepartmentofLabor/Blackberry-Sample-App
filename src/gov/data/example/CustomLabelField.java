package gov.data.example;


import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;


public class CustomLabelField extends Field {

	
	private String label;
	private int foregroundColor;
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getForegroundColor() {
		return foregroundColor;
	}

	public void setForegroundColor(int foregroundColor) {
		this.foregroundColor = foregroundColor;
	}

	public int getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(int backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	private int backgroundColor;
	
	
	
	public CustomLabelField(String label, int foregroundColor, int backgroundColor, long style) {
		    super(style);
		    this.label = label;
		    this.foregroundColor = foregroundColor;
		    this.backgroundColor = backgroundColor;
	}
	
	protected void layout(int width, int height) {
		setExtent(width, getFont().getHeight());
		
	}

	protected void paint(Graphics graphics) {
		graphics.setBackgroundColor(backgroundColor);
		graphics.clear();
		graphics.setColor(foregroundColor);
		graphics.drawText(label,0,0);
		
	}
	
	

}
