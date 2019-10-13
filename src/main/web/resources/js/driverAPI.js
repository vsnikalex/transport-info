var prefix = '/api/driver';

var RestDelete = function(id) {
    $.ajax({
        type: 'DELETE',
        url:  prefix + '/delete/' + id,
        async: true,
        success: function(response) {
            window.location = '/admin_driver';
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

                var id = result[i].id;

                $("#data").append(
                "<tr>" +
                    "<td>" + id           +   "</td>" +
                    "<td>" + result[i].firstName  +   "</td>" +
                    "<td>" + result[i].lastName       +   "</td>" +
                    "<td>" + result[i].workedThisMonth       +   "</td>" +
                    "<td>" + result[i].action     +   "</td>" +
                    "<td>" + result[i].location     +   "</td>" +
                    "<td>" + result[i].truckDTO.plate     +   "</td>" +

                    "<td>" +
                        "<div class=\"btn-sectioned\">" +
                            "<button type=\"button\" class=\"btn btn-default btn-small\" title=\"Add\">" +
                            "<i class=\"icon icon-attachment\" aria-hidden=\"true\"></i></button>" +

                            "<button type=\"button\" class=\"btn btn-default btn-small\" title=\"Update\">" +
                            "<i class=\"icon icon-settings\" aria-hidden=\"true\"></i></button>" +

                            "<button type=\"button\" class=\"btn btn-default btn-small\" title=\"Delete\" " +
                            "onclick='RestDelete(" + id + ")'>" +
                            "<i class=\"icon icon-context-menu\" aria-hidden=\"true\"></i></button>" +
                        "</div>" +
                    "</td>" +
                "</tr>"
                );
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}