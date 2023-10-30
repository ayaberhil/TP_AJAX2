

function confirmerSuppression(id) {
    var result = confirm("Voulez-vous vraiment supprimer cet étudiant ?");
    if (result) {
        window.location.href = "EtudiantController?op=delete&id=" + id;
    }
}

