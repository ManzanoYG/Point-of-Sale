package util;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import model.GestionProduits;
import model.GestionTable;

public class Persistance {
	
	public static void ecrireTxt(String txt, String nomF)
	{
		try(FileWriter f = new FileWriter(new File(nomF)))
		{
			
			f.write(txt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void ecrireTxt(String txt, File file)
	{
		try(FileWriter f = new FileWriter(file))
		{
			
			f.write(txt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static <T> T lireJson(String nomF, Class<T> classOfT)
	{
		Gson g = new Gson();
		try {
			JsonObject jsonObj = JsonParser.parseReader(new FileReader(nomF)).getAsJsonObject(); 
			T c = g.fromJson(jsonObj, classOfT);
			return c;
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T> T lireJson(File file, Class<T> classOfT)
	{
		Gson g = new Gson();
		try {
			JsonObject jsonObj = JsonParser.parseReader(new FileReader(file)).getAsJsonObject(); 
			T c = g.fromJson(jsonObj, new TypeToken<GestionProduits>() {}.getType());
			return c;
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T> T lireJsonTable(File file, Class<T> classOfT)
	{
		Gson g = new Gson();
		try {
			JsonObject jsonObj = JsonParser.parseReader(new FileReader(file)).getAsJsonObject(); 
			T c = g.fromJson(jsonObj, new TypeToken<GestionTable>() {}.getType());
			return c;
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T> T lireJsonTicket(File file, Class<T> classOfT)
	{
		Gson g = new Gson();
		try {
			JsonObject jsonObj = JsonParser.parseReader(new FileReader(file)).getAsJsonObject(); 
			T c = g.fromJson(jsonObj, new TypeToken<GestionTable>() {}.getType());
			return c;
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void ecrireJson(String fileName, Object m) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File(fileName);
        try(FileWriter writer = new FileWriter(file)){
            gson.toJson(m,writer);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
    }
	
	public static void ecrireJson(File file, Object m) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ;
        try(FileWriter writer = new FileWriter(file)){
            gson.toJson(m,writer);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	public static String lectureFichierTexte(File filename) {
		StringBuilder builder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
            	builder.append(ligne);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return builder.toString();
	}
	
	public static String read(File f) 
	{
		String result = "" ;
		
		try(Scanner scanner = new Scanner(new FileReader(f)))
		{
			while(scanner.hasNext()) 
			{
				result += scanner.nextLine();
			}
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		return result ;
	}
}