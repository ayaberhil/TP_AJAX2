

function confirmerSuppression(id) {
    var result = confirm("Voulez-vous vraiment supprimer cette marque ?");
    if (result) {
        window.location.href = "MarqueController?op=delete&id=" + id;
    }
}