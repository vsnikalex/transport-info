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

var RestGetAll = function() {
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
                        "<td>" + result[i].location.country       +   "</td>" +
                        "<td>" + result[i].driversCnt     +   "</td>" +

                        "<td>" +
                            "<div class=\"btn-sectioned\">" +
                                "<button type=\"button\" class=\"btn btn-default btn-small\" title=\"Update\">" +
                                "<i class=\"icon icon-edit\" aria-hidden=\"true\"></i></button>" +

                                "<button type=\"button\" class=\"btn btn-default btn-small\" title=\"Delete\" " +
                                "onclick='RestDelete(\"" + plate + "\")'>" +
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

var RestPost = function() {
    var JSONObject= {
        'plate': $("#plate").val(),
        'capacity': $("#capacity").val(),
        'status': $("#status").val(),
        'coords': $("#location").val()
    };

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url:  prefix + '/add',
        data: JSON.stringify(JSONObject),
        dataType: 'json',
        cache: false,
        async: true,
        success: function(result) {
            alert(result.msg);
            window.location = '/admin_truck_add';
        },
        error: function(e) {
            // TODO: highlight error fields with red
            alert(e.responseText);
        }
    });
}