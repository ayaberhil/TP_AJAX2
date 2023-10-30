$(document).ready(function() {
    $("#reference").change(function() {
        var selectedReference = $(this).val();

        if (selectedReference === "0") {
            $("#body").empty();
        } else {
            $.ajax({
                type: "POST",
                url: "MachineByRefController",
                data: { reference: selectedReference },
                dataType: "json",
                success: function(data) {

                    var tableRows = "";
                    $.each(data, function(index, machine) {
                        tableRows += "<tr>";
                        tableRows += "<td>" + machine.id + "</td>";
                        tableRows += "<td>" + machine.reference + "</td>";
                        tableRows += "<td>" + machine.prix + "</td>";
                        tableRows += "<td>" + machine.marque.libelle + "</td>";
                        tableRows += "<td>" + machine.dateAchat + "</td>";

                        tableRows += '<td><a class="bndelete" href="MachineController?op=delete&id=' + machine.id + '">Supprimer</a></td>';
                        tableRows += '<td><a class="bnupdate" href="">Modifier</a></td>';
                        tableRows += "</tr>";
                    });
                    $("#body").html(tableRows);
                }
            });
        }
    });
});
