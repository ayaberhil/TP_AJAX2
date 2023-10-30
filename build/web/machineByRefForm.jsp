

<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="ma.school.beans.Machine"%>
<%@page import="ma.school.service.MachineService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="script/confirmationSuppression.js" type="text/javascript"></script>
        <title>Machines par reference</title>
        <link href="style/css.css" rel="stylesheet" type="text/css"/>
        <link href="style/csslocal.css" rel="stylesheet" type="text/css"/>
        <script src="script/jquery-3.3.1.min.js" type="text/javascript"></script>
        <script src="script/machineByRef.js" type="text/javascript"></script>
    </head>
    <body>
        <%@include file="template/header.jsp" %>
        <%@include file="template/menu.jsp" %>
        <div class="content">
            <form method="post" action="MachineByRefController">
                <fieldset>
                    <legend>Rechercher par Référence</legend>
                    <table border="0">
                        <tr>
                            <td>Référence</td>
                            <td>
                                <select id="reference" name="reference">
                                    <option value="0">Choisir une référence</option>
                                    <%
                                        MachineService ms = new MachineService();
                                        Set<String> referenceSet = new HashSet<String>(); // Utilisez un ensemble pour éviter les doublons
                                        for (Machine m : ms.findAll()) {
                                            String reference = m.getReference();
                                            if (!referenceSet.contains(reference)) {
                                                referenceSet.add(reference);
                                    %>
                                    <option value="<%=reference%>"><%=reference%></option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                    </table>
                </fieldset>
            </form>

            <fieldset>
                <legend>Liste des Machines</legend>
                <table border="1" class="tab">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Reference</th>
                            <th>Prix</th>
                            <th>Marque</th>
                            <th>Date d'achat</th>                           
                            <th>Supprimer</th>
                            <th>Modifier</th>
                        </tr>
                    </thead>
                    <tbody id='body'>

                    </tbody>
                </table>
            </fieldset>
        </div>
    </body>
</html>
