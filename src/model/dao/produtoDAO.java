 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.connectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Produto;


/**
 *
 * @author Lucas
 */
public class produtoDAO {
    
    public void create(Produto p){
        
        Connection con = connectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO tbproduto (descricao,qtd,preco) VALUES (?,?,?) ");
            stmt.setString(1, p.getDescricao());
            stmt.setInt(2,p.getQtd());
            stmt.setDouble(3,p.getPreco());
            
            stmt.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(produtoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            connectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    public List<Produto> read(){
          
        Connection con = connectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Produto> produtos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM tbproduto");
            rs = stmt.executeQuery();
            
            while (rs.next()){
                
                Produto p = new Produto();
                
                p.setId(rs.getInt("id_produto"));
                p.setDescricao(rs.getString("descricao"));
                p.setQtd(rs.getInt("qtd"));
                p.setPreco(rs.getDouble("preco"));
                
                produtos.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(produtoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            connectionFactory.closeConnection(con, stmt, rs);
        }
       
        return produtos;      
        
    }
}
