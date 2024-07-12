$(document).ready(function () {

    $("#cantidad").on("change", function () {
        let cantidad = $(this).val();
        if (cantidad <= 0) {
            $("#error-message").show();
        } else {
            $("#error-message").hide();
        }
    })

/*    $(".nuevoestatus").on("click", function () {
        var boton = $(this);
        var idProducto = boton.attr("idprod");
        var nuevoEstatus = boton.text() === "Alta" ? 1 : 0;
        var datos = new FormData();
        datos.append("action", "cambiarEstatus");
        datos.append("idProducto", idProducto);
        datos.append("nuevoEstatus", nuevoEstatus);

        /*$.ajax({
            url: 'Inventario',
            type: 'POST',
            data: datos,
            processData: false,
            contentType: false,
            success: */
        /*$.post('Inventario', {
            action : "cambiarEstatus",
            idProducto: idProducto,
            nuevoEstatus: nuevoEstatus
	},function (response) {
                if (response.success) {
                    // Se actualiza el botón y el estatus en la tabla
                    boton.text(nuevoEstatus === 1 ? "Baja" : "Alta");
                    boton.closest("tr").find(".estatus").text(nuevoEstatus === 1 ? "Activo" : "Inactivo");
                } else {
                    console.error('Error:', response.error);
                }
            }
        /*}*//*);
    });*/
})