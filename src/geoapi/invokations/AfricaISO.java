package geoapi.invokations;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AfricaISO {
	static Connection make_Connection (){
		Connection conn = null;
	    try{
	    	Class.forName("com.mysql.jdbc.Driver");	
	    	conn = DriverManager.getConnection( "jdbc:mysql://95.138.168.128:3306/hackathon", "root","root");
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    return conn;
	}
	public static void main(String [] args){
		Connection conn = make_Connection();
		int length = 60;
		System.out.println(conn.toString());
		for (int i=1;i<length+1;i++){
			
			String query = "select country_iso from ISO_Codes where id="+i;
			System.out.println(query);
			String iso3_name = null;
			try{
				PreparedStatement  stmt = conn.prepareStatement(query);
				ResultSet results = stmt.executeQuery();
				if(results.next()){
					   iso3_name = results.getString(1);
				}
				
			}catch(Exception e){}
			System.out.println(iso3_name);
			ArrayList<Double> boundaries = makeWS_invocation("http://www.fao.org/countryprofiles/geoinfo/ws/countryCoordinates/",iso3_name);
			if(boundaries.size()>0){
				Double maxlatitude = boundaries.get(0);
				Double maxlongitude = boundaries.get(1);
				Double minlatitude =  boundaries.get(2);
				Double minlongitude = boundaries.get(3);
				
				try{
					
					query = "insert into Country_GeoPos(id_ISO,max_lat,min_lat,max_long,min_long) values ("
							+i+","+maxlatitude+","+maxlongitude+","+minlatitude+","+minlongitude+")";
					System.out.println(query);
					PreparedStatement stmt = conn.prepareStatement(query);
					stmt.executeUpdate();
				}catch(Exception e){}
			}
		}
	}
	
	static public ArrayList<Double> makeWS_invocation(String serviceURLPath,String parameter){
		ArrayList<Double> results = new ArrayList<Double>();
		
		try{
			   DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			   DocumentBuilder db = dbf.newDocumentBuilder();
			   URL test = new URL(serviceURLPath+parameter);
			   InputStream whatever = test.openStream();
			   Document doc = db.parse(whatever);
			   doc.getDocumentElement().normalize();
			   NodeList nList = doc.getElementsByTagName("Data");
			   for (int temp = 0; temp < nList.getLength(); temp++) {
				   Node nNode = nList.item(temp);
				   if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	                    Element eElement = (Element) nNode;
	                    results.add(Double.parseDouble(eElement.getElementsByTagName("hasMaxLatitude").item(0).getTextContent()));
	                    results.add(Double.parseDouble(eElement.getElementsByTagName("hasMaxLongitude").item(0).getTextContent()));
	                    results.add(Double.parseDouble(eElement.getElementsByTagName("hasMinLatitude").item(0).getTextContent()));
	                    results.add(Double.parseDouble(eElement.getElementsByTagName("hasMinLongitude").item(0).getTextContent()));
				   }
			   }
		}catch(Exception e){}
		return results;
	}
}

	