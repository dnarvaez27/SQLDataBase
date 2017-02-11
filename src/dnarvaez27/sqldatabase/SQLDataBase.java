package dnarvaez27.sqldatabase;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;

public class SQLDataBase
{
	protected Connection conexion;
	
	private String name;
	
	protected LinkedHashMap<String, Table> tables;
	
	public SQLDataBase( String name, String path ) throws SQLException, Exception
	{
		this.name = name;
		System.setProperty( "derby.system.home", new File( path ).getAbsolutePath( ) );
		connect( );
		tables = new LinkedHashMap<>( );
	}
	
	protected void connect( ) throws SQLException, Exception
	{
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		Class.forName( driver ).newInstance( );
		String url = "jdbc:derby:" + name + ";create=true";
		conexion = DriverManager.getConnection( url );
	}
	
	protected Statement createConnection( ) throws SQLException
	{
		return conexion.createStatement( );
	}
	
	protected Table createTable( String nombre )
	{
		Table t = new Table( nombre );
		tables.put( nombre, t );
		return t;
	}
	
	protected void disconnect( ) throws SQLException
	{
		conexion.close( );
		String down = "jdbc:derby:;shutdown=true";
		try
		{
			DriverManager.getConnection( down );
		}
		catch( SQLException e )
		{
			// By disconnecting the DB, produces an Exception
		}
	}
	
	protected boolean existsTable( String tableName )
	{
		try
		{
			Statement s = createConnection( );
			StringBuffer comando = new StringBuffer( );
			comando.append( String.format( "SELECT * FROM %s ", tableName ) );
			comando.append( "WHERE 1 = 2" );
			
			s.execute( comando.toString( ) );
			s.close( );
			return true;
		}
		catch( Exception e )
		{
			return false;
		}
	}
	
	protected static String formatString( String text )
	{
		return String.format( "'%s'", text.replace( "'", "''" ) );
	}
}