var prefix = '/api/truck';

var RestDelete = function(plate) {
    $.ajax({
        type: 'DELETE',
        url:  prefix + '/delete/' + plate,
        async: true,
        success: function(response) {
            window.location = '/admin_truck';
        },
        error: function(jqXHR) {
            alert(jqXHR.status + '\n' + jqXHR.responseText);
        }
    });
}

var RestGet = function() {
    $.ajax({
        type: 'GET',
        url:  prefix + '/all',
        dataType: 'json',
        async: true,
        success: function(result) {
            for(var i = 0; i < result.length; i++) {

                var plate = result[i].plate;

                $("#data").append(
                    "<tr>" +
                        "<td>" + plate           +   "</td>" +
                        "<td>" + result[i].capacity  +   "</td>" +
                        "<td>" + result[i].status       +   "</td>" +
                        "<td>" + result[i].location       +   "</td>" +
                        "<td>" + result[i].driversCnt     +   "</td>" +

                        "<td>" +
                            "<div class=\"btn-sectioned\">" +
                                "<button type=\"button\" class=\"btn btn-default btn-small\" title=\"Add\">" +
                                "<i class=\"icon icon-attachment\" aria-hidden=\"true\"></i></button>" +

                                "<button type=\"button\" class=\"btn btn-default btn-small\" title=\"Update\">" +
                                "<i class=\"icon icon-settings\" aria-hidden=\"true\"></i></button>" +

                                "<button type=\"button\" class=\"btn btn-default btn-small\" title=\"Delete\" " +
                                "onclick='RestDelete(\"" + plate + "\")'>" +
                                "<i class=\"icon icon-context-menu\" aria-hidden=\"true\"></i></button>" +
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