<%-- 
    Document   : chartForm
    Created on : 30 oct. 2023, 12:34:57
    Author     : berhi
--%>

<%@page import="ma.school.service.MachineService"%>
<%@page import="ma.school.beans.Marque"%>
<%@page import="ma.school.service.MarqueService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.google.gson.Gson"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Graphe Machines par marque</title>
    </head>
    <body>

        <canvas id="machinesByBrandChart" width="400" height="200"></canvas>

        <%
            List<String> brandLabels = new ArrayList<String>();
            List<Integer> machineCounts = new ArrayList<Integer>();

            MachineService mms = new MachineService();
            MarqueService ms = new MarqueService();
            List<Marque> marques = ms.findAll();

            for (Marque marque : marques) {
                brandLabels.add(marque.getLibelle());

                int machineCount = mms.getMachineCountByMarque(marque);
                machineCounts.add(machineCount);
            }
        %>

        <script>
            var brandLabels = <%= new Gson().toJson(brandLabels)%>;
            var machineCounts = <%= new Gson().toJson(machineCounts)%>;

            var ctx = document.getElementById('machinesByBrandChart').getContext('2d');
            var chart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: brandLabels,
                    datasets: [{
                            label: 'Nombre de Machines par Marque',
                            data: machineCounts,
                            backgroundColor: 'rgba(75, 192, 192, 0.2)',
                            borderColor: 'rgba(75, 192, 192, 1)',
                            borderWidth: 1
                        }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        </script>

    </body>
</html>
