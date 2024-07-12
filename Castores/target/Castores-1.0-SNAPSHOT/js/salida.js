$(document).ready(function () {
    $("#cantidad").on("change", function () {
        var idProducto = $("#idProducto").val();
        var cantidadSolicitada = parseInt($("#cantidad").val());
        var cantidadDisponible = 0;

        $("table tr").each(function () {
            var id = $(this).find("td:eq(0)").text();
            if (id === idProducto) {
                cantidadDisponible = parseInt($(this).find("td:eq(3)").text());
            }
        });

        if (cantidadSolicitada > cantidadDisponible) {
            $("#error-message").show();
        }
        else{
            $("#error-message").hide();
        }
    });
});
