package outputlabel;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean
public class OutputLabelBean {

	private String test;
	
	public String getLabel() {
		return new Date().toString();
	}
	
	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
	
	public void action() {
		
	}
	
	
}
