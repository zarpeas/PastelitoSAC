package com.company.JSON;
//Library

import com.company.Clases.Cliente;
import com.company.Clases.Pedido;
import com.company.Clases.Producto;
import com.google.gson.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadJSON {

    String jsonPath = "./src/com/company/JSON/data.JSON";

    public ReadJSON() {
    }

    public ArrayList<Producto> getProductos(ArrayList<Producto> productos) {
        File input = new File(jsonPath);
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            //Todos los productos
            JsonArray jsonArrayProductos = fileObject.get("productos").getAsJsonArray();

            if(productos.isEmpty()){
                for (JsonElement productoElement : jsonArrayProductos){
                    //Get the JsonObject
                    JsonObject productoJsonElement = productoElement.getAsJsonObject();

                    //Extract data
                    Gson gson = new Gson();

                    Producto producto = gson.fromJson(productoJsonElement, Producto.class);

                    productos.add(producto);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return productos;
    }

    public ArrayList<Cliente> getClientes(ArrayList<Cliente> clientes) {
        File input = new File(jsonPath);

        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            //Todos los clientes
            JsonArray jsonArrayClientes = fileObject.get("clientes").getAsJsonArray();

            if(clientes.isEmpty()){
                for (JsonElement clienteElement : jsonArrayClientes){
                    //Get the JsonObject
                    JsonObject clienteJsonElement = clienteElement.getAsJsonObject();

                    //Extract data
                    Gson gson = new Gson();

                    Cliente cliente = gson.fromJson(clienteJsonElement, Cliente.class);

                    clientes.add(cliente);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public ArrayList<Pedido> getPedidos(ArrayList<Pedido> pedidos) {
        File input = new File(jsonPath);

        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            //Todos los pedidos
            JsonArray jsonArrayPedidos = fileObject.get("pedidos").getAsJsonArray();
            if(pedidos.isEmpty()){
                for (JsonElement pedidoElement : jsonArrayPedidos){
                    //Get the JsonObject
                    JsonObject pedidoJsonElement = pedidoElement.getAsJsonObject();

                    //Extract data
                    Gson gson = new Gson();

                    Pedido pedido = gson.fromJson(pedidoJsonElement, Pedido.class);

                    pedidos.add(pedido);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return pedidos;
    }

    public void cambiarPosicionElementos(ArrayList<Pedido> pedidos,
                                                      int posicionInicial,
                                                      int posicionMayor) {
        Pedido temporal = pedidos.get(posicionInicial);
        pedidos.set(posicionInicial, pedidos.get(posicionMayor));
        pedidos.set(posicionMayor, temporal);
    };

    public ArrayList<Pedido> ordenarPedidos(ArrayList<Pedido> pedidos){
        int posicionMayor = 0;

        for(int i = 0; i< pedidos.size(); i++){
            posicionMayor = i;

            for (int j=i+1; j < pedidos.size(); j++){
                if(pedidos.get(i).getTotal() < pedidos.get(j).getTotal()) {
                    posicionMayor = j;
                }
            }
            cambiarPosicionElementos(pedidos, i, posicionMayor);
        }
        return pedidos;
    }

}

