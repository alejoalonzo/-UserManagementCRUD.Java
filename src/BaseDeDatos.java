import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.gjt.mm.mysql.Driver;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class BaseDeDatos {
	private String servidor;
	private String usuario;
	private String nombreDB;
	private String password;
	
	//constructor
	public BaseDeDatos (String servidor, String nombreDB, String usuario, String password) {
		this.servidor = servidor;
		this.nombreDB = nombreDB;
		this.usuario = usuario;
		this.password = password;
		
		try {
			DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//validar---------------------------------
	public boolean validadarUser(String user, String pass) {
		
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		lista = read("SELECT * FROM usuarios WHERE nombre='"+user+"' and password= '"+pass+"' LIMIT 0,1 ");
		
		if(lista.size()==0) {
			return false;
		}else {
			return true;
		}

	}
	
	
	//CRUD---------------------------------------------------------------------------------
	
	//create------------------------------------------------------------------------------
	public boolean create (Usuario u, String tabla) {
		
		boolean result = false;
		String consulta ="";
		Connection conexion = null;
		Statement s = null;
		
		try {
			
			//Establecer conexion con jdbc que es como la ruta para java, ademas se le añade la IP(localhosty),
			//nommbre de base de datos(javaejmuno), usuario(root), y password("").
			conexion = (Connection) DriverManager.getConnection("jdbc:mysql://"+this.servidor+"/"+this.nombreDB, usuario, password);
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	
		try {
			s = (Statement) conexion.createStatement();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			
			consulta = "INSERT INTO " + tabla + "(nombre, password, edad, salario, activo) VALUES ('"+u.getNombre()+"', '"+u.getPassword()+"', "+u.getEdad()+","+u.getSalario()+", "+u.getActivo()+")";
			
			result  = s.execute(consulta);//no es esxecuteQuery sino solo execute
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		return result;
	}
	
	
	//read--------------------------------------------------------------------------------
	
	//Esta funcion va a realizar una consulta de tipo SELECT en la BD y va a devolver un arraList en forma de tabla
	public ArrayList<Usuario> read (String consulta) {
		
		int contador = 0;
		ArrayList<Usuario> alu = new ArrayList<Usuario>();
		Usuario u = null;
		
		Connection conexion = null;
		Statement s = null;
		
		
		try {
	
			//Establecer conexion con jdbc que es como la ruta para java, ademas se le añade la IP(localhosty),
			//nommbre de base de datos(javaejmuno), usuario(root), y password("").
			conexion = (Connection) DriverManager.getConnection("jdbc:mysql://"+this.servidor+"/"+this.nombreDB, usuario, password);
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	
		try {
			s = (Statement) conexion.createStatement();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet rs = null;
		try {
			rs = s.executeQuery(consulta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while(rs.next()) {//Bucle que se recorre cuando al menos hay una consulta por visualizar
				
				u= new Usuario();
				u.setId(rs.getInt("id"));
				u.setNombre(rs.getString("nombre"));
				u.setPassword(rs.getString("password"));
				u.setEdad(rs.getInt("edad"));
				u.setSalario(rs.getFloat("salario"));
				u.setActivo(rs.getBoolean("activo"));
				
				alu.add(u);
			}   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		try {
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//Igual que en los ficheros hay que cerrar la conexion.
		
		return alu;
	}
	
		
		
		
	//update----------------------------------------------------------------------------------------
		
	public boolean update (Usuario u, String tabla) {
		boolean result = false;
		String consulta ="";
		Connection conexion = null;
		Statement s = null;
		
		try {
			
			//Establecer conexion con jdbc que es como la ruta para java, ademas se le añade la IP(localhosty),
			//nommbre de base de datos(javaejmuno), usuario(root), y password("").
			conexion = (Connection) DriverManager.getConnection("jdbc:mysql://"+this.servidor+"/"+this.nombreDB, usuario, password);
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	
		try {
			s = (Statement) conexion.createStatement();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			
			consulta = "UPDATE " + tabla + " SET nombre='"+u.getNombre()+"', password='"+u.getPassword()+"', edad="+u.getEdad()+", salario="+u.getSalario()+", activo="+u.getActivo()+" WHERE id= "+u.getId();
			System.out.println(consulta);
			result  = s.execute(consulta);//no es esxecuteQuery sino solo execute
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		return result;
	}
	
	//delete-----------------------------------------------------------------------------------------
	
	public boolean delete (String consulta) {
		
		boolean result = false;
		Connection conexion = null;
		Statement s = null;
		
		try {
			
			//Establecer conexion con jdbc que es como la ruta para java, ademas se le añade la IP(localhosty),
			//nommbre de base de datos(javaejmuno), usuario(root), y password("").
			conexion = (Connection) DriverManager.getConnection("jdbc:mysql://"+this.servidor+"/"+this.nombreDB, usuario, password);
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	
		try {
			s = (Statement) conexion.createStatement();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			result  = s.execute(consulta);//no es esxecuteQuery sino solo execute
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		return result;
	}
}
