package dnarvaez27.sqldatabase;

import java.util.Iterator;
import java.util.LinkedList;

import dnarvaez27.sqldatabase.Conditional.Assignment;

public class Table
{
	private LinkedList<Column> columns;
	
	private String nombre;
	
	private String primaryKey;
	
	public Table( String nombre )
	{
		this.nombre = nombre;
		columns = new LinkedList<>( );
	}
	
	public static void main( String[ ] args )
	{
		Table table = new Table( "tabla" );
		table.addColumn( "columna1", DataType.VARCHAR, DataType.MAX );
		table.addColumn( "columna2", DataType.INTEGER );
		table.setPrimaryKey( "columna1", "columna2" );
		System.out.println( table.create( ) );
		System.out.println( table.insert( null, "David D'Oh", 20 ) );
		System.out.println( table.removeWhere( new Conditional( "columna1", "David", Operator.EQUAL ), new Conditional( "columna2", 20, Operator.LESS_EQUAL ) ) );
		System.out.println( table.update( new Assignment[ ]
		{
				new Assignment( "columna1", "Angela" ),
				new Assignment( "columna2", 20 )
		}, new Conditional( "columna2", 20, Operator.LESS_EQUAL ) ) );
		
		System.out.println( table.get( "*", new Conditional( "columna2", 100, Operator.LESS_EQUAL ) ) );
		
	}
	
	public void addColumn( String field, DataType dataType )
	{
		addColumn( field, dataType, ( Object[ ] ) null );
	}
	
	public void addColumn( String field, DataType dataType, Object ... arg )
	{
		addColumn( field, dataType, arg, ( Constraint[ ] ) null );
	}
	
	public void addColumn( String field, DataType dataType, Object[ ] arg, Constraint ... properties )
	{
		columns.add( new Column( field, dataType, arg, properties ) );
	}
	
	public String create( )
	{
		StringBuilder table = new StringBuilder( );
		table.append( "CREATE TABLE " + nombre );
		table.append( "( " );
		for( Column column : columns )
		{
			table.append( column + ", " );
		}
		table.append( primaryKey );
		table.append( " )" );
		return table.toString( );
	}
	
	public String get( String values, Conditional[ ] conditionals, KeyWord ... keyWords )
	{
		StringBuilder select = new StringBuilder( );
		select.append( String.format( "SELECT %s FROM %s ", values, nombre ) );
		if( conditionals != null && conditionals.length != 0 )
		{
			select.append( "WHERE " );
			for( Conditional conditional : conditionals )
			{
				select.append( conditional + " AND " );
			}
			select.setLength( select.length( ) - 5 );
		}
		if( keyWords != null && keyWords.length != 0 )
		{
			for( KeyWord keyWord : keyWords )
			{
				select.append( keyWord + ", " );
			}
			select.setLength( select.length( ) - 2 );
		}
		return select.toString( );
	}
	
	public String get( String values, Conditional ... conditionals )
	{
		return get( values, conditionals, ( KeyWord[ ] ) null );
	}
	
	public String insert( Object ... values )
	{
		return insert( null, values );
	}
	
	public String insert( String[ ] config, Object ... values )
	{
		StringBuilder insert = new StringBuilder( );
		insert.append( String.format( "INSERT INTO %s ", nombre ) );
		if( config != null )
		{
			insert.append( "(" );
			for( String cField : config )
			{
				insert.append( cField + ", " );
			}
			insert.setLength( insert.length( ) - 2 );
			insert.append( ")" );
		}
		insert.append( "VALUES" );
		insert.append( "( " );
		Iterator<Column> it = columns.iterator( );
		for( Object value : values )
		{
			Column c = it.next( );
			if( c.isIdentity( ) || c.isInstance( value ) || value == null )
			{
				if( c.isIdentity( ) )
				{
					c = it.next( );
				}
				if( value instanceof String )
				{
					value = SQLDataBase.formatString( String.valueOf( value ) );
				}
				insert.append( value + ", " );
			}
			else
			{
				throw new IllegalArgumentException( value.getClass( ).getName( ) + " Value does not match column's DataType " + c.getDataType( ).getDataType( ) );
			}
		}
		insert.setLength( insert.length( ) - 2 );
		insert.append( " )" );
		
		return insert.toString( );
	}
	
	public String removeWhere( Conditional ... conditionals )
	{
		StringBuilder remove = new StringBuilder( );
		remove.append( String.format( "DELETE FROM %s WHERE ", nombre ) );
		for( Conditional conditional : conditionals )
		{
			remove.append( conditional.toString( ) + " AND " );
		}
		remove.setLength( remove.length( ) - 5 );
		return remove.toString( );
	}
	
	public void setPrimaryKey( String ... fields )
	{
		StringBuilder args = new StringBuilder( );
		for( String arg : fields )
		{
			if( !columns.contains( Column.columnName( arg ) ) )
			{
				throw new IllegalArgumentException( "PRIMARY KEY Must contain valid columns from the table. " + arg + " Doesn't exist as column in the table" );
			}
			args.append( arg + ", " );
		}
		args.setLength( args.length( ) - 2 );
		primaryKey = String.format( "PRIMARY KEY ( %s )", args.toString( ) );
	}
	
	public String update( Assignment[ ] updates, Conditional ... conditionals )
	{
		StringBuilder update = new StringBuilder( );
		update.append( String.format( "UPDATE %s SET ", nombre ) );
		for( Assignment set : updates )
		{
			update.append( set + ", " );
		}
		update.setLength( update.length( ) - 2 );
		update.append( " WHERE " );
		for( Conditional conditional : conditionals )
		{
			update.append( conditional.toString( ) + " AND " );
		}
		update.setLength( update.length( ) - 5 );
		
		return update.toString( );
	}
}