package projectPlan.groupe1;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

public class DataBaseConnection {
	private static final String DRIVER = 	"org.postgresql.Driver";
	private static final String JDBC_URL =	"jdbc:postgresql://localhost:5432/libreplan";
	private static final String USER = 		"libreplan";
	private static final String PASSWORD =	"libreplan";
	
	
	public DataBaseConnection() {
		
	}
	private IDataSet readDataSet (String filename) throws Exception{
		return new FlatXmlDataSetBuilder().build(new File(filename));
	}
	
		
		
		
		



}

