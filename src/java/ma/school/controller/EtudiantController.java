
package ma.school.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ma.school.beans.Etudiant;
import ma.school.service.EtudiantService;

@WebServlet(name = "EtudiantController", urlPatterns = {"/EtudiantController"})
public class EtudiantController extends HttpServlet {

    String op = null;

    @Override
    public void init() throws ServletException {
        super.init();
        op = null;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        op = request.getParameter("op");
        EtudiantService es = new EtudiantService();

        if (op != null) {
            if (op.equals("Envoyer")) {
                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                String ville = request.getParameter("ville");
                String date = request.getParameter("dateNaissance");
                Date dateNaissance = null;

                try {
                    dateNaissance = new Date(date.replace("-", "/"));
                } catch (IllegalArgumentException e) {

                    e.printStackTrace();
                }

                String sexe = request.getParameter("sexe");

                es.create(new Etudiant(nom, prenom, ville, dateNaissance, sexe));

                response.sendRedirect("etudiantForm.jsp");
            } else if (op.equals("delete")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Etudiant etudiant = es.findById(id);


                if (etudiant != null) {
                    es.delete(etudiant);
                } 

                response.sendRedirect("etudiantForm.jsp");
            } else if (op.equals("update")){
            int id = Integer.parseInt(request.getParameter("id"));
            Etudiant e = es.findById(id);

            if (e != null) {
                // Remplir les champs du formulaire de modification avec les données de l'étudiant
                request.setAttribute("nom", e.getNom());
                request.setAttribute("prenom", e.getPrenom());
                request.setAttribute("ville", e.getVille());
                request.setAttribute("dateNaissance", e.getDateNaissance());
                request.setAttribute("sexe", e.getSexe());
                request.setAttribute("id", id);
            }
            response.sendRedirect("etudiantForm.jsp");
        }
     }
    }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
