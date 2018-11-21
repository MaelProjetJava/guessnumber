/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guessnumber;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pedago
 */
@WebServlet(name = "GuessNumberServlet", urlPatterns = {"/GuessNumberServlet"})
public class GuessNumberServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        getServletContext().setAttribute("player_count", 0);
        getServletContext().setAttribute("bestplayer", new Player());
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int player_count = (Integer) getServletContext().getAttribute("player_count");
        request.setAttribute("player_count", player_count);
        
        Player best_player = (Player) getServletContext().getAttribute("bestplayer");
        request.setAttribute("bestplayer", best_player);
        
        HttpSession session = request.getSession(true);
        String action = request.getParameter("action");
        
        if (action != null) {
            if (action.equals("Connexion")) {
                Player player = new Player(request.getParameter("playerName"));
                session.setAttribute("player", player);
                getServletContext().setAttribute("player_count", player_count + 1);
                response.sendRedirect(request.getContextPath() + request.getServletPath());
                return;
            } else if (action.equals("Deconnexion")) {
                session.setAttribute("player", null);
                getServletContext().setAttribute("player_count", player_count - 1);
                response.sendRedirect(request.getContextPath() + request.getServletPath());
                return;
            }
            
            Player player = (Player) session.getAttribute("player");
            
            if (action.equals("Deviner")) {
                int guess = Integer.valueOf(request.getParameter("guess"));
                player.guess(guess);
                
                if (guess == player.getNumberToGuess()) {
                    
                    if (best_player.getGuessCount() == 0 || player.getGuessCount() < best_player.getGuessCount())
                        getServletContext().setAttribute("bestplayer", player);
                    
                    request.getRequestDispatcher("views/won.jsp").forward(request, response);
                    return;
                }
            } else if (action.equals("Rejouer")) {
                player = new Player(player.getUsername());
                session.setAttribute("player", player);
            }
        } else if (session.getAttribute("player") == null) {
            request.getRequestDispatcher("views/entry.jsp").forward(request, response);
            return;
        }
        
        request.getRequestDispatcher("views/game.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
