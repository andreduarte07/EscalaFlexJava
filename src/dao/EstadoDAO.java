/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Estado;

/**
 *
 * @author 631820148
 */
public class EstadoDAO {
    
    public static void inserir(Estado estado){
        String query = "INSERT INTO estados (nome) "
                      +" VALUES ('"+estado.getNome()+"'); ";
        Conexao.executar(query);
    }
    
    
    public static void editar(Estado estado){
        String query = "UPDATE estados SET "
                      +" nome = '"+estado.getNome()+"' "
                      +" WHERE id = "+estado.getId();
        Conexao.executar(query);
    }
    
    public static void excluir(int idEstado){
        String query = "DELETE FROM estados" 
                      +"WHERE id = "+idEstado;
        Conexao.executar(query);
    }
  
    public static List<Estado> getEstados(){
        List<Estado> lista = new ArrayList<>();
        
        String query = "SELECT id, nome FROM estados ORDER BY nome";
        ResultSet rs = Conexao.consultar(query);
        if( rs != null ){
 
            try {
               
                while (rs.next()){
                    Estado est = new Estado();
                    est.setId(rs.getInt( 1 ));
                    est.setNome(rs.getString( 2 ));
                    lista.add(est);
                }
                
            } catch (Exception e) {
                
            }
        }
        
        return lista;
    }
    
    public static Estado getEstadoById(int idEstado){
        
        String query = "SELECT id, nome FROM estados "
                      +" WHERE id = "+idEstado;
        ResultSet rs = Conexao.consultar(query);
        if( rs != null ){
 
            try {
               
                rs.next();
                    Estado est = new Estado();
                    est.setId(rs.getInt( 1 ));
                    est.setNome(rs.getString( 2 ));
       
                return est;
            } catch (Exception e) {
                return null;
            }
        }else{
            return null;
        } 
    }
    
}
