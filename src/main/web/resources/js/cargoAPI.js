var prefix = '/api/cargo';

var RestDelete = function(id) {
    $.ajax({
        type: 'DELETE',
        url:  prefix + '/delete/' + id,
        async: true,
        success: function() {
            window.location = '/admin_cargo';
        },
        error: function(jqXHR) {
            alert(jqXHR.status + '\n' + jqXHR.responseText);
        }
    });
};

var ToUpdatePage = function (id) {
    location.href='/admin_cargo_add?id=' + id;
};

var RestGetAll = function() {
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
                        "<td>" + result[i].location.country + "</td>" +

                        "<td>" +
                            "<div class=\"btn-sectioned\">" +
                                "<button type=\"button\" class=\"btn btn-default btn-small\" title=\"Update\" " +
                                "onclick='ToUpdatePage(" + id + ")'>" +
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
};

var RestPost = function() {
    var JSONObject= {
        'description': $("#description").val(),
        'locCoords': $("#location").val(),
        'destCoords': $("#destination").val(),
        'status' : $("#status").val(),
        'weight' : $("#weight").val()
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
            window.location = '/admin_cargo_add';
        },
        error: function(e) {
            // TODO: highlight error fields with red
            alert(e.responseText);
        }
    });
};

var RestPut = function(id) {
    var JSONObject= {
        'id' : id,
        'description': $("#description").val(),
        'locCoords': $("#location").val(),
        'destCoords': $("#destination").val(),
        'status' : $("#status").val(),
        'weight' : $("#weight").val()
    };

    $.ajax({
        type: 'PUT',
        url:  prefix + '/update',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(JSONObject),
        dataType: 'json',
        cache: false,
        async: true,
        success: function(result) {
            alert(result.msg);
            window.location = '/admin_cargo';
        },
        error: function(e) {
            alert(e.responseText);
        }
    });
};

var IfForUpdate = function() {
    var urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has('id')) {
        $.getJSON(prefix + '/' + urlParams.get('id'), function (data) {
            $("#entity_info").text(data.description + ": " +
                                            data.location.country + "\u2192" +
                                            data.destination.country + " " +
                                            data.status + " " +
                                            data.weight + "kg");
            $("#description").val(data.description);
            $("#weight").val(data.weight);

            $("#save_button").on('click', function () {
                RestPut(data.id);
            });
        });
    } else {
        $("#save_button").on('click', function () {
            RestPost();
        });
    }
};