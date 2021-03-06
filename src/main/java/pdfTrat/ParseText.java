package pdfTrat;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by Cristian del Cerro.
 */

public class ParseText {
	
	public ParseText(){
	}
	
	public ArrayList<String> listLineText(String FilePath) {
		ArrayList<String> Line = new ArrayList<String>();
        try{
            // Abrimos el archivo
            FileInputStream fstream = new FileInputStream(FilePath);
            // Creamos el objeto de entrada
            DataInputStream entrada = new DataInputStream(fstream);
            // Creamos el Buffer de Lectura
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
            String strLinea;
            // Leer el archivo linea por linea
            while ((strLinea = buffer.readLine()) != null)   {
                // Guardamos cada linea en una posici�n de la ista
               Line.add(strLinea);
            }
            // Cerramos el archivo
            entrada.close();
            
        }catch (Exception e){ //Catch de excepciones
            System.err.println("Ocurrio un error: " + e.getMessage());
        }
        return Line;
    }
	
	//Limpia texto que estorba del txt de los est�ndares ISO
	public ArrayList<String> cleanTextISO(ArrayList<String> sucia){
		
		
		/*ArrayList<String> semienblanco = QuitarRenglonesBlancos(sucia);
		ArrayList<String> enblanco1 = LimpiarBlancosFin(semienblanco);
		ArrayList<String> enblanco = LimpiarBlancosIni(enblanco1);
*/
		
		ArrayList<String> limpia = new ArrayList<String>();
		
		
		ArrayList<String> enblanco = quitarRenglonesBlancos(sucia);
		enblanco = limpiarBlancosFin(enblanco);
		enblanco = limpiarBlancosIni(enblanco);
		enblanco = puntoPorGuion(enblanco);
		
		String reference = searchReference(enblanco);

		
		enblanco = eliminarTextoInutil(enblanco);
		for(int i=0; i<enblanco.size(); i++){
			if(enblanco.get(i).equals(reference)){
				i++;
			}
			else{
				limpia.add(enblanco.get(i));
			}
		}
		return limpia;
		
	}
	
	//Metodo que busca el numero de referencia del documento.
	public String searchReference(ArrayList<String> lista){
		String reference="Reference wasn't found";

		String patron ="^Contents.*";
		Pattern pat = Pattern.compile(patron);
		
		boolean control = false; //variable para parar el bucle cuando encuentre lo que busca.
		for(int i=0; (i<lista.size() && control==false); i++){
			if(pat.matcher(lista.get(i)).matches()){
				reference = lista.get(i-2).toString(); //Hay que a�adir un espacio por que la cabecera lo tiene.
				control=true;
			}
		}
				return reference;
		
	}
	
	private ArrayList<String> limpiarBlancosFin(ArrayList<String> sucia){
		ArrayList<String> limpia = new ArrayList<String>();
		String cadena ="";
		for(int i=0; i<sucia.size();i++){
			cadena = sucia.get(i);
			while(cadena.charAt(cadena.length()-1)==' '){
				cadena = cadena.substring(0, cadena.length()-1);
			}
			limpia.add(cadena);
		}
		return limpia;
	}
	
	private ArrayList<String> limpiarBlancosIni(ArrayList<String> sucia){
		ArrayList<String> limpia = new ArrayList<String>();
		String cadena ="";
		for(int i=0; i<sucia.size();i++){
			cadena = sucia.get(i);
			while(cadena.charAt(0)==' '){
				cadena = cadena.substring(1, cadena.length());
			}
			limpia.add(cadena);
		}
		return limpia;
	}
	
	public ArrayList<String> quitarRenglonesBlancos(ArrayList<String> Lista){
		ArrayList<String> Limpia = new ArrayList<String>();
		
		String patron = "^ *";
		Pattern pat = Pattern.compile(patron);
		
		String patron2 = " *$";
		Pattern pat2 = Pattern.compile(patron2);
		
		String patron3 = ".*[a-zA-Z0-9].*";
		Pattern pat3 = Pattern.compile(patron3);
		
		for(int i=0; i<Lista.size();i++){
			if(!pat.matcher(Lista.get(i)).matches() && 
					!pat2.matcher(Lista.get(i)).matches() &&
					pat3.matcher(Lista.get(i)).matches()){
				Limpia.add(Lista.get(i));
			}
		}
		return Limpia;
		
	}
	
	private ArrayList<String> puntoPorGuion(ArrayList<String> lista){
		String patron = "^([a-zA-Z0-9]|[-]).*";
		Pattern pat = Pattern.compile(patron);
	
		String cadenaaux="";
		
		ArrayList<String> limpia = new ArrayList<String>();
		
		for(int i=0; i<lista.size(); i++){
			if(!pat.matcher(lista.get(i)).matches()){
				cadenaaux = lista.get(i).substring(1,lista.get(i).length());
				limpia.add("-"+cadenaaux);
			}
			else{
				limpia.add(lista.get(i));
			}
		}
		
		return limpia;
	}
	
	private ArrayList<String> eliminarTextoInutil(ArrayList<String> lista){
		ArrayList<String> limpia = new ArrayList<String>();
		
		int cont = 0;
		int indice = 0;
		boolean condicion = false;
		
		String patron = "^1.*(Scope|SCOPE).*";
		Pattern pat = Pattern.compile(patron);
		
		for(int i=0; i<lista.size() && condicion==false;i++){
			if(pat.matcher(lista.get(i)).matches()){
				cont++;
				if(cont==2){
					condicion=true;
					indice = i;
				}
			}
		}
		if(condicion == true){
			for(int i=indice; i<lista.size();i++){
				limpia.add(lista.get(i));
			}
			
		}
		
		return limpia;
	}
	
	public void writeListToText(ArrayList<String> lista, String ruta){
		File f;
		f = new File(ruta);
		
		try{
			FileWriter w = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(w);
			PrintWriter wr = new PrintWriter(bw);  
			
			wr.write(lista.get(0));
			wr.append("\n");
			for(int i=1; i<lista.size(); i++){
				wr.append(lista.get(i));
				wr.append("\n");
			}
			wr.close();
			bw.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		

	}

}
