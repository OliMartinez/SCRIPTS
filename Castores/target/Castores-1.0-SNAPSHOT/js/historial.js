$(document).ready(function () {
    $('#tipo').on('change', function () {
        console.log("Hola");
        var filterValue = $(this).val().toLowerCase();

        $('#movimientosTable tbody tr').filter(function () {
            if (filterValue === "") {
                $(this).show();
            } else {
                $(this).toggle($(this).find('td:eq(3)').text().toLowerCase().indexOf(filterValue) > -1);
            }
        });
    });
});
