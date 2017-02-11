package dnarvaez27.sqldatabase;

public enum DataType
{
	BOOLEAN( "BOOLEAN", Boolean.class ),
	CHAR( "CHAR", Character.class ),
	DECIMAL( "DECIMAL", Double.class ),
	FLOAT( "FLOAT", Float.class ),
	INTEGER( "INTEGER", Integer.class ),
	REAL( "REAL", Double.class ),
	VARCHAR( "VARCHAR", String.class );
	
	public static final String MAX = "32672";
	
	private Class<?> cl;
	
	private String string;
	
	DataType( String string, Class<?> cl )
	{
		this.string = string;
		this.cl = cl;
	}
	
	public String getDataType( )
	{
		return cl.getName( );
	}
	
	public String getString( )
	{
		return string;
	}
	
	public boolean isInstance( Object o )
	{
		return cl.isInstance( o );
	}
}