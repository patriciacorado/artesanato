package br.unitins.artesanato.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.artesanato.model.Produto;
import br.unitins.artesanato.model.TipoProduto;
import br.unitins.artesanato.model.TipoUsuario;
import br.unitins.artesanato.model.Usuario;

public class ProdutoDAO extends DAO<Produto>{
	
	public boolean create(Produto produto) {
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO produto");
		sql.append("  id, descricao, preco, tipoproduto");
		sql.append("VALUES");
		sql.append("  (?,?,?,?) ");
		
		PreparedStatement stat = null;
		
		try {
			
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(0, produto.getId());
			stat.setString(1, produto.getDescricao());
			stat.setDouble(2, produto.getPreco());
			stat.setInt(3, produto.getTipoProduto().getId());
			
			stat.execute();
			conn.commit();
			
			System.out.println("InclusÃ£o realizada com sucesso.");
			retorno = true;
		}catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return retorno;
	}
	
	public boolean update(Produto produto) {
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE produto");
		sql.append(" 	descricao=?, preco=?, tipoproduto=?");
		sql.append("WHERE");
		sql.append("    id=? ");
		
		PreparedStatement stat = null;
		
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(0, produto.getDescricao());
			stat.setDouble(1, produto.getPreco());
			stat.setInt(2, produto.getTipoProduto().getId());
			
			stat.execute();
			conn.commit();
			System.out.println("Alteração realizada com sucesso");
			retorno = true;
		}catch(SQLException e) {
			e.printStackTrace();
			rollback(conn);
		}finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return retorno;
	}
	
	public boolean delete(int id) {
		boolean retorno = false; 
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM produto");
		sql.append("WHERE");
		sql.append("    id = ?");
		
		PreparedStatement stat = null;
		
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			stat.execute();
			conn.commit();
			System.out.println("Remoção realizada com sucesso");
			retorno = true;	
		}catch(SQLException e) {
			e.printStackTrace();
			rollback(conn);
		}finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return retorno;
	}
	
	public List<Produto> findAll(){
		List<Produto> listaproduto	= new ArrayList<Produto>();
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT");
		sql.append("   id, descricao, preco, tipoproduto");
		sql.append("FROM");
		sql.append("    produto");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			ResultSet rs = stat.executeQuery();
			
			Produto produto = null;
			while(rs.next()) {
				produto = new Produto();
				produto.setId(rs.getInt(""));
				produto.setDescricao(rs.getString("descricao"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setTipoProduto(TipoProduto.valueOf("tipoproduto"));
				listaproduto.add(produto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			rollback(conn);
		}finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return listaproduto;
	}

	@Override
	public Produto findById(int id) {
		Produto produto = new Produto();
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	descricao, preco, tipoproduto ");
		sql.append("FROM ");
		sql.append("	produto ");
		sql.append("WHERE ");
		sql.append("	id = ? ");

		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setTipoProduto(TipoProduto.valueOf(rs.getString("tipoproduto")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return produto;
	}
	
	public Produto findByTipo(int tipoproduto) {
		Produto produto = new Produto();
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, descricao, preco ");
		sql.append("FROM ");
		sql.append("	produto ");
		sql.append("WHERE ");
		sql.append("	tipoproduto = ? ");

		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, tipoproduto);
			
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setPreco(rs.getDouble("preco"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return produto;
	}
}
