package com.framework.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;

import com.framework.components.Settings;

public class SqlDatabase {

	public static HashMap<String, String> resultData = new HashMap<String, String>();
	protected Properties properties = Settings.getInstance();
	
	/**
	 * Method to get the data from SQL database based on Query supplied
	 * 
	 * @param sqlQueryProperties (Key from Property file)
	 * @return Associated Value of Key from Property file
	 */
	public HashMap<String, String> SQL(String sqlQueryProperties) {
		
		String url = properties.getProperty("SQLServer");
		String username = properties.getProperty("SQLUsername");
		String password = properties.getProperty("SQLPassword");
		String sqlQuery = getSqlqueries(sqlQueryProperties);
		try  {
			Class.forName(properties.getProperty("DriverName"));
			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("******Database connected!*******");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			if (resultSet != null) {
				ResultSetMetaData resultmetaData = resultSet.getMetaData();
				int count = resultmetaData.getColumnCount();
				int rowCount = 1;
				while (resultSet.next()) {
					for (int i = 1; i <= count; i++) {
						String column = String.valueOf(resultmetaData.getColumnName(i));
						String value = String.valueOf(resultSet.getObject(i));
						resultData.put(column + rowCount, value);
					}
					rowCount++;
				}
			}
			System.out.println(resultData.toString());
		} catch (SQLException | ClassNotFoundException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
		return resultData;
	}
	
	/**
	 * This method returns the property value of the key provided from Property file
	 * 
	 * @param key
	 * @return value of key
	 */
    public String getSqlqueries(String key) {
        File file = new File("src/test/resources/properties/SqlQueries.properties");
        Properties property = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream(file);
            property.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return property.getProperty(key);
    }
}
