package projectPlan.groupe1;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

public class DataBaseConnection {
	private static final String DRIVER = 	"org.postgresql.Driver";
	private static final String JDBC_URL =	"jdbc:postgresql://localhost:5432/libreplan";
	private static final String USER = 		"libreplan";
	private static final String PASSWORD =	"libreplan";

	//Constructor
	public DataBaseConnection() {

	}

	//NOT USED
	//Read from file
	//return IDataSet
	private IDataSet readDataSet (String filename) throws Exception{
		return new FlatXmlDataSetBuilder().build(new File(filename));
	}

	//Return a specific value from the table with the query sent
	//@table : table to check
	//@query : query to use
	//@value : value returned
	//return String
	public String getData (String table, String query, String value) throws Exception {
		IDatabaseTester databaseTester = new JdbcDatabaseTester(DRIVER, JDBC_URL, USER, PASSWORD);
		QueryDataSet dbDataSet = new QueryDataSet (databaseTester.getConnection());
		dbDataSet.addTable(table, query);
		ITable dbTable = dbDataSet.getTable(table);
		return dbTable.getValue(0, value).toString();
	}


	//INSERT in the database
	//@table : table name
	//@query : query
	public void insertData(String table, String query) throws Exception {
		try {

			IDatabaseTester databaseTester = new JdbcDatabaseTester(DRIVER, JDBC_URL, USER, PASSWORD);
			databaseTester.setSetUpOperation(DatabaseOperation.INSERT);
			QueryDataSet dbDataSet = new QueryDataSet (databaseTester.getConnection());
			dbDataSet.addTable(table, query);
			databaseTester.setDataSet(dbDataSet);
			databaseTester.onSetup();
			
		}
		catch(DataSetException e) {

		}
	}

	//DELETE from the database
	//@table : table name
	//@query : query
	public void deleteData(String table, String query) throws Exception {
		try {

			IDatabaseTester databaseTester = new JdbcDatabaseTester(DRIVER, JDBC_URL, USER, PASSWORD);
			QueryDataSet dbDataSet = new QueryDataSet (databaseTester.getConnection());
			dbDataSet.addTable(table, query);
			databaseTester.setSetUpOperation(DatabaseOperation.DELETE);
			databaseTester.setDataSet(dbDataSet);
			databaseTester.onSetup();
			databaseTester.getConnection().close();
		}
		catch(DataSetException e) {

		}
	}
	public void close() throws Exception {
		//dbDataSet.
	}
}

