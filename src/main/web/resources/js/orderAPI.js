var prefix = '/api/order';

var RestDelete = function(id) {
    $.ajax({
        type: 'DELETE',
        url:  prefix + '/delete/' + id,
        async: true,
        success: function(response) {
            window.location = '/admin_order';
        },
        error: function(jqXHR) {
            alert(jqXHR.status + '\n' + jqXHR.responseText);
        }
    });
}

var RestGetAll = function() {
    $.ajax({
        type: 'GET',
        url:  prefix + '/all',
        dataType: 'json',
        async: true,
        success: function(result) {
            for(var i = 0; i < result.length; i++) {

                var id = result[i].id;

                $("#data").append(
                    "<tr>" +
                        "<td>" + id           +   "</td>" +
                        "<td>" + result[i].done  +   "</td>" +
                        "<td>" + result[i].cargoDTO.id  +   "</td>" +
                        "<td>" + result[i].cargoDTO.location  +   "</td>" +
                        "<td>" + result[i].destination       +   "</td>" +
                        "<td>" + result[i].truckDTO.plate       +   "</td>" +
                        "<td>" + result[i].workingDrivers       +   "</td>" +

                        "<td>" +
                            "<div class=\"btn-sectioned\">" +
                                "<button type=\"button\" class=\"btn btn-default btn-small\" title=\"Update\">" +
                                "<i class=\"icon icon-edit\" aria-hidden=\"true\"></i></button>" +

                                "<button type=\"button\" class=\"btn btn-default btn-small\" title=\"Delete\" " +
                                "onclick='RestDelete(" + id + ")'>" +
                                "<i class=\"icon icon-cancel\" aria-hidden=\"true\"></i></button>" +
                            "</div>" +
                        "</td>" +
                    "</tr>"
                );
            }
        },
        error: function(jqXHR) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}