
function confirmerSuppression(id) {
    var result = confirm("Voulez-vous vraiment supprimer cette machine ?");
    if (result) {
        window.location.href = "MachineController?op=delete&id=" + id;
    }
}

