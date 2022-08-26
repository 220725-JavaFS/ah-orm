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
	
	public void startConfig() {
		
		//String currentDirectory;
		
		//String path = this.getClass().getClassLoader().getResource("ah_orm.xml").toString();
		try {
			Path paths = Paths.get(this.getClass().getClassLoader().getResource("ah_orm.properties").toURI());
			FileReader reader=new FileReader(paths.toString());  
			Properties properties = new Properties();
			properties.load(getClass().getClassLoader().getResourceAsStream("ah_orm.properties"));
			properties.load(reader);
			
			setDatabaseUsername(properties.getProperty("databaseUsername"));
			setDatabasePWD(properties.getProperty("databasePwd"));
			setDatabaseURL(properties.getProperty("databaseURL"));
			
//			Path paths2 = Paths.get(this.getClass().getClassLoader().getParent().getResource("ah_orm.xml").toURI());
//			
//			File file = new File(paths2.toString());
//			
//			readXML(file);
//			File[] matchingFiles = file.listFiles(new FilenameFilter() {
//			    public boolean accept(File dir, String name) {
//			        return name.contentEquals("ah_orm.xml");
//			    }
//			});
//			
//			if(matchingFiles[0] != null) {
//				try {
//					readXML(matchingFiles[0]);
//				} catch (SAXException | IOException | ParserConfigurationException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
		} catch (URISyntaxException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//System.out.println(path);
		//System.out.println(path);
		//File file = new File("");
		//System.out.println(this.getClass().getProtectionDomain().getCodeSource().getLocation());
		//Path paths = Paths.get(ConfigUtil.class.getClassLoader());
		//System.out.println(this.getClass().getResource("/ah_orm.xml").getPath()); 
		//this.getClass().getre
		//URLClassLoader uc = (URLClassLoader) this.getClass().getClassLoader().getParent();
		//System.out.println(uc.getClass().getProtectionDomain().getCodeSource().getLocation());
		//ClassLoader cl = new ClassLoader();
		//System.out.println("Current working directory : "+currentDirectory);


	}
	//currently not implemented
	public void readXML(File matchingFiles) throws SAXException, IOException, ParserConfigurationException {
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
		DocumentBuilder db = dbf.newDocumentBuilder();  
		Document doc = db.parse(matchingFiles.getName());  
		doc.getDocumentElement().normalize(); 
		//System.out.println("Root element: " + doc.getDocumentElement().getNodeName()); 
		if(doc.getDocumentElement().getNodeName().equals("connect")) {
			NodeList nodeList = doc.getElementsByTagName("ormConnection");  
			for (int itr = 0; itr < nodeList.getLength(); itr++){  
				Node node = nodeList.item(itr);  
				//System.out.println("\nNode Name :" + node.getNodeName());  
				if (node.getNodeType() == Node.ELEMENT_NODE)   
				{  
					Element eElement = (Element) node;
					
					databaseUsername = eElement.getElementsByTagName("databaseUsername").item(0).getTextContent();
					setDatabaseUsername(databaseUsername);
					//System.out.println(eElement.getElementsByTagName("databaseUsername").item(0).getTextContent()); 
					databasePWD = eElement.getElementsByTagName("databasePwd").item(0).getTextContent();
					setDatabasePWD(databasePWD);
					//System.out.println(eElement.getElementsByTagName("databasePwd").item(0).getTextContent());  
					databaseURL = eElement.getElementsByTagName("databaseURL").item(0).getTextContent();
					setDatabaseURL(databaseURL);
					//System.out.println(eElement.getElementsByTagName("databaseURL").item(0).getTextContent());  
				}  
			} 
		}
 
		
	}
	
	public String readXMLUrl() {
		
		
		return "";
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
