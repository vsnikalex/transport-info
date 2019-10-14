var prefix = '/api/cargo';

var RestDelete = function(id) {
    $.ajax({
        type: 'DELETE',
        url:  prefix + '/delete/' + id,
        async: true,
        success: function(response) {
            window.location = '/admin_cargo';
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
            for (var i = 0; i < result.length; i++) {

                var id = result[i].id;

                $("#data").append(
                    "<tr>" +
                        "<td>" + id + "</td>" +
                        "<td>" + result[i].description + "</td>" +
                        "<td>" + result[i].weight + "</td>" +
                        "<td>" + result[i].status + "</td>" +
                        "<td>" + result[i].location + "</td>" +

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
            alert(jqXHR.status + '\n' + jqXHR.responseText);
        }
    });
}

var RestPost = function() {
    var JSONObject= {
        'id': null,
        'description': 'IKEA Sofa',
        'location': 'Belgrad',
        'weight' : '100',
        'status' : 'PREPARED'
    };

    $.ajax({
        type: 'POST',
        url:  prefix + '/add',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(JSONObject),
        dataType: 'json',
        async: true,
        success: function(result) {
            alert(result.body);
        },
        error: function(jqXHR) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}
