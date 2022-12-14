package controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class ControladorDoCliente
 */
public class ControladorDoCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	static String url = "jdbc:mysql://localhost:3306/oliveiratrade";
	static String usuario = "root";
	static String senha = "andrey12";
	static Connection conexao;
	HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorDoCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
	    	conexao = DriverManager.getConnection(url, usuario, senha);
			conexao.setAutoCommit(false);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipoFormulario = request.getParameter("tipoformulario");
		if(tipoFormulario.equals("cadastro")) {
			System.out.println(" Acessando formulário de cadastro...");
		    Double idCliente = Math.random();
			String email = request.getParameter("email");
			String nome = request.getParameter("nome");
			String cpf = request.getParameter("cpf");
			String telefone = request.getParameter("telefone");
			String senha = request.getParameter("senha");
			String endereco = request.getParameter("endereco");
			
			
			String insertSQL = "INSERT INTO clientes VALUES ("+idCliente+", '"+email+"', '" +nome+"','"+cpf+"', '"+telefone+"','"+senha+"','"+endereco+"')";
			System.out.println("Ação SQL:" + insertSQL);
			Statement st;
			try {
				session = request.getSession();
				st = conexao.createStatement();
				st.execute(insertSQL);
				conexao.commit();
				session.setAttribute("mensagem", "Cadastro realizado com sucesso");
				response.sendRedirect("login.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Insert falhou; checar banco de dados");
				e.printStackTrace();
			}
			
		} 
		if(tipoFormulario.equals("login")) {
			System.out.println("Entrando no Formulário de Login...");
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");	
			String selectSQL = "SELECT * FROM LOGIN WHERE USUARIO ="+"'" + email + "'" + "AND SENHA="+"'"+senha+"'";
			Statement st;
			try {
				st = conexao.createStatement();
				ResultSet rs = st.executeQuery(selectSQL);
				if(rs.next()) {
					System.out.println("Cliente autenticado...");
					session = request.getSession();
					session.setAttribute("email",email);
					session.setAttribute("senha", senha);
					response.sendRedirect("autenticado.jsp");
				} else {
					System.out.println("Cliente não autenticado!");
				}
			} catch (SQLException e) {
				System.out.println("Select falhou; checar banco de dados");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
				
		else {
			System.out.println("Saindo do controlador...");
		}
	}

}
