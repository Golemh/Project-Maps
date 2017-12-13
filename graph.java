/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication88;

import java.util.LinkedList;

/**
 *
 * @author Abdullah
 */
public class Graph {
    LinkedList<Edge>[] list;
    LinkedList<Vertex> vList;
    
    Graph(LinkedList<Vertex> list){
        vList = list;
        this.list = new LinkedList[list.size()];
        for (int i = 0; i < this.list.length; i++) {
            this.list[i] = new LinkedList<>();
        }
    }
    
    public void insertEdge(String[] arr){
        int h = Integer.parseInt(arr[0]);
        Vertex v1 = findVert(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            Vertex v2 = findVert(arr[i]);
            list[h-1].add(new Edge(v1, v2));
        }
    }
    
    public Vertex findVert(String str){
        for (int i = 0; i < vList.size(); i++) {
            if (str.equals(vList.get(i).label)) {
                return vList.get(i);
            }
        }
        return null;
    }
    
    public String findLatLong(String a) {
        Vertex c = this.findVert(a);
        String str = c.lat + "," + c.lon;
        return str;
    }
    
    public void display(){
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[i].size(); j++) {
                System.out.println(list[i].get(j).weight);
            }
        }
    }
}
public class Vertex {
    String label;
    double lat;
    double lon;
    
    Vertex(String str){
        String[] s1 = str.split(" ");
        label = s1[0];
        String[] s2 = s1[1].split(",");
        lat = Double.parseDouble(s2[0]);
        lon = Double.parseDouble(s2[1]);
    }
}
public class Edge {

    double weight;
    Vertex src;
    Vertex dst;

    Edge(Vertex v1, Vertex v2) {
        weight = Math.sqrt(Math.pow(v2.lat - v1.lat, 2)
                + Math.pow(v2.lon - v1.lon, 2));
    }
}
