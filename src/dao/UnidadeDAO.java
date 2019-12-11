/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Cidade;
import model.Estado;
import model.Unidade;

/**
 *
 * @author Andre
 */
public class UnidadeDAO {
     public static void inserir(Unidade unidade){
         String query = "INSERT INTO unidades "
                      +" (nome, endereco, telefone, cep, email, responsavel, codCidade, codEstado )"
                      +" VALUES ('"+unidade.getNome()          + "' , "
                              +" '"+unidade.getEndereco()      + "' , "
                              +" '"+unidade.getTelefone()      + "' , "
                              +" '"+unidade.getCep()           + "' , "
                              +" '"+unidade.getEmail()         + "' , "
                              +" '"+unidade.getResponsavel()   + "' , "
                              +"  "+unidade.getCidade().getId()+ "  , "
                              +"  "+unidade.getEstado().getId()+ "  ) ";
        Conexao.executar(query);
    }

    public static void excluir(int idUnidade){
        String query = "DELETE FROM unidades " 
                      +" WHERE id = "+idUnidade;
        Conexao.executar(query);
    }
    
 public static void editar(Unidade unidade){
        String query = "UPDATE unidades SET "
                      +" nome =         '"+unidade.getNome()           +"' , "
                      +" endereco =     '"+unidade.getEndereco()       +"' , "
                      +" telefone =     '"+unidade.getTelefone()       +"' , "
                      +" cep =          '"+unidade.getCep()            +"' , "
                      +" email =        '"+unidade.getEmail()          +"' , "
                      +" responsavel =  '"+unidade.getResponsavel()    +"' , "
                      +" codCidade =     "+unidade.getCidade().getId() +"  , "
                      +" codEstado =     "+unidade.getEstado().getId() +"    "
                      +" WHERE id =      "+unidade.getId();
        Conexao.executar(query);
    }
    
    
    
    public static List<Unidade> getUnidades(){
        List<Unidade> lista = new ArrayList<>();
        
        String query = "SELECT u.id, u.nome, u.endereco, u.telefone, u.cep, u.email, u.responsavel, c.id, c.nome, e.id, e.nome "
                + " FROM unidades u "
                + " INNER JOIN cidades c "
                + " ON u.codCidade = c.id "
                + " INNER JOIN estados e "
                + " ON u.codEstado = e.id ";
        
        ResultSet rs = Conexao.consultar(query);
        if( rs != null ){
            try {
                while (rs.next()){
                    
                    Cidade cid = new Cidade();
                    cid.setId(rs.getInt( 8 ));
                    cid.setNome(rs.getString( 9 ));
                    
                    Estado est = new Estado();
                    est.setId(rs.getInt( 10 ));
                    est.setNome(rs.getString( 11 ));
                    
                    
                    Unidade uni = new Unidade();
                    uni.setId(rs.getInt( 1 ));
                    uni.setNome(rs.getString( 2 ));
                    uni.setEndereco(rs.getString( 3 ));
                    uni.setTelefone(rs.getString( 4 ));
                    uni.setCep(rs.getString( 5 ));
                    uni.setEmail(rs.getString( 6 ));
                    uni.setResponsavel(rs.getString( 7 ));
                    uni.setCidade(cid);
                    uni.setEstado(est);
                    lista.add(uni);
                }
                
            } catch (Exception e) {
                
            }
        }
        
        return lista;
    }

}
