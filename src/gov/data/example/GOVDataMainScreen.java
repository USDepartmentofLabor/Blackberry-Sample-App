package gov.data.example;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import gov.data.api.GOVDataContext;
import gov.data.api.GOVDataRequest;
import gov.data.api.GOVDataRequestCallback;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.MainScreen;

/**
 * A class extending the MainScreen class, which provides default standard
 * behavior for BlackBerry GUI applications.
 */
public final class GOVDataMainScreen extends MainScreen implements FieldChangeListener, GOVDataRequestCallback
{
    /**
     * Creates a new DolDataMainScren object
     */


	public final String API_KEY =  "";
	public final String SHARED_SECRET = "";
    public final String API_URI = "V1";
    public final String API_HOST = "http://api.dol.gov";
    public final String  API_DATA = "DOLAgency/Agencies";
	

  
	
	//GOV Data Context object
	private GOVDataContext context;
    
	private ButtonField submitButton;
	
    public GOVDataMainScreen()
    {
    	//Instantiate GOV Data context object
    	//This object stores the API information required to make requests
    	context = new GOVDataContext(API_KEY, SHARED_SECRET, API_HOST, API_URI);
    	
        // Set the displayed title of the screen       
        setTitle("Agencies");
        
        //Create the search button. Add click event. Center it.
		submitButton = new ButtonField("Call API",ButtonField.CONSUME_CLICK | ButtonField.FIELD_HCENTER);
		//Set delegate for button presses to be the same class (this)
		//It will trigger "public void fieldChanged(Field field, int context)" when pressed
		submitButton.setChangeListener(this);
		
		//Add the search button
		add(submitButton);
    }
    
    public void fieldChanged(Field arg0, int arg1) {
		
		//Update the status field to give user feedback of what is occurring (searching)
		LabelField status = new LabelField("Searching...",LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
		setStatus(status);
		
		//Catch any exceptions
		try
		{
			//Instantiate new request object. Pass the context ivar that contains all the API key info
			GOVDataRequest request = new GOVDataRequest(this, context);
			String method = API_DATA;
			
			Hashtable args = new Hashtable(2);
			args.put("select", "Agency,AgencyFullName");
			args.put("orderby", "AgencyFullName");
			
			request.callAPIMethod(method, args);
		}
		catch (Exception e) {
			//Alert the user that an error occurred
			Dialog.alert(e.getMessage());
			//Clear status bar
			setStatus(null);
		}
	}

	public void GOVDataResultsCallback(Vector results) {
		
		for (int i = 0; i < results.size(); i++) {
			Hashtable record = (Hashtable)results.elementAt(i);
			
			Enumeration e = record.keys();
			String key = (String) e.nextElement();
			LabelField l = new LabelField(key + " - " + record.get(key),FOCUSABLE);
			/**
			 * 
			 *  User can enter their own key pair values that are specific or let the process call the data by  default.
			LabelField l = new LabelField(record.get("Agency") + " - " + record.get("AgencyFullName"),FOCUSABLE);
			**/
	    	add(l);
		}

    	//Clear status bar
		setStatus(null);
	}

	public void GOVDataErrorCallback(String error) {
		Dialog.alert(error);
		//Clear status bar
		setStatus(null);
		
	}

	public void GOVDataResultsCallbackText(String results) {
		
	
	    LabelField l = new LabelField(results);
		
	    	add(l);
	 //Clear status bar
		setStatus(null);
	
	}

	



}
