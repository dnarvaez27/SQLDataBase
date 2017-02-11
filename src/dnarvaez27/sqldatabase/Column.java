package dnarvaez27.sqldatabase;

public class Column
{
	private Object[ ] arguments;
	
	private Constraint[ ] constraints;
	
	private DataType dataType;
	
	private String nombre;
	
	public Column( String nombre, DataType dataType )
	{
		this( nombre, dataType, ( Object[ ] ) null );
	}
	
	public Column( String nombre, DataType dataType, Object ... arguments )
	{
		this( nombre, dataType, arguments, null );
	}
	
	public Column( String nombre, DataType dataType, Object[ ] arguments, Constraint[ ] constraints )
	{
		this.nombre = nombre;
		this.dataType = dataType;
		this.arguments = arguments;
		this.constraints = constraints;
	}
	
	public static Column columnName( String name )
	{
		return new Column( name, DataType.INTEGER );
	}
	
	@Override
	public boolean equals( Object obj )
	{
		if( obj instanceof Column )
		{
			return nombre.equals( ( ( Column ) obj ).nombre );
		}
		if( obj instanceof String )
		{
			return nombre.equals( String.valueOf( obj ) );
		}
		return false;
	}
	
	public Object[ ] getArguments( )
	{
		return arguments;
	}
	
	public Constraint[ ] getConstraints( )
	{
		return constraints;
	}
	
	public DataType getDataType( )
	{
		return dataType;
	}
	
	public String getNombre( )
	{
		return nombre;
	}
	
	public boolean isIdentity( )
	{
		if( constraints != null )
		{
			for( Constraint constraint : constraints )
			{
				if( constraint.equals( Constraint.AUTOINCREMENT ) )
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isInstance( Object obj )
	{
		return dataType.isInstance( obj );
	}
	
	@Override
	public String toString( )
	{
		StringBuilder col = new StringBuilder( );
		col.append( String.format( "%s %s", nombre, dataType.getString( ) ) );
		
		if( arguments != null )
		{
			for( Object arg : arguments )
			{
				if( arg != null )
				{
					col.append( "( " + arg + " )" );
					break;// TODO?
				}
			}
		}
		if( constraints != null )
		{
			col.append( " " );
			for( Constraint constraint : constraints )
			{
				col.append( constraint.getString( ) + " " );
			}
		}
		return col.toString( );
	}
}