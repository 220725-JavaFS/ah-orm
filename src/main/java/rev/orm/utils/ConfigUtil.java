package rev.orm.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Work
 *	Will read the configuration file from the java directory application  
 */
public class ConfigUtil {
	
	private String databaseUsername;
	private String databasePWD;
	private String databaseURL;
	
	public ConfigUtil() {
		
	}
	/*
	 * is called when making a connection to the Database
	 * gets the User name, password, and the URL of the Data base under the main resources of the application
	 * Properties file must be named "ah_orm.properties" and its path must be within the main resources of the app
	 * Properties file Must contain 
	 * databaseUsername=? 
	 * databasePwd=?
	 * databaseURL=?
	 */
	public void startConfig() {

		try {
			Path paths = Paths.get(this.getClass().getClassLoader().getResource("ah_orm.properties").toURI());
			FileReader reader=new FileReader(paths.toString());  
			Properties properties = new Properties();
			properties.load(getClass().getClassLoader().getResourceAsStream("ah_orm.properties"));
			properties.load(reader);
			
			setDatabaseUsername(properties.getProperty("databaseUsername"));
			setDatabasePWD(properties.getProperty("databasePwd"));
			setDatabaseURL(properties.getProperty("databaseURL"));
			
		} catch (URISyntaxException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	//currently not implemented
	public void readXML(File matchingFiles) throws SAXException, IOException, ParserConfigurationException {
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
		DocumentBuilder db = dbf.newDocumentBuilder();  
		Document doc = db.parse(matchingFiles.getName());  
		doc.getDocumentElement().normalize(); 
		
		if(doc.getDocumentElement().getNodeName().equals("connect")) {
			NodeList nodeList = doc.getElementsByTagName("ormConnection");  
			for (int itr = 0; itr < nodeList.getLength(); itr++){  
				Node node = nodeList.item(itr);  
				
				if (node.getNodeType() == Node.ELEMENT_NODE)   
				{  
					Element eElement = (Element) node;
					
					databaseUsername = eElement.getElementsByTagName("databaseUsername").item(0).getTextContent();
					setDatabaseUsername(databaseUsername);
					databasePWD = eElement.getElementsByTagName("databasePwd").item(0).getTextContent();
					setDatabasePWD(databasePWD);
					databaseURL = eElement.getElementsByTagName("databaseURL").item(0).getTextContent();
					setDatabaseURL(databaseURL);
				}  
			} 
		}
 
		
	}
	
	public String getDatabaseUsername() {
		return databaseUsername;
	}

	public void setDatabaseUsername(String databaseUsername) {
		this.databaseUsername = databaseUsername;
	}

	public String getDatabasePWD() {
		return databasePWD;
	}

	public void setDatabasePWD(String databasePWD) {
		this.databasePWD = databasePWD;
	}

	public String getDatabaseURL() {
		return databaseURL;
	}

	public void setDatabaseURL(String databaseURL) {
		this.databaseURL = databaseURL;
	}

}
