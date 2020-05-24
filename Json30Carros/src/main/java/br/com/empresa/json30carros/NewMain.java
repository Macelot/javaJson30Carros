/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.json30carros;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



/**
 *
 * @author marce
 * https://stackoverflow.com/questions/10926353/how-to-read-json-file-into-java-with-simple-json-library
 * https://crunchify.com/how-to-read-json-object-from-file-in-java/
 * https://stackoverflow.com/questions/1946668/sorting-using-comparator-descending-order-user-defined-classes
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Carro car;
        car= new Carro();

        ArrayList<Carro> carros;
        carros= new ArrayList();
        
        JSONParser parser = new JSONParser();
       
        try {
            JSONArray carrosArquivo = (JSONArray) parser.parse( new InputStreamReader(new FileInputStream("30Carros.json"), "UTF-8"));
            for (Object o : carrosArquivo){
                JSONObject car_ = (JSONObject) o;
                String nome = car_.get("nome").toString();
                String marca = (String) car_.get("marca");
                Double preco = Double.parseDouble(car_.get("preco").toString().replace(",", ""));
                Integer portas = Integer.parseInt(car_.get("portas").toString());
                            
                car= new Carro();
                car.setNome(nome);
                car.setMarca(marca);
                car.setPreco(preco);
                car.setPortas(portas);
                carros.add(car);
            }
        } catch (Exception e) {
                e.printStackTrace();
        }     
        
        System.out.println("----Original");
        car.print(carros);
        
        ComparaPorPreco cpp ;
        cpp=new ComparaPorPreco();
        Collections.sort(carros,cpp);
        System.out.println("----Ordenado");
        car.print(carros);
        Collections.reverse(carros);
        System.out.println("----Reverso");
        car.print(carros);
    }
    
}
