import java.util.ArrayList;

import java.util.Scanner;

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		boolean resultadoBorrar = false;
		boolean resultadoCrear = false;
		boolean resultadoActualizar = false;
		Usuario user = new Usuario();
		Scanner entradaDeDatos = new Scanner(System.in);
		
		BaseDeDatos bd= new BaseDeDatos("localhost", "javaejmuno", "root","");
		
		String usuarioo="";
		String contrasena ="";
		boolean tieneAcceso =false;
		int opcion = -1;
		
		System.out.println("*******************************************************************");
		System.out.println("*********************GESTOR DE USUARIO*****************************");
		System.out.println("*******************************************************************");
		
		
		do {
			System.out.print("Introduce tu nombre de usuario");
			usuarioo = entradaDeDatos.next();
			System.out.print("Introduce tu nombre de password");
			contrasena = entradaDeDatos.next();
			
			tieneAcceso = bd.validadarUser(usuarioo, contrasena);
			if(!tieneAcceso) {
				System.out.println("Lo siento no tienes acceso");
			}
			
		}while(!tieneAcceso);
		System.out.println("Tienes acceso");
		
		
		
		
		do {
			System.out.println("*******************************************************************");
			System.out.println("*********************GESTOR DE USUARIO*****************************");
			System.out.println("*******************************************************************");
			
			System.out.println("Seleccione una opción del menu:");
			System.out.println("");
			
			System.out.println("1) Crear usuario");
			System.out.println("2) Borrar usuario");
			System.out.println("3) Actualizar usuario");
			System.out.println("4) Listar usuarios");
			System.out.println("");
			
			System.out.println("0) Salir");
			System.out.println("");
			
			System.out.println("Opción");
			opcion = entradaDeDatos.nextInt();
			
			if(opcion == 1) {
				//Crear usuario
				insertarUsuario(bd);
				System.out.println("***********************");
				mostrarUsuarios(bd);
				System.out.println("***********************");
				
			}else if(opcion == 2) {
				//Borrar un usuario
				borrarUsuario(bd);
				
			}else if(opcion == 3) {
				//Modificar un usuario
				modificarUsuario(bd);
				
			}else if(opcion == 4) {
				//Listar usuario
				System.out.println("***********************");
				mostrarUsuarios(bd);
				System.out.println("***********************");
				
			}else if(opcion != 0) {
				System.out.println("****Opción erronea*****");
			}
				
		}while(opcion !=0);
		System.out.println("Fin del programa");
		
		
		
		
		
		
		
		
		
		//read-------------------------------------------------------------
		//mostrarUsuarios(bd);
		
		
		//*******************************CRUD*****************************************************
		//delete----------------------------------------------------------
		/*
		resultadoBorrar = bd.delete("DELETE FROM usuarios WHERE id = 1");
		if(!resultadoBorrar) {
			System.out.println("Borrado correctamente");
		}else {
			System.out.println("Error en el borrado");
		}*/
		
		//create-------------------------------------------------------------
		/*user.setNombre("Pepito");
		user.setPassword("123");
		user.setEdad(19);
		user.setSalario(1000);
		user.setActivo(true);
		
		resultadoCrear = bd.create(user, "usuarios");//El usuario que acabo de crear justo arriba y la tabla de la bd donde se creará
		if(!resultadoCrear) {
			System.out.println("creado correctamente");
		}else {
			System.out.println("Error en el create");
		}*/
		
		//update------------------------------------------------------------
		/*user.setId(3);
		user.setNombre("Pepito Grillo");
		user.setPassword("123");
		user.setEdad(19);
		user.setSalario(1000);
		user.setActivo(true);
		
		resultadoActualizar = bd.update(user, "usuarios");//El usuario que acabo de crear justo arriba y la tabla de la bd donde se creará
		if(!resultadoActualizar) {
			System.out.println("Actualizado correctamente");
		}else {
			System.out.println("Error en actualizar");
		}*/
		
		
		//mostrarUsuarios(bd);
		
	}
	
	
	
	
	//----------------------------------------------------READ----------------------------------------------------------------------
	public static void mostrarUsuarios(BaseDeDatos bd) {
		
		System.out.println("****************************************************************");
		int i =0;
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		
		usuarios=bd.read("SELECT * FROM usuarios");
		
		//comprobar si se ha rrellenado correctamente el arrayList
		
		for(i =0; i < usuarios.size(); i ++) {
		 System.out.println(usuarios.get(i).getId()+ "--" +usuarios.get(i).getNombre()+ "--" +usuarios.get(i).getPassword()+ "--" 
				 +usuarios.get(i).getEdad()+ "--" +usuarios.get(i).getSalario()+ "--" + usuarios.get(i).getActivo()+ "--" );
		}
		System.out.println("****************************************************************");
		
	}
	
	public static void insertarUsuario(BaseDeDatos bd) {
		Scanner entradaDeDatos = new Scanner(System.in);
		Usuario u = new Usuario();
		boolean estado = false;
		
		//Capturo los datos
		System.out.println("Introduce tu nombre");
		u.setNombre(entradaDeDatos.next());
		System.out.println("Introduce tu password");
		u.setPassword(entradaDeDatos.next());
		System.out.println("Introduce tu edad");
		u.setEdad(entradaDeDatos.nextInt());
		System.out.println("Introduce tu salario");
		u.setSalario(entradaDeDatos.nextFloat());
		
		//Creoo en BBDD con la funcion crud
		estado = bd.create(u,"usuarios");
		
		if(!estado) {
			System.out.println("Usuario creado correctamente");
		}else {
			System.out.println("Error al crear usuario");
		}
	}
	
	public static void borrarUsuario(BaseDeDatos bd) {
		Scanner entradaDeDatos = new Scanner(System.in);
		Usuario u = new Usuario();
		boolean estado = false;
		int ide = 0;
		
		mostrarUsuarios(bd);
		System.out.println("***********************");
		
		//Capturo los datos
		System.out.println("Introduce el id del usuario a eliminar");
		ide = (entradaDeDatos.nextInt());
		
		
		
		//Borro en BBDD con la funcion crud
		estado = bd.delete("DELETE FROM usuarios WHERE id="+ide);
		
		if(!estado) {
			System.out.println("Usuario borrado correctamente");
		}else {
			System.out.println("Error al borrar usuario");
		}
	}
	
	public static void modificarUsuario(BaseDeDatos bd) {
		Scanner entradaDeDatos = new Scanner(System.in);
		Usuario u = new Usuario();
		boolean estado = false;
		
		
		mostrarUsuarios(bd);
		System.out.println("***********************");
		
		//Capturo los datos
		System.out.println("Introduce el el id del usuario a modificar");
		u.setId(entradaDeDatos.nextInt());
		System.out.println("Introduce el nombre");
		u.setNombre(entradaDeDatos.next());
		System.out.println("Introduce el password");
		u.setPassword(entradaDeDatos.next());
		System.out.println("Introduce la edad");
		u.setEdad(entradaDeDatos.nextInt());
		System.out.println("Introduce el salario");
		u.setSalario(entradaDeDatos.nextFloat());
		
		//Modifico en BBDD con la funcion crud
		estado = bd.update(u,"usuarios");
		
		if(!estado) {
			System.out.println("Usuario modificado correctamente");
		}else {
			System.out.println("Error al modificar usuario");
		}
	}
	

}
