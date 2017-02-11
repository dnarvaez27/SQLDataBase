package dnarvaez27.sqldatabase;

public enum Constraint
{
	AUTOINCREMENT( "GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)" ),
	FOREIGN_KEY( "FOREIGN KEY" ),
	NOT_NULL( "NOT NULL" ),
	NULL( "NULL" ),
	PRIMARY_KEY( "PRIMARY KEY" ),
	UNIQUE( "UNIQUE" ),
	ORDER_BY_ASC( "" );
	
	private String string;
	
	private Constraint( String string )
	{
		this.string = string;
	}
	
	public String getString( )
	{
		return string;
	}
}